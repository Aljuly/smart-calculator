/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.calculations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import ua.com.foxminded.calculator.dto.DivisionResult;
import ua.com.foxminded.calculator.dto.Step;

/**
 * Class for proceeding of the calculations chain
 *
 * @author Alexander Zhulinsky
 * @version 1.0 12 Jun 2018
 */
public class Division implements BinaryOperation {

    private static final byte FRACTION_DIGITS_PERIOD_SEARCH_LIMIT = 10;
    private static final int DIVISION_INDEX = 100003;
    private static final int DIVISION_WITH_PERIOD_INDEX = 100004;
    
    private boolean withFractionPart;

    /**
     * The constructor
     * @param withFractionPart - defines whether calculate fraction or not
     */
    public Division(boolean withFractionPart) {
        this.withFractionPart = withFractionPart;
    }

    /**
     * Function for searching for period
     *
     * @param steps - list of steps already make
     * @param difference - difference we need to find
     * @return true when such a difference was already obtained
     */
    private boolean isPeriod(List<Step> steps, int difference) {
        return steps.stream().anyMatch(o -> Integer.parseInt(o.getDifference()) == difference);
    }

    /**
     * Method to proceed Division operation
     * @param dividend - dividend
     * @param divisor - divisor
     * @return DivisionResult object
     */
    public DivisionResult calculate(String first, String second) {
        int firstNumber;
        int secondNumber;
        int difference = 0;
        StringBuilder quotient = new StringBuilder();
        StringBuilder fraction = new StringBuilder();
        StringBuilder number = new StringBuilder();
        //Check whereas supplied data is a numeric
        if (!StringUtils.isNumeric(first) || !StringUtils.isNumeric(second)) {
            return new DivisionResult("Dividend and Divisor should be numeric! ");
        }
        //Check whereas divisor is not zero
        if (Integer.parseInt(second) == 0) {
            return new DivisionResult("Division by zero! ");
        }
        DivisionResult result = new DivisionResult();
        result.setDividend(first);
        result.setDivisor(second);
        result.setAlert("");
        int integerDivisor = Integer.parseInt(second);
        //index of the current digit in dividend
        int i = 0;
        int j = 0;
        //Define integer part
        while (i < first.length()) {
            //carry-over digits
            number.append(first.charAt(i));
            //prevent putting leading zeros
            if (quotient.length() != 0) {
                quotient.append('0');
            }
            //after carrying digits process them
            if (Integer.parseInt(number.toString()) >= integerDivisor) {
                firstNumber = Integer.parseInt(number.toString());
                //Define quotient digit
                while ((j + 1) * integerDivisor <= firstNumber) {
                    j++;
                }
                secondNumber = j * integerDivisor;
                //Calculate difference 1-st and 2-nd numbers
                difference = firstNumber - secondNumber;
                //if last result digit is zero, replace it
                if ((quotient.length() > 0) && (quotient.charAt(quotient.length() - 1) == '0')) {
                    quotient.setCharAt(quotient.length() - 1, (char)(j + '0'));
                } else {
                    quotient.append((char)(j + '0'));
                }
                j = 0;
                //Save results
                Step step = new Step(Integer.toString(firstNumber),
                        Integer.toString(secondNumber),
                        Integer.toString(difference));
                result.getSteps().add(step);
                number = new StringBuilder();
                number.append(difference);
            }
            i++;
        }
        //In case when dividend less then divisor
        if (result.getSteps().isEmpty()) {
            firstNumber = Integer.parseInt(number.toString());
            difference = firstNumber;
            Step calcStep = new Step(Integer.toString(firstNumber),
                    Integer.toString(0),
                    Integer.toString(difference));
            result.getSteps().add(calcStep);
            quotient.append('0');
        }
        //Place last zero to reminder
        if (difference == 0 && number.length() == 0) {
            number.append(difference);
        }
        //and make a 'half-step'
        result.getSteps().add(new Step(number.toString(), "0", Integer.toString(Integer.parseInt(number.toString()))));
        //Output results
        result.setQuotient(quotient.toString());
        result.setReminder(number.toString());
        //Now let's get the fraction part!!!!
        i = 0;
        difference = Integer.parseInt(number.toString());
        //to avoid confuses, work with temporary step list
        List<Step> steps = new ArrayList<>();
        //Start with remainder (first number of the last 'half-step') and extract 'half-step'
        number = new StringBuilder(result.getSteps().remove(result.getSteps().size() - 1).getFirstNumber());
        //Take into account last step
        steps.add(result.getSteps().remove(result.getSteps().size() - 1));
        //flag indicating that period found
        boolean periodFind = false;
        while(withFractionPart && i < FRACTION_DIGITS_PERIOD_SEARCH_LIMIT && difference != 0 && !periodFind) {
            //carry-over digits
            number.append('0');
            fraction.append('0');
            //after carrying digits process them
            if (Integer.parseInt(number.toString()) >= integerDivisor) {
                firstNumber = Integer.parseInt(number.toString());
                //Define quotient digit
                while ((j + 1) * integerDivisor <= firstNumber) {
                    j++;
                }
                secondNumber = j * integerDivisor;
                //Calculate difference 1-st and 2-nd numbers
                difference = firstNumber - secondNumber;
                //Search for period
                periodFind = isPeriod(steps, difference);
                //If last result digit is zero, replace it
                if ((fraction.length() > 0) && (fraction.charAt(fraction.length() - 1) == '0')) {
                    fraction.setCharAt(fraction.length() - 1, (char)(j + '0'));
                } else {
                    fraction.append((char)(j + '0'));
                }
                j = 0;
                //Save results
                Step step = new Step(Integer.toString(firstNumber),
                        Integer.toString(secondNumber),
                        Integer.toString(difference));
                steps.add(step);
                number = new StringBuilder();
                number.append(difference);
            }
            i++;
        }
        result.setFraction(fraction.toString());
        if (withFractionPart) {
            //Update reminder if fraction was calculated
            result.setReminder(Integer.toString(difference));
            steps.add(new Step(Integer.toString(difference), "0", Integer.toString(difference)));
            result.setId(DIVISION_WITH_PERIOD_INDEX);
        } else {
            steps.add(new Step(number.toString(), "0", Integer.toString(Integer.parseInt(number.toString()))));
            result.setId(DIVISION_INDEX);
        }
        //add the steps
        result.getSteps().addAll(steps);
        return result;
    }

}

