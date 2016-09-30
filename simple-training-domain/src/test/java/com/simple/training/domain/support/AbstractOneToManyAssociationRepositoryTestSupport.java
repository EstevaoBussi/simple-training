package com.simple.training.domain.support;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.springframework.data.repository.CrudRepository;

public abstract class AbstractOneToManyAssociationRepositoryTestSupport<Entity, Child, Key extends Serializable, RepositoryType extends CrudRepository<Entity, Key>>
        extends AbstractRepositoryTestSupport<Entity, Key, RepositoryType> {

    protected abstract String loadQuery();

    protected abstract void addNewChild(Entity entity);

    protected abstract Entity createNew();

    protected abstract void checkNewEntity(ResultSet rs) throws SQLException;

    protected abstract Key getEntityWithoutChildrenKey();

    protected abstract Key getEntityWithChildrenKey();

    protected abstract void checkWithoutChildren(Entity entity);

    protected abstract void checkWithChildren(Entity entity)
            throws SQLException;

    protected abstract void checkRemovalOfDictionaries();

    protected abstract void removeChild(Entity entity);

    protected abstract void checkUpdatedEntity(ResultSet rs)
            throws SQLException;

    protected abstract void updateChild(Entity entity);

    @Test
    public void insertWithNewRoot() {
        Key chave = doInTransaction(() -> {
            Entity entity = createNew();
            addNewChild(entity);
            return getKey(getRepository().save(entity));
        });
        checkQuery(loadQuery(), rs -> {
            try {
                assertTrue(rs.next());
                checkNewEntity(rs);
                assertFalse(rs.next());
            } catch (Exception e) {
                e.printStackTrace();
                fail("Erro ao salvar");
            }
        }, chave);
    }

    @Test
    public void insertWithExistentRoot() {
        Entity entity = doInTransaction(() -> getRepository().findOne(
                getEntityWithoutChildrenKey()));
        doInTransaction(() -> {
            addNewChild(entity);
            return getKey(getRepository().save(entity));
        });
        checkQuery(loadQuery(), rs -> {
            try {
                assertTrue(rs.next());
                checkNewEntity(rs);
                assertFalse(rs.next());
            } catch (Exception e) {
                e.printStackTrace();
                fail("Erro ao salvar");
            }
        }, getEntityWithoutChildrenKey());
    }

    @Test
    public void testGetWithoutChildren() {
        checkWithoutChildren(doInTransaction(() -> getRepository().findOne(
                getEntityWithoutChildrenKey())));
    }

    @Test
    public void testGetWithChildren() throws SQLException {
        checkWithChildren(doInTransaction(() -> getRepository().findOne(
                getEntityWithChildrenKey())));
    }

    @Test
    public void testRemoveChild() {
        Entity entity = doInTransaction(() -> getRepository().findOne(
                getEntityWithChildrenKey()));
        removeChild(entity);
        doInTransaction(() -> {
            return getKey(getRepository().save(entity));
        });
        checkQuery(loadQuery(), rs -> {
            try {
                assertFalse(rs.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, getEntityWithChildrenKey());
        doInTransaction(() -> {

            checkRemovalOfDictionaries();
            return null;
        });
    }

    @Test
    public void testRemoveRoot() {
        doInTransaction(() -> {
            getRepository().delete(getEntityWithChildrenKey());
            return null;
        });
        checkQuery(loadQuery(), rs -> {
            try {
                assertFalse(rs.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, getEntityWithChildrenKey());
        doInTransaction(() -> {
            checkRemovalOfDictionaries();
            return null;
        });
    }

    @Test
    public void testUpdate() {
        Entity entity = doInTransaction(() -> getRepository().findOne(
                getEntityWithChildrenKey()));
        updateChild(entity);
        doInTransaction(() -> {
            getRepository().save(entity);
            return null;
        });
        checkQuery(loadQuery(), rs -> {
            try {
                assertTrue(rs.next());
                checkUpdatedEntity(rs);
                assertFalse(rs.next());
            } catch (Exception e) {
                e.printStackTrace();
                fail("Erro ao salvar");
            }
        }, getEntityWithChildrenKey());

    }

}
