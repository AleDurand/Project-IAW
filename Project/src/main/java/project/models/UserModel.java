package project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@ApiModel(value = "User", description = "User representation")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @ApiModelProperty(name = "Id", value = "User id", required = false)
    private Integer id;

    @Column(name = "username", unique = true, nullable = false)
    @ApiModelProperty(name = "User Name", value = "User name", required = true)
    private String username;

    @Column(name = "password", nullable = false)
    @ApiModelProperty(name = "Password", value = "User password", required = true)
    private String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_has_real_estate_agent",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "real_estate_agent_id", referencedColumnName = "id")})
    private List<RealEstateAgentModel> realEstateAgents;

    public UserModel() {

    }

    public UserModel(Integer id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public List<RealEstateAgentModel> getRealEstateAgents() {
        return realEstateAgents;
    }

    public void setRealEstateAgents(List<RealEstateAgentModel> realEstateAgents) {
        this.realEstateAgents = realEstateAgents;
    }
}
