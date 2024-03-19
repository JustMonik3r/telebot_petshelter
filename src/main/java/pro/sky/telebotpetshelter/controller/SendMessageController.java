package pro.sky.telebotpetshelter.controller;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/message-sender")
@Tag(name = "Отправить сообщение усыновителю", description = "Метод для отправки любого сообщения пользователю по его ID (через бота)")
public class SendMessageController {

    private final TelegramBot telegramBot;

    public SendMessage sendMessage;

    public SendMessageController(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostMapping
    @Operation(summary = "Отправить любое сообщение усыновителю")
    public SendMessage createMessage(
        @RequestParam @Parameter(description = "ID усыновителя") Long chatId,
        @RequestParam @Parameter(description = "Сообщение") String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        telegramBot.execute(sendMessage);
        return sendMessage;

    }
    @PostMapping("/admonition")
    @Operation(summary = "Дорогой усыновитель, мы заметили, что ты заполняешь отчет не так подробно, как необходимо. Пожалуйста, подойди ответственнее к этому занятию. В противном случае волонтеры приюта будут обязаны самолично проверять условия содержания животного")
    public SendMessage admonition(
            @RequestParam @Parameter(description = "ID усыновителя") Long chatId) {
        SendMessage sendMessage1 = new SendMessage(chatId, String.format("Дорогой усыновитель, мы заметили, что ты заполняешь отчет не так подробно, как необходимо. Пожалуйста, подойди ответственнее к этому занятию. В противном случае волонтеры приюта будут обязаны самолично проверять условия содержания животного"));
        telegramBot.execute(sendMessage1);
        return sendMessage1;
    }

}
