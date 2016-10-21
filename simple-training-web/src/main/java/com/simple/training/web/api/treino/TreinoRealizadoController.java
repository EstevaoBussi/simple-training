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
import com.simple.training.domain.treino.realizado.TreinoRealizado;
import com.simple.training.domain.treino.realizado.service.TreinoRealizadoService;
import com.simple.training.domain.usuario.service.UsuarioService;
import com.simple.training.web.api.exception.CampoObrigatorioException;

@RestController
@RequestMapping("/api/v1/treinosRealizados")
public class TreinoRealizadoController {

	@Autowired
	private TreinoRealizadoService service;
	    
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET)
    public List<TreinoRealizado> getAll(@RequestParam(value="dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial, 
    		                       @RequestParam(value="dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
    		                       @QueryParam(value="categoria") String categoria, 
    		                       @QueryParam(value="usuario") Long usuario) throws Exception {
		if (dataInicial == null) {
    		dataInicial = LocalDate.now().withDayOfMonth(1);
		}
		if (dataFinal == null) {
    		dataFinal = LocalDate.now();
		}
		if (categoria == null) {
    		throw new CampoObrigatorioException("categoria");
		}
		if (usuario == null) {
    		throw new CampoObrigatorioException("usuario");
		}
        return service.getTreinos(dataInicial, dataFinal, Categoria.valueOf(categoria), usuarioService.getUsuario(usuario));
    }
	
	@Transactional
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TreinoRealizado update(@RequestBody final TreinoRealizado treino) throws Exception {
        return service.save(treino);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public TreinoRealizado create(@RequestBody final TreinoRealizado treino) throws Exception {
    	return service.save(treino);
    }
}
