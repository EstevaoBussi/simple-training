package com.simple.training.domain.usuario.service;

import com.simple.training.domain.usuario.Usuario;

public interface UsuarioService {

	Usuario findByEmail(String email);
	Usuario getUsuario(String username);
    Usuario save(Usuario usuario);
}
