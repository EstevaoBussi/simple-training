package com.simple.training.domain.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.simple.training.domain.usuario.Usuario;
import com.simple.training.domain.usuario.UsuarioRepository;
import com.simple.training.domain.usuario.UsuarioSpecifications;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
	public Usuario findByEmail(String email) {
		return repository.findByEmail(email);
	}
    
    @Override
	public List<Usuario> findByName(String name) {
    	Specification<Usuario> spec = UsuarioSpecifications.filterName(name);
		return repository.findAll(spec, new Sort(Direction.ASC,"nome"));
	}
    
	@Override
	public Usuario getUsuario(Long codigo) {
		return repository.getOne(codigo);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return repository.save(usuario);
	}
	
}
