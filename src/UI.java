import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by zaers on 16-Apr-16.
 */
public class UI {
    SystemTray sys_t;
    TrayIcon t_icon;
    MouseAdapter m_adapter;
    public UI(Vector<String> websites){
        if (SystemTray.isSupported()) { //Here we create the Taskbar Icon, if it's supported on this platform
            sys_t = SystemTray.getSystemTray();
            t_icon = new TrayIcon(new ImageIcon("resources/images/systi.png").getImage(), String.format("Monitoring Sites:\n%s\nClick to close", websites.toString()));
            t_icon.setImageAutoSize(true);
            m_adapter = new MouseAdapter() {
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
            showMessage("Test");
        } else {
            System.out.println("Platform not supported.");
            System.exit(4);
        }
    }
    public void showMessage(String title){
        t_icon.displayMessage(title + "updated!", "Click on the app icon to open website", TrayIcon.MessageType.INFO);
    }
}
