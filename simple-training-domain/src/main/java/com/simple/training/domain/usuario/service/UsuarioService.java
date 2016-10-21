package com.simple.training.domain.usuario.service;

import java.util.List;

import com.simple.training.domain.usuario.Usuario;

public interface UsuarioService {

	List<Usuario> findByName(String name);
	Usuario findByEmail(String email);
	Usuario getUsuario(Long codigo);
    Usuario save(Usuario usuario);
}
