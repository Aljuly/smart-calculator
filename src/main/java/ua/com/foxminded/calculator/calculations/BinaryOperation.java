
package ua.com.foxminded.calculator.calculations;

import ua.com.foxminded.calculator.dto.CalculationResult;

public interface BinaryOperation {
	CalculationResult calculate(String first, String second);
}
