package gui;

import models.Reiseagentur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.FileSystems;

public class MainGUI extends JFrame {

    Reiseagentur magic;

    public MainGUI() {
        super("Reiseagentur");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.initMenu();
        this.loadMagic();

        this.setSize(new Dimension(1200, 800));
        this.setVisible(true);
    }

    private void initMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu info = new JMenu("Info");
        info.setMnemonic(KeyEvent.VK_I);
        menubar.add(info);

        JMenuItem info_about = new JMenuItem("About");
        info_about.setMnemonic(KeyEvent.VK_A);
        info.add(info_about);

        this.setJMenuBar(menubar);
        this.validate();
    }

    private void loadMagic() {
        try {
            String path = FileSystems.getDefault().getPath("resources").toAbsolutePath().toString() + "/";
            String filename = "data.ser";
            FileInputStream fin = new FileInputStream(path + filename);
            ObjectInputStream ois = new ObjectInputStream(fin);

            this.magic = (Reiseagentur) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        magic.initObserver();
    }
}
