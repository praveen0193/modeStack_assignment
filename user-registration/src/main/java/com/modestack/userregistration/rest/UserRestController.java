package com.modestack.userregistration.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.modestack.userregistration.entity.User;
import com.modestack.userregistration.model.Response;
import com.modestack.userregistration.model.UserDTO;
import com.modestack.userregistration.repository.UserRepository;

@RestController

public class UserRestController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AccessTokenUtil accessTokenUtil;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Response createUser(@RequestBody UserDTO userDTO) {
		User user = new User();
		Response response = new Response();
		try {
			user.setAddress(userDTO.getAddress());
			user.setName(userDTO.getUserName());
			user.setEmail(userDTO.getEmail());
			user.setPassword(userDTO.getPassword());
			String accessToken = accessTokenUtil.getAccessToken(userDTO.getPassword(), userDTO.getEmail());
			user.setAccessToken(accessToken);
			userRepository.save(user);
		} catch (Exception e) {
			System.out.println("Exception occurred:" + e);
		}

		response.setErrorCode("00");
		response.setErrorMesage("SUCCESS");
		return response;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Response login(@RequestBody UserDTO userDTO) {
		User user = null;
		Response response = new Response();
		try {

			user = userRepository.findByName(userDTO.getUserName());
			if (user == null) {
				response.setErrorCode("01");
				response.setErrorMesage("No User Found With This UserName");
			} else if (!user.getPassword().equals(userDTO.getPassword())) {
				response.setErrorCode("01");
				response.setErrorMesage("Invaid Password");
			} else {

				response.setErrorCode("00");
				response.setErrorMesage("SUCCESS");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred:" + e);
		}

		return response;

	}

}
