package project.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "real_state_agent")
public class RealStateAgentModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "web")
	private String web;

	@Column(name = "email")
	private String email;

	@OneToOne
	@JoinColumn(name = "user_id", unique=true)
	private UserModel user;

	public RealStateAgentModel() {

	}

	public RealStateAgentModel(Integer id, String name, String description, String web, String email, UserModel user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.web = web;
		this.email = email;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

}
