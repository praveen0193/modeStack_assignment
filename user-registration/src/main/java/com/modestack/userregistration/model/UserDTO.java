package com.modestack.userregistration.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class UserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -746482388307130870L;
	private Long userId;
	private String userName;
	private String password;
	private String email;
	private String address;

}
