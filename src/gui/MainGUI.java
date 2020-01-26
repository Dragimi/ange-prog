package gui;

import models.Adresse;
import models.Reiseagentur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.FileSystems;

public class MainGUI extends JFrame {

    private static final String KUNDEN_GEFUNDEN_PATTERN = "Kunden gefunden (%03d)";

    Reiseagentur magic;
    JLabel lblReiseagentur;
    JTextField tfKundenName;
    JButton btnSearchKundenName;
    JSplitPane splitPaneBottom;
    JLabel lblKundenGefunden;
    JList kundenList;
    JLabel lblKundenNummer;
    JLabel lblGeburtsdatum;
    JLabel lblAdresse;
    JList listReservierungen;

    public MainGUI() {
        super("Reiseagentur");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.initMenu();
        this.initContentView();
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

    private void initContentView() {
        this.getContentPane().setLayout(new BorderLayout(16, 16));

        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());
        panelTop.getInsets().set(16, 16, 16, 16);

        lblReiseagentur = new JLabel("-- noch keine Agentur geladen --");
        lblReiseagentur.setHorizontalAlignment(SwingConstants.CENTER);
        lblReiseagentur.setFont(new Font("Arial", Font.BOLD, 16));
        panelTop.add(lblReiseagentur, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout());
        JLabel kundennameLabel = new JLabel("Kundenname:");
        tfKundenName = new JTextField("", 30);

        btnSearchKundenName = new JButton("Suchen");
        searchPanel.add(kundennameLabel);
        searchPanel.add(tfKundenName);
        searchPanel.add(btnSearchKundenName);
        panelTop.add(searchPanel, BorderLayout.CENTER);

        this.getContentPane().add(panelTop, BorderLayout.NORTH);

        JPanel panelBottomLeft = new JPanel(new BorderLayout());
        lblKundenGefunden = new JLabel(String.format(KUNDEN_GEFUNDEN_PATTERN, 0));
        lblKundenGefunden.getInsets().set(16, 16, 16, 16);
        panelBottomLeft.add(lblKundenGefunden, BorderLayout.NORTH);
        kundenList = new JList();
        JScrollPane kundenScroll = new JScrollPane( kundenList );
        panelBottomLeft.add(kundenScroll, BorderLayout.CENTER);

        JPanel panelBottomRight = new JPanel(new BorderLayout());
        JPanel panelKundenDetails = new JPanel(new GridLayout(3, 2));
        JLabel label1 = new JLabel("Kundennummer: ");
        lblKundenNummer = new JLabel("K-xxx-xxx");
        JLabel label2 = new JLabel("Geburtsdatum: ");
        lblGeburtsdatum = new JLabel("--.--.----");
        JLabel label3 = new JLabel("Adresse: ");
        lblAdresse = new JLabel("--");
        panelKundenDetails.add(label1);
        panelKundenDetails.add(lblKundenNummer);
        panelKundenDetails.add(label2);
        panelKundenDetails.add(lblGeburtsdatum);
        panelKundenDetails.add(label3);
        panelKundenDetails.add(lblAdresse);
        listReservierungen = new JList();
        JScrollPane reservierungenScroll = new JScrollPane(listReservierungen);

        panelBottomRight.add(panelKundenDetails, BorderLayout.NORTH);
        panelBottomRight.add(reservierungenScroll, BorderLayout.CENTER);

        splitPaneBottom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelBottomLeft, panelBottomRight);

        this.getContentPane().add(splitPaneBottom, BorderLayout.CENTER);
        this.pack();
        restoreDefaults();
    }

    private void restoreDefaults() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                splitPaneBottom.setDividerLocation(.999);
            }
        });
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
        if (magic == null){
            // todo register new reiseagentur over gui
            magic = new Reiseagentur(
                    "Magic Holidays Reiseagentur",
                    "DE812524001",
                    new Adresse("Hauptstraße", "5a", 10559, "Berlin")
            );
        }
        magic.initObserver();
        lblReiseagentur.setText(magic.getNamen());
    }
}
