package ua.com.foxminded.calculator.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SubtractionResult extends CalculationResult {

	private String menued;
	private String subtrahent;
	private String difference;
	private boolean negative;
	
	@Builder
	@JsonCreator
	public SubtractionResult(
			@JsonProperty("id") int id,
			@JsonProperty("menued") String menued,
			@JsonProperty("subtrahent") String subtrahent, 
			@JsonProperty("difference") String difference, 
			@JsonProperty("negative") boolean negative) {
		super(id);
		this.menued = menued;
		this.subtrahent = subtrahent;
		this.difference = difference;
		this.negative = negative;
	}
}
