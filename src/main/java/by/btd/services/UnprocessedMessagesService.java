package by.btd.services;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;

public class UnprocessedMessagesService {

    private static int counter;

    @SneakyThrows
    public static SendMessage unprocessedMessage(long chatId) {
        counter++;

        if (counter == 3) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("Чмо заебёшь");

            return sendMessage;
        } else if (counter == 5) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            SendAnimation sendAnimation = new SendAnimation();

            File ff = new File("src/main/resources/gif/niggers.gif");
            InputFile inf = new InputFile(ff);

            sendAnimation.setAnimation(inf);

            sendMessage.setText("5");
            counter = 0;

            return sendMessage;
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Я не розумію.");

        return sendMessage;
    }
}
