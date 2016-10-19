package com.simple.training.domain.nutri.cardapio;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "REFEICAO")
public class Refeicao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="PERIODO")
	@Enumerated(EnumType.STRING)
	private Periodo periodo;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<RefeicaoAlimento> alimentos = new ArrayList<>();
	
	public Long getID() {
		return id;
	}
	public void setID(Long id) {
		this.id = id;
	}
	public Periodo getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	public List<RefeicaoAlimento> getAlimentos() {
		return alimentos;
	}
	public void setAlimentos(List<RefeicaoAlimento> alimentos) {
		this.alimentos.clear();
		if (alimentos != null) {
			this.alimentos.addAll(alimentos);
		}
	}
	
}
