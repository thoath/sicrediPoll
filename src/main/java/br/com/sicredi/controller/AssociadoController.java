package br.com.sicredi.controller;

import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.dto.AssociadoDto;
import br.com.sicredi.models.Associado;
import br.com.sicredi.models.Response;
import br.com.sicredi.repository.AssociadoRepository;
import br.com.sicredi.service.AssociadoService;

/**
 * Controlador de registros de associados para votacao
 * @author lucaskoch
 *
 */
@RestController
@RequestMapping("/api/v1/associado")
public class AssociadoController {
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
	@Autowired
	private AssociadoService associadoService;
	
	/**
	 * Persiste um novo associado na aplicacao
	 *  - CPF deve ser valido e nao pode ser repetido
	 * @param associado
	 * @param result resultado da validacao do request body com o model
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Response<AssociadoDto>> store(@RequestBody @Valid Associado associado, BindingResult result){
		
		LinkedList<String> errors = new  LinkedList<>();
		
		if (result.hasErrors()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new Response<>(null, result
							.getAllErrors()
							.stream()
							.map(error -> error.getDefaultMessage())
							.collect(Collectors.toList())
						));
		}
		
		if (associadoRepository.findByCpf(associado.getCpf()) != null) {
			errors.add("Conta já existe no sistema.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(null, errors));
		}
	
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>(associadoService.store(associado), null));
		} catch(Exception ex) {
			errors.add("Error ao processar o cadastro, tente novamente mais tarde.");		
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(null, errors));	
	}

}
