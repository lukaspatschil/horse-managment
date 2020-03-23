package at.ac.tuwien.sepm.assignment.individual.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseRace;

public class Horse extends BaseEntity {

    private String name;
    private String notes;
    private int rating;
    private LocalDate birthday;
    private Long owner;
    private HorseRace race;

    public Horse() {
    }

    public Horse(Long id, String name, int rating, LocalDate birthday, HorseRace race, Long owner, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.rating = rating;
        this.birthday = birthday;
        this.owner = owner;
        this.race = race;
    }

    public Horse(Long id, String name, String notes, int rating, LocalDate birthday, HorseRace race, Long owner, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.notes = notes;
        this.rating = rating;
        this.birthday = birthday;
        this.owner = owner;
        this.race = race;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Horse horse = (Horse) o;
        return rating == horse.rating &&
            owner == horse.owner &&
            name.equals(horse.name) &&
            Objects.equals(notes, horse.notes) &&
            birthday.equals(horse.birthday) &&
            race == horse.race;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, notes, rating, birthday, owner, race);
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
