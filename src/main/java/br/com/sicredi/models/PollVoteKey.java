package br.com.sicredi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PollVoteKey implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "associado_id")
	private Long associadoId;
	
	@Column(name = "poll_id")
	private Long pollId;

	public PollVoteKey() {}
	
	
	public PollVoteKey(Long associadoId, Long pollId) {
		super();
		this.associadoId = associadoId;
		this.pollId = pollId;
	}

	/**
	 * @return the userId
	 */
	public Long getAssociadoId() {
		return associadoId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setAssociadoId(Long associadoId) {
		this.associadoId = associadoId;
	}

	/**
	 * @return the pollId
	 */
	public Long getPollId() {
		return pollId;
	}

	/**
	 * @param pollId the pollId to set
	 */
	public void setPollId(Long pollId) {
		this.pollId = pollId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associadoId == null) ? 0 : associadoId.hashCode());
		result = prime * result + ((pollId == null) ? 0 : pollId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PollVoteKey other = (PollVoteKey) obj;
		if (associadoId == null) {
			if (other.associadoId != null)
				return false;
		} else if (!associadoId.equals(other.associadoId))
			return false;
		if (pollId == null) {
			if (other.pollId != null)
				return false;
		} else if (!pollId.equals(other.pollId))
			return false;
		return true;
	}
}
