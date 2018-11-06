
package ua.com.foxminded.calculator.calculations;

import ua.com.foxminded.calculator.dto.CalculationResult;
import ua.com.foxminded.calculator.service.CalculationException;

public interface BinaryOperation {
	CalculationResult calculate(String first, String second);
}
