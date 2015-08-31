package project.models;

import javax.persistence.*;

@Embeddable
@Table(name = "operation")
public class OperationModel {

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "ENUM('FOR_SALE', 'FOR_RENT')")
    public TypeEnum type;

    public enum TypeEnum {FOR_SALE, FOR_RENT}

    @Column(name = "price")
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
}
