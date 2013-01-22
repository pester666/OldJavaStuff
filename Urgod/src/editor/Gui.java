package editor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class Gui extends JFrame{

    final int MAP_SIZE = 16;
    String[] names;
    DefaultListModel spriteListModel = new DefaultListModel();
    Integer[][] spriteIdArray = new Integer[MAP_SIZE][3]; 
    JPanel map;
    MyButton[] btn;
    JList spriteList;

    public static void main(String[] args) {
        
        initLookAndFeel();
        Gui g = new Gui();
        g.init();
    }
    
    private static void initLookAndFeel() {
        //PlasticLookAndFeel.setPlasticTheme(new SkyGreen());
        try {
            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
        } catch (Exception e) {
        }

    }
    
    public Gui(){
        
        JFrame frame = new JFrame("Wambo");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel sideBar = new JPanel();
        JLabel lbSprites = new JLabel("Sprites");
        JPanel buttonBar = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu();
        JMenuItem mBeenden = new JMenuItem();
        spriteList = new JList(spriteListModel);
        JScrollPane scrPane = new JScrollPane(spriteList);
        
        ImageIcon iconNeu = new ImageIcon("images/neu.png", "Karte leeren");
        ImageIcon iconExportieren = new ImageIcon("images/ex.png", "Karte exportieren");
        ImageIcon iconPlus = new ImageIcon("images/plus.png", "Vergrößern");
        ImageIcon iconMinus = new ImageIcon("images/minus.png", "Verkleinern");
        
        JButton btnNeu = new JButton(iconNeu);
        btnNeu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < btn.length; i++) {
                    btn[i].setIcon(null);
                }
            }
        });
        
        JButton btnExportieren = new JButton(iconExportieren);
        
        JButton btnPlus = new JButton(iconPlus);
        btnPlus.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < btn.length; i++) {
                    int hoehe = btn[i].getHeight()+10;
                    int breite = btn[i].getWidth()+10;
                    btn[i].setBounds(btn[i].getX(),btn[i].getY(), breite, hoehe);
                    btn[i].setLocation(btn[i].getLocation().x + (10), btn[i].getLocation().y + (10));
                }
            }
        });
        
        JButton btnMinus = new JButton(iconMinus);
        btnMinus.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = 1;
                for (int i = 0; i < btn.length; i++) {
                    int hoehe = btn[i].getHeight()-10;
                    int breite = btn[i].getWidth()-10;
                    btn[i].setBounds(btn[i].getX(),btn[i].getY(), breite, hoehe);
                    btn[i].setLocation(btn[i].getLocation().x - (column*10), btn[i].getLocation().y - (row*10));
                    
                    if(column < MAP_SIZE){
                        column++;
                    }else{
                        row++;
                        column = 0;
                    }
                    

                }
            }
        });
        
        map = new JPanel();
        btnErstellen();
        map.setLayout(new GridLayout(MAP_SIZE, MAP_SIZE));
        JScrollPane scrMapEditor = new JScrollPane(map);
        
        menu.setText("Einstellungen");
        mBeenden.setText("Beenden");
        
        mBeenden.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        
        menu.add(mBeenden);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        
        sideBar.setLayout(new BorderLayout());
        sideBar.add(lbSprites, BorderLayout.NORTH);
        sideBar.add(scrPane, BorderLayout.CENTER);
        sideBar.add(new JLabel(" "), BorderLayout.SOUTH);
        
        buttonBar.add(btnNeu);
        buttonBar.add(btnExportieren);
        buttonBar.add(btnPlus);
        buttonBar.add(btnMinus);
        
        frame.setLayout(new BorderLayout());
        frame.add(sideBar, BorderLayout.WEST);
        frame.add(buttonBar, BorderLayout.NORTH);
        frame.add(scrMapEditor, BorderLayout.CENTER);

        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    private void init() {
        String[] sprites = {"Wald", "Sumpf", "Fels", "Sand", "Haus", "Blume", "Wasser", "Wiese"};
        for (int i = 0; i < sprites.length; i++) {
            spriteListModel.addElement(sprites[i]);
        }

    }
    
    private void btnErstellen(){
        
        int count = (MAP_SIZE * MAP_SIZE);
        names = new String[count];
        for (int i = 0; i < names.length; i++) {
            names[i] = Integer.valueOf(i).toString();
        }

        btn = new MyButton[count];
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                count = count-1;
                btn[count] = new MyButton(names[count], count);
                map.add(btn[count], i, j);
                btn[count].setText(" ");
                btn[count].addActionListener(new listen());
            }
        }
    }
    
    class listen implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(spriteList.getSelectedIndex() > 0){
            Component comp = new JButton();
            comp = (Component) e.getSource();
            
            String imgName = (String)spriteListModel.getElementAt(spriteList.getSelectedIndex());
            ((AbstractButton) map.getComponentAt(comp.getX(), comp.getY())).setIcon(new ImageIcon("images/"+imgName+".PNG"));
            ((AbstractButton) map.getComponentAt(comp.getX(), comp.getY())).setRolloverEnabled(true);                        
            } 
        }
    }
}
