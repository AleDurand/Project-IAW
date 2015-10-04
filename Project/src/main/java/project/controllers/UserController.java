package project.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.exceptions.messages.DefaultMessage;
import project.models.RealEstateAgentModel;
import project.models.UserModel;
import project.services.UserService;
import project.validators.UserModelValidator;
import project.validators.Validator;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@Api(value = "/users", description = "Manage users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelValidator userValidator;

    @ApiOperation(
            value = "Creates a new user",
            notes = "",
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "User created", response = UserModel.class),
            @ApiResponse(code = 400, message = "Constrains fails", response = DefaultMessage.class)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserModel> create(@RequestBody UserModel user) {
        userValidator.validateForCreate(user);
        UserModel toReturn = userService.create(user);
        return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Gets a user by id",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found", response = UserModel.class),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "User has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserModel> read(@PathVariable String id) {
        int userId = Validator.validateId(id);
        UserModel toReturn = userService.read(userId);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Updates a user by id",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "User updated", response = UserModel.class),
            @ApiResponse(code = 400, message = "Constrains fails or invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "User has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserModel> update(@PathVariable String id, @RequestBody UserModel user) {
        int userId = Validator.validateId(id);
        userValidator.validateForUpdate(user);
        UserModel toReturn = userService.update(userId, user);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Deletes a user by id",
            notes = "",
            code = 204
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "User deleted"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "User has not been found", response = DefaultMessage.class)
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        int userId = Validator.validateId(id);
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
            value = "Gets all users",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users returned", response = UserModel.class, responseContainer = "List"),
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<UserModel>> getAll() {
        List<UserModel> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Gets all real estate agents belongs to a particular user",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the real estate agents associated with the user", response = RealEstateAgentModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "User has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}/real-estate-agents", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<RealEstateAgentModel>> getRealEstateAgents(@PathVariable String id) {
        int userId = Validator.validateId(id);
        List<RealEstateAgentModel> realEstateAgents = userService.getRealEstateAgents(userId);
        return new ResponseEntity<>(realEstateAgents, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Associates a real estate agent with a particular user",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Associates a real estate agent to the user and returns the real estate agents associated", response = RealEstateAgentModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid id or association already exists", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "User or real estate agent has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{userId}/real-estate-agents/{realEstateAgentId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<RealEstateAgentModel>> addRealEstateAgent(@PathVariable String userId, @PathVariable String realEstateAgentId) {
        int userIdValidated = Validator.validateId(userId);
        int realEstateAgentIdValidated = Validator.validateId(realEstateAgentId);
        List<RealEstateAgentModel> realEstateAgents = userService.addRealEstateAgents(userIdValidated, realEstateAgentIdValidated);
        return new ResponseEntity<>(realEstateAgents, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Removes the association between a user and a real estate agent",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deletes an association and returns real estate agents associated with the user", response = RealEstateAgentModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "User or real estate agent or association has not been found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{userId}/real-estate-agents/{realEstateAgentId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<RealEstateAgentModel>> deleteRealEstateAgent(@PathVariable String userId, @PathVariable String realEstateAgentId) {
        int userIdValidated = Validator.validateId(userId);
        int realEstateAgentIdValidated = Validator.validateId(realEstateAgentId);
        List<RealEstateAgentModel> realEstateAgents = userService.deleteRealEstateAgent(userIdValidated, realEstateAgentIdValidated);
        return new ResponseEntity<>(realEstateAgents, HttpStatus.OK);
    }
}
