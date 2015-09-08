package project.services;

import project.models.OfficeModel;

import java.util.List;

public interface OfficeService {

    public OfficeModel create(OfficeModel office);

    public OfficeModel read(Integer id);

    public OfficeModel update(Integer id, OfficeModel office);

    public void delete(Integer id);

    public List<OfficeModel> getAll();
}
