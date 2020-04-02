package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import javax.validation.constraints.Null;
import java.time.LocalDate;

public class HorseSearch {

    @Null
    String name;
    @Null
    String notes;
    @Null
    LocalDate birhtday;
    @Null
    String race;
    @Null
    Integer rating;

    public HorseSearch(String name, String notes, LocalDate birhtday, String race, Integer rating) {
        this.name = name;
        this.notes = notes;
        this.birhtday = birhtday;
        this.race = race;
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public LocalDate getBirhtday() {
        return birhtday;
    }

    public void setBirhtday(LocalDate birhtday) {
        this.birhtday = birhtday;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
