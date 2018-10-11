/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.calculations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import ua.com.foxminded.calculator.dto.MultiplicationResult;

/**
 * Class for making multiplication operation
 *
 * @author Alexander Zhulinsky
 * @version 1.0 31 Aug 2018
 */
public class Multiplication extends CommonAdder implements BinaryOperation {

	private static final int MULTIPLICATION_INDEX = 100002;
	
	@Override
	public MultiplicationResult calculate(String first, String second) {
		
		Function<String, List<Character>> chars = string -> (new StringBuilder(string))
				.reverse()
				.toString()
				.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.toList());
		
		Function<Integer, String> assembly = k -> {
			StringBuilder string = new StringBuilder();
	        for (int i = 0; i < k; i++) {
	            string.append('0');
	        }
	        return string.toString();
		};
		
		List<Character> ft = chars.apply(first);
		List<Character> st = chars.apply(second);
		int j = 0; 
		String product = "0";
		List<String> steps = new ArrayList<>();
		for (Character x : st) {
			List<Integer> result = new ArrayList<>();
			int excess = 0;
			for (Character y : ft) {
				int p = Character.getNumericValue(x) * Character.getNumericValue(y) + excess;
				if (p > 9) {
					excess = (p - p % 10) / 10;
					p %= 10;
				} else {
					excess = 0;
				}
				result.add(p);
			}
			if (excess > 0) result.add(excess);
			Collections.reverse(result);
			if (result.stream().mapToInt(Integer::intValue).sum() == 0) {
				steps.add("0");
			} else {
				steps.add(result.stream().map(String::valueOf).collect(Collectors.joining()));
				product = add(product, steps.get(steps.size() - 1).concat(assembly.apply(j)));
			}
			j++;
		}
		
		return MultiplicationResult.builder()
				.id(MULTIPLICATION_INDEX)
				.first(first)
				.second(second)
				.product(product)
				.steps(steps)
				.build();
	}
}
