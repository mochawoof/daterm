import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Main {
    private static JFrame f;
    public static void main(String[] args) {
        JFrame mf = new JFrame("Daterm");
        
        // Copy properties of old frame to new one, if exists
        if (f != null) {
            mf.setSize(f.getSize());
            mf.setLocation(f.getLocation());
            f.dispose();
        } else {
            mf.setSize(600, 400);
        }
        f = mf;
        
        // Apply theme
        try {
            if (Settings.get("Menu_Theme").equals("System")) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } else {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }
            SwingUtilities.updateComponentTreeUI(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setIconImage(Res.getAsImage("icon.png"));
        
        JComponent p = new JComponent() {
            public void paintComponent(Graphics g) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        f.setContentPane(p);
        
        JMenuBar mb = new JMenuBar();
        f.setJMenuBar(mb);
        
        JMenu am = new JMenu("App");
        mb.add(am);
        JMenuItem sam = new JMenuItem("Settings");
        sam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int s = Settings.showEditWindow(f);
                if (s == Settings.OK || s == Settings.RESET) {
                    main(args);
                }
            }
        });
        am.add(sam);
        JMenuItem aam = new JMenuItem("About");
        aam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f, "Daterm v1.0", "About", JOptionPane.PLAIN_MESSAGE, new ImageIcon(f.getIconImage()));
            }
        });
        am.add(aam);
        
        f.setVisible(true);
    }
}