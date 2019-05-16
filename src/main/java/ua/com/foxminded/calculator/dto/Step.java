/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Class for storing data of the one step of the calculations
 *
 * @author Alexander Zhulinsky
 * @version 1.0 06 Jun 2018
 */

@Data
public class Step {

    private String firstNumber;
    private String secondNumber;
    private String difference;

    @JsonCreator
    public Step(@JsonProperty("firstNumber") String firstNumber,
                @JsonProperty("secondNumber") String secondNumber,
                @JsonProperty("difference") String difference) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.difference = difference;
    }
}
