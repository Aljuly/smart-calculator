/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.dto;

import lombok.Data;

@Data
public abstract class CalculationResult {
	private int id;
	
	public CalculationResult(int id) {
		this.id = id;
	}
}
