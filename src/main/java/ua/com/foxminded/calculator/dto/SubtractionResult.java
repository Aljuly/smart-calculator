package ua.com.foxminded.calculator.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SubtractionResult extends CalculationResult {

	private String menued;
	private String subtrahent;
	private String difference;
	
	@JsonCreator
	public SubtractionResult(
			@JsonProperty("id") int id,
			@JsonProperty("menued") String menued,
			@JsonProperty("subtrahent") String subtrahent, 
			@JsonProperty("difference") String difference) {
		super(id);
		this.menued = menued;
		this.subtrahent = subtrahent;
		this.difference = difference;
	}
}
