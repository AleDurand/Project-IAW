package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.PropertyModel;

/**
 * Created by Ale on 30/08/2015.
 */
@Repository
public interface PropertyRepository extends JpaRepository<PropertyModel, Integer> {

    public PropertyModel findById(Integer id);

}
