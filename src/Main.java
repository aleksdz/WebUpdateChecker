import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

/**
 * Created by zaers on 15-Apr-16.
 */
public class Main {
    public static void main(String[] args) {
        Vector<String> websites = new Vector<>();
        UI ui = new UI(websites);
        try(BufferedReader br = new BufferedReader(new FileReader("resources/websites.txt"))) {
            for(String line; (line = br.readLine()) != null; ) {
                websites.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String string : websites){
            try {
                (new Thread(new PageSearch(string))).start();
                System.out.println(string + "monitor added!");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
