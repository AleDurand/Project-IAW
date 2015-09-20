package project.services;

import project.exceptions.EntityNotFoundException;
import project.exceptions.InvalidIdException;
import project.models.OfficeModel;

import java.util.List;

public interface OfficeService {

    OfficeModel create(OfficeModel office);

    OfficeModel read(Integer id) throws EntityNotFoundException, InvalidIdException;

    OfficeModel update(Integer id, OfficeModel office) throws InvalidIdException;

    void delete(Integer id) throws EntityNotFoundException, InvalidIdException;

    List<OfficeModel> getAll();
}
