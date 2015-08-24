package project.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Commerce")
@ApiModel(value = "Commerce", description = "Commerce representation")
public class Commerce {
	@Id
	@GeneratedValue
	@Column(name = "id")
	@ApiModelProperty(value = "Commerce id", required = true)
	private Long id;

	@Column(name = "name")
	@ApiModelProperty(value = "Commerce name", required = true)
	private String name;

	@Column(name = "description")
	@ApiModelProperty(value = "Commerce description", required = true)
	private String description;

	@Column(name = "web")
	@ApiModelProperty(value = "Commerce web url", required = true)
	private String web;

	public Commerce() {

	}

	public Commerce( Long id , String name , String description , String web ) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.web = web;
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

	public String getWeb() {
		return web;
	}

	public void setWeb( String web ) {
		this.web = web;
	}

}
