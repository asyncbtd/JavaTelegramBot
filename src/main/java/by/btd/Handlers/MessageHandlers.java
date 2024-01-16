package by.btd.Handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageHandlers {

    SendMessage SendMessageHandler(Update update);

}