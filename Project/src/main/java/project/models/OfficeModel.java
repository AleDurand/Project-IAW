package project.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "office")
@ApiModel(value = "Office", description = "Real state agent office representation")
public class OfficeModel implements Serializable {
    private static final long serialVersionUID = 9190080453022215012L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @ApiModelProperty(name = "Id", value = "Office id", required = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    @ApiModelProperty(name = "Name", value = "Office name", required = true)
    private String name;

    @Column(name = "phone")
    @ApiModelProperty(name = "Phone", value = "Office phone", required = true)
    private String phone;

    @OneToOne
    @JoinColumn(name = "address_id")
    @ApiModelProperty(name = "Address", value = "Office address", required = true)
    private AddressModel address;

    public OfficeModel() {

    }

    public OfficeModel(Integer id, String name, String phone, AddressModel address) {
        super();
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
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

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

}
