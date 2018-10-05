/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ua.com.foxminded.calculator.calculations.Addition;

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
		SubtractionResult subtractionResult = (new Subtraction()).subtract(first, second);
		assertNotNull(subtractionResult);
		assertEquals(difference, subtractionResult.getDifference());
	}
	
	static Stream<Arguments> provideTestData() {
		return Stream.of(
				Arguments.of("6748", "381", "7129"),
				Arguments.of("7602", "18273", "25875"),
				Arguments.of("0", "0", "0"));
	}

}
