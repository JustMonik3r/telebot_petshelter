package pro.sky.telebotpetshelter.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cat_shelter")
public class CatShelter {

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
    @Column(name = "cat_id")
    private Long catId;

    //связь с охраной
    @Column
    private String security;

    //техника безопасности
    @Column(name = "safety_measures")
    private String safetyMeasures;

    @Column(name = "HomeRecommendForBigPet")
    private String HomeRecommendForBigPet;
    @Column(name = "HomeRecommendForSmallPet")
    private String HomeRecommendForSmallPet;
    @Column(name = "RecForTransport")
    private String RecForTransport;
    @Column(name = "DocumentList")
    private String DocumentList;
    @Column(name = "RulesForMeeting")
    private String RulesForMeeting;
    @Column(name = "ReasonsForRefusal")
    private String ReasonsForRefusal;
    @Column(name = "RecForProvenHandlers")
    private String RecForProvenHandlers;
    @Column(name = "HandlerTips")
    private String HandlerTips;
    @Column(name = "HomeRecommendForDisable")
    private String HomeRecommendForDisable;

    //POST конструктор
    public CatShelter(Long id, String name, String location, String timetable, String aboutShelter, String security, String safetyMeasures) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.timetable = timetable;
        this.aboutShelter = aboutShelter;
        this.security = security;
        this.safetyMeasures = safetyMeasures;
    }

    //PUT конструктор
    public CatShelter(String name, String location, String timetable,
                      String aboutShelter, String security, String safetyMeasures) {
        this.name = name;
        this.location = location;
        this.timetable = timetable;
        this.aboutShelter = aboutShelter;
        this.security = security;
        this.safetyMeasures = safetyMeasures;
    }

    public CatShelter() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
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
}