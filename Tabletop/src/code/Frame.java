package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Frame {
	
	final static int maxGap = 20;

	Color gruen = Color.green;
	Color grau = Color.gray;
	Color weiss = Color.white;
	Color gelb = Color.yellow;
	GridLayout grid = new GridLayout(5, 5);
	JPanel panel = new JPanel();
	JFrame frame = new JFrame("Warhammer");
	DefaultListModel listModel = new DefaultListModel();
	JList list = new JList(listModel);
	String[] gelaende = { "Wiese", "Wueste", "Eis", "Fels" };
	JComboBox farbenListe = new JComboBox(gelaende);
	JCheckBox cb = new JCheckBox("Einheiten setzen");
	Unit u = new Unit();
	UnitCreator[][] battleField = new UnitCreator[6][5];
	UnitCreator dummy = new UnitCreator("Dummy", 0, 0, 0, 0, 0, 0, 0, 10, false, false);
	Prepare pr = new Prepare();
	boolean bewegung = true;
	boolean neueEinheit = false;
	Component dummyComp = new JButton();
	String farbe = "gelb";
	boolean farbenwahl = false;

	public Frame(){
		
	try {
		UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
	} catch (Exception e) {
		JOptionPane.showMessageDialog(frame, "Look and Feel Änderung Fehlgeschlagen!", "Funktion nicht möglich", 2);					
	}
	  initGui();
	}
	
	@SuppressWarnings("static-access")
	public void initGui(){
				
		panel.setLayout(grid);
		JLabel l = new JLabel();
        Dimension buttonSize = l.getPreferredSize();
        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,
                (int)(buttonSize.getHeight() * 3.5)+maxGap * 2));
		frame.setSize(800, 700);
		JButton button00 = new JButton();
		JButton button10 = new JButton();
		JButton button20 = new JButton();
		JButton button30 = new JButton();
		JButton button40 = new JButton();
		JButton button50 = new JButton();
		JButton button01 = new JButton();
		JButton button11 = new JButton();
		JButton button21 = new JButton();
		JButton button31 = new JButton();
		JButton button41 = new JButton();
		JButton button51 = new JButton();
		JButton button02 = new JButton();
		JButton button12 = new JButton();
		JButton button22 = new JButton();
		JButton button32 = new JButton();
		JButton button42 = new JButton();
		JButton button52 = new JButton();
		JButton button03 = new JButton();
		JButton button13 = new JButton();
		JButton button23 = new JButton();
		JButton button33 = new JButton();
		JButton button43 = new JButton();
		JButton button53 = new JButton();
		JButton button04 = new JButton();
		JButton button14 = new JButton();
		JButton button24 = new JButton();
		JButton button34 = new JButton();
		JButton button44 = new JButton();
		JButton button54 = new JButton();
		
		button00.addActionListener(new bListener());
		button10.addActionListener(new bListener());
		button20.addActionListener(new bListener());
		button30.addActionListener(new bListener());
		button40.addActionListener(new bListener());
		button50.addActionListener(new bListener());
		button01.addActionListener(new bListener());
		button11.addActionListener(new bListener());
		button21.addActionListener(new bListener());
		button31.addActionListener(new bListener());
		button41.addActionListener(new bListener());
		button51.addActionListener(new bListener());
		button02.addActionListener(new bListener());
		button12.addActionListener(new bListener());
		button22.addActionListener(new bListener());
		button32.addActionListener(new bListener());
		button42.addActionListener(new bListener());
		button52.addActionListener(new bListener());
		button03.addActionListener(new bListener());
		button13.addActionListener(new bListener());
		button23.addActionListener(new bListener());
		button33.addActionListener(new bListener());
		button43.addActionListener(new bListener());
		button53.addActionListener(new bListener());
		button04.addActionListener(new bListener());
		button14.addActionListener(new bListener());
		button24.addActionListener(new bListener());
		button34.addActionListener(new bListener());
		button44.addActionListener(new bListener());
		button54.addActionListener(new bListener());
			
		panel.add(button00);
		panel.add(button10);
		panel.add(button20);
		panel.add(button30);
		panel.add(button40);
		panel.add(button50);
        panel.add(button01);
		panel.add(button11);
		panel.add(button21);
		panel.add(button31);
		panel.add(button41);
		panel.add(button51);
        panel.add(button02);
		panel.add(button12);
		panel.add(button22);
		panel.add(button32);
		panel.add(button42);
		panel.add(button52);
        panel.add(button03);
		panel.add(button13);
		panel.add(button23);
		panel.add(button33);
		panel.add(button43);
		panel.add(button53);
        panel.add(button04);
		panel.add(button14);
		panel.add(button24);
		panel.add(button34);
		panel.add(button44);
		panel.add(button54);
		u.setAllUnits();
		for (int i = 0; i < u.getAllUnits().size(); i++) {
			listModel.addElement(u.getAllUnits().get(i).getName());			
		}
		farbenListe.addActionListener(new FarbenListener());		
		cb.addItemListener(new checkListener());
		cb.setMnemonic(KeyEvent.VK_E);
		list.setSelectionMode(0);
		frame.add(panel, new BorderLayout().CENTER);
		frame.add(list, new BorderLayout().WEST);
		frame.add(farbenListe, new BorderLayout().SOUTH);
		frame.add(cb, new BorderLayout().NORTH);
		
		frame.setResizable(false);
		frame.setVisible(true);
		pr.b.init();
		frame.setDefaultCloseOperation(3);
	}
	
	class FarbenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(farbenListe.getSelectedIndex() == 3)
				changeColor("grau");
			if(farbenListe.getSelectedIndex() == 2)
				changeColor("weiss");
			if(farbenListe.getSelectedIndex() == 1)
				changeColor("gelb");
			if(farbenListe.getSelectedIndex() == 0)
				changeColor("gruen");
		}
		
	}
	
	class bListener implements ActionListener{
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent ev) {
			Component comp = new JButton();
			comp = (Component) ev.getSource();
			int i = panel.getComponentZOrder(comp);
			int x = 0;
			int y = 0;
			if(i > 23){
				y = 4;
				x = i -24;
				}else if(i > 17){
					y = 3;
					x = i -18;
					}else if(i > 11){
						y = 2;
						x = i -12;
						}else if(i > 5){
							y = 1;
							x = i -6;
							}else if(i >= 0){
								y = 0;
								x = i;
							}
			if(neueEinheit == false){
				if(bewegung == true){
					if(battleField[x][y] != null){
						try {
							dummyComp = comp;
							dummy = battleField[x][y];
							battleField[x][y] = null;
							farbenwahl = true;
							((AbstractButton) panel.getComponentAt(comp.getX(),comp.getY())).setLabel("");
							setButtonIcon(((JButton) panel.getComponentAt(comp.getX(),comp.getY())), farbe, dummy.getName(), comp.getX(),comp.getY(), 0);
							((AbstractButton) panel.getComponentAt(comp.getX(),comp.getY())).setEnabled(false);
							bewegung = false;
							farbenwahl = false;
						} catch (Exception e) {
							JOptionPane.showMessageDialog(frame, "Hier steht keine Figur zum bewegen!", "Funktion nicht möglich", 2);					
						}
					}
				}else{
					try {
						bewegung = true;
						if(battleField[x][y] == null){
						battleField[x][y] = dummy;
						((AbstractButton) panel.getComponentAt(comp.getX(),comp.getY())).setLabel(battleField[x][y].getName());
						setButtonIcon(((JButton) panel.getComponentAt(comp.getX(),comp.getY())), dummy.getName(), dummy.getName(), comp.getX(),comp.getY(), 0);
						}else{
							pr.angreifer = dummy;
							pr.verteidiger = battleField[x][y];
							pr.charge();
							if(pr.angreifer.isFlieht() == true){
								((AbstractButton) panel.getComponentAt(dummyComp.getX(),dummyComp.getY())).setLabel("");
							}else if(pr.verteidiger.isFlieht() == true){
									battleField[x][y] = dummy;
									((AbstractButton) panel.getComponentAt(comp.getX(),comp.getY())).setLabel(dummy.getName());	
									setButtonIcon(((JButton) panel.getComponentAt(comp.getX(),comp.getY())), dummy.getName(), dummy.getName(), comp.getX(),comp.getY(), 0);
								}
						}
						((AbstractButton) panel.getComponentAt(dummyComp.getX(),dummyComp.getY())).setEnabled(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, "Hier steht keine Figur zum bewegen!", "Funktion nicht möglich", 2);					
					}			
				}
			}else{
				setBattleField(u.getAllUnits().get((list.getSelectedIndex())), x, y, i);
			}
		}	
	}
	
	@SuppressWarnings("deprecation")
	public void setBattleField(UnitCreator unit, int x, int y, int z){
		battleField[x][y] = unit;
		((AbstractButton) panel.getComponent(z)).setLabel(battleField[x][y].getName());
		setButtonIcon(((JButton) panel.getComponent(z)), battleField[x][y].getName(), battleField[x][y].getName(), 0, 0, z);
	}
	class checkListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(neueEinheit == false)
				neueEinheit = true;
			else
				neueEinheit = false;
		}
	}
	
	protected void setButtonIcon(JButton button, String imageName, String altText, int x, int y, int z){

		String imgLocation ="/resources/"+imageName+".PNG";
        URL imageURL = Frame.class.getResource(imgLocation);
       
        if(farbenwahl == false){
        	if(z != 0){
		        if (imageURL != null) {
		        	((AbstractButton) panel.getComponent(z)).setIcon(new ImageIcon(imageURL));
		        	((AbstractButton) panel.getComponent(z)).setRolloverEnabled(true);
	//	            button.setIcon(new ImageIcon(imageURL));
		        } else {
		            button.setText(altText);
	//	            System.err.println("Resource not found: " + imgLocation);
		            pr.b.txt.append("Resource not found: " + imgLocation);
		        }
	        }else{
	        	if (imageURL != null) {
					((AbstractButton) panel.getComponentAt(x, y)).setIcon(new ImageIcon(imageURL));
					((AbstractButton) panel.getComponentAt(x, y)).setRolloverEnabled(true);
	//	            button.setIcon(new ImageIcon(imageURL));
		        } else {
		            button.setText(altText);
	//	            System.err.println("Resource not found: " + imgLocation);
		            pr.b.txt.append("Resource not found: " + imgLocation + "\n");
		        }
	        }
        }else{
        	int zufall = 0;
    		zufall = (int) (Math.random()*2);
    		if(zufall == 0){
				((AbstractButton) panel.getComponentAt(x, y)).setIcon(new ImageIcon(imageURL));
				((AbstractButton) panel.getComponentAt(x, y)).setRolloverEnabled(true);
    		}else{
        		imgLocation ="/resources/"+imageName+ "2.PNG";
                imageURL = Frame.class.getResource(imgLocation);
				((AbstractButton) panel.getComponentAt(x, y)).setIcon(new ImageIcon(imageURL));
				((AbstractButton) panel.getComponentAt(x, y)).setRolloverEnabled(true);
    		}
        }
        return;
	}
	
	public void changeColor(String farbe){
		this.farbe = farbe;
		int zufall = 0;
		if(farbe == "grau"){
			for(Component button : panel.getComponents()){
				button.setBackground(grau);
			}
			for (int i = 0; i < panel.getComponentCount(); i++) {
				if(battleField[buttonNumberX(i)][buttonNumberY(i)] == null){
					zufall = (int) (Math.random()*2);
					if(zufall == 0)
						setButtonIcon(((JButton) panel.getComponent(i)), "grau", "", 0, 0, i);
					else
						setButtonIcon(((JButton) panel.getComponent(i)), "grau2", "", 0, 0, i);
				}	
			}
		}
		if(farbe == "weiss"){
			for(Component button : panel.getComponents()){
				button.setBackground(weiss);
			}
			for (int i = 0; i < panel.getComponentCount(); i++) {
				if(battleField[buttonNumberX(i)][buttonNumberY(i)] == null){
					zufall = (int) (Math.random()*2);
					if(zufall == 0)
						setButtonIcon(((JButton) panel.getComponent(i)), "weiss", "", 0, 0, i);
					else
						setButtonIcon(((JButton) panel.getComponent(i)), "weiss2", "", 0, 0, i);
				}	
			}
		}	
		if(farbe == "gruen"){
			for(Component button : panel.getComponents()){
				button.setBackground(gruen);
			}
			for (int i = 0; i < panel.getComponentCount(); i++) {
				if(battleField[buttonNumberX(i)][buttonNumberY(i)] == null){
					zufall = (int) (Math.random()*2);
					if(zufall == 0)
						setButtonIcon(((JButton) panel.getComponent(i)), "gruen", "", 0, 0, i);
					else
						setButtonIcon(((JButton) panel.getComponent(i)), "gruen2", "", 0, 0, i);
				}	
			}
		}	
		if(farbe == "gelb"){
			for(Component button : panel.getComponents()){
				button.setBackground(gelb);
			}
			for (int i = 0; i < panel.getComponentCount(); i++) {
				if(battleField[buttonNumberX(i)][buttonNumberY(i)] == null){
					zufall = (int) (Math.random()*2);
					if(zufall == 0)
						setButtonIcon(((JButton) panel.getComponent(i)), "gelb", "", 0, 0, i);
					else
						setButtonIcon(((JButton) panel.getComponent(i)), "gelb2", "", 0, 0, i);
				}
			}
		}	
	}
	
	private int buttonNumberX(int i){
		int x = 1;
		if(i > 23){
			x = i -24;
			}else if(i > 17){
				x = i -18;
				}else if(i > 11){
					x = i -12;
					}else if(i > 5){
						x = i -6;
						}
		return x;
	}
	
	private int buttonNumberY(int i){
		int y = 0;
		if(i > 23){
			y = 4;
			}else if(i > 17){
				y = 3;
				}else if(i > 11){
					y = 2;
					}else if(i > 5){
						y = 1;
						}
		return y;
	}
}
