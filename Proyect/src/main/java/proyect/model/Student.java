package proyect.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@ApiModel( value = "Student", description = "Person resource representation" )
public class Student {
	@Id
	@ApiModelProperty( value = "Student's id", required = true )
	private long id;
	@ApiModelProperty( value = "Student's lastname", required = true )
	private String lastname;
	@ApiModelProperty( value = "Student's first name", required = true )
	private String name;
	@ApiModelProperty( value = "Student's lu", required = true )
	private long lu;
	
	public Student() {
		
	}

	public Student(long id, String lastname, String name, long lu) {
		this.id = id;
		this.lastname = lastname;
		this.name = name;
		this.lu = lu;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getLu() {
		return lu;
	}

	public void setLu(long lu) {
		this.lu = lu;
	}
	
	
	
	

}
