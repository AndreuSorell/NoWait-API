package edu.poniperro.nowait.core.interestPlace.application.search;

import edu.poniperro.nowait.core.interestPlace.application.InterestPlaceResponse;
import edu.poniperro.nowait.core.interestPlace.application.InterestPlacesResponse;
import edu.poniperro.nowait.core.interestPlace.domain.InterestPlace;
import edu.poniperro.nowait.core.interestPlace.domain.InterestPlaceRepository;
import edu.poniperro.nowait.shared.domain.Service;

import java.util.stream.Collectors;

@Service
public final class InterestPlaceSearcher {
    private final InterestPlaceRepository repository;

    public InterestPlaceSearcher(InterestPlaceRepository repository) {
        this.repository = repository;
    }

    public InterestPlaceResponse search(String email, String placeId) {
        InterestPlace interestPlace = repository.searchByEmailAndPlaceId(email, placeId);
        if (interestPlace!=null) {
            return new InterestPlaceResponse(interestPlace.getPlaceId());
        } else {
            return new InterestPlaceResponse(null);
        }
    }

    public InterestPlacesResponse searchByEmail(String email) {
        return new InterestPlacesResponse(
                repository.searchByEmail(email)
                        .stream()
                        .map(InterestPlaceResponse::fromAggregate)
                        .collect(Collectors.toList())
        );
    }
}
