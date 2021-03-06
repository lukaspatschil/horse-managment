package at.ac.tuwien.sepm.assignment.individual.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class Horse extends BaseEntity {

    private String name;
    private String notes;
    private Integer rating;
    private LocalDate birthday;
    private Long owner;
    private String race;
    private byte[] image;
    private String type;

    public Horse() {
    }

    public Horse(Long id, String name, Integer rating, LocalDate birthday, String race, Long owner, byte[] image, String type, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.rating = rating;
        this.birthday = birthday;
        this.owner = owner;
        this.race = race;
        this.image = image;
        this.type = type;
    }

    public Horse(Long id, String name, String notes, Integer rating, LocalDate birthday, String race, Long owner, byte[] image, String type, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.notes = notes;
        this.rating = rating;
        this.birthday = birthday;
        this.owner = owner;
        this.race = race;
        this.image = image;
        this.type = type;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Horse horse = (Horse) o;
        return rating == horse.rating &&
            name.equals(horse.name) &&
            Objects.equals(notes, horse.notes) &&
            birthday.equals(horse.birthday) &&
            owner.equals(horse.owner) &&
            race == horse.race &&
            Arrays.equals(image, horse.image) &&
            type.equals(horse.type);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), name, notes, rating, birthday, owner, race, type);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }



    @Override
    protected String fieldsString() {
        return super.fieldsString() + ", name='" + name + ", notes='" + notes + ", rating='" + rating + ", birthday='" + birthday + '\'';
    }

    @Override
    public String toString() {
        return "Horse{" + fieldsString() + '}';
    }
}
