/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.calculations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ua.com.foxminded.calculator.dto.AdditionResult;

/**
 * Class for making addition operation
 *
 * @author Alexander Zhulinsky
 * @version 1.0 31 Aug 2018
 */
public class Addition implements BinaryOperation {

	private static final int ADDITION_INDEX = 100000; 
	
	@Override
	public AdditionResult calculate(String first, String second) {
		// Let's do this without using BigInteger!
		char[] ft = (new StringBuilder(first)).reverse().toString().toCharArray();
		char[] st = (new StringBuilder(second)).reverse().toString().toCharArray();
		int[] result = new int[Math.max(ft.length, st.length) + 1];
		byte excess = 0;
		for(int i = 0; i < result.length; i++) {
			int x = (i < ft.length) ? Character.getNumericValue(ft[i]) : 0;
			int y = (i < st.length) ? Character.getNumericValue(st[i]) : 0;
			int sum = x + y + excess;
			if (sum > 9) {
                excess = 1;
                sum %= 10;
            } else {
                excess = 0;
            }
			result[i] = sum;
		}
		// Prepare result to output
		List<String> output = new ArrayList<>();
		// Reverse it and check whereas first character is't zero
		if(result[result.length - 1] > 0) output.add(String.valueOf(result[result.length - 1]));
		for(int i = result.length - 2; i >= 0 ; i--) {
			output.add(String.valueOf(result[i]));
		}
		// Return the result
		return AdditionResult.builder()
				.id(ADDITION_INDEX)
				.first(first)
				.second(second)
				.sum(output.stream().collect(Collectors.joining()))
				.build();
	}
	
}
