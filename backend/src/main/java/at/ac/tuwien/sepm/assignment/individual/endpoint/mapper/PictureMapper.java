package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.PictureDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Picture;
import org.springframework.stereotype.Component;

@Component
public class PictureMapper {

    public PictureDto entityToDto(Picture picture) {
        return new PictureDto(picture.getId(), picture.getName(), picture.getFile());
    }

    public Picture dtoToEntity(PictureDto picture) {
        return new Picture(picture.getId(), picture.getName(), picture.getFile());
    }
}
