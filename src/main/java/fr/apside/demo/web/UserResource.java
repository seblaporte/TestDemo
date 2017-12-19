package fr.apside.demo.web;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.apside.demo.domain.User;
import fr.apside.demo.exception.UserAlreadyExistsException;
import fr.apside.demo.mapper.UserMapper;
import fr.apside.demo.repository.UserRepository;
import fr.apside.demo.service.UserService;
import fr.apside.demo.web.dto.UserDto;

@RestController
@RequestMapping("/user")
public class UserResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public UserDto getUserById(@PathVariable Integer userId) {

		User user = userRepository.findOne(userId);

		return UserMapper.instance.userToUserDto(user);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws UserAlreadyExistsException {

		User createdUser = userService.createUserIfNotExists(userDto);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
