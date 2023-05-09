package org.example;

import java.util.ArrayList;
import java.util.Timer;

import static org.example.Run.*;
public class Commands {

    ArrayList<String> avalibleTags = new ArrayList<>();

    public void RegisterCommands(){

        avalibleTags.add("tag");
        avalibleTags.add("tag");
        avalibleTags.add("tag");
        avalibleTags.add("tag");
        avalibleTags.add("tag");
        avalibleTags.add("tag");
        avalibleTags.add("tag");
        avalibleTags.add("tag");
        avalibleTags.add("tag");
        avalibleTags.add("tag");

        api.addMessageCreateListener(event -> {

            int i = 0;

            if (event.getMessage().getReadableContent().equals("!start")) {

                Program program = new Program();
                timer.cancel();
                timer = new Timer();
                timer.schedule(program, 10, 1000);
                if(!onoff){
                    event.getChannel().sendMessage(event.getMessageAuthor().getDisplayName() + " has started the bot!");
                }else{
                    event.getChannel().sendMessage(event.getMessageAuthor().getDisplayName() + ".. The bot is already working wonders!");
                }
                onoff = true;


            } else if (event.getMessage().getReadableContent().equals("!stop")) {

                tags.clear();

                if(onoff){
                    event.getChannel().sendMessage(event.getMessageAuthor().getDisplayName() + " has stopped the bot!");
                }else{
                    event.getChannel().sendMessage(event.getMessageAuthor().getDisplayName() + ", the bot isn't doing anything right now..");
                }
                onoff = false;


            }else if (event.getMessage().getReadableContent().contains("!summon")) {

                timer.cancel();
                timer = new Timer();
                Program program = new Program();
                timer.schedule(program, 10, 1);


                summons = 0;

                i = 0;

                boolean hasArgs = false;
                while(true){

                    try{

                        i++;
                        count = Integer.parseInt(event.getMessage().getReadableContent().substring(8, i + 8));

                    }catch (NumberFormatException e){

                        event.getChannel().sendMessage(e.getMessage());
                        i--;
                        hasArgs = true;
                        break;

                    }catch (IndexOutOfBoundsException e){

                        event.getChannel().sendMessage(e.getMessage());
                        break;

                    }

                }

                try{

                    if(hasArgs){
                        count = Integer.parseInt(event.getMessage().getReadableContent().substring(8, i + 8));
                    }else{
                        count = Integer.parseInt(event.getMessage().getReadableContent().substring(8));
                    }
                    onoff = true;
                    event.getChannel().sendMessage(event.getMessageAuthor().getDisplayName() + " has summoned " + count + " images!");

                }catch (NumberFormatException ignored){



                }

            }

            for(String aTag : avalibleTags){

                if (event.getMessage().getReadableContent().contains(aTag) && !(event.getMessageAuthor().getId() == 1095432727915933717L)){

                    tags.add(aTag);

                }

            }

        });

    }

}
