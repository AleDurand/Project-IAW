package project.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "office")
public class OfficeModel implements Serializable {
    private static final long serialVersionUID = 9190080453022215012L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone")
    private String phone;

    @Embedded
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
