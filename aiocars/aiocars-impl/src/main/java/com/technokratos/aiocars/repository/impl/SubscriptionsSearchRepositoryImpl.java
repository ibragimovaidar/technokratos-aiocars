package com.technokratos.aiocars.repository.impl;

import com.technokratos.aiocars.model.AdvertisementEntity;
import com.technokratos.aiocars.model.SubscriptionEntity;
import com.technokratos.aiocars.repository.SubscriptionsSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class SubscriptionsSearchRepositoryImpl implements SubscriptionsSearchRepository {

    private final EntityManager entityManager;

    @Override
    public List<SubscriptionEntity> findAllMatchingAdvertisement(AdvertisementEntity advertisement) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubscriptionEntity> criteriaQuery = cb.createQuery(SubscriptionEntity.class);
        Root<SubscriptionEntity> subscription = criteriaQuery.from(SubscriptionEntity.class);
        Predicate minMileagePredicate = cb.greaterThanOrEqualTo(subscription.get("minMileage"), advertisement.getMileage());
        Predicate maxMileagePredicate = cb.lessThanOrEqualTo(subscription.get("maxMileage"), advertisement.getMileage());
        Predicate minYearPredicate = cb.greaterThanOrEqualTo(subscription.get("minYear"), advertisement.getYear());
        Predicate maxYearPredicate = cb.lessThanOrEqualTo(subscription.get("maxYear"), advertisement.getYear());
        Predicate minPricePredicate = cb.greaterThanOrEqualTo(subscription.get("minPrice"), advertisement.getPrice());
        Predicate maxPricePredicate = cb.lessThanOrEqualTo(subscription.get("maxPrice"), advertisement.getPrice());
        Predicate cityPredicate = cb.equal(subscription.get("city.id"), advertisement.getCity().getId());
        Predicate carPredicate = cb.equal(subscription.get("car.id"), advertisement.getCar().getId());
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
