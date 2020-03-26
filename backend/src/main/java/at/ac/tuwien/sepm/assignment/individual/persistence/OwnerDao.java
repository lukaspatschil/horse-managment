package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface OwnerDao {

    /**
     * @param id of the owner to find.
     * @return the owner with the specified id.
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException   will be thrown if the owner could not be found in the database.
     */
    Owner findOneById(Long id);

    /**
     * TODO: add doc
     * @return
     */
    List<Owner> getAllOwner();

    /**
     *
     * @param owner the owner to save in the db
     * @return the owner which was saved
     * @throws //add here
     */
    Owner save(Owner owner);

    /**
     * TODO: add doc
     * @param id
     * @return
     */
    void delete(Long id);

    /**
     *
     * @param id
     * @param owner
     * @return
     */
    Owner update(Long id, Owner owner);
}
