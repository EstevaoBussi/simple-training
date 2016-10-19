package com.simple.training.web.api.nutri;

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

import com.simple.training.domain.nutri.cardapio.Cardapio;
import com.simple.training.domain.nutri.cardapio.service.CardapioService;
import com.simple.training.domain.usuario.service.UsuarioService;
import com.simple.training.web.api.exception.CampoObrigatorioException;

@RestController
@RequestMapping("/api/v1/cardapios")
public class CardapioController {

	@Autowired
	private CardapioService cardapioService;
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Cardapio> getAll(@RequestParam(value="data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, 
    		                       @QueryParam(value="usuario") String usuario) throws Exception {
		if (data == null) {
    		throw new CampoObrigatorioException("data");
		}
		if (StringUtils.isBlank(usuario)) {
    		throw new CampoObrigatorioException("usuario");
		}
        return cardapioService.getCardapios(data, usuarioService.getUsuario(usuario));
    }
	
	@Transactional
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Cardapio update(@RequestBody final Cardapio cardapio) throws Exception {
        return cardapioService.save(cardapio);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Cardapio create(@RequestBody final Cardapio cardapio) throws Exception {
    	return cardapioService.save(cardapio);
    }
    
}
