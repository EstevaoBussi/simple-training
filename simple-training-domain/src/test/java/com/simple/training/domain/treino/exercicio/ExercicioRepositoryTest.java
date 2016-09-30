package com.simple.training.domain.treino.exercicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.simple.training.domain.support.AbstractRootEntityRepositoryTestSupport;

@Sql("exercicio-repository-setup.sql")
public class ExercicioRepositoryTest extends AbstractRootEntityRepositoryTestSupport <Exercicio, Long, ExercicioRepository> {
	@Autowired
	private ExercicioRepository repository;
	
	@Override
	protected Exercicio createNewEntity() {
		Exercicio exercicio = new Exercicio();
		exercicio.setNome("NOME");
		exercicio.setDescricao("DESCRICAO");
		exercicio.setGrupoMuscular(GrupoMuscular.ABDOMINAIS);
		return exercicio;
	}

	@Override
	protected Long getKey(Exercicio entity) {
		return entity.getID();
	}

	@Override
	protected Exercicio updateEntity() {
		Exercicio exercicio = new Exercicio();
		exercicio.setNome("NOME2");
		exercicio.setDescricao("DESCRICAO2");
		exercicio.setGrupoMuscular(GrupoMuscular.BICEPS);
		return exercicio;
	}

	@Override
	protected String loadQuery() {
		return "SELECT * FROM EXERCICIO WHERE ID = ?";
	}

	@Override
	protected void checkNewEntity(ResultSet rs) throws SQLException {
		assertNotNull(rs.getLong("ID"));
		assertEquals("NOME", rs.getString("NOME"));
		assertEquals("DESCRICAO", rs.getString("DESCRICAO"));
		assertEquals("ABDOMINAIS", rs.getString("GRUPO_MUSCULAR"));
	}

	@Override
	protected Long getExistentEntityKey() {
		return 10L;
	}

	@Override
	protected void checkRead(Exercicio entity) {
		assertNotNull(entity);
		assertEquals("DESCRICAOO", entity.getDescricao());
		assertEquals("COSTAS", entity.getGrupoMuscular().name());
		assertEquals("NOMEE", entity.getNome());
	}

	@Override
	protected void updateEntity(Exercicio entity) {
		entity.setNome("NOME2");
		entity.setDescricao("DESCRICAO2");
		entity.setGrupoMuscular(GrupoMuscular.BICEPS);
	}

	@Override
	protected void checkUpdatedEntity(ResultSet rs) throws SQLException {
		assertEquals("NOME2", rs.getString("NOME"));
		assertEquals("DESCRICAO2", rs.getString("DESCRICAO"));
		assertEquals("BICEPS", rs.getString("GRUPO_MUSCULAR"));
	}

	@Override
	protected ExercicioRepository getRepository() {
		return repository;
	}
}
