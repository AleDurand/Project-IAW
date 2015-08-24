package project.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.EntityNotFoundException;
import project.model.Commerce;
import project.repositories.CommerceRepository;
import project.services.CommerceService;

@Service
public class CommerceServiceImplementation implements CommerceService {
	@Autowired
	private CommerceRepository commerceRepository;

	@Override
	public Commerce create( Commerce commerce ) {
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