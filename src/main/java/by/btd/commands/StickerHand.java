package by.btd.commands;

import by.btd.Handlers.MessageHandlers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class StickerHand implements MessageHandlers {

    @Override
    public SendMessage SendMessageHandler(Update update) {
        SendMessage sendMessage = new SendMessage();
        long chatId = update.getMessage().getChatId();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Одобряю");

        return sendMessage;
    }
}
