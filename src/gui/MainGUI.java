package gui;

import models.Adresse;
import models.Kunde;
import models.Reiseagentur;
import models.Reservierung;
import utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.IconUIResource;
import javax.swing.text.IconView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.FileSystems;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainGUI extends JFrame implements ActionListener {

    private static final String KUNDEN_GEFUNDEN_PATTERN = "Kunden gefunden (%03d)";

    Reiseagentur magic;
    JLabel lblReiseagentur;
    JTextField tfKundenName;
    JButton btnSearchKundenName;
    JSplitPane splitPaneBottom;
    TitledBorder borderKundenGefunden;
    DefaultListModel<Kunde> kundenListModel;
    Kunde selectedKunde = null;

    JLabel lblKundenNummer;
    JLabel lblGeburtsdatum;
    JLabel lblAdresse;
    DefaultListModel<Reservierung> reservierungenListModel;

    public MainGUI() {
        super("Reiseagentur");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.initMenu();
        this.initContentView();
        this.resetLabels();

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
        info_about.setActionCommand("info");
        info_about.addActionListener(this);
        info.add(info_about);

        this.setJMenuBar(menubar);
        this.validate();
    }

    private void initContentView() {
        this.getContentPane().setLayout(new BorderLayout(16, 16));

        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());
        panelTop.setBorder(new EmptyBorder(8, 8, 8, 8));

        lblReiseagentur = new JLabel("-- noch keine Agentur geladen --");
        lblReiseagentur.setHorizontalAlignment(SwingConstants.CENTER);
        lblReiseagentur.setFont(new Font("Arial", Font.BOLD, 16));
        panelTop.add(lblReiseagentur, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout());
        JLabel kundennameLabel = new JLabel("Kundenname:");
        tfKundenName = new JTextField("", 30);

        btnSearchKundenName = new JButton("Suchen");
        btnSearchKundenName.setActionCommand("search");
        btnSearchKundenName.addActionListener(this);
        searchPanel.add(kundennameLabel);
        searchPanel.add(tfKundenName);
        searchPanel.add(btnSearchKundenName);
        panelTop.add(searchPanel, BorderLayout.CENTER);

        this.getContentPane().add(panelTop, BorderLayout.NORTH);

        JPanel panelBottomLeft = new JPanel(new BorderLayout());
        borderKundenGefunden = new TitledBorder(String.format(KUNDEN_GEFUNDEN_PATTERN, 0));
        panelBottomLeft.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(14, 14, 14, 14), borderKundenGefunden));

        // Kundenliste initialisieren
        JComponent kundenScroll = this.initKundenScrollView();
        panelBottomLeft.add(kundenScroll, BorderLayout.CENTER);

        JPanel panelBottomRight = new JPanel(new BorderLayout());
        panelBottomRight.setBorder(new EmptyBorder(16, 16, 16, 16));
        JPanel panelKundenDetails = new JPanel(new GridLayout(3, 2));
        JLabel label1 = new JLabel("Kundennummer: ");
        lblKundenNummer = new JLabel();
        JLabel label2 = new JLabel("Geburtsdatum: ");
        lblGeburtsdatum = new JLabel();
        JLabel label3 = new JLabel("Adresse: ");
        lblAdresse = new JLabel();
        panelKundenDetails.add(label1);
        panelKundenDetails.add(lblKundenNummer);
        panelKundenDetails.add(label2);
        panelKundenDetails.add(lblGeburtsdatum);
        panelKundenDetails.add(label3);
        panelKundenDetails.add(lblAdresse);

        JComponent reservierungenView = this.initReservierungenView();

        panelBottomRight.add(panelKundenDetails, BorderLayout.NORTH);
        panelBottomRight.add(reservierungenView, BorderLayout.CENTER);

        splitPaneBottom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelBottomLeft, panelBottomRight);

        this.getContentPane().add(splitPaneBottom, BorderLayout.CENTER);
        restoreDefaults();
    }

    private JComponent initKundenScrollView() {
        kundenListModel = new DefaultListModel<>();
        JList<Kunde> listKunden = new JList<>(kundenListModel);
        listKunden.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listKunden.setLayoutOrientation(JList.VERTICAL);
        listKunden.setVisibleRowCount(-1);
        listKunden.setCellRenderer(new KundenCellRenderer());

        listKunden.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    int index = listKunden.getSelectedIndex();
                    if (index == -1) {
                        //No selection, disable fire button.

                    } else {
                        //Selection, enable the fire button.
                        MainGUI.this.selectedKunde = kundenListModel.get(index);
                        MainGUI.this.updateContent();
                    }
                }
            }
        });
        return new JScrollPane(listKunden);
    }

    private JComponent initReservierungenView() {
        reservierungenListModel = new DefaultListModel<>();
        JList<Reservierung> listReservierungen = new JList<>(reservierungenListModel);
        listReservierungen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listReservierungen.setLayoutOrientation(JList.VERTICAL);
        listReservierungen.setVisibleRowCount(-1);
        listReservierungen.setCellRenderer(new ReservierungenCellRenderer());

        listReservierungen.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    int index = listReservierungen.getSelectedIndex();
                    if (index == -1) {
                        //No selection, disable fire button.

                    } else {
                        //Selection, enable the fire button.

                    }
                }
            }
        });
        return new JScrollPane(listReservierungen);
    }

    private void restoreDefaults() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                splitPaneBottom.setDividerLocation(.5);
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
        if (magic == null) {
            // todo register new reiseagentur over gui
            magic = new Reiseagentur(
                    "Magic Holidays Reiseagentur",
                    "DE812524001",
                    new Adresse("Hauptstraße", "5a", 10559, "Berlin")
            );
        }
        magic.initObserver();
        this.setTitle(magic.getNamen());
        lblReiseagentur.setText(magic.getNamen());
        this.updateKundenList(null);
    }

    private void resetLabels() {
        lblKundenNummer.setText("K-xxx-xxx");
        lblGeburtsdatum.setText("--.--.----");
        lblAdresse.setText("--");
        borderKundenGefunden.setTitle(String.format(KUNDEN_GEFUNDEN_PATTERN, 0));
    }

    private void updateKundenList(List<Kunde> foundKunden) {
        kundenListModel.clear();
        if (foundKunden == null) {
            for (Kunde k : magic.getKunden().values()) {
                kundenListModel.addElement(k);
            }
            borderKundenGefunden.setTitle(String.format(KUNDEN_GEFUNDEN_PATTERN, magic.getKunden().size()));
        } else {
            for (Kunde k : foundKunden) {
                kundenListModel.addElement(k);
            }
            System.out.println(foundKunden.size());
            borderKundenGefunden.setTitle(String.format(KUNDEN_GEFUNDEN_PATTERN, foundKunden.size()));
        }
        this.repaint();
    }

    private void updateContent() {
        if (this.selectedKunde == null) {
            this.resetLabels();
        } else {
            lblKundenNummer.setText(this.selectedKunde.getKundenNummer());
            lblGeburtsdatum.setText(Utils.dateToStr(this.selectedKunde.getGeburtstag()));
            lblAdresse.setText(this.selectedKunde.getAdresse().shortStringRepresentation());

            reservierungenListModel.clear();
            for(Reservierung r: this.selectedKunde.getReservierungen().values()) {
                reservierungenListModel.addElement(r);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "info": {
                String path = FileSystems.getDefault().getPath("resources").toAbsolutePath().toString() + "/" + "img/htw-logo.png";
                ImageIcon icon = new ImageIcon(path);
                JOptionPane.showMessageDialog(MainGUI.this,
                        "Dragana Mitrovic\n" +
                                "MatrikelNr.: s0559694\n" +
                                "Hochschule für Technik und Wirtschaft Berlin\n" +
                                "s0559694@htw-berlin.de",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE,
                        icon);
                break;
            }
            case "search": {
                this.resetLabels();
                this.reservierungenListModel.clear();

                String eingabe = tfKundenName.getText();
                if (eingabe != null && !eingabe.isEmpty()) {
                    List<Kunde> foundKunden = magic.getKunden().values().stream()
                            .filter((k) ->
                                    k.getName().toLowerCase().trim().replaceAll("\\s", "")
                                            .contains(eingabe.toLowerCase().trim().replaceAll("\\s", "")))
                            .collect(Collectors.toList());

                    this.updateKundenList(foundKunden);
                } else {
                    this.updateKundenList(null);
                }
                break;
            }
        }
    }
}
