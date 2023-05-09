package org.example;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageSet;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.UncheckedIOException;
public class MessageCreateListener implements org.javacord.api.listener.message.MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

            try{

                Message message = event.getMessage();

                if (message.getContent().contains("!delete")) {

                    int volume = Integer.parseInt(message.getContent().substring(8));

                    MessageSet messages = event.getChannel().getMessagesBefore(volume, message.getId()).join();
                    event.getChannel().deleteMessages(messages);
                    message.delete();

                }

            }catch (UncheckedIOException e){

                event.getChannel().sendMessage("Did not work lul");

            }

    }

}
