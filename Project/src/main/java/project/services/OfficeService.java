package project.services;

import java.util.List;

import project.models.OfficeModel;

public interface OfficeService {

	public OfficeModel create(OfficeModel office);

	public OfficeModel read(Integer id);

	public OfficeModel update(Integer id, OfficeModel office);

	public void delete(Integer id);

	public List<OfficeModel> getAll();
}
