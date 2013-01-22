package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import client.database.Buildings;

import client.database.Player;
import client.lib.Building;

@SuppressWarnings("serial")
public class Mainpanel extends JPanel {

    private ImageIcon[] icons;
    private ImageIcon imgCredits = new ImageIcon("images/credits.PNG");
    private ImageIcon imgGas = new ImageIcon("images/gas.PNG");
    private ImageIcon imgSteel = new ImageIcon("images/steel.PNG");
    private ImageIcon imgFood = new ImageIcon("images/food.PNG");
    private ImageIcon imgPalace = new ImageIcon("images/buildings/Palace.PNG");
    private ImageIcon imgHarvester = new ImageIcon("images/buildings/Harvester.PNG");

    private int tileNr = 0;
    private Player player = new Player();

    public Mainpanel() {
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(4, 4));
        icons = new ImageIcon[16];
        loadPlayer1();
        for (int i = 0; i < icons.length; i++) {
            if (player.getBuilding(i).isEmpty()) {
                icons[i] = new ImageIcon("images/ground/sand.PNG");
            } else {
                icons[i] = new ImageIcon("images/buildings/" + player.getBuilding(i).getName() + ".PNG");
            }
        }
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent arg0) {

            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseExited(MouseEvent arg0) {

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {

            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                readClickCoordinates(arg0.getX(), arg0.getY());
                selectTile();
            }
        });
    }

    private void loadPlayer1() {
        try {
            FileReader fr = new FileReader("database/PlayerRessources.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                loadRessources(count, Integer.parseInt(line.substring(0, line.indexOf("/"))), Integer.parseInt(line.substring(line.indexOf(":") + 1)));
                count++;
            }
            fr = new FileReader("database/PlayerBuildings.txt");
            br = new BufferedReader(fr);
            count = 0;
            while ((line = br.readLine()) != null) {
                loadBuildings(count, line);
                count++;
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRessources(int count, int perMinute, int amount) {
        if (count == 0) {
            player.setCreditsPerMinute(perMinute);
            player.setCredits(amount);
        } else if (count == 1) {
            player.setGasPerMinute(perMinute);
            player.setGas(amount);
        } else if (count == 2) {
            player.setSteelPerMinute(perMinute);
            player.setSteel(amount);
        } else if (count == 3) {
            player.setFoodPerMinute(perMinute);
            player.setFood(amount);
        } else if (count == 4) {
            player.setPopulationPerMinute(perMinute);
            player.setPopulation(amount);
        }
    }

    private void loadBuildings(int count, String line) {
        player.setBuilding(count, line);
    }

    private void selectTile() {
        if (player.getBuilding(tileNr).isEmpty()) {
            showBuildMenu();
        } else {
            showUpgradeMenu();
        }
    }

    private void readClickCoordinates(int x, int y) {
        tileNr = (y / 125) * 4 + (x / 125);
    }

    public void paintComponent(Graphics g) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                g.drawImage(icons[count].getImage(), j * 125, i * 125, 125, 125, null);
                count++;
            }
        }
    }

    private void showBuildMenu() {
        final JDialog dlg = new JDialog();
        Buildings b = new Buildings();
        dlg.setModal(true);
        dlg.setTitle("Build Menu");
        JPanel panel = new JPanel();
        JPanel buildings = new JPanel();
        buildings.setLayout(new GridLayout(3, 5));
        panel.setLayout(new BorderLayout());

        final ButtonGroup bg = new ButtonGroup();

        JRadioButton rbR = new JRadioButton("Refinery");
        rbR.setToolTipText("Credits: " + b.REFINERY.getCredits() + " Gas: " + b.REFINERY.getGas() + " Steel: " + b.REFINERY.getSteel() + " Food: " + b.REFINERY.getFood());
        rbR.setIcon(imgGas);
        rbR.setActionCommand("Refinery");
        bg.add(rbR);

        JRadioButton rbB = new JRadioButton("Barracks");
        rbB.setToolTipText("Credits: " + b.BARRACKS.getCredits() + " Gas: " + b.BARRACKS.getGas() + " Steel: " + b.BARRACKS.getSteel() + " Food: " + b.BARRACKS.getFood());
        rbB.setIcon(imgCredits);
        rbB.setActionCommand("Barracks");
        bg.add(rbB);

        JRadioButton rbM = new JRadioButton("Mine");
        rbM.setToolTipText("Credits: " + b.MINE.getCredits() + " Gas: " + b.MINE.getGas() + " Steel: " + b.MINE.getSteel() + " Food: " + b.MINE.getFood());
        rbM.setIcon(imgSteel);
        rbM.setActionCommand("Mine");
        bg.add(rbM);
        
        JRadioButton rbH = new JRadioButton("Harvester");
        rbH.setToolTipText("Credits: " + b.HARVESTER.getCredits() + " Gas: " + b.HARVESTER.getGas() + " Steel: " + b.HARVESTER.getSteel() + " Food: " + b.HARVESTER.getFood());
        rbH.setIcon(imgHarvester);
        rbH.setActionCommand("Harvester");
        bg.add(rbH);
        
        JRadioButton rbP = new JRadioButton("Palace");
        rbP.setToolTipText("Credits: " + b.PALACE.getCredits() + " Gas: " + b.PALACE.getGas() + " Steel: " + b.PALACE.getSteel() + " Food: " + b.PALACE.getFood());
        rbP.setIcon(imgPalace);
        rbP.setActionCommand("Palace");
        bg.add(rbP);

        JButton ok = new JButton("Build");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dlg.dispose();
                createBuilding(bg.getSelection().getActionCommand());
            }
        });

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dlg.dispose();
            }
        });

        buildings.add(rbR);
        buildings.add(rbB);
        buildings.add(rbM);
        buildings.add(rbH);
        buildings.add(rbP);
        JPanel buttons = new JPanel();
        panel.add(buildings, BorderLayout.CENTER);
        buttons.add(ok);
        buttons.add(cancel);
        panel.add(buttons, BorderLayout.SOUTH);
        dlg.add(panel);
        dlg.setSize(200, 200);
        
        if(!isPalaceBuilt()){
            rbR.setEnabled(false);
            rbB.setEnabled(false);
            rbM.setEnabled(false);
            rbH.setEnabled(false);
        }
        
        if(isPalaceBuilt()){
            rbP.setEnabled(false);
        }
        
        rbR.setSelected(true);

        panel.setBackground(Color.black);
        buttons.setBackground(Color.black);
        buildings.setBackground(Color.black);
        rbR.setBackground(Color.black);
        rbR.setForeground(Color.white);
        rbB.setBackground(Color.black);
        rbB.setForeground(Color.white);
        rbM.setBackground(Color.black);
        rbM.setForeground(Color.white);
        rbH.setBackground(Color.black);
        rbH.setForeground(Color.white);
        rbP.setBackground(Color.black);
        rbP.setForeground(Color.white);
        dlg.setResizable(false);
        dlg.setVisible(true);
    }
    
    private boolean isPalaceBuilt(){
        for (int i = 0; i < 16; i++) {
            if(player.getBuilding(i).getName().equals("Palace")){
                return true;
            }
        }
        return false;
    }

    private void showUpgradeMenu() {
        final JDialog dlg = new JDialog();
        Building b = player.getBuilding(tileNr);
        JPanel panel = new JPanel();
        JPanel info = new JPanel();
        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        info.setLayout(new GridLayout(2,1));
        JPanel res = new JPanel();
        res.setLayout(new GridLayout(3,2));
        
        final JLabel creditCosts = new JLabel("" + player.getBuilding(tileNr).getCredits());
        final JLabel gasCosts = new JLabel("" + player.getBuilding(tileNr).getGas());
        final JLabel steelCosts = new JLabel("" + player.getBuilding(tileNr).getSteel());
        final JLabel foodCosts = new JLabel("" + player.getBuilding(tileNr).getFood());
        JLabel desc = new JLabel(b.getDescription());
        JLabel costs = new JLabel("Upgrade Costs:");
        
        creditCosts.setIcon(imgCredits);
        gasCosts.setIcon(imgGas);
        steelCosts.setIcon(imgSteel);
        foodCosts.setIcon(imgFood);
        panel.setBackground(Color.black);
        info.setBackground(Color.black);
        creditCosts.setForeground(Color.white);
        steelCosts.setForeground(Color.white);
        gasCosts.setForeground(Color.white);
        foodCosts.setForeground(Color.white);
        desc.setForeground(Color.white);
        desc.setBackground(Color.black);
        costs.setBackground(Color.black);
        costs.setForeground(Color.white);
        res.setBackground(Color.black);
        
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dlg.dispose();
            }
        });
        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (deleteBuilding()) {
                    dlg.dispose();
                }
            }
        });
        JButton upgrade = new JButton("Upgrade");
        upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dlg.dispose();
                upgradeBuilding(Integer.parseInt(creditCosts.getText()), Integer.parseInt(gasCosts.getText()), Integer.parseInt(steelCosts.getText()), Integer.parseInt(foodCosts.getText()));
            }
        });
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,3));
        buttons.setBackground(Color.black);
        buttons.add(upgrade);
        buttons.add(delete);
        buttons.add(cancel);
        info.add(desc);
        res.add(costs);
        res.add(new JLabel(""));
        res.add(creditCosts);
        res.add(gasCosts);
        res.add(steelCosts);
        res.add(foodCosts);
        
        dlg.setTitle(b.getName() + "(Level " + b.getLevel() + ") Upgrade Menu");
        
        if (player.getBuilding(tileNr).isProvidesRessources()) {

            JLabel amount = null;
            if (b.getLevel() == 1) {
                amount = new JLabel("Ressources per minute: " + b.getLevel() * 68);
            } else {
                if(b.getCreditsPerMinute() > 0){
                    amount = new JLabel("Ressources per minute: " + b.getCreditsPerMinute());
                }else if(b.getGasPerMinute() > 0){
                    amount = new JLabel("Ressources per minute: " + b.getGasPerMinute());
                }else if(b.getSteelPerMinute() > 0){
                    amount = new JLabel("Ressources per minute: " + b.getSteelPerMinute());
                }else if(b.getFoodPerMinute() > 0){
                    amount = new JLabel("Ressources per minute: " + b.getFoodPerMinute());
                }
            }
            amount.setBackground(Color.black);
            amount.setForeground(Color.white);

            info.add(amount);
            panel.add(info);
            panel.add(res);
            root.add(panel, BorderLayout.CENTER);
            root.add(buttons, BorderLayout.SOUTH);
            dlg.add(root);
        } else {
            panel.add(info);
            panel.add(res);
            root.add(panel, BorderLayout.CENTER);
            root.add(buttons, BorderLayout.SOUTH);
            dlg.add(root);
            //TODO troops
        }
        dlg.setModal(true);
        dlg.setSize(300, 200);
        dlg.setResizable(false);
        dlg.setVisible(true);
    }

    private boolean deleteBuilding() {
        int n = JOptionPane.showConfirmDialog(this, "Really delete this building?", "Safety first", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            player.setCreditsPerMinute(player.getCreditsPerMinute()-player.getBuilding(tileNr).getCreditsPerMinute());
            player.setGasPerMinute(player.getGasPerMinute()-player.getBuilding(tileNr).getGasPerMinute());
            player.setSteelPerMinute(player.getSteelPerMinute()-player.getBuilding(tileNr).getSteelPerMinute());
            player.setFoodPerMinute(player.getFoodPerMinute()-player.getBuilding(tileNr).getFoodPerMinute());
            player.setPopulationPerMinute(player.getPopulationPerMinute()-player.getBuilding(tileNr).getPopulationPerMinute());
            player.setBuilding(tileNr, new Building(0, 0, 0, 0, 0, true, "Ground", "Empty Space", false, 0, 0, 0, 0, 0));
            updateBuildings();
            icons[tileNr] = new ImageIcon("images/ground/sand.PNG");
            repaint();
            return true;
        } else if (n == JOptionPane.NO_OPTION) {
            return false;
        } else {
            return false;
        }
    }

    private void upgradeBuilding(int credits, int gas, int steel, int food) {
        if (credits > player.getCredits() || gas > player.getGas() || steel > player.getSteel() || food > player.getFood()) {
            JOptionPane.showMessageDialog(null, "You have insufficient funds!", "The Failure!", JOptionPane.ERROR_MESSAGE);
        } else {
            player.setCredits(player.getCredits() - credits);
            player.setGas(player.getGas() - gas);
            player.setSteel(player.getSteel() - steel);
            player.setFood(player.getFood() - food);
            Building bi = player.getBuilding(tileNr);
            int c = bi.getCreditsPerMinute() + (bi.getCreditsPerMinute()*(100/bi.getLevel())/100);
            int g = bi.getGasPerMinute() + (bi.getGasPerMinute()*(100/bi.getLevel())/100);
            int s = bi.getSteelPerMinute() + (bi.getSteelPerMinute()*(100/bi.getLevel())/100);
            int f = bi.getFoodPerMinute() + (bi.getFoodPerMinute()*(100/bi.getLevel())/100);
            int p = bi.getPopulationPerMinute() + (bi.getPopulationPerMinute()*(100/bi.getLevel())/100);
            if(c > 0){
                player.setCreditsPerMinute(player.getCreditsPerMinute()+c-bi.getCreditsPerMinute());
            }
            if(g > 0){
                player.setGasPerMinute(player.getGasPerMinute()+g-bi.getGasPerMinute());
            }
            if(s > 0){
                player.setSteelPerMinute(player.getSteelPerMinute()+s-bi.getSteelPerMinute());
            }
            if(f > 0){
                player.setFoodPerMinute(player.getFoodPerMinute()+f-bi.getFoodPerMinute());
            }
            if(p > 0){
                player.setPopulationPerMinute(player.getPopulationPerMinute()+p-bi.getPopulationPerMinute());
            }
            player.setBuilding(tileNr, new Building(bi.getLevel() + 1, (int)(bi.getCredits() * 1.40), (int)(bi.getGas() * 1.40), (int)(bi.getSteel() * 1.40), (int)(bi.getFood() * 1.40), false, bi.getName(), bi.getDescription(), bi
                    .isProvidesRessources(), c, g, s, f, p));
            saveRessources();
            updateBuildings();
        }
    }

    private void createBuilding(String name) {
        Buildings b = new Buildings();
        Building bi = b.getBuilding(name);
        if (bi.getCredits() > player.getCredits() || bi.getGas() > player.getGas() || bi.getSteel() > player.getSteel() || bi.getFood() > player.getFood()) {
            JOptionPane.showMessageDialog(null, "You have insufficient funds!", "The Failure!", JOptionPane.ERROR_MESSAGE);
        } else {
            icons[tileNr] = new ImageIcon("images/buildings/" + name + ".PNG");
            player.setCredits(player.getCredits() - bi.getCredits());
            player.setGas(player.getGas() - bi.getGas());
            player.setSteel(player.getSteel() - bi.getSteel());
            player.setFood(player.getFood() - bi.getFood());
            player.setCreditsPerMinute(player.getCreditsPerMinute() + bi.getCreditsPerMinute());
            player.setGasPerMinute(player.getGasPerMinute() + bi.getGasPerMinute());
            player.setSteelPerMinute(player.getSteelPerMinute() + bi.getSteelPerMinute());
            player.setFoodPerMinute(player.getFoodPerMinute() + bi.getFoodPerMinute());
            player.setPopulationPerMinute(player.getPopulationPerMinute() + bi.getPopulationPerMinute());
            saveRessources();
            player.setBuilding(tileNr, new Building(1, (int)(bi.getCredits() * 1.40), (int)(bi.getGas() * 1.40), (int)(bi.getSteel() * 1.40), (int)(bi.getFood() * 1.40), false, bi.getName(), bi.getDescription(), bi.isProvidesRessources(), bi.getCreditsPerMinute(), bi.getGasPerMinute(), bi.getSteelPerMinute(), bi.getFoodPerMinute(), bi.getPopulationPerMinute()));
            updateBuildings();
            repaint();
        }
    }

    private void saveRessources() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("database/PlayerRessources.txt");
            fw.write(player.getCreditsPerMinute() + "/credits:" + player.getCredits());
            fw.append(System.getProperty("line.separator"));
            fw.append(player.getGasPerMinute() + "/gas:" + player.getGas());
            fw.append(System.getProperty("line.separator"));
            fw.append(player.getSteelPerMinute() + "/steel:" + player.getSteel());
            fw.append(System.getProperty("line.separator"));
            fw.append(player.getFoodPerMinute() + "/food:" + player.getFood());
            fw.append(System.getProperty("line.separator"));
            fw.append(player.getPopulationPerMinute() + "/population:" + player.getPopulation());
            fw.append(System.getProperty("line.separator"));
            fw.flush();
        } catch (IOException e) {
            System.err.println("Konnte Datei nicht erstellen");
        } finally {
            if (fw != null)
                try {
                    fw.close();
                } catch (IOException e) {
                    System.err.println("File can't be written!");
                }
        }
    }
    
    public void updateRessources(int i, String line, int refreshCounter) {
        if (i == 0) {
            int provided = Integer.parseInt(line.substring(0, line.indexOf("/")));
            int amount = Integer.parseInt(line.substring(line.indexOf(":") + 1));
            amount = amount + provided*refreshCounter/60;
            player.setCredits(amount);
        } else if (i == 1) {
            int provided = Integer.parseInt(line.substring(0, line.indexOf("/")));
            int amount = Integer.parseInt(line.substring(line.indexOf(":") + 1));
            amount = amount + provided*refreshCounter/60;
            player.setGas(amount);
        } else if (i == 2) {
            int provided = Integer.parseInt(line.substring(0, line.indexOf("/")));
            int amount = Integer.parseInt(line.substring(line.indexOf(":") + 1));
            amount = amount + provided*refreshCounter/60;
            player.setSteel(amount);
        } else if (i == 3) {
            int provided = Integer.parseInt(line.substring(0, line.indexOf("/")));
            int amount = Integer.parseInt(line.substring(line.indexOf(":") + 1));
            amount = amount + provided*refreshCounter/60;
            player.setFood(amount);
        } else if (i == 4) {
            int provided = Integer.parseInt(line.substring(0, line.indexOf("/")));
            int amount = Integer.parseInt(line.substring(line.indexOf(":") + 1));
            amount = amount + provided*refreshCounter/60;
            player.setPopulation(amount);
        }
        saveRessources();
    }

    private void updateBuildings() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("database/PlayerBuildings.txt");
            Building bi;
            for (int i = 1; i <= 16; i++) {
                bi = player.getBuilding(i - 1);
                fw.write(i + ":" + bi.getLevel() + ":" + bi.isEmpty() + ":" + bi.getName() + ":" + bi.getDescription() + ":" + bi.isProvidesRessources());
                fw.append(System.getProperty("line.separator"));
            }
            fw.flush();
        } catch (IOException e) {
            System.err.println("Konnte Datei nicht erstellen");
        } finally {
            if (fw != null)
                try {
                    fw.close();
                } catch (IOException e) {
                    System.err.println("File can't be written!");
                }
        }
    }
}
