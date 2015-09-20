package project.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "property")
@ApiModel(value = "Property", description = "Property representation")
public class PropertyModel {
    @Enumerated(EnumType.STRING)
    @Column(name = "state", columnDefinition = "ENUM('AVAILABLE', 'UNAVAILABLE')")
    public StateEnum state;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @ApiModelProperty(name = "Id", value = "Property id", required = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressModel address;

    @Column(name = "description")
    @ApiModelProperty(name = "Description", value = "Property description", required = false)
    private String description;

    @ApiModelProperty(name = "Rooms", value = "Property rooms", required = false)
    @Column(name = "rooms")
    private Integer rooms;

    @ApiModelProperty(name = "Size", value = "Property size", required = false)
    @Column(name = "size")
    private Integer size;


    @ElementCollection
    @CollectionTable(
            name = "operation",
            joinColumns = @JoinColumn(name = "property_id")
    )
    @ApiModelProperty(name = "Operation", value = "Operations available", required = false)
    private List<OperationModel> operation;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "property_has_category",
            joinColumns = {@JoinColumn(name = "property_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private List<CategoryModel> categories;

    public PropertyModel() {

    }

    public PropertyModel(StateEnum state, AddressModel address, String description, Integer rooms, Integer size, List<OperationModel> operation) {
        this.state = state;
        this.address = address;
        this.description = description;
        this.rooms = rooms;
        this.size = size;
        this.operation = operation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public List<OperationModel> getOperation() {
        return operation;
    }

    public void setOperation(List<OperationModel> operation) {
        this.operation = operation;
    }

    public List<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryModel> categories) {
        this.categories = categories;
    }

    public enum StateEnum {AVAILABLE, UNAVAILABLE}
}
