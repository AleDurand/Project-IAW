package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.model.Office;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {

	public Office findById( Long id );

	public List<Office> findByCommerceId( Long id );
}
