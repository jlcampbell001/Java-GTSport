package backEnd.general;

import backEnd.general.cars.Car;
import backEnd.general.cars.CarRepository;
import backEnd.general.cars.CarsForTesting;
import backEnd.general.countries.CountriesForTesting;
import backEnd.general.countries.Country;
import backEnd.general.countries.CountryRepository;
import backEnd.general.manufacturers.Manufacturer;
import backEnd.general.manufacturers.ManufacturerForTesting;
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
import backEnd.general.manufacturers.ManufacturerRepository;

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
     * Testing data for a country.
     */
    protected static final Country COUNTRY4 = CountriesForTesting.COUNTRY4;

    /**
     * Testing data for a country.
     */
    protected static final Country COUNTRY5 = CountriesForTesting.COUNTRY5;

    /**
     * Testing data for a manufacturer.
     */
    protected static final Manufacturer MANUFACTURER1 = ManufacturerForTesting.MANUFACTURER1;

    /**
     * Testing data for a manufacturer.
     */
    protected static final Manufacturer MANUFACTURER2 = ManufacturerForTesting.MANUFACTURER2;

    /**
     * testing data for a manufacturer.
     */
    protected static final Manufacturer MANUFACTURER3 = ManufacturerForTesting.MANUFACTURE3;

    /**
     * testing data for a manufacturer.
     */
    protected static final Manufacturer MANUFACTURER4 = ManufacturerForTesting.MANUFACTURE4;

    /**
     * testing data for a manufacturer.
     */
    protected static final Manufacturer MANUFACTURER5 = ManufacturerForTesting.MANUFACTURE5;

    /**
     * testing data for a manufacturer.
     */
    protected static final Manufacturer MANUFACTURER6 = ManufacturerForTesting.MANUFACTURE6;

    /**
     * testing data for a manufacturer.
     */
    protected static final Manufacturer MANUFACTURER7 = ManufacturerForTesting.MANUFACTURE7;

    /**
     * testing data for a manufacturer.
     */
    protected static final Manufacturer MANUFACTURER8 = ManufacturerForTesting.MANUFACTURE8;

    /**
     * testing data for a manufacturer.
     */
    protected static final Manufacturer MANUFACTURER9 = ManufacturerForTesting.MANUFACTURE9;

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

    /**
     * Testing data for an owner car.
     */
    protected static final OwnerCar OWNERCAR1 = OwnerCarsForTesting.OWNERCAR1;

    /**
     * Testing data for an owner car.
     */
    protected static final OwnerCar OWNERCAR2 = OwnerCarsForTesting.OWNERCAR2;

    /**
     * Testing data for an owner car.
     */
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
     * The manufacturer repository so data can be setup/deleted for a manufacturer.
     */
    @Autowired
    protected ManufacturerRepository manufacturerRepository;

    /**
     * The car repository so data can be setup/deleted for a car.
     */
    @Autowired
    protected CarRepository carRepository;

    /**
     * The owner car repository so data can be setup/deleted for an owner car.
     */
    @Autowired
    protected OwnerCarRepository ownerCarRepository;

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
     * Deletes a manufacturer test record.
     *
     * @param deleteKey the manufacturer primary key to delete
     */
    protected void deleteManufacturerTestRecord(String deleteKey) {
        Manufacturer manufacturer = manufacturerRepository.findOne(deleteKey);

        if (manufacturer != null) {
            manufacturerRepository.delete(manufacturer);
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

    /**
     * Deletes an owner car test record.
     *
     * @param deleteKey the owner car primary key to delete
     */
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
