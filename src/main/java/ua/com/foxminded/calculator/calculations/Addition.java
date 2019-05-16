/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.calculations;

import ua.com.foxminded.calculator.dto.AdditionResult;

/**
 * Class for making addition operation
 *
 * @author Alexander Zhulinsky
 * @version 1.0 31 Aug 2018
 */
public class Addition extends CommonAdder implements BinaryOperation {

	private static final int ADDITION_INDEX = 100000; 
	
	@Override
	public AdditionResult calculate(String first, String second) {
		// Return the result
		return AdditionResult.builder()
				.id(ADDITION_INDEX)
				.first(first)
				.second(second)
				.sum(add(first, second))
				.build();
	}
	
}
