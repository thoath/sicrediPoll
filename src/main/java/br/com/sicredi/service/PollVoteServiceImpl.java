package br.com.sicredi.service;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.sicredi.dto.PollVoteDto;
import br.com.sicredi.models.CPFValidationStatusResponse;
import br.com.sicredi.models.PollVote;
import br.com.sicredi.repository.PollVoteRepository;

@Component
public class PollVoteServiceImpl implements PollVoteService {

	@Autowired
	private PollVoteRepository pollVoteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PollVoteDto store(PollVote pollVote) {
		return modelMapper.map(pollVoteRepository.save(pollVote), PollVoteDto.class);
	}

	@Override
	public Set<PollVoteDto> index() {
		
		return pollVoteRepository
		.findAll()
		.stream()
		.map(pollVote -> modelMapper.map(pollVote, PollVoteDto.class))
		.collect(Collectors.toCollection(TreeSet::new));
	}

	@Override
	public CPFValidationStatusResponse checkCPF(String cpf) throws RestClientException{
		
		RestTemplate restTemplate = new RestTemplate();
		
		StringBuilder sb = new StringBuilder();
		sb.append("https://user-info.herokuapp.com/users/");
		sb.append(cpf);
		
		ResponseEntity<CPFValidationStatusResponse> response = restTemplate.getForEntity(sb.toString(), CPFValidationStatusResponse.class);
		
		return response.getBody();
	}

}
