package com.simple.training.domain.support;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.simple.training.domain.config.RepositoryHibernateTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryHibernateTestConfiguration.class })
public abstract class AbstractRepositoryTestSupport <Entity, Key extends Serializable, RepositoryType extends CrudRepository <Entity, Key>>{
    protected abstract RepositoryType getRepository();
    protected abstract Key getKey(Entity entity);

    private EmbeddedDatabase db;

    @Autowired
    private TransactionTemplate txTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected <Type> Type doInTransaction(Supplier <Type> lambda) {
        return txTemplate.execute(new TransactionCallback<Type>() {
            @Override
            public Type doInTransaction(TransactionStatus status) {
                return lambda.get();
            }
        });
    }

    protected void checkQuery(String query, Consumer <ResultSet> checker, Object ... params) {
        doInTransaction(() -> {
            return jdbcTemplate.query(query, params, new ResultSetExtractor<Object>() {
                @Override
                public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                    checker.accept(rs);
                    return null;
                }
            });
        });
    }

    //second attempt to load sql file
    @Before
    public void setUp() throws Exception {
        db = new EmbeddedDatabaseBuilder().build();
    }

    @After
    public void tearDown() throws Exception {
        clearDatabase();
        db.shutdown();
    }

    public void clearDatabase() throws SQLException {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");
        try {
            Iterator var1 = getTableNames().iterator();
            while(var1.hasNext()) {
                String tableName = (String)var1.next();
                jdbcTemplate.execute("TRUNCATE TABLE " + tableName);
            }
        } finally {
            jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");
        }

    }

    private List<String> getTableNames() throws SQLException {
        return (List)jdbcTemplate.execute(new ConnectionCallback() {
            @Override
            public List<String> doInConnection(Connection conn) throws SQLException, DataAccessException {
                DatabaseMetaData metaData = conn.getMetaData();
                ResultSet tables = metaData.getTables((String)null, (String)null, "%", new String[]{"TABLE"});
                ArrayList tableNames = new ArrayList();

                try {
                    while(tables.next()) {
                        tableNames.add(tables.getString("TABLE_NAME"));
                    }
                } finally {
                    tables.close();
                }

                return tableNames;
            }
        });
    }
}
