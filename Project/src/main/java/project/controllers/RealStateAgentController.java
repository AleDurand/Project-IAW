package project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.models.RealStateAgentModel;
import project.services.RealStateAgentService;

@RestController
@RequestMapping(value = "/realStateAgents")
public class RealStateAgentController {

	@Autowired
	private RealStateAgentService realStateAgentService;

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<RealStateAgentModel> create(@RequestBody RealStateAgentModel realStateAgent) {
		RealStateAgentModel toReturn = realStateAgentService.create(realStateAgent);
		return new ResponseEntity<RealStateAgentModel>(toReturn, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RealStateAgentModel> read(@PathVariable Integer id) {
		RealStateAgentModel toReturn = realStateAgentService.read(id);
		return new ResponseEntity<RealStateAgentModel>(toReturn, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<RealStateAgentModel> update(@PathVariable Integer id, @RequestBody RealStateAgentModel realStateAgent) {
		RealStateAgentModel toReturn = realStateAgentService.update(id, realStateAgent);
		return new ResponseEntity<RealStateAgentModel>(toReturn, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		realStateAgentService.delete(id);
		return new ResponseEntity<String>("RealStateAgentModel deleted.", HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<RealStateAgentModel>> getAll() {
		List<RealStateAgentModel> realStateAgents = realStateAgentService.getAll();
		return new ResponseEntity<List<RealStateAgentModel>>(realStateAgents, HttpStatus.OK);
	}
}