package proyect.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Commerce")
@ApiModel(value = "Commerce", description = "Commerce representation")
public class Commerce {
	@Id
	@ApiModelProperty(value = "Commerce's id", required = true)
	private long id;
	@ApiModelProperty(value = "Commerce's name", required = true)
	private String name;
	@ApiModelProperty(value = "Commerce's description", required = true)
	private String description;
	@ApiModelProperty(value = "Commerce's web url", required = true)
	private String web;

	public Commerce() {

	}

	public Commerce(long id, String name, String description, String web) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.web = web;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

}
