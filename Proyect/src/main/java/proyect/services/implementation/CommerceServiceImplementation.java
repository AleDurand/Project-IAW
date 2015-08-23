package proyect.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyect.exceptions.EntityAlreadyExistsException;
import proyect.exceptions.EntityNotFoundException;
import proyect.model.Commerce;
import proyect.repositories.CommerceRepository;
import proyect.services.CommerceService;

@Service
public class CommerceServiceImplementation implements CommerceService {
	@Autowired
	private CommerceRepository commerceRepository;

	@Override
	public Commerce create( Commerce commerce ) throws EntityAlreadyExistsException {
		if (commerceRepository.findById(commerce.getId()) != null) throw new EntityAlreadyExistsException();
		return commerceRepository.save(commerce);
	}

	@Override
	public Commerce read( Long id ) throws EntityNotFoundException {
		Commerce toReturn = commerceRepository.findById(id);
		if (toReturn == null) throw new EntityNotFoundException();
		return toReturn;
	}

	@Override
	public Commerce update( Long id , Commerce commerce )
			throws EntityNotFoundException , EntityAlreadyExistsException {
		Commerce toReturn = commerceRepository.findById(id);
		if (toReturn == null) throw new EntityNotFoundException();
		return commerceRepository.save(commerce);
	}

	@Override
	public void delete( Long id ) throws EntityNotFoundException {
		Commerce toDelete = commerceRepository.findById(id);
		if (toDelete == null) throw new EntityNotFoundException();
		commerceRepository.delete(toDelete.getId());
	}

	@Override
	public List<Commerce> getAll() {
		return commerceRepository.findAll();
	}

}
