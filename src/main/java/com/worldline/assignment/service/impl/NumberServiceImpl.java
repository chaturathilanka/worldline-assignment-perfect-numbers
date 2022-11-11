package com.worldline.assignment.service.impl;

import com.worldline.assignment.service.NumberService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NumberServiceImpl implements NumberService {

    @Override
    public boolean isPerfectNumber(Integer number,String correlationId) {
        var total = 0;
        for (var i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                total += i;
            }
        }
        if (total == number) {
            return true;
        } else
            return false;
    }

    @Override
    public List<Integer> getPerfectNumberSeries(Integer start, Integer end, String correlationId) {

        List<Integer> perfectNumberSeries = new ArrayList<>();

        for (var i = start; i < end; i++) {
            if (isPerfectNumber(i, correlationId)) {
                perfectNumberSeries.add(i);
            }
        }
        return perfectNumberSeries;
    }
}
