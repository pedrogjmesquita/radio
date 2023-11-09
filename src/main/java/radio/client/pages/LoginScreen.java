package radio.client.pages;


import javax.swing.*;


import radio.client.pages.components.RadioList;
import radio.client.utils.Client;
import radio.client.utils.CryptoDummy;
import radio.client.utils.TextHandler;
import radio.client.utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;


public class LoginScreen extends JFrame implements ActionListener {
    private JLabel titleLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, cancelButton;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private JComboBox<String> comboBox;
    private ResourceBundle bn;
    private String language;
    private final String[] languages = {"English","Português(Br)", "Français", "Español", "Italiano", "Deutsch"};
    private JFrame frame;
    private boolean loogedIn = false;
    private Client client;
    
    
    public LoginScreen(Client cliente) {
        // Set up the JFrame
        setTitle("Login Screen");
        
        client = cliente;
        // Create the components
        bn = ResourceBundle.getBundle("bundle", getPreferedLanguage());
        this.language = Utils.getLangNameFromLocal(bn.getLocale());
        initComponents();   
        
        // Add action listeners to the buttons
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
        comboBox.addActionListener(this);
        menuItem.addActionListener(this);
        
        // Set up the layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(comboBox, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(titleLabel, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(usernameLabel, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(usernameField, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(passwordLabel, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(passwordField, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(loginButton, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(cancelButton, constraints);
        
        add(panel);
        setJMenuBar(menuBar);
        setVisible(true);
        setMinimumSize(new Dimension(450, 300));
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponents(){
        titleLabel = new JLabel(bn.getString("title_label"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel = new JLabel(bn.getString("username_label"));
        passwordLabel = new JLabel(bn.getString("password_label"));
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton(bn.getString("login_button"));
        cancelButton = new JButton(bn.getString("cancel_button"));
        comboBox = new JComboBox<>(languages);
        comboBox.setSelectedItem(this.language);
        menuBar = new JMenuBar();
        menu = new JMenu(bn.getString("menu"));
        menuItem = new JMenuItem(bn.getString("menu_item"));
        menu.add(menuItem);
        menuBar.add(menu);
    }

    private void renameComponents(){
        titleLabel.setText(bn.getString("title_label"));
        usernameLabel.setText(bn.getString("username_label"));
        passwordLabel.setText(bn.getString("password_label"));
        loginButton.setText(bn.getString("login_button"));
        cancelButton.setText(bn.getString("cancel_button"));
        menu.setText(bn.getString("menu"));
        menuItem.setText(bn.getString("menu_item"));
        comboBox.setSelectedItem(this.language);
    }

    private Locale getPreferedLanguage(){
        TextHandler th = new TextHandler();
        th.abreArquivo();
        String preferedLanguage = th.leArquivo();
        th.fechaArquivo();
        return new Locale(preferedLanguage);
    }

    private void setPreferedLanguage(String language){
        TextHandler th = new TextHandler();
        th.abreArquivo();
        th.escreveArquivo(language);
        th.fechaArquivo();
    }

    private void selectLanguage(String language){
        Locale locale = new Locale(Utils.getLocalFromLangName(language));
        bn = ResourceBundle.getBundle("bundle", locale);
    }

    private void openTable(){
        if(frame != null){
            frame.dispose();
        }
        frame = new RadioList(bn);
    }

    public boolean isLoggedIn(){
        return loogedIn;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Handle login button click
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            verifyLogin(username, password);
            System.out.println(loogedIn);
            if(loogedIn){
                dispose();
            } else{
                JOptionPane.showMessageDialog(this, bn.getString("login_error"));
            }

        } else if (e.getSource() == cancelButton) {
            dispose();
        } else if (e.getSource() == comboBox) {
            selectLanguage(comboBox.getSelectedItem().toString());
            setPreferedLanguage(bn.getLocale().toString());
            this.language = Utils.getLangNameFromLocal(bn.getLocale());
            renameComponents();
            if(frame != null){
                openTable();
            }
        } else if (e.getSource() == menuItem) {
            openTable();
        }
    }

    private void verifyLogin(String username, String password) {
        try{
            byte[] bUsername = username.getBytes("ISO-8859-1");
            byte[] bPassword = password.getBytes("ISO-8859-1");
            username = CryptoDummy.cifraTexto(bUsername, 8);
            password = CryptoDummy.cifraTexto(bPassword, 3);
        }catch(Exception e){}
        String msg = username+"  "+password;
        client.sendMessage(msg);
        checkLoginResponse();
    }

    private void checkLoginResponse(){
        String msg;
        while((msg = client.getMessage()) == null){
            try{
                Thread.sleep(100);
            } catch(Exception e){}
        }
        if(msg.equals("true")){
            loogedIn =  true;
        } else{
            loogedIn =  false;
        }
    }

}
