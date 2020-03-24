package at.ac.tuwien.sepm.assignment.individual.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.OwnerDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.PictureDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.mapper.PictureMapper;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.entity.Picture;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.service.PictureService;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(PictureEndpoint.BASE_URL)
public class PictureEndpoint {

    static final String BASE_URL = "/picture";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final PictureService pictureService;
    private final PictureMapper pictureMapper;

    @Autowired
    public PictureEndpoint(PictureService pictureService, PictureMapper pictureMapper) {
        this.pictureService = pictureService;
        this.pictureMapper = pictureMapper;
    }

    @GetMapping(value = "/{id}")
    public PictureDto getOneById(@PathVariable("id") Long id) {
        LOGGER.info("GET " + BASE_URL + "/{}", id);
        try {
            return pictureMapper.entityToDto(pictureService.findOneById(id));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading owner", e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PictureDto post(@RequestBody PictureDto picture) {
        LOGGER.info("POST " + BASE_URL + "/{}", picture.getName());
        try {
            Picture pictureEntity = pictureMapper.dtoToEntity(picture);
            return pictureMapper.entityToDto(pictureService.save(pictureEntity));
        } catch (ValidationException e) {
            throw new
                ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                "Error during saving owner", e);
        }
    }

    @PostMapping("/upload/db")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity uploadToDB(@RequestParam("file") MultipartFile file, @RequestParam("text") OwnerDto owner) {
        Picture doc = new Picture();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        LOGGER.warn(file.getContentType());
        doc.setName(fileName);
        LOGGER.warn(owner.getName());
        try {
            doc.setFile(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pictureService.save(doc);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/files/download/")
            .path(fileName).path("/db")
            .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }

    @GetMapping("/download/{id}/db")
    public ResponseEntity downloadFromDB(@PathVariable Long id) {
        Picture document = pictureService.findOneById(id);
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
            .body(document.getFile());
    }
}
