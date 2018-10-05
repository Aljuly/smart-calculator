/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator;

/**
 * Class for testing Addition class
 *
 * @author Alexander Zhulinsky
 * @version 1.0 05 Oct 2018
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ua.com.foxminded.calculator.calculations.Addition;

class AdderTest {

	@ParameterizedTest
	@MethodSource("provideTestData")
	public void shouldMakeAddition(String first, String second, String sum) {
		AdditionResult additionResult = (new Addition()).add(first, second);
		assertNotNull(additionResult);
		assertEquals(sum, additionResult.getSum());
	}
	
	static Stream<Arguments> provideTestData() {
		return Stream.of(
				Arguments.of("6748", "381", "7129"),
				Arguments.of("7602", "18273", "25875"),
				Arguments.of("0", "0", "0"));
	}

}
