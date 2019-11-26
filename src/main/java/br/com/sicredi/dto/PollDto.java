package br.com.sicredi.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sicredi.models.PollOptions;
import br.com.sicredi.models.PollStatus;

/**
 * Objeto de transferencia de dados do modelo de enquete
 * @author lucaskoch
 *
 */
@SuppressWarnings("rawtypes")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PollDto implements Comparable{
	
	private String id;
	private String subject;
	private Date expireIn;
	private PollStatus status;
	private PollOptions result;

	
	public PollDto() {}
	
	public PollDto(String id, String subject, Date expireIn, PollStatus status, PollOptions result) {
		super();
		this.id = id;
		this.subject = subject;
		this.expireIn = expireIn;
		this.status = status;
		this.result = result;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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

	@Override
	public int compareTo(Object o) {
		return this.id.compareTo(((PollDto) o).getId());
	}

}
