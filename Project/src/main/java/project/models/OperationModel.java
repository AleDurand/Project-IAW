package project.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "operation")
@ApiModel(value = "Property operation type", description = "Operation type available for a property.")
public class OperationModel {

    @Column(name = "type", columnDefinition = "ENUM('FOR_SALE', 'FOR_RENT')")
    @ApiModelProperty(name = "Type", value = "Operation type", required = true, allowableValues = "FOR_SALE,FOR_RENT")
    private String type;

    @Column(name = "price")
    @ApiModelProperty(name = "Price", value = "Price of the operation", required = true)
    private Float price;

    public OperationModel() {

    }

    public OperationModel(String type, Float price) {
        this.type = type;
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
