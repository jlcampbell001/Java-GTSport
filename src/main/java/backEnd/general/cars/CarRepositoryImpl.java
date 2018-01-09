package backEnd.general.cars;

import backEnd.general.countries.Country;
import backEnd.general.countries.Country_;
import backEnd.general.manufacturers.Manufacturer;
import backEnd.general.manufacturers.Manufacturer_;
import backEnd.general.regions.Region;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * The is the implement for the car search code that will get a list of cars
 * based on criteria.
 *
 * @author jonathan
 */
public class CarRepositoryImpl implements CarRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Car> findAllByCriteria(CarSearchJson searchJson) throws CarException {
        boolean addDealerJoin = false;
        boolean addCountryJoin = false;
        boolean addRegionJoin = false;

        Join<Car, Manufacturer> dealerJoin = null;
        Join<Manufacturer, Country> countryJoin = null;
        Join<Country, Region> regionJoin = null;

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = builder.createQuery(Car.class);
        Root<Car> criteriaRoot = criteriaQuery.from(Car.class);
        criteriaQuery.select(criteriaRoot);

        //Figure out the criteria using the passed search json.
        Predicate criteria = builder.conjunction();

        // level criteria
        if (searchJson.getLevelFrom() != null || searchJson.getLevelTo() != null) {
            Expression<Integer> levelExpression = criteriaRoot.get("level");

            Integer levelFrom = searchJson.getLevelFrom();
            Integer levelTo = searchJson.getLevelTo();

            if (levelFrom == null) {
                levelFrom = 0;
            }

            if (levelTo == null) {
                levelTo = Integer.MAX_VALUE;
            }

            criteria = builder.and(criteria, builder.between(levelExpression, levelFrom, levelTo));
        }

        // year criteria
        if (searchJson.getYearFrom() != null || searchJson.getYearTo() != null) {
            Expression<Integer> yearExpression = criteriaRoot.get("year");

            Integer yearFrom = searchJson.getYearFrom();
            Integer yearTo = searchJson.getYearTo();

            if (yearFrom == null) {
                yearFrom = 0;
            }

            if (yearTo == null) {
                yearTo = Integer.MAX_VALUE;
            }

            criteria = builder.and(criteria, builder.between(yearExpression, yearFrom, yearTo));
        }

        // power points criteria
        if (searchJson.getPowerPointsFrom() != null || searchJson.getPowerPointsTo() != null) {
            Expression<Integer> powerPointsExpression = criteriaRoot.get("powerPoints");

            Integer powerPointsFrom = searchJson.getPowerPointsFrom();
            Integer powerPointsTo = searchJson.getPowerPointsTo();

            if (powerPointsFrom == null) {
                powerPointsFrom = 0;
            }

            if (powerPointsTo == null) {
                powerPointsTo = Integer.MAX_VALUE;
            }

            criteria = builder.and(criteria, builder.between(powerPointsExpression,
                    powerPointsFrom, powerPointsTo));
        }

        // horse power criteria
        if (searchJson.getHorsePowerFrom() != null || searchJson.getHorsePowerTo() != null) {
            Expression<Integer> horsePowerExpression = criteriaRoot.get("horsePower");

            Integer horsePowerFrom = searchJson.getHorsePowerFrom();
            Integer horsePowerTo = searchJson.getHorsePowerTo();

            if (horsePowerFrom == null) {
                horsePowerFrom = 0;
            }

            if (horsePowerTo == null) {
                horsePowerTo = Integer.MAX_VALUE;
            }

            criteria = builder.and(criteria, builder.between(horsePowerExpression,
                    horsePowerFrom, horsePowerTo));
        }

        // drive train criteria
        if (searchJson.getDriveTrain() != null) {
            Expression<String> driveTrainExpression = criteriaRoot.get("driveTrain");

            criteria = builder.and(criteria, builder.equal(driveTrainExpression,
                    searchJson.getDriveTrain()));
        }

        // add joins
        if (searchJson.getDealerName() != null) {
            addDealerJoin = true;
        }

        if (searchJson.getCountryDescription() != null) {
            addDealerJoin = true;
            addCountryJoin = true;
        }

        if (searchJson.getRegionDescription() != null) {
            addDealerJoin = true;
            addCountryJoin = true;
            addRegionJoin = true;
        }

        if (addDealerJoin) {
            dealerJoin = criteriaRoot.join(Car_.manufacturer, JoinType.INNER);
        }

        if (addCountryJoin && dealerJoin != null) {
            countryJoin = dealerJoin.join(Manufacturer_.country, JoinType.INNER);
        }

        if (addRegionJoin && countryJoin != null) {
            regionJoin = countryJoin.join(Country_.region, JoinType.INNER);
        }

        // dealer criteria
        if (searchJson.getDealerName() != null && dealerJoin != null) {
            Expression<String> dealerNameExpression = dealerJoin.get("name");

            criteria = builder.and(criteria, builder.equal(dealerNameExpression,
                    searchJson.getDealerName()));

        }

        // country critera
        if (searchJson.getCountryDescription() != null && countryJoin != null) {
            Expression<String> countryDescriptionExpression = countryJoin.get("description");

            criteria = builder.and(criteria, builder.equal(countryDescriptionExpression,
                    searchJson.getCountryDescription()));
        }

        // region critera
        if (searchJson.getRegionDescription() != null && regionJoin != null) {
            Expression<String> regionDescriptionExpression = regionJoin.get("description");

            criteria = builder.and(criteria, builder.equal(regionDescriptionExpression,
                    searchJson.getRegionDescription()));
        }

        if (criteria.getExpressions().isEmpty()) {
            throw new CarException(CarException.SEARCH_CRITERIA_NOT_PROVIDED);
        }

        criteriaQuery.where(criteria);

        Query query = entityManager.createQuery(criteriaQuery);
        List<Car> cars = query.getResultList();
        if (cars.isEmpty()) {
            throw new CarException(CarException.NO_CARS_FOUND_FOR_CRITERIA);
        }

        return cars;
    }

}
