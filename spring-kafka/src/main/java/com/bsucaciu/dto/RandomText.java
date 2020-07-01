package com.bsucaciu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RandomText {

    private String type;

    private String format;

    private Integer number;

    @JsonProperty("number_max")
    private Integer numberMax;

    private Integer amount;

    private String time;

    @JsonProperty("text_out")
    private String text;

}
