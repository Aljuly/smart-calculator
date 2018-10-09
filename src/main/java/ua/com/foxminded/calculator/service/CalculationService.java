/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.service;

import java.util.HashMap;
import java.util.Map;

import ua.com.foxminded.calculator.calculations.Addition;
import ua.com.foxminded.calculator.calculations.Division;
import ua.com.foxminded.calculator.calculations.Multiplication;
import ua.com.foxminded.calculator.calculations.Subtraction;
import ua.com.foxminded.calculator.dto.CalculationResult;

/**
 * Class that provides chosen by the user calculation
 *
 * @author Alexander Zhulinsky
 * @version 1.0 31 Aug 2018
 */
public class CalculationService {
	
	private Map<Integer, BiFunction<String, String, CalculationResult>> operations = new HashMap<>();
	
	public CalculationService() {
		operations.put(100000, (new Addition())::calculate);
		operations.put(100001, (new Subtraction())::calculate);
		operations.put(100002, (new Multiplication())::calculate);
		operations.put(100003, (new Division(false))::calculate);
		operations.put(100004, (new Division(true))::calculate);
	}
	
	public CalculationResult calculate(int operation, String first, String second) throws CalculationException {
		if(operation < 100000 || operation > 100004) throw new CalculationException("Selected operation undefined!");
		return this.operations.get(operation).apply(first, second);
	}
}
