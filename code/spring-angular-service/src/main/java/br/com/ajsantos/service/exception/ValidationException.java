package br.com.ajsantos.service.exception;

public class ValidationException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String menssage;
	private Object[] params;

	/**
	 * Aramagem informações das mensagems.
	 * 
	 * @param menssage
	 *          Chave de mapeamento do propertie.
	 * @param params
	 *          parametros a serem adicionados na mensagem.
	 */
	public ValidationException(String menssage, Object... params) {
	  this.menssage = menssage;
	  this.params = params;
	}

	/**
	 * Retorna a chave de mensagem mapeada no properties.
	 * 
	 * @return the menssage
	 */
	public String getMenssage() {
	  return menssage;
	}

	/**
	 * Retorna os parametros a serem passados na mensagem.
	 * 
	 * @return the params
	 */
	public Object[] getParams() {
	  return params;
	}
	  
}
