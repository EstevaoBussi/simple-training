package com.simple.training.domain.usuario;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simple.training.domain.json.LocalDateDeserializer;
import com.simple.training.domain.json.LocalDateSerializer;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CODIGO")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codigo;
	@Column(name="NOME", unique=true)
	private String nome;
	@Column(name="EMAIL", unique=true)
	private String email;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
    @JsonSerialize(using = LocalDateSerializer.class) 
	@Column(name="DATA_NASCIMENTO")
	private LocalDate dataNascimento;
	@Column(name="PERFIL")
	private Perfil perfil;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
}
