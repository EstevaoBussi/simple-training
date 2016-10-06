package com.simple.training.domain.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.training.domain.usuario.Usuario;
import com.simple.training.domain.usuario.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

	@Override
	public Usuario getUsuario(String username) {
		return repository.getOne(username);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return repository.save(usuario);
	}
	
}
