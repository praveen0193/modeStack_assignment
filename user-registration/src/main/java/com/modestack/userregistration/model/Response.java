package com.modestack.userregistration.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Response {

	public String errorCode;
	public String errorMesage;
	public List<ArticleDTO> article;

}
