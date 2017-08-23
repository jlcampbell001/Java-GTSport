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
import backEnd.general.ownerCars.OwnerCar;
import backEnd.general.ownerCars.OwnerCarRepository;
import backEnd.general.ownerCars.OwnerCarsForTesting;
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

/**
 * Basic testing class for database testing.
 *
 * @author jonathan
 */
@ContextConfiguration(classes = GTSportConfig.class)
@Rollback
public class GTSportDataTesting extends AbstractTestNGSpringContextTests {

    /**
     * Testing data for an owner.
     */
    protected static final Owner OWNER1 = OwnersForTesting.OWNER1;

    /**
     * Testing data for an owner.
     */
    protected static final Owner OWNER2 = OwnersForTesting.OWNER2;

    /**
     * Testing data for an owner.
     */
    protected static final Owner OWNER3 = OwnersForTesting.OWNER3;

    /**
     * Testing data for a region.
     */
    protected static final Region REGION1 = RegionsForTesting.REGION1;

    /**
     * Testing data for a region.
     */
    protected static final Region REGION2 = RegionsForTesting.REGION2;

    /**
     * Testing data for a region.
     */
    protected static final Region REGION3 = RegionsForTesting.REGION3;

    /**
     * Testing data for a country.
     */
    protected static final Country COUNTRY1 = CountriesForTesting.COUNTRY1;

    /**
     * Testing data for a country.
     */
    protected static final Country COUNTRY2 = CountriesForTesting.COUNTRY2;

    /**
     * Testing data for a country.
     */
    protected static final Country COUNTRY3 = CountriesForTesting.COUNTRY3;

    /**
     * Testing data for a dealer.
     */
    protected static final Dealer DEALER1 = DealersForTesting.DEALER1;

    /**
     * Testing data for a dealer.
     */
    protected static final Dealer DEALER2 = DealersForTesting.DEALER2;

    /**
     * testing data for a dealer.
     */
    protected static final Dealer DEALER3 = DealersForTesting.DEALER3;

    /**
     * Testing data for a car.
     */
    protected static final Car CAR1 = CarsForTesting.CAR1;

    /**
     * Testing data for a car.
     */
    protected static final Car CAR2 = CarsForTesting.CAR2;

    /**
     * Testing data for a car.
     */
    protected static final Car CAR3 = CarsForTesting.CAR3;

    /**
     * Testing data for a car.
     */
    protected static final Car CAR4 = CarsForTesting.CAR4;

    /**
     * Testing data for a car.
     */
    protected static final Car CAR5 = CarsForTesting.CAR5;

    /**
     * Testing data for a car.
     */
    protected static final Car CAR6 = CarsForTesting.CAR6;

    /**
     * Testing data for a car.
     */
    protected static final Car CAR7 = CarsForTesting.CAR7;

    /**
     * Testing data for a car.
     */
    protected static final Car CAR8 = CarsForTesting.CAR8;

    /**
     * Testing data for a car.
     */
    protected static final Car CAR9 = CarsForTesting.CAR9;
    
    protected static final OwnerCar OWNERCAR1 = OwnerCarsForTesting.OWNERCAR1;
    protected static final OwnerCar OWNERCAR2 = OwnerCarsForTesting.OWNERCAR2;
    protected static final OwnerCar OWNERCAR3 = OwnerCarsForTesting.OWNERCAR3;

    /**
     * The owner repository so data can be setup/deleted for an owner.
     */
    @Autowired
    protected OwnerRepository ownerRepository;

    /**
     * The region repository so data can be setup/deleted for a region.
     */
    @Autowired
    protected RegionRepository regionRepository;

    /**
     * The country repository so data can be setup/deleted for a country.
     */
    @Autowired
    protected CountryRepository countryRepository;

    /**
     * The dealer repository so data can be setup/deleted for a dealer.
     */
    @Autowired
    protected DealerRepository dealerRepository;

    /**
     * The car repository so data can be setup/deleted for a car.
     */
    @Autowired
    protected CarRepository carRepository;
    
    @Autowired
    protected  OwnerCarRepository ownerCarRepository;

    /**
     * Deletes a car test record.
     *
     * @param deleteKey the car primary key to delete
     */
    protected void deleteCarTestRecord(String deleteKey) {
        Car car = carRepository.findOne(deleteKey);

        if (car != null) {
            carRepository.delete(car);
        }
    }

    /**
     * Deletes a country test record.
     *
     * @param deleteKey the country primary key to delete
     */
    protected void deleteCountryTestRecord(String deleteKey) {
        Country country = countryRepository.findOne(deleteKey);

        if (country != null) {
            countryRepository.delete(country);
        }
    }

    /**
     * Deletes a dealer test record.
     *
     * @param deleteKey the dealer primary key to delete
     */
    protected void deleteDealerTestRecord(String deleteKey) {
        Dealer dealer = dealerRepository.findOne(deleteKey);

        if (dealer != null) {
            dealerRepository.delete(dealer);
        }
    }

    /**
     * Deletes an owner test record.
     *
     * @param deleteKey the owner primary key to delete
     */
    protected void deleteOwnerTestRecord(String deleteKey) {
        Owner owner = ownerRepository.findOne(deleteKey);

        if (owner != null) {
            ownerRepository.delete(owner);
        }
    }

    protected void deleteOwnerCarTestRecord(String deleteKey) {
        OwnerCar ownerCar = ownerCarRepository.findOne(deleteKey);
        
        if (ownerCar != null) {
            ownerCarRepository.delete(ownerCar);
        }
    }
    
    /**
     * Deletes a region test record.
     *
     * @param deleteKey the region primary key to delete
     */
    protected void deleteRegionTestRecord(String deleteKey) {
        Region region = regionRepository.findOne(deleteKey);

        if (region != null) {
            regionRepository.delete(region);
        }
    }    
}
