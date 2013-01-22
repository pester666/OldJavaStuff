package client.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Ressourcepanel extends JPanel {

    private ImageIcon credits;
    private ImageIcon gas;
    private ImageIcon steel;
    private ImageIcon food;
    private ImageIcon population;

    private JLabel creditsAmount;
    private JLabel gasAmount;
    private JLabel steelAmount;
    private JLabel foodAmount;
    private JLabel populationAmount;

    public Ressourcepanel() {
        this.setBackground(Color.black);

        credits = new ImageIcon("images/credits.PNG");
        gas = new ImageIcon("images/gas.PNG");
        steel = new ImageIcon("images/steel.PNG");
        food = new ImageIcon("images/food.PNG");
        population = new ImageIcon("images/population.PNG");
        
        creditsAmount = new JLabel("1000");
        gasAmount = new JLabel("1000");
        steelAmount = new JLabel("1000");
        foodAmount = new JLabel("1000");
        populationAmount = new JLabel("500");

        creditsAmount.setForeground(Color.white);
        gasAmount.setForeground(Color.white);
        steelAmount.setForeground(Color.white);
        foodAmount.setForeground(Color.white);
        populationAmount.setForeground(Color.white);

        creditsAmount.setIcon(credits);
        gasAmount.setIcon(gas);
        steelAmount.setIcon(steel);
        foodAmount.setIcon(food);
        populationAmount.setIcon(population);
        
        this.setLayout(new GridLayout(1, 5));
        this.add(creditsAmount);
        this.add(gasAmount);
        this.add(steelAmount);
        this.add(foodAmount);
        this.add(populationAmount);

        loadRessources();
    }

    public int getCredits() {
        return Integer.parseInt(creditsAmount.getText());
    }

    public int getGas() {
        return Integer.parseInt(gasAmount.getText());
    }

    public int getSteel() {
        return Integer.parseInt(steelAmount.getText());
    }

    public int getFood() {
        return Integer.parseInt(foodAmount.getText());
    }

    public int getPopulation() {
        return Integer.parseInt(populationAmount.getText());
    }

    public void setCreditsAmount(String creditsAmount) {
        this.creditsAmount.setText(creditsAmount);
    }

    public void setGasAmount(String gasAmount) {
        this.gasAmount.setText(gasAmount);
    }

    public void setSteelAmount(String steelAmount) {
        this.steelAmount.setText(steelAmount);
    }

    public void setFoodAmount(String foodAmount) {
        this.foodAmount.setText(foodAmount);
    }

    public void setPopulationAmount(String populationAmount) {
        this.populationAmount.setText(populationAmount);
    }

    private void loadRessources() {
        try {
            FileReader fr = new FileReader("database/PlayerRessources.txt");
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < 4; i++) {
                String line = br.readLine();
                if (i == 0) {
                    creditsAmount.setText(line.substring(line.indexOf(":") + 1));
                } else if (i == 1) {
                    gasAmount.setText(line.substring(line.indexOf(":") + 1));
                } else if (i == 2) {
                    steelAmount.setText(line.substring(line.indexOf(":") + 1));
                } else if (i == 3) {
                    foodAmount.setText(line.substring(line.indexOf(":") + 1));
                } else if (i == 4) {
                    populationAmount.setText(line.substring(line.indexOf(":") + 1));
                }
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateRessources(int i, String line, int refreshCounter) {
        if (i == 0) {
            int provided = Integer.parseInt(line.substring(0, line.indexOf("/")));
            int amount = Integer.parseInt(line.substring(line.indexOf(":") + 1));
            amount = amount + provided*refreshCounter/60;
            creditsAmount.setText(""+amount);
            creditsAmount.setToolTipText("Credits per Minute: " + provided);
        } else if (i == 1) {
            int provided = Integer.parseInt(line.substring(0, line.indexOf("/")));
            int amount = Integer.parseInt(line.substring(line.indexOf(":") + 1));
            amount = amount + provided*refreshCounter/60;
            gasAmount.setText(""+amount);
            gasAmount.setToolTipText("Gas per Minute: " + provided);
        } else if (i == 2) {
            int provided = Integer.parseInt(line.substring(0, line.indexOf("/")));
            int amount = Integer.parseInt(line.substring(line.indexOf(":") + 1));
            amount = amount + provided*refreshCounter/60;
            steelAmount.setText(""+amount);
            steelAmount.setToolTipText("Steel per Minute: " + provided);
        } else if (i == 3) {
            int provided = Integer.parseInt(line.substring(0, line.indexOf("/")));
            int amount = Integer.parseInt(line.substring(line.indexOf(":") + 1));
            amount = amount + provided*refreshCounter/60;
            foodAmount.setText(""+amount);
            foodAmount.setToolTipText("Food per Minute: " + provided);
        } else if (i == 4) {
            int provided = Integer.parseInt(line.substring(0, line.indexOf("/")));
            int amount = Integer.parseInt(line.substring(line.indexOf(":") + 1));
            amount = amount + provided*refreshCounter/60;
            populationAmount.setText(""+amount);
            populationAmount.setToolTipText("Population per Minute: " + provided);
        }
    }
}
