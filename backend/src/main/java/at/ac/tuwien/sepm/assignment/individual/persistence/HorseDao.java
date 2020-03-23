package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface HorseDao {

    /**
     * @param id of the horse to find.
     * @return the horse with the specified id.
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException   will be thrown if the owner could not be found in the database.
     */
    Horse findOneById(Long id);

    /**
     * TODO: add doc
     * @return
     */
    List<Horse> getAllHorse();

    /**
     *
     * @param horse the horse to save in the db
     * @return the horse which was saved
     * @throws //add here
     */
    Horse save(Horse horse);

    /**
     * TODO: add doc
     * @param id
     * @return
     */
    void delete(Long id);
}
