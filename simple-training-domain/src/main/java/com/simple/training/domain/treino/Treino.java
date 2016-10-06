package com.simple.training.domain.treino;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simple.training.domain.json.LocalDateDeserializer;
import com.simple.training.domain.json.LocalDateSerializer;
import com.simple.training.domain.usuario.Usuario;
import com.simple.training.domain.usuario.json.UsuarioDeserializer;
import com.simple.training.domain.usuario.json.UsuarioSerializer;

@Entity
@Table(name = "TREINO")
public class Treino implements Serializable	{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy="treino")
	private List<ExercicioVariante> exercicios = new ArrayList<>();
	@Column(name="LOCAL_TREINO")
	@Enumerated(EnumType.STRING)
	private LocalTreino localTreino;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
    @JsonSerialize(using = LocalDateSerializer.class) 
	@Column(name="DATA")
	private LocalDate data;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="USUARIO")
	@JsonDeserialize(using = UsuarioDeserializer.class)  
    @JsonSerialize(using = UsuarioSerializer.class) 
	private Usuario usuario;
	
	public Long getID() {
		return id;
	}
	
	public void setID(Long id) {
		this.id = id;
	}
	
	public List<ExercicioVariante> getExercicios() {
		return exercicios;
	}
	
	public void setExercicios(List<ExercicioVariante> exercicios) {
		this.exercicios.clear();
		if (exercicios != null) {
			this.exercicios.addAll(exercicios);
		}
	}

	public LocalTreino getLocalTreino() {
		return localTreino;
	}

	public void setLocalTreino(LocalTreino localTreino) {
		this.localTreino = localTreino;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
}
