package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.models.OfficeModel;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeModel, Integer> {
	public OfficeModel findById(Integer id);
}
