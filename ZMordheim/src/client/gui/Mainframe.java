package client.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Mainframe extends JFrame{

//    private Tavern tv;
//    private TitleScreen title;
//    private Hire hire;
//    private HireTroops hireTroops;
//    private Load load;
    private MenuPanel[] panels;
    
    public Mainframe(){
        this.setTitle("Mordheim");
        this.setSize(500, 520);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar mbar = new JMenuBar();
        JMenu m = new JMenu("What is your wish master?");
        JMenuItem mCreate = new JMenuItem("Create a Warband!");
        JMenuItem mLoad = new JMenuItem("Get me the old ones!");
        JMenuItem mExit = new JMenuItem("Hell i dont go in there!");
        m.add(mCreate);
        m.add(mLoad);
        m.add(mExit);
        mbar.add(m);
//        this.setJMenuBar(mbar);
        panels = new MenuPanel[]{
          new TitleScreen(true),
          new Tavern(false),
          new Hire(false),
          new HireTroops(false),
          new Load(false)
        };
//        title = new TitleScreen(true);
//        tv = new Tavern(false);
//        hire = new Hire(false);
//        load = new Load(false);
//        hireTroops = new HireTroops(false);
        for (int i = 0; i < panels.length; i++) {
            panels[i].addComponentListener(heyListen);
        }
        for (int i = 1; i < panels.length; i++) {
            panels[i].setVisible(false);
        }
        this.add(panels[0]);
//        title.addComponentListener(heyListen);
//        tv.addComponentListener(heyListen);
//        hire.addComponentListener(heyListen);
//        load.addComponentListener(heyListen);
//        hireTroops.addComponentListener(heyListen);
//        this.add(title);
        this.setResizable(false);
        
        mCreate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO create a warband
            }
            
        });
        mLoad.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO create a warband
            }
            
        });
        mExit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(JFrame.EXIT_ON_CLOSE);
            }
            
        });
    }
    
    private ComponentListener heyListen = new ComponentListener(){
        @Override
        public void componentResized(ComponentEvent e) {
            
        }

        @Override
        public void componentMoved(ComponentEvent e) {
            
        }

        @Override
        public void componentShown(ComponentEvent e) {
            
        }

        @Override
        public void componentHidden(ComponentEvent e) {
            changePanel();
        }
    };
    
    public void showFrame() {   
        this.setVisible(true);
    }
        
    private void changePanel(){
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels.length; j++) {
                if (panels[i].getPanelToActive() == panels[j].getPanelId()) {
                    this.add(panels[j]);
                    this.panels[j].setActive(true);
                } else {
                    this.remove(panels[j]);
                    this.panels[j].setActive(false);
                }
            }
        }
//        if(Hire.isActive){
//            remove(title);
//            remove(tv);
//            remove(load);
//            remove(hireTroops);
//            add(hire);
//            tv.setActive(false);
//            title.setActive(false);
//            hire.setActive(true);
//            load.setActive(false);
//            hireTroops.setActive(false);
//        }else if(Load.isActive){
//            remove(title);
//            remove(hire);
//            remove(tv);
//            remove(hireTroops);
//            add(load);
//            tv.setActive(false);
//            title.setActive(false);
//            hire.setActive(false);
//            load.setActive(true);
//            hireTroops.setActive(false);
//        }else if(Tavern.isActive){
//            remove(title);
//            remove(hire);
//            remove(load);
//            remove(hireTroops);
//            add(tv);
//            tv.setActive(true);
//            title.setActive(false);
//            hire.setActive(false);
//            load.setActive(false);
//            hireTroops.setActive(false);
//        }else if(HireTroops.isActive){
//            remove(tv);
//            remove(hire);
//            remove(load);
//            remove(title);
//            add(hireTroops);
//            tv.setActive(false);
//            title.setActive(false);
//            hire.setActive(false);
//            load.setActive(false);
//            hireTroops.setActive(true);
//        }
    }
}
