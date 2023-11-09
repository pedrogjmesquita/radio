package radio.client.pages.components;

import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.table.DefaultTableCellRenderer;

public class RadioList extends JFrame {
private Hashtable<String, String[]> radioList;

public RadioList(ResourceBundle bn){
    // Create a table model with three columns
    DefaultTableModel model = new DefaultTableModel();
    
    model.addColumn(bn.getString("column_1"));
    model.addColumn(bn.getString("column_2"));
    model.addColumn(bn.getString("column_3"));
    createList();

    // Add data to the table
    for (String key : radioList.keySet()) {
        model.addRow(new Object[] { key, radioList.get(key)[0], radioList.get(key)[1] });
    }

    // Create a JTable with the model
    JTable table = new JTable(model);

    // Set the font size and center the text
    table.setFont(new Font("Arial", Font.PLAIN, 16));
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    table.setDefaultRenderer(String.class, centerRenderer);

    // Highlight the column titles
    table.getTableHeader().setBackground(Color.lightGray);
    table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

    // Add the table to a scroll pane
    JScrollPane scrollPane = new JScrollPane(table);

    // Add the scroll pane to the frame
    add(scrollPane);

    // Set the frame properties
    setTitle("Radio List");
    setSize(500, 500);
    setLocationRelativeTo(null);
    setVisible(true);
}

private void createList() {
    radioList = new Hashtable<String, String[]>();
    radioList.put("76.7 MHz",new String[] {"Jovem Pan News", "Notícias"});
    radioList.put("77.5 MHz",new String[] {"Radio Capital", "Variado"});
    radioList.put("77.9 MHz",new String[] {"Cultura Brasil", "Musica"});
    radioList.put("79.5 MHz",new String[] {"Radio São Paulo", "Variádo"});
    radioList.put("79.9 MHz",new String[] {"Nova Difusora", "Musica"});
    radioList.put("80.9 MHz",new String[] {"Radio RBC FM", "Variado"});
    radioList.put("81.9 MHz",new String[] {"Radio ABC", "Programas de Audio"});
    radioList.put("82.9 MHz",new String[] {"Melhor FM", "Notícias"});
    radioList.put("83.1 MHz",new String[] {"Vibe Mundial", "Programas de Audio"});
    radioList.put("84.3 MHz",new String[] {"Nova Morada", "Musicas"});
    radioList.put("84.7 MHz",new String[] {"Gru FM", ""});
    radioList.put("86.3 MHz",new String[] {"Bandeirantes", "Notícias"});
    radioList.put("87.1 MHz",new String[] {"Radio Nacional", "Notícias"});
    radioList.put("88.1 MHz",new String[] {"Gazeta FM", "Musicas"});
    radioList.put("88.5 MHz",new String[] {"Radio Laser FM", "Notícias"});
    radioList.put("89.1 MHz",new String[] {"Radio Rock", "Musicas"});
    radioList.put("89.7 MHz",new String[] {"Novabrasil FM", "Variado"});
    radioList.put("90.1 MHz",new String[] {"Radio Gospel", "Gospel"});
    radioList.put("90.5 MHz",new String[] {"CBN", "Noticias"});
    radioList.put("91.3 MHz",new String[] {"Radio Disney", "Musicas"});
    radioList.put("92.5 MHz",new String[] {"Radio Kiss", "Musicas"});
    radioList.put("92.9 MHz",new String[] {"Massa FM", "Variados"});
    radioList.put("93.3 MHz",new String[] {"Estilo FM", "Musicas"});
    radioList.put("93.7 MHz",new String[] {"Radio USP FM", "Notícias"});
    radioList.put("94.1 MHz",new String[] {"Nossa Radio ", "Variados"});
    radioList.put("94.7 MHz",new String[] {"Antena 1", "Musicas"});
    radioList.put("95.3 MHz",new String[] {"Nativa FM", "Musicas"});
    radioList.put("96.1 MHz",new String[] {"Band FM", "Musicas"});
    radioList.put("96.9 MHz",new String[] {"BandNews FM", "Notícias"});
    radioList.put("97.7 MHz",new String[] {"Energia 97 FM", "Musicas"});
    radioList.put("98.5 MHz",new String[] {"Metropolitana", "Musicas"});
    radioList.put("99.5 MHz",new String[] {"Rede Aleluia", "Notícias"});
    radioList.put("100.1 MHz",new String[] {"Transamérica", "Musicas"});
    radioList.put("100.9 MHz",new String[] {"Jovem Pan FM", "Musicas"});
    radioList.put("101.7 MHz",new String[] {"Alpha FM", "Musicas"});
    radioList.put("102.5 MHz",new String[] {" Imprensa FM", "Notícias"});
    radioList.put("103.3 MHz",new String[] {"Cultura FM", "Musicas"});
    radioList.put("104.1 MHz",new String[] {"Top FM", "Musicas"});
    radioList.put("105.7 MHz",new String[] {"Musical FM", "Musicas"});
    radioList.put("106.3 MHz",new String[] {"Radio Mix FM", "Musicas"});
    radioList.put("107.3 MHz",new String[] {"Eldorado FM", "Musicas"});
    radioList.put("107.9 MHz",new String[] {"Tropical FM", "Musicas"});
    }
}
