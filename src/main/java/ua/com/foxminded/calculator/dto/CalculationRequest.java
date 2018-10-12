/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
public class CalculationRequest {

	private int id;
	private String first;
	private String second;
	
	@Builder
	@JsonCreator
	public CalculationRequest(
			@JsonProperty("id") int id, 
			@JsonProperty("firstNumber") String first,
			@JsonProperty("secondNumber") String second) {
		this.id = id;
		this.second = second;
		this.first = first;
	}
}
