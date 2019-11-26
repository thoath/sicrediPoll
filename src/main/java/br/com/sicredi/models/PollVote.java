package br.com.sicredi.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class PollVote implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PollVoteKey id;
	
	@ManyToOne
	@MapsId("poll_id")
	@JoinColumn(name = "poll_id")
	private Poll poll;

	@ManyToOne
	@MapsId("associado_id")
	@JoinColumn(name = "associado_id")
	private Associado associado;
	
	@Enumerated(value = EnumType.STRING)
	private PollOptions vote;
	
	public PollVote() {}
	
	public PollVote(PollVoteKey id, Poll poll, Associado associado, PollOptions vote) {
		super();
		this.id = id;
		this.poll = poll;
		this.associado = associado;
		this.vote = vote;
	}

	/**
	 * @return the id
	 */
	public PollVoteKey getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(PollVoteKey id) {
		this.id = id;
	}

	/**
	 * @return the poll
	 */
	public Poll getPoll() {
		return poll;
	}

	/**
	 * @param poll the poll to set
	 */
	public void setPoll(Poll poll) {
		this.poll = poll;
	}


	/**
	 * @return the associado
	 */
	public Associado getAssociado() {
		return associado;
	}

	/**
	 * @param associado the associado to set
	 */
	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	/**
	 * @return the vote
	 */
	public PollOptions getVote() {
		return vote;
	}

	/**
	 * @param vote the vote to set
	 */
	public void setVote(PollOptions vote) {
		this.vote = vote;
	}

}
