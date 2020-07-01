package com.bsucaciu.streams.serde;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
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

    @SerializedName("number_max")
    private Integer numberMax;

    private Integer amount;

    private String time;

    @SerializedName("text_out")
    private String text;

}
