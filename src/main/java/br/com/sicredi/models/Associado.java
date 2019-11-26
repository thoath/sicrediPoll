package br.com.sicredi.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Associado implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@CPF
	@NotEmpty(message = "CPF não pode estar vazio")
	private String cpf;
	
	@NotEmpty(message = "Nome não pode estar vazio")
	private String name;

	@OneToMany(mappedBy = "associado", fetch = FetchType.LAZY)
	private Set<PollVote> polls;
	
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
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the polls
	 */
	public Set<PollVote> getPolls() {
		return polls;
	}

	/**
	 * @param polls the polls to set
	 */
	public void setPolls(Set<PollVote> polls) {
		this.polls = polls;
	}
	
	
}
