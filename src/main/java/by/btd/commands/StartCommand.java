package by.btd.commands;

import by.btd.Handlers.MessageHandlers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartCommand implements MessageHandlers {

    public SendMessage SendMessageHandler(Update update) {
        String command = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        return switch (command) {
            case "/start" -> startCommand(chatId);
            case "/help" -> helpCommand(chatId);
            case "/test" -> testkeyboard(chatId);
            default -> null;
        };
    }

    private SendMessage startCommand(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdownV2(true);
        sendMessage.setText("Старт");


        addKeyboardToStartCommand(sendMessage);

        return sendMessage;
    }

    private SendMessage helpCommand(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdownV2(true);
        sendMessage.setText("""
                            Привет ~чемпик~
                            """);

        return sendMessage;
    }

    private SendMessage testConnectToDB(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Попытка создать объект в базе");

        return sendMessage;
    }

    private SendMessage testkeyboard(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdownV2(true);
        sendMessage.setText("Попытка создать объект в базе");

        addTets(sendMessage);

        return sendMessage;
    }

    private void addKeyboardToStartCommand(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();

        row.add("1");
        row.add("2");
        row.add("3");

        keyboard.add(row);

        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    private void addTets(SendMessage sendMessage) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("tyjgrf 1");
        inlineKeyboardButton1.setUrl("https://www.youtube.com/");
        inlineKeyboardButton1.setCallbackData("Button \"Тык\" has been pressed");

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("tyjgrf 2");
//        inlineKeyboardButton2.setUrl("https://habr.com/ru/articles/418905/");
        inlineKeyboardButton2.setCallbackData("testcallbackmessage");

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("tyjgrf 3");
        inlineKeyboardButton3.setSwitchInlineQuery("X~~~~E@EASDZXCX");
        inlineKeyboardButton3.setCallbackData("CallFi4a");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton3);
        keyboardButtonsRow2.add(inlineKeyboardButton2);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }
}
