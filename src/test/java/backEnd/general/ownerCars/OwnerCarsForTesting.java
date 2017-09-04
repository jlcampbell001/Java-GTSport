package backEnd.general.ownerCars;

import backEnd.general.cars.CarsForTesting;
import backEnd.general.owners.OwnersForTesting;
import java.time.LocalDate;
import java.time.Month;

/**
 * Test data for owner cars.
 *
 * @author jonathan
 */
public class OwnerCarsForTesting {

    private static final String OWNERCAR_1_KEY = "OWC900000001";
    private static final String OWNERCAR_1_OWNER_KEY = OwnersForTesting.OWNER1.getPrimaryKey();
    private static final String OWNERCAR_1_CAR_KEY = CarsForTesting.CAR1.getPrimaryKey();
    private static final String OWNERCAR_1_ID = CarsForTesting.CAR1.getName() + "_01";
    private static final String OWNERCAR_1_COLOUR = "Red";
    private static final int OWNERCAR_1_POWERPOINTS = CarsForTesting.CAR1.getPowerPoints() + 30;
    private static final LocalDate OWNERCAR_1_AQUIRE_DATE = LocalDate.of(2017, Month.AUGUST, 5);

    private static final String OWNERCAR_2_KEY = "OWC900000002";
    private static final String OWNERCAR_2_OWNER_KEY = OwnersForTesting.OWNER2.getPrimaryKey();
    private static final String OWNERCAR_2_CAR_KEY = CarsForTesting.CAR2.getPrimaryKey();
    private static final String OWNERCAR_2_ID = CarsForTesting.CAR2.getName() + "_02";
    private static final String OWNERCAR_2_COLOUR = "Blue";
    private static final int OWNERCAR_2_POWERPOINTS = CarsForTesting.CAR2.getPowerPoints() + 130;
    private static final LocalDate OWNERCAR_2_AQUIRE_DATE = LocalDate.of(2017, Month.JULY, 15);

    private static final String OWNERCAR_3_KEY = "OWC900000003";
    private static final String OWNERCAR_3_OWNER_KEY = OwnersForTesting.OWNER1.getPrimaryKey();
    private static final String OWNERCAR_3_CAR_KEY = CarsForTesting.CAR3.getPrimaryKey();
    private static final String OWNERCAR_3_ID = CarsForTesting.CAR3.getName() + "_03";
    private static final String OWNERCAR_3_COLOUR = "Silver";
    private static final int OWNERCAR_3_POWERPOINTS = CarsForTesting.CAR3.getPowerPoints();
    private static final LocalDate OWNERCAR_3_AQUIRE_DATE = LocalDate.of(2016, Month.DECEMBER, 25);

    /**
     * Test data for an owner car.
     */
    public static final OwnerCar OWNERCAR1 = new OwnerCar(OWNERCAR_1_KEY, OWNERCAR_1_OWNER_KEY,
            OWNERCAR_1_CAR_KEY, OWNERCAR_1_ID, OWNERCAR_1_COLOUR,
            OWNERCAR_1_POWERPOINTS, OWNERCAR_1_AQUIRE_DATE);

    /**
     * Test data for an owner car.
     */
    public static final OwnerCar OWNERCAR2 = new OwnerCar(OWNERCAR_2_KEY, OWNERCAR_2_OWNER_KEY,
            OWNERCAR_2_CAR_KEY, OWNERCAR_2_ID, OWNERCAR_2_COLOUR,
            OWNERCAR_2_POWERPOINTS, OWNERCAR_2_AQUIRE_DATE);

    /**
     * Test data for an owner car.
     */
    public static final OwnerCar OWNERCAR3 = new OwnerCar(OWNERCAR_3_KEY, OWNERCAR_3_OWNER_KEY,
            OWNERCAR_3_CAR_KEY, OWNERCAR_3_ID, OWNERCAR_3_COLOUR,
            OWNERCAR_3_POWERPOINTS, OWNERCAR_3_AQUIRE_DATE);
}
