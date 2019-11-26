package br.com.sicredi.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class Poll implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "O assunto do tópico não pode estar vazio.")
	private String subject;
	
	@CreatedDate
	@CreationTimestamp
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expireIn;
	
	@Transient
	private PollStatus status;
	
	@Enumerated(value = EnumType.STRING)
	private PollOptions result;
	
	@OneToMany(mappedBy = "poll", fetch = FetchType.LAZY)
	private Set<PollVote> polls;
	
	@PrePersist
	private void prePersist() {
		
		if (this.expireIn != null && this.expireIn.after(new Date())) {
			return;
		}
		
		LocalDateTime expireInDefault = LocalDateTime.now();
		expireInDefault = expireInDefault.plusMinutes(1);
		this.expireIn = Date.from(expireInDefault.atZone(ZoneId.systemDefault()).toInstant());
		
	}
	
	@PostLoad
	private void onLoad() {
		
		PollStatus status = PollStatus.CLOSED;
		
		if (this.expireIn.after(new Date())) {
			status = PollStatus.OPEN;
		} 
		
		this.status = status;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the expireIn
	 */
	public Date getExpireIn() {
		return expireIn;
	}

	/**
	 * @param expireIn the expireIn to set
	 */
	public void setExpireIn(Date expireIn) {
		this.expireIn = expireIn;
	}

	/**
	 * @return the status
	 */
	public PollStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(PollStatus status) {
		this.status = status;
	}

	/**
	 * @return the result
	 */
	public PollOptions getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(PollOptions result) {
		this.result = result;
	}

	/**
	 * @return the votes
	 */
	public Set<PollVote> getPolls() {
		return polls;
	}

	/**
	 * @param votes the votes to set
	 */
	public void setPolls(Set<PollVote> polls) {
		this.polls = polls;
	}

	@Override
	public String toString() {
		return "Poll [id=" + id + ", subject=" + subject + ", createdAt=" + createdAt + ", expireIn=" + expireIn
				+ ", status=" + status + ", result=" + result + "]";
	}
	
}
