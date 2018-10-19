/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.calculator.dto.AdditionResult;
import ua.com.foxminded.calculator.dto.DivisionResult;
import ua.com.foxminded.calculator.dto.MultiplicationResult;
import ua.com.foxminded.calculator.dto.SubtractionResult;
import ua.com.foxminded.calculator.service.CalculationException;
import ua.com.foxminded.calculator.service.CalculationService;

/**
 * Class for testing CalculationService class
 *
 * @author Alexander Zhulinsky
 * @version 1.0 09 October 2018
 */
class CalculationServiceTest {

	private static CalculationService calculationService;

	@BeforeAll
	public static void setUp() {
		calculationService = new CalculationService();	
	}
	
	@Test
	public void shouldReturnSelectedResult() throws CalculationException {
		assertTrue(calculationService.calculate(100000, "1", "1") instanceof AdditionResult);
		assertTrue(calculationService.calculate(100001, "1", "1") instanceof SubtractionResult);
		assertTrue(calculationService.calculate(100002, "1", "1") instanceof MultiplicationResult);
		assertTrue(calculationService.calculate(100003, "1", "1") instanceof DivisionResult);
		assertTrue(calculationService.calculate(100004, "1", "1") instanceof DivisionResult);
	}
	
	@Test
	public void shouldThrowExceptionIfWrongOperationCodeIsGiven() {
		assertThrows(CalculationException.class, () -> calculationService.calculate(10, "1", "1"));
		assertThrows(CalculationException.class, () -> calculationService.calculate(100005, "1", "1"));
	}
	
	@AfterAll
	public static void tearDown( ) {
		calculationService = null;
	}
}
