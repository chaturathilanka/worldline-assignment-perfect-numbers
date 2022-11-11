package com.worldline.assignment.service;

import java.util.List;

public interface NumberService {

    boolean isPerfectNumber(Integer number, String correlationId);
    List<Integer> getPerfectNumberSeries(Integer start, Integer end, String correlationId);

}
