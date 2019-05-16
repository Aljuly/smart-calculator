/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class for storing information about particular user
 *
 * @author Alexander Zhulinsky
 * @version 1.0 26 Jun 2018
 */
@Data
@NoArgsConstructor
public class Operation {
    private int id;
    private String name;
    private String description;
}
