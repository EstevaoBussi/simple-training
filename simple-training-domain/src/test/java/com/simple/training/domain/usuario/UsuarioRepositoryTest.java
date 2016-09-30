package com.simple.training.domain.usuario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.simple.training.domain.support.AbstractRootEntityRepositoryTestSupport;

@Sql("usuario-repository-setup.sql")
public class UsuarioRepositoryTest extends AbstractRootEntityRepositoryTestSupport <Usuario, String, UsuarioRepository> {
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	protected Usuario createNewEntity() {
		Usuario entity = new Usuario();
		entity.setUsername("USERNAME");
		entity.setDataNascimento(LocalDate.of(2001, 1, 1));
		entity.setEmail("EMAIL");
		entity.setNome("NOME");
		return entity;
	}

	@Override
	protected String getKey(Usuario entity) {
		return entity.getUsername();
	}

	@Override
	protected Usuario updateEntity() {
		Usuario entity = new Usuario();
		entity.setUsername("USERNAME2");
		entity.setDataNascimento(LocalDate.of(2001, 1, 2));
		entity.setEmail("EMAIL2");
		entity.setNome("NOME2");
		return entity;
	}

	@Override
	protected String loadQuery() {
		return "SELECT * FROM USUARIO WHERE USERNAME = ?";
	}

	@Override
	protected void checkNewEntity(ResultSet rs) throws SQLException {
		assertEquals("NOME", rs.getString("NOME"));
		assertEquals("USERNAME", rs.getString("USERNAME"));
	}

	@Override
	protected String getExistentEntityKey() {
		return "USERNAMEE";
	}

	@Override
	protected void checkRead(Usuario entity) {
		assertNotNull(entity);
		assertEquals("USERNAMEE", entity.getUsername());
		assertEquals("NOMEE", entity.getNome());
	}

	@Override
	protected void updateEntity(Usuario entity) {
		entity.setNome("NOME2");
	}

	@Override
	protected void checkUpdatedEntity(ResultSet rs) throws SQLException {
		assertEquals("NOME2", rs.getString("NOME"));
	}

	@Override
	protected UsuarioRepository getRepository() {
		return repository;
	}
}
