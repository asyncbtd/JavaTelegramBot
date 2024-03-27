package by.btd.services;

import java.io.File;

import by.btd.BotHandlers;
import by.btd.config.TelegramProp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class UnprocessedMessagesService {

    private final TelegramProp telegramProp;
    private static int counter;

    @SneakyThrows
    public void unprocessedMessage(Update update) {
        var botHandlers = new BotHandlers(telegramProp);
        long chatId = update.getMessage().getChatId();
        counter++;

        if (counter == 3) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("Чмо заебёшь");

            botHandlers.anyExecute(sendMessage, update);
        } else if (counter == 5) {
            SendAnimation sendAnimation = new SendAnimation();

            File ff = new File("src/main/resources/gif/niggers.gif");
            InputFile inf = new InputFile(ff);

            sendAnimation.setHasSpoiler(true);
            sendAnimation.setChatId(chatId);
            sendAnimation.setAnimation(inf);
            counter = 0;

            botHandlers.anyExecute(sendAnimation, update);
        } else {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("Я не розумію.");

            botHandlers.anyExecute(sendMessage, update);
        }
    }
}
