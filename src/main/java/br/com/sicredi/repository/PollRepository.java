package br.com.sicredi.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sicredi.models.Poll;
import br.com.sicredi.models.PollOptions;

@Repository
public interface PollRepository extends JpaRepository<Poll, String>{

	Optional<Poll> findById(Long id);
	
	Set<Poll> findByResult(PollOptions result);
	
	@Query("SELECT p from Poll p WHERE p.expireIn <= now() and p.result is null")
	Set<Poll> findByNotComputed();
	
	@Query("SELECT p from Poll p WHERE p.expireIn > now()")
	Set<Poll> findByOpenStatus();
	
	@Query("SELECT p from Poll p WHERE p.expireIn <= now()")
	Set<Poll> findByClosedStatus();
}
