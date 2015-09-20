package project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "real_estate_agent")
@ApiModel(value = "Real estate agent", description = "Real estate agent representation")
public class RealEstateAgentModel implements Serializable {
    private static final long serialVersionUID = -429153276492199091L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @ApiModelProperty(name = "Id", value = "Real estate agent id", required = false)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    @ApiModelProperty(name = "Name", value = "Real estate agent name", required = true)
    private String name;

    @Column(name = "description")
    @ApiModelProperty(name = "Description", value = "Real estate agent description", required = false)
    private String description;

    @Column(name = "web")
    @ApiModelProperty(name = "Web url", value = "Real estate agent web", required = false)
    private String web;

    @Column(name = "email")
    @ApiModelProperty(name = "Email", value = "Real estate agent email", required = false)
    private String email;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "real_estate_agent_has_office",
            joinColumns = {@JoinColumn(name = "real_estate_agent_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "office_id", referencedColumnName = "id")})
    private List<OfficeModel> offices;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "real_estate_agent_has_property",
            joinColumns = {@JoinColumn(name = "real_estate_agent_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "property_id", referencedColumnName = "id")})
    private List<PropertyModel> properties;

    public RealEstateAgentModel() {

    }

    public RealEstateAgentModel(Integer id, String name, String description, String web, String email) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.web = web;
        this.email = email;
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

    public List<OfficeModel> getOffices() {
        return offices;
    }

    public void setOffices(List<OfficeModel> offices) {
        this.offices = offices;
    }

    public List<PropertyModel> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyModel> properties) {
        this.properties = properties;
    }
}
