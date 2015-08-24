package proyect.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "Office")
@ApiModel(value = "Office", description = "Office representation")
public class Office {
	@Id
	@GeneratedValue
	@Column(name = "id")
	@ApiModelProperty(value = "Office id", required = true)
	private Long id;

	@Column(name = "name")
	@ApiModelProperty(value = "Office name", required = true)
	private String name;

	@Column(name = "description")
	@ApiModelProperty(value = "Office description", required = true)
	private String description;

	@Column(name = "hours")
	@ApiModelProperty(value = "Office attention hours", required = true)
	private String hours;

	@Column(name = "address")
	@ApiModelProperty(value = "Office address", required = true)
	private String address;

	@Column(name = "latitude")
	@ApiModelProperty(value = "Office latitude for google map", required = true)
	private Long latitude;

	@Column(name = "longitude")
	@ApiModelProperty(value = "Office longitude for google map", required = true)
	private Long longitude;

	@ManyToOne
	@JoinColumn(name = "Commerce_id")
	private Commerce commerce;

	public Office() {
		
	}

	public Office( Long id , String name , String description , String hours , String address , Long latitude ,
			Long longitude , Commerce commerce ) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.hours = hours;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.commerce = commerce;
	}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	public String getHours() {
		return hours;
	}

	public void setHours( String hours ) {
		this.hours = hours;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress( String address ) {
		this.address = address;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude( Long latitude ) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude( Long longitude ) {
		this.longitude = longitude;
	}

	public Commerce getCommerce() {
		return commerce;
	}

	public void setCommerce( Commerce commerce ) {
		this.commerce = commerce;
	}
}
