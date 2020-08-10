package com.solutions.backend;

import com.solutions.backend.model.Pharmacy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;

import static com.solutions.backend.utils.SanitizeStrings.sanitizeDoubleQuotes;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BackendApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testPharmacyParseAndMethods() {
		String csvString = "\"HUNTERS RIDGE PHARMACY\",\"3405 NW HUNTERS RIDGE TER STE 200\",\"TOPEKA\",\"KS\",66618,39.12984500,-95.71265400";
		String[] parts = sanitizeDoubleQuotes(csvString.split(","));

		assertEquals(parts[0], "HUNTERS RIDGE PHARMACY");
		assertEquals(parts[1], "3405 NW HUNTERS RIDGE TER STE 200");
		assertEquals(parts[2], "TOPEKA");
		assertEquals(parts[3], "KS");
		assertEquals(parts[4], "66618");
		assertEquals(parts[5], "39.12984500");
		assertEquals(parts[6], "-95.71265400");

		Pharmacy pharmacy = new Pharmacy(parts[0], parts[1], parts[2], parts[3], parts[4], Double.parseDouble(parts[5]), Double.parseDouble(parts[6]));

		// Nelson-Atkins Museum 4525 Oak St, Kansas City, MO 64111
		double lat = 39.045168;
		double lon = -94.580913;

		double distance = Pharmacy.distance(pharmacy.getLatitude(), lat, pharmacy.getLongitude(), lon);

		DecimalFormat df = new DecimalFormat(".####");
		String formattedDistanceString = df.format(distance);

		assertEquals("60.9723", formattedDistanceString);

	}

	@Test
	void testExampleDistance() {
		double distance = Pharmacy.distance(38.877897, 51.405577,  -94.800384, -0.282272);

		DecimalFormat df = new DecimalFormat(".####");
		String formattedDistanceString = df.format(distance);

		assertEquals("4360.1106", formattedDistanceString);
	}

}
