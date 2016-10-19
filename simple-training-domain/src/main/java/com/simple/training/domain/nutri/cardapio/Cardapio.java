package com.simple.training.domain.nutri.cardapio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "CARDAPIO")
public class Cardapio implements Serializable{

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
    @JoinColumn(name="USUARIO")
	@JsonDeserialize(using = UsuarioDeserializer.class)  
    @JsonSerialize(using = UsuarioSerializer.class)
	private Usuario usuario;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Refeicao> refeicoes = new ArrayList<>();
	
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Refeicao> getRefeicoes() {
		return refeicoes;
	}
	public void setRefeicoes(List<Refeicao> refeicoes) {
		this.refeicoes.clear();
		if (refeicoes != null) {
			this.refeicoes.addAll(refeicoes);
		}
	}
	
}
