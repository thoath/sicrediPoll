package br.com.sicredi.models;

import java.io.Serializable;

public class CPFValidationStatusResponse implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private CPFValidationStatus status;
	
	public CPFValidationStatusResponse() {}



	public CPFValidationStatusResponse(CPFValidationStatus status) {
		super();
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public CPFValidationStatus getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(CPFValidationStatus status) {
		this.status = status;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		CPFValidationStatusResponse other = (CPFValidationStatusResponse) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
