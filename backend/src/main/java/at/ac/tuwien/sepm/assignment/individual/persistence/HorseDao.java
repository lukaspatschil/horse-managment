package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import org.springframework.dao.DataAccessException;

public interface HorseDao {

    /**
     * @param id of the owner to find.
     * @return the owner with the specified id.
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException   will be thrown if the owner could not be found in the database.
     */
    Horse findOneById(Long id);

    /**
     *
     * @param horse the horse to save in the db
     * @return the horse which was saved
     * @throws add here
     */
    Horse save(Horse horse);
}
