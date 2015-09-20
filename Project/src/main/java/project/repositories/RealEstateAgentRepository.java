package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.RealEstateAgentModel;

@Repository
public interface RealEstateAgentRepository extends JpaRepository<RealEstateAgentModel, Integer> {

    RealEstateAgentModel findById(Integer id);

    RealEstateAgentModel findByName(String name);

}
