package pro.sky.telebotpetshelter.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "chat_id")
    private long chatId;
    @Column(name = "photo")
    private String photo;
    @Column(name = "sent_date")
    private LocalDate date;
    @Column(name = "report_text_under_photo")
    private String reportTextUnderPhoto;

    public Report(long id, long chatId, String photo, LocalDate date, String reportTextUnderPhoto) {
        this.id = id;
        this.chatId = chatId;
        this.photo = photo;
        this.date = date;
        this.reportTextUnderPhoto = reportTextUnderPhoto;
    }

    public Report() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReportTextUnderPhoto() {
        return reportTextUnderPhoto;
    }

    public void setReportTextUnderPhoto(String reportTextUnderPhoto) {
        this.reportTextUnderPhoto = reportTextUnderPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id && chatId == report.chatId && Objects.equals(photo, report.photo) && Objects.equals(date, report.date) && Objects.equals(reportTextUnderPhoto, report.reportTextUnderPhoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, photo, date, reportTextUnderPhoto);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", photo='" + photo + '\'' +
                ", date=" + date +
                ", reportTextUnderPhoto='" + reportTextUnderPhoto + '\'' +
                '}';
    }
}
