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
public class UsuarioRepositoryTest extends AbstractRootEntityRepositoryTestSupport <Usuario, Long, UsuarioRepository> {
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	protected Usuario createNewEntity() {
		Usuario entity = new Usuario();
		entity.setCodigo(0L);
		entity.setDataNascimento(LocalDate.of(2001, 1, 1));
		entity.setEmail("EMAIL");
		entity.setNome("NOME");
		return entity;
	}

	@Override
	protected Long getKey(Usuario entity) {
		return entity.getCodigo();
	}

	@Override
	protected Usuario updateEntity() {
		Usuario entity = new Usuario();
		entity.setDataNascimento(LocalDate.of(2001, 1, 2));
		entity.setEmail("EMAIL2");
		entity.setNome("NOME2");
		return entity;
	}

	@Override
	protected String loadQuery() {
		return "SELECT * FROM USUARIO WHERE CODIGO = ?";
	}

	@Override
	protected void checkNewEntity(ResultSet rs) throws SQLException {
		assertEquals("NOME", rs.getString("NOME"));
		assertEquals("0", rs.getString("CODIGO"));
	}

	@Override
	protected Long getExistentEntityKey() {
		return 0L;
	}

	@Override
	protected void checkRead(Usuario entity) {
		assertNotNull(entity);
		assertNotNull(entity.getCodigo());
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
