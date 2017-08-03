/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general;

import backEnd.general.cars.Car;
import backEnd.general.cars.CarRepository;
import backEnd.general.cars.CarsForTesting;
import backEnd.general.countries.CountriesForTesting;
import backEnd.general.countries.Country;
import backEnd.general.countries.CountryRepository;
import backEnd.general.dealers.Dealer;
import backEnd.general.dealers.DealerRepository;
import backEnd.general.dealers.DealersForTesting;
import backEnd.general.owners.Owner;
import backEnd.general.owners.OwnerRepository;
import backEnd.general.owners.OwnersForTesting;
import backEnd.general.regions.Region;
import backEnd.general.regions.RegionRepository;
import backEnd.general.regions.RegionsForTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;

/**
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class GTSportDataTesting extends AbstractTestNGSpringContextTests {

    protected static final Owner OWNER1 = OwnersForTesting.owner1;
    protected static final Owner OWNER2 = OwnersForTesting.owner2;
    protected static final Owner OWNER3 = OwnersForTesting.owner3;

    protected static final Region REGION1 = RegionsForTesting.region1;
    protected static final Region REGION2 = RegionsForTesting.region2;
    protected static final Region REGION3 = RegionsForTesting.region3;

    protected static final Country COUNTRY1 = CountriesForTesting.country1;
    protected static final Country COUNTRY2 = CountriesForTesting.country2;
    protected static final Country COUNTRY3 = CountriesForTesting.country3;
    
    protected static final Dealer DEALER1 = DealersForTesting.dealer1;
    protected static final Dealer DEALER2 = DealersForTesting.dealer2;
    protected static final Dealer DEALER3 = DealersForTesting.dealer3;

    protected static final Car CAR1 = CarsForTesting.car1;
    protected static final Car CAR2 = CarsForTesting.car2;
    protected static final Car CAR3 = CarsForTesting.car3;
    protected static final Car CAR4 = CarsForTesting.car4;
    protected static final Car CAR5 = CarsForTesting.car5;
    protected static final Car CAR6 = CarsForTesting.car6;
    protected static final Car CAR7 = CarsForTesting.car7;
    protected static final Car CAR8 = CarsForTesting.car8;
    protected static final Car CAR9 = CarsForTesting.car9;

    @Autowired
    protected OwnerRepository ownerRepository;
    
    @Autowired
    protected RegionRepository regionRepository;

    @Autowired
    protected CountryRepository countryRepository;

    @Autowired
    protected DealerRepository dealerRepository;

    @Autowired
    protected CarRepository carRepository;

    protected void deleteCarTestRecord(String deleteKey) {
        Car car = carRepository.findOne(deleteKey);

        if (car != null) {
            carRepository.delete(car);
        }
    }

    protected void deleteCountryTestRecord(String deleteKey) {
        Country country = countryRepository.findOne(deleteKey);

        if (country != null) {
            countryRepository.delete(country);
        }
    }

    protected void deleteDealerTestRecord(String deleteKey) {
        Dealer dealer = dealerRepository.findOne(deleteKey);

        if (dealer != null) {
            dealerRepository.delete(dealer);
        }
    }

    protected void deleteOwnerTestRecord(String deleteKey) {
        Owner owner = ownerRepository.findOne(deleteKey);

        if (owner != null) {
            ownerRepository.delete(owner);
        }
    }

    protected void deleteRegionTestRecord(String deleteKey) {
        Region region = regionRepository.findOne(deleteKey);

        if (region != null) {
            regionRepository.delete(region);
        }
    }
}
