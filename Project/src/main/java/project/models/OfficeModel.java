package project.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "office")
public class OfficeModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone")
	private String phone;

//	@Embedded
//	private AddressModel address;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "real_state_agent_id", referencedColumnName = "id"),
			@JoinColumn(name = "real_state_agent_user_id", referencedColumnName = "user_id") })
	private RealStateAgentModel realStateAgent;

	public OfficeModel() {

	}

	public OfficeModel(Integer id, String name, String phone,
			RealStateAgentModel realStateAgent) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.realStateAgent = realStateAgent;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public RealStateAgentModel getRealStateAgent() {
		return realStateAgent;
	}

	public void setRealStateAgent(RealStateAgentModel realStateAgent) {
		this.realStateAgent = realStateAgent;
	}

	// public AddressModel getAddress() {
	// return address;
	// }
	//
	// public void setAddress(AddressModel address) {
	// this.address = address;
	// }

}
