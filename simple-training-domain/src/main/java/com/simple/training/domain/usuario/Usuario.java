package com.simple.training.domain.usuario;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USERNAME")
	private String username;
	@Column(name="NOME")
	private String nome;
	@Column(name="EMAIL")
	private String email;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
    @JsonSerialize(using = LocalDateSerializer.class) 
	@Column(name="DATA_NASCIMENTO")
	private LocalDate dataNascimento;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
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
	
}
