package com.simple.training.domain.support;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.data.repository.CrudRepository;

public abstract class AbstractRootEntityRepositoryTestSupport <Entity, Key extends Serializable, RepositoryType extends CrudRepository <Entity, Key>> extends AbstractRepositoryTestSupport <Entity, Key, RepositoryType> {
	protected abstract Entity createNewEntity();
	protected abstract Entity updateEntity();
	protected abstract String loadQuery();
	protected abstract void checkNewEntity(ResultSet rs) throws SQLException;
	protected abstract Key getExistentEntityKey();
	protected abstract void checkRead(Entity entity);
	protected abstract void updateEntity(Entity entity);
	protected abstract void checkUpdatedEntity(ResultSet rs) throws SQLException;
	
	@Test
	public void testSave() throws SQLException{
		Key chave = doInTransaction(() -> {
   		  Entity entity = createNewEntity();
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
	public void testRead() {
		checkRead(doInTransaction(() -> getRepository().findOne(getExistentEntityKey())));
	}
	
	@Test
	public void testRemove() {
		doInTransaction(() -> { 
			getRepository().delete(getExistentEntityKey()); 
			return null;
		});
		checkQuery(loadQuery(), rs -> {
			try {
				assertFalse(rs.next());
			} catch (Exception e) {
				e.printStackTrace();
				fail("Erro ao salvar");
			}
		}, getExistentEntityKey());
	}
	
	@Test
	public void testUpdate() {
		Entity entity = doInTransaction(() -> getRepository().findOne(getExistentEntityKey()));
		updateEntity(entity);
		doInTransaction(() -> getRepository().save(entity));
		checkQuery(loadQuery(), rs -> {
			try {
				assertTrue(rs.next());
				checkUpdatedEntity(rs);
				assertFalse(rs.next());
			} catch (Exception e) {
				e.printStackTrace();
				fail("Erro ao salvar");
			}
		}, getExistentEntityKey());
	}
}
