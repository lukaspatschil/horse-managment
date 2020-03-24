package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

public class PictureDto {

    private Long id;
    private String name;
    private byte[] file;

    public PictureDto() {

    }

    public PictureDto(Long id, String name, byte[] file) {
        this.id = id;
        this.name = name;
        this.file = file;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
