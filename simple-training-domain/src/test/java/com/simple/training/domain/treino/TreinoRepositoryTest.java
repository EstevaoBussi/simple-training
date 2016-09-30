package com.simple.training.domain.treino;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.simple.training.domain.support.AbstractRootEntityRepositoryTestSupport;
import com.simple.training.domain.usuario.UsuarioRepository;

@Sql("treino-repository-setup.sql")
public class TreinoRepositoryTest extends AbstractRootEntityRepositoryTestSupport <Treino, Long, TreinoRepository> {
	@Autowired
	private TreinoRepository repository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	protected Treino createNewEntity() {
		Treino entity= new Treino();
		entity.setUsuario(usuarioRepository.getOne("A"));
		entity.setData(LocalDate.of(2001, 1, 1));
		entity.setLocalTreino(LocalTreino.ACADEMIA);
		return entity;
	}

	@Override
	protected Long getKey(Treino entity) {
		return entity.getID();
	}

	@Override
	protected Treino updateEntity() {
		Treino entity= new Treino();
		entity.setUsuario(usuarioRepository.getOne("B"));
		entity.setData(LocalDate.of(2001, 1, 3));
		entity.setLocalTreino(LocalTreino.CASA);
		return entity;
	}

	@Override
	protected String loadQuery() {
		return "SELECT * FROM TREINO WHERE ID = ?";
	}

	@Override
	protected void checkNewEntity(ResultSet rs) throws SQLException {
		assertNotNull(rs.getLong("ID"));
		assertEquals("aced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770703000007d1010178", rs.getString("DATA"));
		assertEquals("ACADEMIA", rs.getString("LOCAL_TREINO"));
		assertEquals("A", rs.getString("USUARIO"));
	}

	@Override
	protected Long getExistentEntityKey() {
		return 10L;
	}

	@Override
	protected void checkRead(Treino entity) {
		assertNotNull(entity.getID());
		assertEquals("CASA", entity.getLocalTreino().name());
		assertEquals("A", entity.getUsuario().getUsername());
	}

	@Override
	protected void updateEntity(Treino entity) {
		entity.setUsuario(usuarioRepository.getOne("B"));
	}

	@Override
	protected void checkUpdatedEntity(ResultSet rs) throws SQLException {
		assertEquals("B", rs.getString("USUARIO"));
	}

	@Override
	protected TreinoRepository getRepository() {
		return repository;
	}
}
