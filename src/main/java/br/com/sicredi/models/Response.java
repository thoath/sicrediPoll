package br.com.sicredi.models;

import java.util.List;

public class Response<T>{
	
	private T data;
	
	private List<String> error;
	
	public Response() {}

	public Response(T data, List<String> error) {
		super();
		this.data = data;
		this.error = error;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the error
	 */
	public List<String> getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(List<String> error) {
		this.error = error;
	}
	

}