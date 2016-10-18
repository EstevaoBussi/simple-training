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
import com.simple.training.domain.treino.exercicio.ExercicioVariante;
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
	@Column(name="CLASSIFICACAO")
	private String classificacao;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy="treino")
	private List<ExercicioVariante> exercicios = new ArrayList<>();
	@Column(name="CATEGORIA")
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
    @JsonSerialize(using = LocalDateSerializer.class) 
	@Column(name="DATA_INICIO")
	private LocalDate dataInicio;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
    @JsonSerialize(using = LocalDateSerializer.class) 
	@Column(name="DATA_FIM")
	private LocalDate dataFim;
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
	
	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

}
