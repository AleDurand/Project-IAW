package project.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.models.OfficeModel;
import project.repositories.OfficeRepository;
import project.services.OfficeService;

@Service
public class OfficeServiceImplementation implements OfficeService{
	@Autowired
	private OfficeRepository officeRepository;

	@Override
	public OfficeModel create(OfficeModel office) {
		OfficeModel toReturn = officeRepository.save(office);
		return toReturn;
	}

	@Override
	public OfficeModel read(Integer id) {
		OfficeModel toReturn = officeRepository.findById(id);
		return toReturn;
	}

	@Override
	public OfficeModel update(Integer id, OfficeModel office) {
		OfficeModel toReturn = officeRepository.findById(id);
		toReturn.setName(office.getName());
		toReturn.setPhone(office.getPhone());
		toReturn.setRealStateAgent(office.getRealStateAgent());

		officeRepository.save(toReturn);
		return toReturn;
	}

	@Override
	public void delete(Integer id) {
		officeRepository.delete(id);
	}

	@Override
	public List<OfficeModel> getAll() {
		return officeRepository.findAll();
	}
}
