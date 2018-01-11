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

        // category criteria
        if (searchJson.getCategoryFrom()!= null || searchJson.getCategoryTo()!= null) {
            Expression<Category> categoryExpression = criteriaRoot.get("category");

            Category categoryFrom = searchJson.getCategoryFrom();
            Category categoryTo = searchJson.getCategoryTo();

            if (categoryFrom == null) {
                categoryFrom = Category.EMPTY;
            }

            if (categoryTo == null) {
                categoryTo = Category.MAX;
            }

            criteria = builder.and(criteria, builder.between(categoryExpression, 
                    categoryFrom, categoryTo));
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

        // max power criteria
        if (searchJson.getMaxPowerFrom() != null || searchJson.getMaxPowerTo() != null) {
            Expression<Integer> maxPowerExpression = criteriaRoot.get("maxPower");

            Integer maxPowerFrom = searchJson.getMaxPowerFrom();
            Integer maxPowerTo = searchJson.getMaxPowerTo();

            if (maxPowerFrom == null) {
                maxPowerFrom = 0;
            }

            if (maxPowerTo == null) {
                maxPowerTo = Integer.MAX_VALUE;
            }

            criteria = builder.and(criteria, builder.between(maxPowerExpression,
                    maxPowerFrom, maxPowerTo));
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
