package com.worldline.assignment.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResponseDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 356773167937417835L;

    /** The success. */
    public boolean success;

    /** The message. */
    private String message;

    private List<Integer> numbers;




}
