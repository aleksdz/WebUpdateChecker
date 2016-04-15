import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                System.out.println("Added!");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (SystemTray.isSupported()) { //Here we create the Taskbar Icon, if it's supported on this platform
            final SystemTray sys_t = SystemTray.getSystemTray();
            final TrayIcon t_icon = new TrayIcon(new ImageIcon("resources/images/systi.png").getImage(), String.format("Monitoring Sites:\n%s\nClick to close", websites.toString()));
            t_icon.setImageAutoSize(true);
            MouseAdapter m_adapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    sys_t.remove(t_icon);
                    System.exit(0);
                }
            };

            t_icon.addMouseListener(m_adapter);
            try {
                sys_t.add(t_icon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }
}
