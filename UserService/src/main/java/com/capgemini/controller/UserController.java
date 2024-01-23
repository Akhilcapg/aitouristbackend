package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.dto.UserDto;
import com.capgemini.services.UserService;





@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")	//Frontend Connection
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public UserDto addCustomer(@RequestBody UserDto userDTO) {
		return userService.registerUser(userDTO);
	}

	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(value= "userId") Integer userId) {
		UserDto user = userService.findUserById(userId);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/getbyname/{userName}")
	public ResponseEntity<Integer> getUserByName(@PathVariable String userName) {
	    UserDto user = userService.findUserByName(userName);
	    if (user != null) {
	        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}



	@GetMapping("/all")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId, @RequestBody UserDto userDTO) {
		UserDto updatedUser = userService.updateUser(userId, userDTO);
		if (updatedUser != null) {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getuserforclient/{userId}")
	public Integer getUserForClient (@PathVariable(name = "userId") Integer userId) {
		return userService.getUserForClient(userId);
	}
	
	
}