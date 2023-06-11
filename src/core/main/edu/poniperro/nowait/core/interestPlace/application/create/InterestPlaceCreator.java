package edu.poniperro.nowait.core.interestPlace.application.create;

import edu.poniperro.nowait.core.interestPlace.domain.InterestPlace;
import edu.poniperro.nowait.core.interestPlace.domain.InterestPlaceRepository;
import edu.poniperro.nowait.shared.domain.Service;

@Service
public class InterestPlaceCreator {
    private final InterestPlaceRepository repository;

    public InterestPlaceCreator(InterestPlaceRepository repository) {
        this.repository = repository;
    }

    public void create(String email, String placeId) {
        InterestPlace interestPlace = InterestPlace.create(email, placeId);
        repository.save(interestPlace);
    }
}
