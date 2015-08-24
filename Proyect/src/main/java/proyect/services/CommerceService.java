package proyect.services;

import java.util.List;

import proyect.exceptions.EntityAlreadyExistsException;
import proyect.exceptions.EntityNotFoundException;
import proyect.model.Commerce;

public interface CommerceService {

	public Commerce create(Commerce commerce);

	public Commerce read(Long id) throws EntityNotFoundException;

	public Commerce update(Long id, Commerce commerce) throws EntityNotFoundException, EntityAlreadyExistsException;

	public void delete(Long id) throws EntityNotFoundException;

	public List<Commerce> getAll();
	
}
