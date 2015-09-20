package project.validators;

import org.springframework.stereotype.Component;
import project.exceptions.InvalidEntityConstraintsException;
import project.models.CategoryModel;

@Component
public class CategoryModelValidator {

    public void validateForCreate(CategoryModel category) throws InvalidEntityConstraintsException {
        if (category.getName() == null || category.getName().isEmpty())
            throw new InvalidEntityConstraintsException("Category", "Name is null or empty.");
    }

    public void validateForUpdate(CategoryModel category) throws InvalidEntityConstraintsException {
        if (category.getName() != null && category.getName().isEmpty())
            throw new InvalidEntityConstraintsException("Category", "Name is null or empty.");
    }
}
