package at.ac.tuwien.sepm.assignment.individual.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseRace;

public class Horse extends BaseEntity {

    private String name;
    private String notes;
    private int rating;
    private LocalDate birthday;
    private HorseRace race;
    private Long owner;
    private byte[] picture;
    private String datatype;

    public Horse() {
    }

    public Horse(Long id, String name, String notes, int rating, LocalDate birthday, HorseRace race, Long owner, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.notes = notes;
        this.rating = rating;
        this.birthday = birthday;
        this.race = race;
        this.owner = owner;
    }

    public Horse(Long id, String name, int rating, LocalDate birthday, HorseRace race, Long owner, byte[] picture, String datatype, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.rating = rating;
        this.birthday = birthday;
        this.owner = owner;
        this.race = race;
        this.picture = picture;
        this.datatype = datatype;
    }

    public Horse(Long id, String name, String notes, int rating, LocalDate birthday, HorseRace race, Long owner, byte[] picture, String datatype, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.notes = notes;
        this.rating = rating;
        this.birthday = birthday;
        this.owner = owner;
        this.race = race;
        this.picture = picture;
        this.datatype = datatype;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public HorseRace getRace() {
        return race;
    }

    public void setRace(HorseRace race) {
        this.race = race;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
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
            race == horse.race &&
            owner.equals(horse.owner) &&
            Arrays.equals(picture, horse.picture) &&
            datatype.equals(horse.datatype);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), name, notes, rating, birthday, race, owner, datatype);
        result = 31 * result + Arrays.hashCode(picture);
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
