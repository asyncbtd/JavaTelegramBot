package by.btd.Handlers;

import by.btd.commands.StartCommand;
import by.btd.commands.StickerHand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class BotHandlers extends TelegramLongPollingBot {

    private final StickerHand stickerHand;
    private final StartCommand startCommand;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.name}")
    private String botName;

    public BotHandlers(StickerHand stickerHand, StartCommand startCommand) {
        this.stickerHand = stickerHand;
        this.startCommand = startCommand;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText().replaceAll("\n", " ");
            if (messageText.startsWith("/")) {
                log.info("Bot take command [{}] from chatId [{}]", messageText, chatId);
                sendAnyMessage(startCommand.SendMessageHandler(update), chatId);
            } else {
                log.info("Bot take text [{}] from chatId [{}]", messageText, chatId);
            }
        } else if (update.hasMessage() && update.getMessage().hasSticker()) {
            long chatId = update.getMessage().getChatId();
            sendAnyMessage(stickerHand.SendMessageHandler(update), chatId);
        } else if (update.hasCallbackQuery()) {
            var callBackMessage = update.getCallbackQuery();
            System.out.println(update.getCallbackQuery().getData());
            System.out.println(callBackMessage);
            System.out.println("ПОЛУЧИЛ testcallbackmessage");
        }
    }

    public void sendAnyMessage(SendMessage message, long chatId) {
        try {
            execute(message);
            log.info("Bot sent message to chatId [{}]", chatId);
        } catch (TelegramApiException e) {
            log.error("Error in method sendAnyMessage [{}]", e.getMessage());
        }
    }
}