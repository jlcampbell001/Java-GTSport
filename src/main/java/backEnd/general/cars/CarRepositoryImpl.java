/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.cars;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author jonathan
 */
public class CarRepositoryImpl implements CarRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Car> findAllByCriteria(CarSearchJson searchJson) throws CarException {
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
        
        if (criteria.getExpressions().isEmpty()) {
            throw new CarException(CarException.SEARCH_CRITERIA_NOT_PROVIDED);
        }

        criteriaQuery.where(criteria);

        Query query = entityManager.createQuery(criteriaQuery);
        List<Car> cars = query.getResultList();
        if (cars.size() == 0) {
            throw new CarException(CarException.NO_CARS_FOUND_FOR_CRITERIA);
        }

        return cars;
    }

}
