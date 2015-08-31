package project.services;


import project.models.CategoryModel;

import java.util.List;

public interface CategoryService {

    public CategoryModel create(CategoryModel category);

    public CategoryModel read(Integer id);

    public CategoryModel update(Integer id, CategoryModel category);

    public void delete(Integer id);

    public List<CategoryModel> getAll();

}
