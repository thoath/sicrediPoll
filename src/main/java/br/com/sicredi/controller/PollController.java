package br.com.sicredi.controller;

import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.dto.PollDto;
import br.com.sicredi.models.Poll;
import br.com.sicredi.models.PollOptions;
import br.com.sicredi.models.Response;
import br.com.sicredi.service.PollService;

/**
 * Controle de criacao de enquetes para votacao
 * @author lucaskoch
 *
 */
@RestController
@RequestMapping("/api/v1/poll")
public class PollController {

	@Autowired
	private PollService pollService;
	
	@GetMapping
	public ResponseEntity<Response<Set<PollDto>>> index() {
		return ResponseEntity.ok().body(new Response<>(pollService.index(), null));
	}
	
	/**
	 * Persiste uma enquete na base de dados
	 * @param poll
	 * @param result
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Response<PollDto>> store(@RequestBody @Valid Poll poll, BindingResult result) {
		
		LinkedList<String> errors = new  LinkedList<>();
		
		if (result.hasErrors()) {
			return ResponseEntity
					.ok()
					.body(new Response<>(null, result
							.getAllErrors()
							.stream()
							.map(error -> error.getDefaultMessage())
							.collect(Collectors.toList())
						));
		}
		
		try {
			return ResponseEntity.ok().body(new Response<>(pollService.store(poll), null));
		} catch(Exception ex) {
			errors.add("Error ao processar o cadastro, tente novamente mais tarde.");		
		}
		
		return ResponseEntity.ok().body(new Response<>(null, errors));
	}
	
	/**
	 * Busca por todas as enquetes abertas para votacao
	 * @return
	 */
	@GetMapping("/open")
	public ResponseEntity<Response<Set<PollDto>>> findByOpenStatus() {
		return ResponseEntity.ok().body(new Response<>(pollService.findOpen(), null));
	}
	
	/**
	 * Busca todas as enquetes fechadas/expiradas
	 * @return
	 */
	@GetMapping("/closed")
	public ResponseEntity<Response<Set<PollDto>>> findByCloseStatus() {
		return ResponseEntity.ok().body(new Response<>(pollService.findClosed(), null));
	}
	
	/**
	 * Busca todas as enquetes aprovadas pela maioria de voto
	 * @return
	 */
	@GetMapping("/approved")
	public ResponseEntity<Response<Set<PollDto>>> findByApproved() {
		return ResponseEntity.ok().body(new Response<>(pollService.findByResult(PollOptions.YES), null));
	}
	
	/**
	 * Busca todoas as enquetes rejeitadas pela maioria de voto
	 * @return
	 */
	@GetMapping("/rejected")
	public ResponseEntity<Response<Set<PollDto>>> findByRejected() {
		return ResponseEntity.ok().body(new Response<>(pollService.findByResult(PollOptions.NO), null));
	}
	
}
