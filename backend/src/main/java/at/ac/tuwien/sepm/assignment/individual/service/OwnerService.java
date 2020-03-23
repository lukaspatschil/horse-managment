package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;

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
     *
     * @return
     */
    List<Owner> getAllOwner();

    /**
     *
     * @param owner
     * @return
     */
    Owner save(Owner owner);

    /**
     *
     * @param id
     * @return
     */
    void delete(Long id);

}
