package com.solutions.backend;

import com.solutions.backend.model.Pharmacy;
import com.solutions.backend.repository.PharmacyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.solutions.backend.utils.SanitizeStrings.sanitizeDoubleQuotes;

@Slf4j
@SpringBootApplication
public class BackendApiApplication {

	@Autowired
	private PharmacyRepository pharmacyRepository;

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			log.info("populating database with csv file.");
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/data/pharmacies.csv")))) {
				String record;

				reader.readLine();  // skip header
				while ((record = reader.readLine()) != null) {
					String[] parts = sanitizeDoubleQuotes(record.split(","));
					if (parts.length > 6) {
						pharmacyRepository.save(new Pharmacy(parts[0], parts[1], parts[2], parts[3], parts[4], Double.parseDouble(parts[5]), Double.parseDouble(parts[6])));
					}
				}

			} catch (Exception ex) {
				log.error(ex.getMessage());
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApiApplication.class, args);
	}





}
