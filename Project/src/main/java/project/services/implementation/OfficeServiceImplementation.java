package project.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.EntityNotFoundException;
import project.model.Office;
import project.repositories.CommerceRepository;
import project.repositories.OfficeRepository;
import project.services.OfficeService;

@Service
public class OfficeServiceImplementation implements OfficeService {

	@Autowired
	private OfficeRepository officeRepository;

	@Autowired
	private CommerceRepository commerceRepository;

	@Override
	public Office create( Office office ){
		return officeRepository.save(office);
	}

	@Override
	public Office read( Long id ) throws EntityNotFoundException {
		return null;
	}

	@Override
	public Office update( Long id , Office office ) throws EntityNotFoundException , EntityAlreadyExistsException {
		return null;
	}

	@Override
	public void delete( Long id ) throws EntityNotFoundException {

	}

	@Override
	public List<Office> getAll() {
		return officeRepository.findAll();
	}

	@Override
	public List<Office> getAllByCommerceId( Long id ) throws EntityNotFoundException {
		if (commerceRepository.findById(id) == null) throw new EntityNotFoundException();
		return officeRepository.findByCommerceId(id);
	}

}
