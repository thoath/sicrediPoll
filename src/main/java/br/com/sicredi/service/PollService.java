package br.com.sicredi.service;

import java.util.Set;

import br.com.sicredi.dto.PollDto;
import br.com.sicredi.models.Poll;
import br.com.sicredi.models.PollOptions;

public interface PollService {
	
	PollDto store(Poll poll);
	Set<PollDto> index();
	Set<PollDto> findOpen();
	Set<PollDto> findClosed();
	Set<PollDto> findByResult(PollOptions result);
}
