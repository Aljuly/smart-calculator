/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representing info about selected calculation mode
 *
 * @author Alexander Zhulinsky
 * @version 1.0 27 Aug 2018
 */
@Data
public class DescriptionResponse {
    private String name;
    private String description;

    @JsonCreator
    public DescriptionResponse(
            @JsonProperty("id") int id,
            @JsonProperty("title") String name,
            @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }
}
