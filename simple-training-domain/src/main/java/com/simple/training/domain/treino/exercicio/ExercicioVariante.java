package com.simple.training.domain.treino.exercicio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simple.training.domain.treino.Status;
import com.simple.training.domain.treino.Treino;
import com.simple.training.domain.treino.exercicio.json.ExercicioDeserializer;
import com.simple.training.domain.treino.exercicio.json.ExercicioSerializer;

@Entity
@Table(name = "EXERCICIO_VARIANTE")
public class ExercicioVariante implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="DURACAO")
	private String duracao;
	@Column(name="OBSERVACAO")
	private String observacao;
	@Column(name="APARELHO")
	private String aparelho;
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EXERCICIO", nullable=false)
	@JsonDeserialize(using = ExercicioDeserializer.class)  
    @JsonSerialize(using = ExercicioSerializer.class) 
	private Exercicio exercicio;
	@Column(name="STATUS")
	private Status status;
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TREINO", nullable=false)
    @JsonIgnore
	private Treino treino;
	
	public Long getID() {
		return id;
	}
	
	public void setID(Long id) {
		this.id = id;
	}
	
	public String getDuracao() {
		return duracao;
	}
	
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public String getAparelho() {
		return aparelho;
	}
	
	public void setAparelho(String aparelho) {
		this.aparelho = aparelho;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}
	
}
