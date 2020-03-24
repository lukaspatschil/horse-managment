package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Picture;

public interface PictureService {

    Picture findOneById(Long id);

    Picture save(Picture owner);
}
