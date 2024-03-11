package pro.sky.telebotpetshelter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
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


