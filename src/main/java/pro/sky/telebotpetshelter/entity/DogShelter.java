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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
