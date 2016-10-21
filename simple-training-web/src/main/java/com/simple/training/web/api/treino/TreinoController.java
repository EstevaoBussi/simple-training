package com.simple.training.web.api.treino;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.training.domain.treino.Categoria;
import com.simple.training.domain.treino.Treino;
import com.simple.training.domain.treino.service.TreinoService;
import com.simple.training.domain.usuario.service.UsuarioService;
import com.simple.training.web.api.exception.CampoObrigatorioException;

@RestController
@RequestMapping("/api/v1/treinos")
public class TreinoController {

	@Autowired
	private TreinoService treinoService;
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Treino> getAll(@RequestParam(value="dataTreino") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, 
    		                       @QueryParam(value="categoria") String categoria, 
    		                       @QueryParam(value="usuario") Long usuario) throws Exception {
		if (data == null) {
    		throw new CampoObrigatorioException("dataTreino");
		}
		if (categoria == null) {
    		throw new CampoObrigatorioException("categoria");
		}
		if (usuario == null) {
    		throw new CampoObrigatorioException("usuario");
		}
        return treinoService.getTreinos(data, Categoria.valueOf(categoria), usuarioService.getUsuario(usuario));
    }
	
	@Transactional
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Treino update(@RequestBody final Treino treino) throws Exception {
        return treinoService.save(treino);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Treino create(@RequestBody final Treino treino) throws Exception {
    	return treinoService.save(treino);
    }
    
}
