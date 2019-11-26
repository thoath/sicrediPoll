package br.com.sicredi.scheduler;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.sicredi.jobs.PollVoteJob;
import br.com.sicredi.models.Poll;
import br.com.sicredi.models.PollOptions;
import br.com.sicredi.models.PollVote;
import br.com.sicredi.repository.PollRepository;
import br.com.sicredi.repository.PollVoteRepository;

@Component
@EnableScheduling
public class PollVoteComputeScheduler {
	private final long SECOND = 1000; 
    private final long MINUTE = SECOND * 60;
    private final long HOUR = MINUTE * 60; 
	
    @Autowired
    private PollRepository pollRepository;
    
    @Autowired
    private PollVoteRepository pollVoteRepository;
    
    @Autowired
    private PollVoteJob pollVoteJob;
    
    @Scheduled(fixedDelay = HOUR)
    public void computeVotes() {
    	
    	Set<Poll> polls = pollRepository.findByNotComputed();
    	
    	if (polls != null && polls.size() > 0) {
        	
    		System.out.println("Computing " + polls.size() + " polls.");
        	
        	polls.stream().forEach(poll -> compute(poll));
    	}
    	
	}
    
    public void compute(Poll poll) {
    	
    	synchronized (poll) {
        	
    		Set<PollVote> votes = pollVoteRepository.findByPollId(poll.getId());
        	
    		long positive = votes.stream().filter(vote -> vote.getVote() == PollOptions.YES).count();
        	long negative = votes.stream().filter(vote -> vote.getVote() == PollOptions.NO).count();
        	
    		poll.setResult(positive > negative ? PollOptions.YES : PollOptions.NO);
        	
    		pollRepository.save(poll);
    		
    		pollVoteJob.sendMessage(poll.toString());
    		
		}
    	
    }
	
}
