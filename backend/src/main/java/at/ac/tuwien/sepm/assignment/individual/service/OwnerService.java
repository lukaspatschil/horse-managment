package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;

import java.util.List;

public interface OwnerService {
    /**
     * @param id of the owner to find.
     * @return the owner with the specified id.
     * @throws RuntimeException  will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the owner could not be found in the system.
     */
    Owner findOneById(Long id);

    /**
     * finds all owners in the database
     * @return the owners saved in the database
     * @throws RuntimeException  will be thrown if something goes wrong during data processing.
     */
    List<Owner> getAllOwner();

    /**
     * saves a owner entity to the database
     * @param owner the owner to save in the database
     * @return the owner which was saved
     * @throws RuntimeException  will be thrown if something goes wrong during data processing.
     * @throws ValidationException  will be thrown if the input is not valid.
     */
    Owner save(Owner owner);

    /**
     * deletes an owner with the given id
     * @param id the id of the owner which should be deleted
     * @throws RuntimeException  will be thrown if something goes wrong during data processing.
     */
    void delete(Long id);

    /**
     * updates the owner with the id
     * @param id the id of the owner which should be updated
     * @param owner the new values for the owner
     * @return the updated owner
     * @throws NotFoundException will be thrown if the owner could not be found in the system.
     * @throws RuntimeException  will be thrown if something goes wrong during data processing.
     * @throws ValidationException  will be thrown if the input is not valid.
     */
    Owner update(Long id, Owner owner);

    /**
     * search for all owners with the given parameters
     * @param param the search parameters for the owners
     * @return all owners which are in the parameters
     * @throws NotFoundException will be thrown if the owner could not be found in the system.
     * @throws RuntimeException  will be thrown if something goes wrong during data processing.
     */
    List<Owner> searchOwner(Owner param);

}
