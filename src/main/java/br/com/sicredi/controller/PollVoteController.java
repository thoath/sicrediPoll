package br.com.sicredi.controller;

import java.util.LinkedList;
import java.util.Optional;
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

import br.com.sicredi.dto.PollVoteDto;
import br.com.sicredi.models.Associado;
import br.com.sicredi.models.CPFValidationStatus;
import br.com.sicredi.models.Poll;
import br.com.sicredi.models.PollStatus;
import br.com.sicredi.models.PollVote;
import br.com.sicredi.models.Response;
import br.com.sicredi.repository.AssociadoRepository;
import br.com.sicredi.repository.PollRepository;
import br.com.sicredi.service.PollVoteService;

/**
 * Controlador de votacao das enquetes
 * @author lucaskoch
 *
 */
@RestController
@RequestMapping("/api/v1/vote")
public class PollVoteController {
	
	@Autowired
	private PollVoteService pollVoteService;
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
	@Autowired
	private PollRepository pollRepository;
	
	@GetMapping
	private ResponseEntity<Response<Set<PollVoteDto>>> index() {
		
		return ResponseEntity.ok().body(new Response<>(pollVoteService.index(), null));
	}
	
	/**
	 * Persiste uma votacao no sistema
	 *  - Apenas 1 votacao por cpf e permitida
	 *  - So pode votar em enquetes abertas
	 *  - Apos votar, se o mesmo cpf tentar votar novamente, vai apenas atualizar seu voto
	 * @param pollVote
	 * @param result
	 * @return
	 */
	@PostMapping
	private ResponseEntity<Response<PollVoteDto>> store(@RequestBody @Valid PollVote pollVote, BindingResult result){
		
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
		
		Optional<Poll> poll = pollRepository.findById(pollVote.getId().getPollId());
		
		if (poll.isPresent() && poll.get().getStatus() == PollStatus.CLOSED) {
			errors.add("As votações já foram encerradas.");
			return ResponseEntity.ok().body(new Response<>(null, errors));
		}
		
		
		try {
			
			Optional<Associado>  associado = associadoRepository.findById(pollVote.getId().getAssociadoId());
			
			
			if (associado.isPresent() 
					&& pollVoteService.checkCPF(associado.get().getCpf()).getStatus() == CPFValidationStatus.ABLE_TO_VOTE) {
				return ResponseEntity.ok().body(new Response<>(pollVoteService.store(pollVote), null));
			}
			
			errors.add("Seu CPF não está autorizado para essa votação.");
			
			
		} catch (Exception ex) {
			errors.add("Error ao processar o cadastro, tente novamente mais tarde.");	
		}
		
		return ResponseEntity.ok().body(new Response<>(null, errors));
		
	}

}
