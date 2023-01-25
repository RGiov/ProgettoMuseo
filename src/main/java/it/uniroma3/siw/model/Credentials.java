package it.uniroma3.siw.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//import lombok.Data;



@Entity
public class Credentials {
	
	public static final String DEFAULT_ROLE = "DEFAULT";
	public static final String ADMIN_ROLE = "ADMIN";
	
	/**
	 *  Unique identifier for this User
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 *  Unique username for this User
	 */
	@Column(nullable = false, unique = true, length = 100)
	private String userName;

	/**
	 *  Password for this User
	 */
	@Column(nullable = false, length = 100)
	private String password;
	
	/**
	 *  The role of this User
	 */
	@Column(nullable = false)
	private String role;
	
	@OneToOne(fetch = FetchType.EAGER,
			cascade = CascadeType.ALL)
	private User user;
	
	public Credentials() {

	}

	public Credentials(Long id, String userName, String password, String role, User user) {
		this();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.user = user;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		Credentials other = (Credentials) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Credentials [userName=" + userName + "]";
	}

	
	
}
