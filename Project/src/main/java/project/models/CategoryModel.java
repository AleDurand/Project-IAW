package project.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "category")
@ApiModel(value = "Category", description = "Category representation")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @ApiModelProperty(name = "Id", value = "Category id", required = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    @ApiModelProperty(name = "Name", value = "Category name", required = true)
    private String name;

    @Column(name = "description")
    @ApiModelProperty(name = "Description", value = "Category description", required = false)
    private String description;

    public CategoryModel() {

    }

    public CategoryModel(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
