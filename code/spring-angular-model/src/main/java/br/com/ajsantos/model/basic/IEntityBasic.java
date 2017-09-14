package br.com.ajsantos.model.basic;

import java.io.Serializable;

public interface IEntityBasic<T> extends Serializable{

	/**
	 * Implementar o id de uma entitidade
	 * @return
	 */
	public T getId();
}
