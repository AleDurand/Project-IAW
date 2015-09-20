package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.PropertyModel;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyModel, Integer> {

    PropertyModel findById(Integer id);

}
