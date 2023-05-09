package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimerTask;

import static org.example.Run.*;
public class Program extends TimerTask {
    @Override
    public void run() {

        if(onoff){

            double i = Math.random();

            int y = (int) Math.round((i*100000)/2) + 7000000;

            input = Integer.toString(y);

            String content = null;
            URLConnection connection = null;

            try {

                connection =  new URL("-" + input).openConnection();
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
            Elements section = doc.getElementsByTag("section");

            boolean param = false;
            boolean matchtags = false;

            for (Element el : section){

                if(el.attributes().get("data-tags").contains("-") || el.attributes().get("data-tags").contains("-")){

                    param = true;
                    break;

                }

                ArrayList<String> tempTags = new ArrayList<>();
                for(String tag : tags){

                    if(el.attributes().get("data-tags").contains(" " + tag + " ")){

                        tempTags.add(tag);

                    }

                }

                if(tempTags.equals(tags)){

                    matchtags = true;

                }



            }

            for (Element el : image) {

                if(!el.parent().toString().contains("picture")){

                    continue;

                }

                if(el.absUrl("src").contains("img3")){

                    if(count == 0){

                        onoff = false;
                        api.getChannelById(feed).get().asTextChannel().get().sendMessage("Finished summoning " + summons + " images!");
                        api.getChannelById(feed).get().asTextChannel().get().sendMessage(tags.toString());
                        tags.clear();
                        count = Integer.MAX_VALUE;
                        break;

                    }


                    if(!param && matchtags){

                        src = el.absUrl("src");
                        System.out.println("src attribute is : " + src);
                        api.getChannelById(feed).get().asTextChannel().get().sendMessage(src);
                        summons++;
                        count--;
                        break;

                    }

                }

            }

        }


    }

}
