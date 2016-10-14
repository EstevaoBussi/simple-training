package com.simple.training.web.api.authentication;

import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.simple.training.domain.usuario.Usuario;

public class TokenAuthentication extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private String token;

    public TokenAuthentication(Usuario usuario,  String token, List<GrantedAuthority> authorities) {
        super(authorities);
        this.usuario = usuario;
        this.token = token;
    }

    @Override
    public String getName() {
        return usuario.getNome();
    }

    @Override
    public Usuario getPrincipal() {
        return usuario;
    }

    @Override
    public String getCredentials() {
        return token;
    }

}
