package proyect.services;

import java.util.List;

import proyect.exceptions.EntityAlreadyExistsException;
import proyect.exceptions.EntityNotFoundException;
import proyect.model.Office;

public interface OfficeService {
	public Office create( Office office );

	public Office read( Long id ) throws EntityNotFoundException, EntityNotFoundException;

	public Office update( Long id , Office office ) throws EntityNotFoundException , EntityAlreadyExistsException;

	public void delete( Long id ) throws EntityNotFoundException;

	public List<Office> getAll();
	
	public List<Office> getAllByCommerceId(Long id) throws EntityNotFoundException;
}
