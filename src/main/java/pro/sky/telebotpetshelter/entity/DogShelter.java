package pro.sky.telebotpetshelter.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dog_shelter")
public class DogShelter {

    //id приюта
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //название приюта
    @Column
    private String name;

    //адрес приюта
    @Column
    private String location;

    // график работы приюта
    @Column
    private String timetable;

    //информация о приюте
    @Column(name = "about_shelter")
    private String aboutShelter;

    //cписок животных в приюте
    @Column(name = "dog_id")
    private Long dogId;

    //связь с охраной
    @Column
    private String security;

    //техника безопасности
    @Column(name = "safety_measures")
    private String safetyMeasures;

    //POST конструктор
    public DogShelter(Long id, String name, String location, String timetable, String aboutShelter, String security, String safetyMeasures) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.timetable = timetable;
        this.aboutShelter = aboutShelter;
        this.security = security;
        this.safetyMeasures = safetyMeasures;
    }

    //PUT конструктор
    public DogShelter(String name, String location, String timetable,
                      String aboutShelter, String security, String safetyMeasures) {
        this.name = name;
        this.location = location;
        this.timetable = timetable;
        this.aboutShelter = aboutShelter;
        this.security = security;
        this.safetyMeasures = safetyMeasures;
    }

    public DogShelter() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public String getAboutShelter() {
        return aboutShelter;
    }

    public void setAboutShelter(String aboutShelter) {
        this.aboutShelter = aboutShelter;
    }

    public Long getDogId() {
        return dogId;
    }

    public void setDogId(Long dogId) {
        this.dogId = dogId;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getSafetyMeasures() {
        return safetyMeasures;
    }

    public void setSafetyMeasures(String safetyMeasures) {
        this.safetyMeasures = safetyMeasures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
