package com.hackbulgaria.gas_stations;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class GPSTest {

	@Test
	public void test() {
		List<Integer> actual = GPS.getGasStations(320, 90, Arrays.asList(50,80,140,180,220,290));
		List<Integer> expected = Arrays.asList(80, 140, 220, 290);
		assertThat(actual, is(expected));
	}

}
