package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Picture;

public interface PictureDao {

    Picture findOneById(Long id);

    Picture save(Picture picture);
}
