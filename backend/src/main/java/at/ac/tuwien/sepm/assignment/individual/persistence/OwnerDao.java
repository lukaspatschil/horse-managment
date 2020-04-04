package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;

import java.util.List;

public interface OwnerDao {

    /**
     * finds a owner in the database by id
     * @param id of the owner to find.
     * @return the owner with the specified id.
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException   will be thrown if the owner could not be found in the database.
     */
    Owner findOneById(Long id);

    /**
     * finds all owners in the database
     * @return the owners saved in the database
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     */
    List<Owner> getAllOwner();

    /**
     * saves a owner entity to the database
     * @param owner the owner to save in the database
     * @return the owner which was saved
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     */
    Owner save(Owner owner);

    /**
     * deletes an owner with the given id
     * @param id the id of the owner which should be deleted
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     */
    void delete(Long id);

    /**
     * updates the owner with the id
     * @param id the id of the owner which should be updated
     * @param owner the new values for the owner
     * @return the updated owner
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException   will be thrown if the owner could not be found in the database.
     */
    Owner update(Long id, Owner owner);

    /**
     * search for all owners with the given parameters
     * @param param the search parameters for the owners
     * @return all owners which are in the parameters
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException   will be thrown if the owner could not be found in the database.
     */
    List<Owner> searchOwner(Owner param);
}
