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
import ua.com.foxminded.calculator.dto.CalculationRequest;
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
		assertTrue(calculationService.calculate(
				CalculationRequest.builder().id(100000).first("1").second("1").build()) instanceof AdditionResult);
		assertTrue(calculationService.calculate(
				CalculationRequest.builder().id(100001).first("1").second("1").build()) instanceof SubtractionResult);
		assertTrue(calculationService.calculate(
				CalculationRequest.builder().id(100002).first("1").second("1").build()) instanceof MultiplicationResult);
		assertTrue(calculationService.calculate(
				CalculationRequest.builder().id(100003).first("1").second("1").build()) instanceof DivisionResult);
		assertTrue(calculationService.calculate(
				CalculationRequest.builder().id(100004).first("1").second("1").build()) instanceof DivisionResult);
	}
	
	@Test
	public void shouldThrowExceptionIfWrongOperationCodeIsGiven() {
		assertThrows(CalculationException.class, () -> calculationService.calculate(
				CalculationRequest.builder().id(1).first("1").second("1").build()));
		assertThrows(CalculationException.class, () -> calculationService.calculate(
				CalculationRequest.builder().id(100005).first("1").second("1").build()));
	}
	
	@AfterAll
	public static void tearDown( ) {
		calculationService = null;
	}
}
