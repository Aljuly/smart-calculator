/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for storing results of the calculations
 *
 * @author Alexander Zhulinsky
 * @version 1.0 06 Jun 2018
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DivisionResult extends CalculationResult {
    private String dividend;
    private String divisor;
    private String quotient;
    private String reminder;
    private String fraction;

    private List<Step> steps = new ArrayList<>();

    @JsonCreator
    public DivisionResult(
    		@JsonProperty("id") int id,
    		@JsonProperty("dividend") String dividend,
            @JsonProperty("divisor") String divisor,
            @JsonProperty("quotient") String quotient,
            @JsonProperty("reminder") String reminder,
            @JsonProperty("alert") String alert,
            @JsonProperty("fraction") String fraction,
            @JsonProperty("calculationSteps") List<Step> steps) {
    	super(id, alert);
        this.dividend = dividend;
        this.divisor = divisor;
        this.quotient = quotient;
        this.reminder = reminder;
        this.fraction = fraction;
        this.steps = steps;
    }

    public DivisionResult(String alert) {
    	super(0, alert);
    }
    
    public DivisionResult() {
    	super(0);
    }
}