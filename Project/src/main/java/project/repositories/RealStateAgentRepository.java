package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.RealStateAgentModel;

@Repository
public interface RealStateAgentRepository extends JpaRepository<RealStateAgentModel, Integer> {

    RealStateAgentModel findById(Integer id);

    RealStateAgentModel findByName(String name);

}
