/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ua.com.foxminded.calculator.calculations.Multiplication;
import ua.com.foxminded.calculator.dto.MultiplicationResult;

/**
 * Class for testing Multiplication class
 *
 * @author Alexander Zhulinsky
 * @version 1.0 05 Oct 2018
 */
class MultiplierTest {
	
	@ParameterizedTest
	@MethodSource("provideTestData")
	public void shouldMakeMultiplication(String first, String second, String product, String steps) {
		MultiplicationResult multiplicationResult = (new Multiplication()).calculate(first, second);
		assertNotNull(multiplicationResult);
		assertEquals(product, multiplicationResult.getProduct());
		assertIterableEquals(Arrays.asList(steps.split(" ")), multiplicationResult.getSteps());
	}
	
	static Stream<Arguments> provideTestData() {
		return Stream.of(
				Arguments.of("427", "36", "15372", "2562 1281"),
				Arguments.of("7602", "18273", "138911346", "22806 53214 15204 60816 7602"),
				Arguments.of("0", "0", "0", "0"),
				Arguments.of("207", "505", "104535", "1035 0 1035"),
				Arguments.of("12", "0", "0", "0"), 
				Arguments.of("1", "1", "1", "1"));
	}
}
