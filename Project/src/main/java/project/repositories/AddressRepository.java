package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.AddressModel;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Integer> {

    AddressModel findById(Integer id);

}
