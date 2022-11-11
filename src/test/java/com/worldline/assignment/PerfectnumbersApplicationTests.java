package com.worldline.assignment;

import com.worldline.assignment.service.NumberService;
import com.worldline.assignment.service.impl.NumberServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@SpringBootTest
class PerfectNumbersApplicationTests {

	private static NumberService numberService;


	@BeforeAll
	public static void initNumberService() {
		numberService = new NumberServiceImpl();
	}

	@BeforeEach
	public void beforeEachTest() {
		System.out.println("New test case is starting now");
	}

	@AfterEach
	public void afterEachTest() {
		System.out.println("Test case is ended");
	}

	@Test
	public void isTheGivenNumberSixAPerfectNumber() {
		String correlationId = UUID.randomUUID().toString();
		Boolean isPerfect;
		isPerfect = numberService.isPerfectNumber(6,correlationId);
		assertEquals(isPerfect, true);
	}
	@Test
	public void givenNumberSevenIsNotAPerfectNumber() {
		String correlationId = UUID.randomUUID().toString();
		Boolean isPerfect;
		isPerfect = numberService.isPerfectNumber(7, correlationId);
		assertEquals(isPerfect, false);
	}

	@Test
	public void getPerfectNumberSeriesBetweenOneAndThousand() {
		String correlationId = UUID.randomUUID().toString();
		List<Integer> perfectNumberSeries;
		perfectNumberSeries = numberService.getPerfectNumberSeries(1, 1000, correlationId);
		List<Integer> numberSeries = Arrays.asList(6, 28, 496);
		assertIterableEquals(perfectNumberSeries, numberSeries);
	}
}
