package pro.sky.telebotpetshelter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    private String diet;
    private String wellBeing;
    private String changeBehavior;
    private LocalDateTime date;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    @JsonIgnore
    private byte[] photo;

    @OneToOne
    @JoinColumn(name = "petOwner_telegramId", referencedColumnName = "telegramId")
    private PetOwner petOwner;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "cat_id", referencedColumnName = "id")
    private Cat catId;

    public Report(long id, String diet, String wellBeing, String changeBehavior, LocalDateTime date, byte[] photo) {
        this.id = id;
        this.diet = diet;
        this.wellBeing = wellBeing;
        this.changeBehavior = changeBehavior;
        this.date = date;
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getWellBeing() {
        return wellBeing;
    }

    public void setWellBeing(String wellBeing) {
        this.wellBeing = wellBeing;
    }

    public String getChangeBehavior() {
        return changeBehavior;
    }

    public void setChangeBehavior(String changeBehavior) {
        this.changeBehavior = changeBehavior;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id && Objects.equals(diet, report.diet) && Objects.equals(wellBeing, report.wellBeing) && Objects.equals(changeBehavior, report.changeBehavior) && Arrays.equals(photo, report.photo) && Objects.equals(date, report.date);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, diet, wellBeing, changeBehavior, date);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", diet='" + diet + '\'' +
                ", wellBeing='" + wellBeing + '\'' +
                ", changeBehavior='" + changeBehavior + '\'' +
                ", date=" + date +
                ", photo=" + Arrays.toString(photo) +
                '}';
    }
}

