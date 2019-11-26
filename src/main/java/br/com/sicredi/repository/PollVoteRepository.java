package br.com.sicredi.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sicredi.models.PollVote;
import br.com.sicredi.models.PollVoteKey;

public interface PollVoteRepository extends JpaRepository<PollVote, PollVoteKey>{
	
	Set<PollVote> findByPollId(Long pollId);
	Set<PollVote> findByAssociado(Long associadoId);	
}
