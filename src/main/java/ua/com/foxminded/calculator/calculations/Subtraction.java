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

import ua.com.foxminded.calculator.dto.SubtractionResult;

/**
 * Class for making subtraction operation
 *
 * @author Alexander Zhulinsky
 * @version 1.0 31 Aug 2018
 */
public class Subtraction implements BinaryOperation {

	private static final int SUBTRACTION_INDEX = 100001;
	
	private boolean resultIsNegative = false;
	
	/**
	 * Method for comparing two integers which are given in a string format
	 * @param first - first integer
	 * @param second - second integer
	 * @return list of two elements. First element is a Menued, given as a List of Characters.
	 * Second element is a Subtrahent, given as a List of Characters.
	 */
	private List<List<Character>> compare(String first, String second) {
		
		Function<String, List<Character>> chars = string -> (new StringBuilder(string))
				.reverse()
				.toString()
				.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.toList());
		
		if (first.length() > second.length()) {
			this.resultIsNegative = false;
		} else if (first.length() < second.length()) {
			this.resultIsNegative = true;
		} else {
			resultIsNegative = false;
			for (int i = 0; i < first.length(); i++) {
				if (Character.getNumericValue(first.charAt(i)) < Character.getNumericValue(second.charAt(i))) {
					resultIsNegative = true;
					break;
				}
			}
		}
		List<List<Character>> result = new ArrayList<>();
		if (resultIsNegative) {
			result.add(chars.apply(second));
			result.add(chars.apply(first));
		} else {
			result.add(chars.apply(first));
			result.add(chars.apply(second));
		}
		return result;
	}
	
	@Override
	public SubtractionResult calculate(String first, String second) {
		
		List<List<Character>> source = compare(first, second);
		List<Character> ft = source.get(0);
		List<Character> st = source.get(1);
		List<Integer> result = new ArrayList<>();
		byte excess = 0;
		for (int i = 0; i < ft.size(); i++) {
			int x = (i < ft.size()) ? Character.getNumericValue(ft.get(i)) : 0;
			int y = (i < st.size()) ? Character.getNumericValue(st.get(i)) : 0;
			int difference;
			if (x - y - excess >= 0) {
				difference = x - y - excess;
				excess = 0;
			} else {
				difference = x + 10 - y - excess;
				excess = 1;
			}
			result.add(difference);
		}
		// Remove leading zero if exists
		if (result.get(result.size() - 1) == 0) result.remove(result.size() - 1);
		Collections.reverse(result);
		// Return the result
		return SubtractionResult.builder()
				.id(SUBTRACTION_INDEX)
				.menued(first)
				.subtrahent(second)
				.difference(result.stream().map(String::valueOf).collect(Collectors.joining()))
				.negative(resultIsNegative)
				.build();
	}

}
