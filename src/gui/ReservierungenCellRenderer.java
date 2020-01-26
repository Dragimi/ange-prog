package gui;

import models.Flugreservierung;
import models.Hotelreservierung;
import models.Reservierung;
import utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReservierungenCellRenderer extends JLabel implements ListCellRenderer<Reservierung> {

    public ReservierungenCellRenderer()  {
        setOpaque(true);
    }

    /**
     *
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param cellHasFocus
     * @return
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Reservierung> list, Reservierung value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof Flugreservierung) {
            setText(String.format("%s - %s - Flugnummer %s", value.getReservierungsNr(), Utils.dateToStr(value.getDatum()), ((Flugreservierung) value).getFlugnr()));
        } else if (value instanceof Hotelreservierung) {
            setText(String.format("%s - %s - beim %s", value.getReservierungsNr(), Utils.dateToStr(value.getDatum()), ((Hotelreservierung) value).getHotelname()));
        } else {
            setText("<== Fehler 001 ==>");
        }
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
            background = new Color(0, 128, 128);
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
