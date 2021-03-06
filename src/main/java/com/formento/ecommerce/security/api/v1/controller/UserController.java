package com.formento.ecommerce.security.api.v1.controller;

import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.user.model.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/users")
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Api(value = "API of user", description = "User", basePath = "/api/v1/users", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Load logged user", notes = "Return logged user", response = User.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<User>> getUser() {
        Optional<User> loadedUser = userService.loadUser();
        if (loadedUser.isPresent()) {
            return new ResponseEntity<>(new Resource<>(loadedUser.get(), linkTo(UserController.class).withSelfRel()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Create an user", notes = "Create and return an user", response = User.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<User>> create(@RequestBody User user) {
        return new ResponseEntity<>(new Resource<>(userService.create(user), linkTo(UserController.class).withSelfRel()), HttpStatus.CREATED);
    }

}
