package by.btd.handlers;

import by.btd.BotHandlers;
import by.btd.keyboard.ReplyCommandHandler;
import by.btd.kkk;
import by.btd.services.Emoji;
import by.btd.services.LocalService;
import by.btd.services.UnprocessedMessagesService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
//@RequiredArgsConstructor
public class CommandHandler extends BotHandlers {
    public CommandHandler(kkk props) {
        super(props);
    }

    public static SendMessage commandHandler(Update update) {
        String command = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        log.info("Принял запрос");
        return switch (command) {
            case "/start" -> startCommand(chatId);
            case "/help" -> helpCommand(chatId);
            case "/test" -> testCommand(chatId);
            default -> UnprocessedMessagesService.unprocessedMessage(chatId);
        };
    }

    private static SendMessage startCommand(long chatId) {
        SendMessage startCommandMessage = new SendMessage();
        startCommandMessage.setChatId(chatId);
        startCommandMessage.enableMarkdownV2(true);
        startCommandMessage.setText("Старт");

        ReplyCommandHandler.addKeyboardToStartCommand(startCommandMessage);

        return startCommandMessage;
    }

    private static SendMessage helpCommand(long chatId) {
        SendMessage helpCommandMessage = new SendMessage();
        helpCommandMessage.setChatId(chatId);
        helpCommandMessage.enableMarkdownV2(true);
        helpCommandMessage.setText(LocalService.getString("pl", "HELP_COMMAND"));

        return helpCommandMessage;
    }

    private static SendMessage testCommand(long chatId) {
        var testCommandMessage = new SendMessage();
        testCommandMessage.setChatId(chatId);
        testCommandMessage.enableMarkdownV2(true);
        var stringBuilder = new StringBuilder();
        //stringBuilder.append(LocalService.getString("ru", "TEST_COMMAND"));
        stringBuilder.append(Emoji.KISSING_HEART);
        //testCommandMessage.setText(LocalService.getString("ru", "TEST_COMMAND") + Emoji.RELAXED);
        testCommandMessage.setText(stringBuilder.toString());

        return testCommandMessage;
    }

//    private static SendMessage testCommand2(long chatId) {
//        var testCommand2 = new SendMessage();
//        testCommand2.setChatId(chatId);
//        testCommand2.setText("METHOD");
//        GKGK.method1();
//
//        return testCommand2;

    public static void testCommand2(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("fdfd");
        //var botHandlers = new BotHandlers();
        //botHandlers.botSendMessage(sendMessage, chatId);
    }
}
