package xyz.tinyorb.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class user {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iduser")
	private int id;
	
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="Email")
	private String Email;
	
	@Column(name="Password")
	private String pass;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getId() {
		return id;
	}
	
	
}
