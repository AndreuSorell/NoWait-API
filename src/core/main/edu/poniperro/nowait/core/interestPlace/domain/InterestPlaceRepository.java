package edu.poniperro.nowait.core.interestPlace.domain;

import java.util.List;

public interface InterestPlaceRepository {
    boolean save(InterestPlace interestPlace);
    InterestPlace searchByEmailAndPlaceId(String email, String placeId);
    void deleteByEmailAndPlaceId(String email, String placeId);
    List<InterestPlace> searchByEmail(String email);
}
