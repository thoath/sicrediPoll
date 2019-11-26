package br.com.sicredi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sicredi.models.PollOptions;

/**
 * Objeto de transferencia de dados do modelo de votacao
 * @author lucaskoch
 *
 */
@SuppressWarnings("rawtypes")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PollVoteDto implements Comparable{

	private AssociadoDto associado;
	private PollDto poll;
	private PollOptions vote;
	/**
	 * @return the associado
	 */
	public AssociadoDto getAssociado() {
		return associado;
	}
	/**
	 * @param associado the associado to set
	 */
	public void setAssociado(AssociadoDto associado) {
		this.associado = associado;
	}
	/**
	 * @return the poll
	 */
	public PollDto getPoll() {
		return poll;
	}
	/**
	 * @param poll the poll to set
	 */
	public void setPoll(PollDto poll) {
		this.poll = poll;
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
	@Override
	public int compareTo(Object o) {
		return this.associado.getCpf().compareTo(((PollVoteDto)o).getAssociado().getCpf());
	}
	
}
