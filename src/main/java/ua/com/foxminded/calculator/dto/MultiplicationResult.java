package ua.com.foxminded.calculator.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MultiplicationResult extends CalculationResult {
	
	private String first;
	private String second;
	private String product;
	private List<String> steps = new ArrayList<>();
	
	@Builder
	@JsonCreator
	public MultiplicationResult(
			@JsonProperty("id") int id, 
			@JsonProperty("firstTerm") String first, 
			@JsonProperty("secondTertm") String second, 
			@JsonProperty("product") String product,
			@JsonProperty("calculationSteps") List<String> steps) {
		super(id, "");
		this.second = second;
		this.first = first;
		this.product = product;
		this.steps = steps;
	}
}
