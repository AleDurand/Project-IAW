package project.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Embeddable
@Table(name = "operation")
@ApiModel(value = "Property operation type", description = "Operation type available for a property.")
public class OperationModel {

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "ENUM('FOR_SALE', 'FOR_RENT')")
    @ApiModelProperty(name = "Type", value = "Operation type", required = true)
    private TypeEnum type;

    @Column(name = "price")
    @ApiModelProperty(name = "Price", value = "Price of the operation", required = true)
    private Float price;

    public OperationModel() {

    }

    public OperationModel(TypeEnum type, Float price) {
        this.type = type;
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public enum TypeEnum {FOR_SALE, FOR_RENT}
}
