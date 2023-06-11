package edu.poniperro.nowait.core.interestPlace.domain;

public interface InterestPlaceRepository {
    boolean save(InterestPlace interestPlace);
    InterestPlace findByEmailAndPlaceId(String email, String placeId);
    void deleteByEmailAndPlaceId(String email, String placeId);
}
