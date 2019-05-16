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
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdditionResult extends CalculationResult {
	private String firstTerm;
	private String secondTerm;
	private String sum;
	
	@Builder
	@JsonCreator
	public AdditionResult(
			@JsonProperty("id") int id,
			@JsonProperty("firstTerm") String first,
			@JsonProperty("secondTerm") String second,
			@JsonProperty("sum") String sum) {
		super(id, "");
		this.firstTerm = first;
		this.secondTerm = second;
		this.sum = sum;
	}
}
