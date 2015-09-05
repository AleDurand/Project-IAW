package project.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.models.RealStateAgentModel;
import project.repositories.RealStateAgentRepository;
import project.services.RealStateAgentService;

@Service
public class RealStateAgentServiceImplementation implements RealStateAgentService {
	@Autowired
	private RealStateAgentRepository realStateAgentRepository;

	@Override
	public RealStateAgentModel create(RealStateAgentModel realStateAgent) {
		RealStateAgentModel toReturn = realStateAgentRepository.save(realStateAgent);
		return toReturn;
	}

	@Override
	public RealStateAgentModel read(Integer id) {
		RealStateAgentModel toReturn = realStateAgentRepository.findById(id);
		return toReturn;
	}

	@Override
	public RealStateAgentModel update(Integer id, RealStateAgentModel realStateAgent) {
		RealStateAgentModel toReturn = realStateAgentRepository.findById(id);

		toReturn.setName(realStateAgent.getName());
		toReturn.setEmail(realStateAgent.getEmail());
		toReturn.setWeb(realStateAgent.getWeb());
		toReturn.setDescription(realStateAgent.getDescription());

		realStateAgentRepository.save(toReturn);
		return toReturn;
	}

	@Override
	public void delete(Integer id) {
		realStateAgentRepository.delete(id);
	}

	@Override
	public List<RealStateAgentModel> getAll() {
		return realStateAgentRepository.findAll();
	}
}
