package ua.com.foxminded.calculator.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MultiplicationResult extends CalculationResult {
	
	private String first;
	private String second;
	private String product;
	
	@JsonCreator
	public MultiplicationResult(
			@JsonProperty("id") int id, 
			@JsonProperty("firstTerm") String first, 
			@JsonProperty("secondTertm") String second, 
			@JsonProperty("product") String product) {
		super(id);
		this.second = second;
		this.first = first;
		this.product = product;
	}
}
