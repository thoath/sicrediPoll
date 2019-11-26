package br.com.sicredi.service;

import br.com.sicredi.dto.AssociadoDto;
import br.com.sicredi.models.Associado;

public interface AssociadoService {

	AssociadoDto store(Associado associado);
	
}
