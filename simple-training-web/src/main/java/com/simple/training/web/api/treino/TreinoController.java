package com.simple.training.web.api.treino;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simple.training.domain.treino.LocalTreino;
import com.simple.training.domain.treino.Treino;
import com.simple.training.domain.treino.service.TreinoService;
import com.simple.training.domain.usuario.Usuario;
import com.simple.training.web.api.exception.CampoObrigatorioException;

@RestController
@RequestMapping("/api/v1/treinos")
public class TreinoController {

	@Autowired
	private TreinoService treinoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Treino> getTreinos(@QueryParam("data") final LocalDate data, @QueryParam("local") final LocalTreino local, @QueryParam("usuario") final Usuario usuario) throws Exception {
		if (data == null) {
    		throw new CampoObrigatorioException("data");
		}
		if (local == null) {
    		throw new CampoObrigatorioException("local");
		}
		if (usuario == null || StringUtils.isBlank(usuario.getUsername())) {
    		throw new CampoObrigatorioException("usuario");
		}
        return treinoService.getTreinos(data, local, usuario);
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
