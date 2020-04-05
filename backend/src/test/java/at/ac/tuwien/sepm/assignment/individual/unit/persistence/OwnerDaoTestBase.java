package at.ac.tuwien.sepm.assignment.individual.unit.persistence;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class OwnerDaoTestBase {

    @Autowired
    OwnerDao ownerDao;

    @Test
    @DisplayName("Finding owner by non-existing ID should throw NotFoundException")
    public void findingOwnerById_nonExisting_shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class,
            () -> ownerDao.findOneById(100L));
    }

    @Test
    @DisplayName("Search for all existing owner with empty name")
    public void searchOwner_withEmptyName_shouldWork() {
        assert (!ownerDao.searchOwner(new Owner("")).isEmpty());
    }

    @Test
    @DisplayName("Get all owner from database")
    public void gettingAllOwner_shouldWork() {
        assert (!ownerDao.getAllOwner().isEmpty());
    }

    @Test
    @DisplayName("Save new owner and it works")
    public void savingNewOwner_nameIsFred() {
        assert (ownerDao.save(new Owner("Fred")).getName().equals("Fred"));
    }
}
