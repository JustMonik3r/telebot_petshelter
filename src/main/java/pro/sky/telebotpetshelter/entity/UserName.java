package pro.sky.telebotpetshelter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserName {
    @Id
    @Column
    private Long telegramId;

    @Column
    private String firstName;


    public UserName(Long telegramId, String firstName) {
        this.telegramId = telegramId;
        this.firstName = firstName;

    }
    public UserName(){

    }

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
