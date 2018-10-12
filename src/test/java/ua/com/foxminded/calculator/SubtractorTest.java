/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ua.com.foxminded.calculator.calculations.Subtraction;
import ua.com.foxminded.calculator.dto.SubtractionResult;

/**
 * Class for testing Substraction class
 *
 * @author Alexander Zhulinsky
 * @version 1.0 05 Oct 2018
 */
class SubtractorTest {

	@ParameterizedTest
	@MethodSource("provideTestData")
	public void shouldMakeSubtraction(String first, String second, String difference) {
		SubtractionResult subtractionResult = (new Subtraction()).calculate(first, second);
		assertNotNull(subtractionResult);
		assertEquals(difference, subtractionResult.getDifference());
	}
	
	static Stream<Arguments> provideTestData() {
		return Stream.of(
				Arguments.of("1111", "999", "112"),
				Arguments.of("6748", "381", "6367"),
				Arguments.of("7602", "18273", "10671"),
				Arguments.of("0", "0", "0"));
	}

}
