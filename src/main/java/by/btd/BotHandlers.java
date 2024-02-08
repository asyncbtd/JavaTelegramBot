package by.btd;

import by.btd.handlers.*;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
public class BotHandlers extends TelegramLongPollingBot {

    private final kkk props;

    @Value("${telegram.bot.name}")
    private String BOT_TOKEN;

    @Value("${telegram.bot.name}")
    private String BOT_NAME;

    @Override
    public String getBotToken() {
        return "6368107490:AAGA_58ftfdg-MgT1OQDomrPfAjqYrcvgXM";
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/exit")) {
                System.exit(0);
            } else if (messageText.startsWith("/")) {
                log.info("Bot take command [{}] from chatId [{}]", messageText, chatId);
                botSendMessage(CommandHandler.commandHandler(update), chatId);
            } else {
                log.info("Bot take text [{}] from chatId [{}]", messageText, chatId);
                CommandHandler.testCommand2(chatId);
                //botSendMessage(TextHandler.textHandler(update), chatId);
            }
        } else if (update.hasMessage() && update.getMessage().hasSticker()) {
            long chatId = update.getMessage().getChatId();
            log.info("Bot take sticker [{}] from chatId [{}]", update.getMessage().getSticker().getSetName() ,chatId);
            //botSendMessage(StickerHandler.stickerHandler(update), chatId);
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
        } else if (update.hasEditedMessage()) {
            long EditChatId = update.getEditedMessage().getChatId();
            int messageId = update.getEditedMessage().getMessageId();
            String messageNewText = update.getEditedMessage().getText();

            log.info("Message [{}] from chat [{}] was changed from [{}] to []", messageId, EditChatId, messageNewText);

            SendMessage message = new SendMessage();
            message.setChatId(EditChatId);
            message.setText("ТЫ ЧЁ АХУЕЛ? \uD83E\uDD28\uD83E\uDD28\uD83E\uDD28");
            //botSendMessage(message, EditChatId);

            Thread.sleep(2500);

            try {
                EditMessageText editMessageText = new EditMessageText();
                editMessageText.setChatId(update.getEditedMessage().getChatId());
                editMessageText.setText("Ты ничего не видел");
                editMessageText.setMessageId(update.getEditedMessage().getMessageId() + 1); //FIXME
                execute(editMessageText);
            } catch (Exception eae) {
                System.out.println(eae);
            }
        }
    }

    public void botSendMessage(SendMessage message, long chatId) {
        try {
            execute(message);
            log.info("Bot sent message to chatId [{}]", chatId);
        } catch (Exception e) {
            log.error("Error in method botSendMessage [{}]", e.getMessage());
        }
    }
}
