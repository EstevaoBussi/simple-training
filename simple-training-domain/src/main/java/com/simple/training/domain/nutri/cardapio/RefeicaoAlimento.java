package com.simple.training.domain.nutri.cardapio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REFEICAO_ALIMENTO")
public class RefeicaoAlimento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="ALIMENTO")
	private String alimento;
	@Column(name="QUANTIDADE")
	private String quantidade;
	@Column(name="CALORIAS")
	private Long calorias;
	
	public Long getID() {
		return id;
	}
	public void setID(Long id) {
		this.id = id;
	}
	public String getAlimento() {
		return alimento;
	}
	public void setAlimento(String alimento) {
		this.alimento = alimento;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public Long getCalorias() {
		return calorias;
	}
	public void setCalorias(Long calorias) {
		this.calorias = calorias;
	}

}
