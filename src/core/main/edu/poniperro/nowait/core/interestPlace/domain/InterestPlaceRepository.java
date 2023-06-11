package edu.poniperro.nowait.core.interestPlace.domain;

public interface InterestPlaceRepository {
    void save(InterestPlace interestPlace);
    InterestPlace findByEmailAndPlaceId(String email, String placeId);
}
