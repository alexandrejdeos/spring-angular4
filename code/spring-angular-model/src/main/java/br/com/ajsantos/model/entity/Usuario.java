package br.com.ajsantos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.ajsantos.model.basic.IEntityBasic;

@Entity(name = "users")
public class Usuario implements IEntityBasic<Long> {
	private static final long serialVersionUID = -1593359042148299877L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "username", nullable = false, unique = true)
    private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Override
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
