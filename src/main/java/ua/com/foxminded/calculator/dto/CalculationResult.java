/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculationResult {
	private int id;
	private String alert;
	
	public CalculationResult(int id) {
		this.id = id;
	}
	
	public boolean hasAlert() {
		return alert.length() > 0;
	}
}
