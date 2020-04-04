package at.ac.tuwien.sepm.assignment.individual.unit.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.OwnerEndpoint;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.OwnerDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class OwnerEndpointTestBase {

    @Autowired
    OwnerEndpoint ownerEndpoint;

    @Test
    @DisplayName("Find owner by id and get NotFindException")
    public void gettingOwnerById_IdIs100_shouldThrowNotFound() {
        assertThrows(ResponseStatusException.class,
            () -> ownerEndpoint.getOneById(100L));
    }

    @Test
    @DisplayName("Find one with id 0 should throw ValidationException")
    public void findingOwnerwithId_noValidId_shouldThrowValidationException() {
        assertThrows(ResponseStatusException.class,
            () -> ownerEndpoint.getOneById(0L));
    }

    @Test
    @DisplayName("Add Owner with name Luki")
    public void saveOwnerWithName_NameIsLuki_shouldWork() {
        assert (ownerEndpoint.post(new OwnerDto("Luki")).getName().equals("Luki"));
    }

    @Test
    @DisplayName("Search for owner and work")
    public void searchOwnerwithName_NameIsEmptyString_shouldWork() {
        assert (!ownerEndpoint.searchOwner(new OwnerDto("")).isEmpty());
    }
}
