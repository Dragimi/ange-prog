package gui;

import models.Kunde;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class KundenCellRenderer extends JLabel implements ListCellRenderer<Kunde> {

    public KundenCellRenderer() {
        setOpaque(true);
    }

    /**
     * Copy Pase CellRenderer from https://docs.oracle.com/javase/8/docs/api/javax/swing/ListCellRenderer.html
     *
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param cellHasFocus
     * @return
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Kunde> list, Kunde value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(String.format("%s %s", value.getVorname(), value.getNachname()));
        setBorder(new EmptyBorder(8, 8, 8, 8));

        Color background;
        Color foreground;

        // check if this cell represents the current DnD drop location
        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null && !dropLocation.isInsert() && dropLocation.getIndex() == index) {
            background = new Color(0, 30, 40);
            foreground = Color.WHITE;
        } else if (isSelected) {
            // if this cell is selected
            background = new Color(0, 129, 0);
            foreground = Color.WHITE;
        } else {
            // else cell is unselected, and not the DnD drop location
            background = Color.WHITE;
            foreground = new Color(0, 30, 40);;
        }

        setBackground(background);
        setForeground(foreground);

        return this;
    }
}
