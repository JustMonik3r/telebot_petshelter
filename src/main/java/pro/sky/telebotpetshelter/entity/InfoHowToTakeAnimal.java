package pro.sky.telebotpetshelter.entity;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "take_animal_info")
public class InfoHowToTakeAnimal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;


    @Column(name = "rules_for_meeting_animal")
    private String rulesForMeetingAnimal;


    @Column(name = "document_list")
    private String documentList;

    @Column(name = "recommendations_for_transport")
    private String recForTransport;

    @Column(name = "home_recommendations_for_small")
    private String homeRecommendForSmall;

    @Column(name = "home_recommendations_for_big")
    private String homeRecommendForBig;

    @Column(name = "home_recommendations_for_disable")
    private String homeRecommendForDisable;

    @Column(name = "dog_handler_tips")
    private String dogHandlerTips;

    @Column(name = "recommendations_for_proven_dog_handlers")
    private String recForProvenDogHandlers;

    @Column(name = "reasons_for_refusal")
    private String reasonsForRefusal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRulesForMeetingAnimal() {
        return rulesForMeetingAnimal;
    }

    public void setRulesForMeetingAnimal(String rulesForMeetingAnimal) {
        this.rulesForMeetingAnimal = rulesForMeetingAnimal;
    }

    public String getDocumentList() {
        return documentList;
    }

    public void setDocumentList(String documentList) {
        this.documentList = documentList;
    }

    public String getRecForTransport() {
        return recForTransport;
    }

    public void setRecForTransport(String recForTransport) {
        this.recForTransport = recForTransport;
    }

    public String getHomeRecommendForSmall() {
        return homeRecommendForSmall;
    }

    public void setHomeRecommendForSmall(String homeRecommendForSmall) {
        this.homeRecommendForSmall = homeRecommendForSmall;
    }

    public String getHomeRecommendForBig() {
        return homeRecommendForBig;
    }

    public void setHomeRecommendForBig(String homeRecommendForBig) {
        this.homeRecommendForBig = homeRecommendForBig;
    }

    public String getHomeRecommendForDisable() {
        return homeRecommendForDisable;
    }

    public void setHomeRecommendForDisable(String homeRecommendForDisable) {
        this.homeRecommendForDisable = homeRecommendForDisable;
    }

    public String getDogHandlerTips() {
        return dogHandlerTips;
    }

    public void setDogHandlerTips(String dogHandlerTips) {
        this.dogHandlerTips = dogHandlerTips;
    }

    public String getRecForProvenDogHandlers() {
        return recForProvenDogHandlers;
    }

    public void setRecForProvenDogHandlers(String recForProvenDogHandlers) {
        this.recForProvenDogHandlers = recForProvenDogHandlers;
    }

    public String getReasonsForRefusal() {
        return reasonsForRefusal;
    }

    public void setReasonsForRefusal(String reasonsForRefusal) {
        this.reasonsForRefusal = reasonsForRefusal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoHowToTakeAnimal that = (InfoHowToTakeAnimal) o;
        return Objects.equals(id, that.id) && Objects.equals(rulesForMeetingAnimal, that.rulesForMeetingAnimal) && Objects.equals(documentList, that.documentList) && Objects.equals(recForTransport, that.recForTransport) && Objects.equals(homeRecommendForSmall, that.homeRecommendForSmall) && Objects.equals(homeRecommendForBig, that.homeRecommendForBig) && Objects.equals(homeRecommendForDisable, that.homeRecommendForDisable) && Objects.equals(dogHandlerTips, that.dogHandlerTips) && Objects.equals(recForProvenDogHandlers, that.recForProvenDogHandlers) && Objects.equals(reasonsForRefusal, that.reasonsForRefusal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rulesForMeetingAnimal, documentList, recForTransport, homeRecommendForSmall, homeRecommendForBig, homeRecommendForDisable, dogHandlerTips, recForProvenDogHandlers, reasonsForRefusal);
    }

    @Override
    public String toString() {
        return "InfoHowToTakeAnimal{" +
                "id=" + id +
                ", rulesForMeetingAnimal='" + rulesForMeetingAnimal + '\'' +
                ", documentList='" + documentList + '\'' +
                ", recForTransport='" + recForTransport + '\'' +
                ", homeRecommendForSmall='" + homeRecommendForSmall + '\'' +
                ", homeRecommendForBig='" + homeRecommendForBig + '\'' +
                ", homeRecommendForDisable='" + homeRecommendForDisable + '\'' +
                ", dogHandlerTips='" + dogHandlerTips + '\'' +
                ", recForProvenDogHandlers='" + recForProvenDogHandlers + '\'' +
                ", reasonsForRefusal='" + reasonsForRefusal + '\'' +
                '}';
    }
}
