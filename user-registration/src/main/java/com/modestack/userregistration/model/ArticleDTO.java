package com.modestack.userregistration.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ArticleDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 201343428323626614L;
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String body;
	private String author;
	private String createdBy;
	private String access_Token;
}
