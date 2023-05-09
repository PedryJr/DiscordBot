package org.example;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.intent.Intent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
public class Run{

    public static int i;
    public static int count;
    public static int summons;
    public static String send;
    public static String feed;
    public static String input;
    public static String src;
    public static String contentsent;
    public static boolean onoff;
    public static ArrayList<String> tags = new ArrayList<>();
    public static java.util.Timer timer;
    public static DiscordApi api;
    Run() throws IOException {

        feed = "1095484188389543977";
        send = "1095484241988550656";

        count = Integer.MAX_VALUE;


        // Log the bot in
        api = new DiscordApiBuilder()
                .setToken("-")
                .addIntents(Intent.MESSAGE_CONTENT)
                .login().join();

        Commands commands = new Commands();
        commands.RegisterCommands();

        api.addMessageCreateListener(new MessageCreateListener());

        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addReactionAddListener(event -> {

            if (event.getUserIdAsString().equals("196415503185608705") || event.getUserIdAsString().equals("249556127539265536")) {

                contentsent = event.getMessage().get().getReadableContent().toString();
                api.getChannelById(send).get().asTextChannel().get().sendMessage(contentsent);

            }

        });

        api.updateActivity(ActivityType.PLAYING, "with the Vapid gods..");



        i = 0;

        String url = "url";

        String content = null;
        URLConnection connection = null;
        try {
            connection =  new URL("url" + input).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

        String html = content;

        Document doc = Jsoup.parse(html);
        Elements image = doc.getElementsByTag("img");

        int i = 1;
        for (Element el : image) {

            if(i == 5){

                src = el.absUrl("src");
                System.out.println("src attribute is : " + src);

            }


            i++;

        }

        Thread thread = new Thread(new FrameThread());
        thread.start();



        timer = new Timer();

        Program program = new Program();

        timer.schedule(program, 10, 1000);

    }

}
