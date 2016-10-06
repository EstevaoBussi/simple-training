package com.simple.training.web.api.treino;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.training.domain.treino.LocalTreino;
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
    public List<Treino> getTreinos(@RequestParam(value="dataTreino") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, 
    		                       @QueryParam(value="local") String local, 
    		                       @QueryParam(value="usuario") String usuario) throws Exception {
		if (data == null) {
    		throw new CampoObrigatorioException("dataTreino");
		}
		if (local == null) {
    		throw new CampoObrigatorioException("local");
		}
		if (StringUtils.isBlank(usuario)) {
    		throw new CampoObrigatorioException("usuario");
		}
        return treinoService.getTreinos(data, LocalTreino.valueOf(local), usuarioService.getUsuario(usuario));
    }
	
	@Transactional
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Treino updateCestaBeneficio(@RequestBody final Treino treino) throws Exception {
        return treinoService.save(treino);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Treino createCestaBeneficio(@RequestBody final Treino treino) throws Exception {
    	return treinoService.save(treino);
    }
    
}
