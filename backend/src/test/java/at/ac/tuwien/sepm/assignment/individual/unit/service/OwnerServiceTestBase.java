package at.ac.tuwien.sepm.assignment.individual.unit.service;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.service.OwnerService;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class OwnerServiceTestBase {

    @Autowired
    OwnerService ownerService;

    @Test
    @DisplayName("Find one with id 0 should throw ValidationException")
    public void findingOwnerwithId_noValidId_shouldThrowValidationException() {
        assertThrows(ValidationException.class,
            () -> ownerService.findOneById(0L));
    }

    @Test
    @DisplayName("Find one Owner by id and work")
    public void findingOwnerById_IdIs100_shouldThrowNotFound() {
        assertThrows(NotFoundException.class,
            () -> ownerService.findOneById(100L));
    }

    @Test
    @DisplayName("Add Owner with name Luki")
    public void saveOwnerWithName_NameIsLuki_shouldWork() {
        assert (ownerService.save(new Owner("Luki")).getName().equals("Luki"));
    }

    @Test
    @DisplayName("Search for owner and work")
    public void searchOwnerwithName_NameIsEmptyString_shouldWork() {
        assert (!ownerService.searchOwner(new Owner("")).isEmpty());
    }
}
