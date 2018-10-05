/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ua.com.foxminded.calculator.calculations.Division;
import ua.com.foxminded.calculator.dto.DivisionResult;
import ua.com.foxminded.calculator.dto.Step;

/**
 * Class for testing Division class
 *
 * @author Alexander Zhulinsky
 * @version 1.0 06 Jun 2018
 */
public class DividerTest {

    /**
     * This method tests division process correctness
     *
     * @param dividend Integer dividend
     * @param divisor Integer divisor
     * @param quotient Integer part of the division
     * @param reminder Division remainder
     * @param pairs List of Calculation steps
     *
     */
    @ParameterizedTest
    @MethodSource("provideTestData")
    void shouldMakeIntegerDivision(String dividend, String divisor, String quotient, String reminder, String pairs) {
        DivisionResult divisionResult = (new Division(false)).divide(dividend, divisor);
        assertNotNull(divisionResult);
        assertEquals(quotient, divisionResult.getQuotient());
        assertEquals(reminder, divisionResult.getReminder());
        assertIterableEquals(steps(pairs), divisionResult.getSteps());
    }

    /**
     * This method tests division with fraction part correctness
     *
     * @param dividend Integer dividend
     * @param divisor Integer divisor
     * @param quotient Integer part of the division
     * @param fraction Fraction part
     * @param reminder Division remainder
     * @param pairs List of Calculation steps
     *
     */
    @ParameterizedTest
    @MethodSource("provideTestDataForDivisionWithPeriod")
    void shouldMakeDivisionWithPeriod(String dividend, String divisor, String quotient,
            String fraction, String reminder, String pairs) {
        DivisionResult divisionResult = (new Division(true)).divide(dividend, divisor);
        assertNotNull(divisionResult);
        assertEquals(quotient, divisionResult.getQuotient());
        assertEquals(fraction, divisionResult.getFraction());
        assertIterableEquals(steps(pairs), divisionResult.getSteps());
    }

    /**
     * Utility method for conversion of the calculation steps to List
     * @param pairs Whitespace separated pairs of numbers, which represents calculation steps
     * @return ArrayList of steps
     */
    private List<Step> steps(String pairs) {
        List<Step> stepList = new ArrayList<>();
        Function<String[], Step> makeStep = pair -> new Step(pair[0], pair[1], 
                Integer.toString(Integer.parseInt(pair[0]) - Integer.parseInt(pair[1])));
        for(String pair : pairs.split(" ")) {
            stepList.add(makeStep.apply(pair.split(",")));
        }
        return stepList;
    }

    /**
     * Method for supply test data
     * @return Stream of test data
     */
    static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of("630440", "610", "1033", "310", "630,610 2044,1830 2140,1830 310,0"),
                Arguments.of("78454", "4", "19613", "2", "7,4 38,36 24,24 5,4 14,12 2,0"),
                Arguments.of("12341234", "1234", "10001", "0", "1234,1234 1234,1234 0,0"),
                Arguments.of("593593593", "593", "1001001", "0", "593,593 593,593 593,593 0,0"),
                Arguments.of("12351234", "1234", "10009", "128", "1235,1234 11234,11106 128,0"),
                Arguments.of("12", "123", "0", "12", "12,0 12,0"),
                Arguments.of("12341233", "1234", "10000", "01233", "1234,1234 01233,0"),
                Arguments.of("12353574", "1234", "10011", "0", "1235,1234 1357,1234 1234,1234 0,0"),
                Arguments.of("12350000", "1234", "10008", "128", "1235,1234 10000,9872 128,0"),
                Arguments.of("12341234", "1", "12341234", "0", "1,1 2,2 3,3 4,4 1,1 2,2 3,3 4,4 0,0"),
                Arguments.of("12340035", "1234", "10000", "00035", "1234,1234 00035,0"),
                Arguments.of("345", "8", "43", "1", "34,32 25,24 1,0"),
                Arguments.of("10000", "2", "5000", "0000", "10,10 0000,0"),
                Arguments.of("10000", "20", "500", "000", "100,100 000,0"),
                Arguments.of("6546532", "113", "57933", "103", "654,565 896,791 1055,1017 383,339 442,339 103,0"),
                Arguments.of("596437859643785964378", "5964378", "100000010000001", "0",
                        "5964378,5964378 5964378,5964378 5964378,5964378 0,0")
        );
    }

    /**
     * Method for supply test data for division with fraction part
     * @return Stream of test data
     */
    static Stream<Arguments> provideTestDataForDivisionWithPeriod() {
        return Stream.of(
                Arguments.of("630440", "610", "1033", "5081967213", "70", "630,610 2044,1830 2140,1830 3100,3050 " +
                        "5000,4880 1200,610 5900,5490 4100,3660 4400,4270 1300,1220 800,610 1900,1830 70,0"),
                Arguments.of("78459", "4", "19614", "75", "0", "7,4 38,36 24,24 5,4 19,16 30,28 20,20 0,0"),
                Arguments.of("1000", "3", "333", "3", "1", "10,9 10,9 10,9 10,9 1,0"),
                Arguments.of("12", "123", "0", "09756", "12", "12,0 1200,1107 930,861 690,615 750,738 12,0"),
                Arguments.of("12341233", "1234", "10000", "9991896272", "352", "1234,1234 12330,11106 12240,11106 " +
                        "11340,11106 2340,1234 11060,9872 11880,11106 7740,7404 3360,2468 8920,8638 2820,2468 352,0")
        );
    }
}

