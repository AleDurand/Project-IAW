package project.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.exceptions.EntityNotFoundException;
import project.models.OfficeModel;
import project.repositories.AddressRepository;
import project.repositories.OfficeRepository;
import project.services.OfficeService;

import java.util.List;

@Service
public class OfficeServiceImplementation implements OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public OfficeModel create(OfficeModel office) {
        office.setAddress(addressRepository.save(office.getAddress()));
        return officeRepository.save(office);
    }

    @Override
    public OfficeModel read(Integer id) {
        OfficeModel toReturn = officeRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("Office", id);
        return toReturn;
    }

    @Override
    public OfficeModel update(Integer id, OfficeModel office) {
        OfficeModel toReturn = officeRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("Office", id);
        if (office.getName() != null) toReturn.setName(office.getName());
        if (office.getPhone() != null) toReturn.setPhone(office.getPhone());
        if (office.getAddress() != null) {
            if (office.getAddress().getCity() != null) toReturn.getAddress().setCity(office.getAddress().getCity());
            if (office.getAddress().getStreet() != null)
                toReturn.getAddress().setStreet(office.getAddress().getStreet());
            if (office.getAddress().getSuite() != null)
                toReturn.getAddress().setSuite(office.getAddress().getSuite());
            if (office.getAddress().getZipCode() != null)
                toReturn.getAddress().setZipCode(office.getAddress().getZipCode());
            if (office.getAddress().getGeoLocation() != null)
                toReturn.getAddress().setGeoLocation(office.getAddress().getGeoLocation());
        }
        return officeRepository.save(toReturn);
    }

    @Override
    public void delete(Integer id) {
        OfficeModel toReturn = officeRepository.findById(id);
        if (toReturn == null)
            throw new EntityNotFoundException("Office", id);
        officeRepository.delete(id);
    }

    @Override
    public List<OfficeModel> getAll() {
        return officeRepository.findAll();
    }
}
