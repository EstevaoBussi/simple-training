package com.simple.training.domain.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryHibernateTestConfiguration.class })
public class BDTest {

    private EmbeddedDatabase db;
    
    //second attempt to load sql file
    @Before
    public void setUp() throws Exception {
        db = new EmbeddedDatabaseBuilder().build();
    }

    @After
    public void tearDown() throws Exception {
        db.shutdown();
    }
    
    @Test
    public void testXPTO() {
    }

}
