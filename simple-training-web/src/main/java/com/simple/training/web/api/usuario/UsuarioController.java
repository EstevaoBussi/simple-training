package com.simple.training.web.api.usuario;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simple.training.domain.usuario.Usuario;
import com.simple.training.domain.usuario.service.UsuarioService;
import com.simple.training.web.api.exception.CampoObrigatorioException;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @RequestMapping(method = RequestMethod.GET)
    public Usuario get(@QueryParam(value="usuario") String usuario) throws Exception {
		if (StringUtils.isBlank(usuario)) {
    		throw new CampoObrigatorioException("usuario");
		}
        return usuarioService.getUsuario(usuario);
    }
    
    @Transactional
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Usuario update(@RequestBody final Usuario usuario) throws Exception {
        return usuarioService.save(usuario);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Usuario insert(@RequestBody final Usuario usuario) throws Exception {
    	return usuarioService.save(usuario);
    }
}