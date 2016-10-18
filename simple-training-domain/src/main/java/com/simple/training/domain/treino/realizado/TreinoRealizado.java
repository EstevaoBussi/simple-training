package com.simple.training.domain.treino.realizado;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simple.training.domain.json.LocalDateDeserializer;
import com.simple.training.domain.json.LocalDateSerializer;
import com.simple.training.domain.treino.Treino;
import com.simple.training.domain.treino.json.TreinoDeserializer;

@Entity
@Table(name = "TREINO_REALIZADO")
public class TreinoRealizado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
    @JsonSerialize(using = LocalDateSerializer.class) 
	@Column(name="DATA")
	private LocalDate data;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="TREINO")
	@JsonDeserialize(using = TreinoDeserializer.class)
	private Treino treino;

	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}

}
