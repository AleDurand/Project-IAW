package project.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.CategoryModel;
import project.repositories.CategoryRepository;
import project.services.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryModel create(CategoryModel category) {
        CategoryModel toReturn = categoryRepository.save(category);
        return toReturn;
    }

    @Override
    public CategoryModel read(Integer id) {
        CategoryModel toReturn = categoryRepository.findById(id);
        return toReturn;
    }

    @Override
    public CategoryModel update(Integer id, CategoryModel category) {
        CategoryModel toReturn = categoryRepository.findById(id);
        toReturn.setName(category.getName());
        toReturn.setDescription(category.getDescription());
        categoryRepository.save(toReturn);
        return toReturn;
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.delete(id);
    }

    @Override
    public List<CategoryModel> getAll() {
        return categoryRepository.findAll();
    }
}
