package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;

import java.util.List;

public interface HorseService {
    /**
     * @param id of the horse to find.
     * @return the horse with the specified id.
     * @throws RuntimeException  will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the owner could not be found in the system.
     */
    Horse findOneById(Long id);

    /**
     *
     * @return
     */
    List<Horse> getAllHorse();

    /**
     * @param horse horse to save.
     * @return the horse saved in the db.
     * @throws RuntimeException  will be thrown if something goes wrong during data processing.
     */
    Horse save(Horse horse);

    /**
     *
     * @param id
     * @return
     */
    void delete(Long id);

    /**
     *
     * @param id
     * @param horse
     * @return
     */
    Horse update(Long id, Horse horse);

    /**
     *
     * @param param
     * @return
     */
    List<Horse> searchHorse(Horse param);
}
