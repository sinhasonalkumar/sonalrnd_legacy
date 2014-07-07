package com.springweb.rnd.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentWebVO {
	
	@JsonProperty(value = "name")
	private String studentName;
	private Integer age;

}
