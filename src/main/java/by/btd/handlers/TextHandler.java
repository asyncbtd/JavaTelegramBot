package by.btd.handlers;

import by.btd.services.UnprocessedMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class TextHandler {

    public static SendMessage textHandler(Update update) {
        String command = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        log.info("Принял запрос");
        return switch (command) {
            default -> UnprocessedMessagesService.unprocessedMessage(chatId);
        };
    }
}
