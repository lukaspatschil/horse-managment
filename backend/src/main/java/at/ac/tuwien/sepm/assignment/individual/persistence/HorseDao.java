package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import org.springframework.dao.DataAccessException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;

import java.util.List;

public interface HorseDao {

    /**
     * finds a horse in the database by id
     * @param id of the horse to find.
     * @return the horse with the specified id.
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws PersistenceException will be thrown if the owner could not be found in the database.
     */
    Horse findOneById(Long id);

    /**
     * finds all horses in the database
     * @return the horses saved in the database
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     */
    List<Horse> getAllHorse();

    /**
     * get all horses from a specific owner
     * @param id the id of the owner
     * @return all the horses from the owner
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     */
    List<Horse> getHorsefromOwner(Long id);

    /**
     * saves a horse entity to the database
     * @param horse the horse to save in the database
     * @return the horse which was saved
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     */
    Horse save(Horse horse);

    /**
     * deletes a horse with the given id
     * @param id the id of the horse which should be deleted
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     */
    void delete(Long id);

    /**
     * updates the horse with the id
     * @param id the id of the horse which should be updated
     * @param horse the new values for the horse
     * @return the updated horse
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException   will be thrown if the owner could not be found in the database.
     */
    Horse update(Long id, Horse horse);

    /**
     * search for all horses with the given parameters
     * @param param the search parameters for the horses
     * @return all horses which are in the parameters
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException   will be thrown if the owner could not be found in the database.
     */
    List<Horse> searchHorse(Horse param);
}
