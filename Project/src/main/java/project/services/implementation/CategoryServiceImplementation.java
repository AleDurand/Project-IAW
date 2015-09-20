package project.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.EntityNotFoundException;
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
        if (categoryRepository.findByName(category.getName()) != null)
            throw new EntityAlreadyExistsException("Category", "name", category.getName());
        return categoryRepository.save(category);
    }

    @Override
    public CategoryModel read(Integer id) {
        CategoryModel toReturn = categoryRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("Category", id);
        return toReturn;
    }

    @Override
    public CategoryModel update(Integer id, CategoryModel category) {
        CategoryModel toReturn = categoryRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("Category", id);
        if (categoryRepository.findByName(category.getName()) != null)
            throw new EntityAlreadyExistsException("Category", "name", category.getName());
        if (category.getName() != null) toReturn.setName(category.getName());
        if (category.getDescription() != null) toReturn.setDescription(category.getDescription());
        return categoryRepository.save(toReturn);
    }

    @Override
    public void delete(Integer id) {
        CategoryModel toReturn = categoryRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("Category", id);
        categoryRepository.delete(id);
    }

    @Override
    public List<CategoryModel> getAll() {
        return categoryRepository.findAll();
    }
}
