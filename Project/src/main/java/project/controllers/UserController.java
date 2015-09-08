package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.models.RealStateAgentModel;
import project.models.UserModel;
import project.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserModel> create(@RequestBody UserModel user) {
        UserModel toReturn = userService.create(user);
        return new ResponseEntity<UserModel>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserModel> read(@PathVariable Integer id) {
        UserModel toReturn = userService.read(id);
        return new ResponseEntity<UserModel>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserModel> update(@PathVariable Integer id, @RequestBody UserModel user) {
        UserModel toReturn = userService.update(id, user);
        return new ResponseEntity<UserModel>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        userService.delete(id);
        return new ResponseEntity<String>("UserModel deleted.", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<UserModel>> getAll() {
        List<UserModel> users = userService.getAll();
        return new ResponseEntity<List<UserModel>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/real-state-agents", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<RealStateAgentModel>> getRealStateAgents(@PathVariable Integer id) {
        List<RealStateAgentModel> realStateAgents = userService.getRealStateAgents(id);
        return new ResponseEntity<List<RealStateAgentModel>>(realStateAgents, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/real-state-agents/{realStateAgentId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<RealStateAgentModel>> addRealStateAgent(@PathVariable Integer userId, @PathVariable Integer realStateAgentId) {
        List<RealStateAgentModel> realStateAgents = userService.addRealStateAgents(userId, realStateAgentId);
        return new ResponseEntity<List<RealStateAgentModel>>(realStateAgents, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/real-state-agents/{realStateAgentId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<RealStateAgentModel>> deleteRealStateAgent(@PathVariable Integer userId, @PathVariable Integer realStateAgentId) {
        List<RealStateAgentModel> realStateAgents = userService.deleteRealSateAgent(userId, realStateAgentId);
        return new ResponseEntity<List<RealStateAgentModel>>(realStateAgents, HttpStatus.OK);
    }
}
