package com.capgemini.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dto.UserDto;
import com.capgemini.entity.User;
import com.capgemini.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	
  

	public UserDto findUserById(Integer userId) {
		Optional<User> userOptional = userRepo.findById(userId);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			UserDto userDTO = new UserDto();
			userDTO.setId(user.getId());
			userDTO.setFullName(user.getFullName());
			userDTO.setEmail(user.getEmail());
			userDTO.setMobileNumber(user.getMobileNumber());
			userDTO.setAddress(user.getAddress());
			return userDTO;
			
		} else {
			// Handle scenario when user is not found
			return null;
		}
	}

	public UserDto updateUser(Integer userId, UserDto userDTO) {
		Optional<User> optionalUser = userRepo.findById(userId);
		if (optionalUser.isPresent()) {
			User existingUser = optionalUser.get();
			existingUser.setFullName(userDTO.getFullName());
			existingUser.setEmail(userDTO.getEmail());
			existingUser.setAddress(userDTO.getAddress());
			existingUser.setMobileNumber(userDTO.getMobileNumber());
			userRepo.save(existingUser);
			return userDTO;
		} else {
			// Handle scenario when user to update is not found
			return null;
		}
	}

	public List<UserDto> getAllUsers() {
		List<User> users = userRepo.findAll();
		List<UserDto> userDTOs = new ArrayList<>();

		for (User user : users) {
			UserDto userDTO = new UserDto();
			userDTO.setId(user.getId());
			userDTO.setFullName(user.getFullName());
			userDTO.setEmail(user.getEmail());
			userDTO.setMobileNumber(user.getMobileNumber());
			userDTO.setAddress(user.getAddress());
			userDTO.setPassword(user.getPassword());
			userDTOs.add(userDTO);
		}

		return userDTOs;
	}

	public UserDto findUserByName(String userName) {
		Optional<User> userOptional = userRepo.findByFullName(userName);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			UserDto userDTO = new UserDto();
			userDTO.setId(user.getId());
			userDTO.setFullName(user.getFullName());
			userDTO.setEmail(user.getEmail());
			userDTO.setMobileNumber(user.getMobileNumber());
			userDTO.setAddress(user.getAddress());
			return userDTO;
		} else {
			// Handle scenario when user is not found
			return null;
		}
	}

	

	

	public UserDto registerUser(UserDto userDTO) {
		User user = new User();
		user.setFullName(userDTO.getFullName());
		user.setAddress(userDTO.getAddress());
		user.setEmail(userDTO.getEmail());
		user.setMobileNumber(userDTO.getMobileNumber());
		user.setPassword(userDTO.getPassword());
		user.setId(userDTO.getId());

		userRepo.save(user);
		return userDTO;
	}
	
	
	public Integer getUserForClient(Integer userId) {
		User user = userRepo.findById(userId).get();
		Integer userId1 = user.getId();
		return userId1;
	}
	// create user
	// view user by id
	// view users
	// update user

}
