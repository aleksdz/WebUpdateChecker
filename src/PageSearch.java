import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by zaers on 15-Apr-16.
 */
public class PageSearch implements Runnable {
    private String ws, ws_name;
    public PageSearch(String name) throws IOException {
        ws_name = name;
        ws = new Scanner(new URL(ws_name).openStream(), "UTF-8").useDelimiter("\\A").next();
    }

    public void run() {
        while (true) {
            try {
                System.out.println(String.format("Checking %s", ws_name));
                String u_s = new Scanner(new URL(ws_name).openStream(), "UTF-8").useDelimiter("\\A").next();
                if (!u_s.equals(ws)) {
                    java.awt.Desktop.getDesktop().browse(new URI(ws_name));
                }
                try {
                    Thread.sleep(360000);
                } catch (InterruptedException e) {
                    System.exit(1); //OS screwed our program
                }

            } catch (IOException e) {
                System.exit(2);   //Blog is kill
            } catch (URISyntaxException e) {
                System.exit(3); //YouTube is kill
            }
        }
    }
}

