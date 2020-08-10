# Rx Savings Solutions RESTful API Backend

### Created by Evan Thompson on August 9, 2020

This API provides the following endpoints:

| API Name   |      HTTP Method      |  Path | Description | Return Format Example |
|----------|:-------------:|:-------------:|:-------------:|:-------------:|
| GET Closest Pharmacy |  GET | /api/closestPharmacy/{latLongPair} | Returns the closest pharmacy and its distance from passed location formatted as latitude,longitude | "Pharmacy {name='CVS PHARMACY', address='5001 WEST 135 ST', city='LEAWOOD', state='KS', zip='66224'}, {distance='8.3555'}"


Pharmacy locations are populated to the included database from a csv data from `src/main/resources/data/pharmacies.csv`.

### Requires
- Java 11
- Maven 3.6.3

### To Run Backend

`mvn spring-boot:run`