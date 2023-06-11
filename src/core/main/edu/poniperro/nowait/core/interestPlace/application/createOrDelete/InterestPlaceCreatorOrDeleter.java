package edu.poniperro.nowait.core.interestPlace.application.createOrDelete;

import edu.poniperro.nowait.core.interestPlace.domain.InterestPlace;
import edu.poniperro.nowait.core.interestPlace.domain.InterestPlaceRepository;
import edu.poniperro.nowait.shared.domain.Service;

@Service
public class InterestPlaceCreatorOrDeleter {
    private final InterestPlaceRepository repository;

    public InterestPlaceCreatorOrDeleter(InterestPlaceRepository repository) {
        this.repository = repository;
    }

    public void createOrDelete(String email, String placeId) {
        InterestPlace interestPlace = InterestPlace.create(email, placeId);
        if (repository.save(interestPlace)) {
            return;
        } else {
            repository.deleteByEmailAndPlaceId(email, placeId);
        }
    }
}
