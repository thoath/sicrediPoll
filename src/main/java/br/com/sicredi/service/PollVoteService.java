package br.com.sicredi.service;

import java.util.Set;

import br.com.sicredi.dto.PollVoteDto;
import br.com.sicredi.models.CPFValidationStatusResponse;
import br.com.sicredi.models.PollVote;

public interface PollVoteService {

	PollVoteDto store(PollVote pollVote);
	Set<PollVoteDto> index();
	CPFValidationStatusResponse checkCPF(String cpf);
	
}
