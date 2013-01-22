package base;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import base.data.units.GI;
import base.data.units.Volksgrenadier;
import base.lib.Dice;
import base.lib.Infantry;
import base.lib.Squad;
import base.lib.Unit;

public class Standalone {
    static Squad s1;
    static Squad s2;
    static JLabel axis;
    static JLabel allied;
    static int a = 10;
    static int b = 10;
    /**
     * @param args
     */
    public static void main(String[] args) {
        s1 = new Squad();
        Volksgrenadier v = new Volksgrenadier();
        s1.setUnits(new Unit[]{v, v, v, v, v, v, v, v, v, v});
        s2 = new Squad();
        GI g = new GI();
        s2.setUnits(new Unit[]{g, g, g, g, g, g, g, g, g, g});
        JFrame f = new JFrame();
        JButton attackS1 = new JButton("Angriff!");
        attackS1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                angriff();
            }
            
        });
        JButton attackS2 = new JButton("Attack!");
        attackS2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                attack();
            }
            
        });
        axis = new JLabel("Axis: " + s1.getUnits().length);
        allied = new JLabel("Allied: " + s2.getUnits().length);
        f.setLayout(new GridLayout(2, 2));
        f.add(attackS1);
        f.add(attackS2);
        f.add(axis);
        f.add(allied);
        f.setSize(300, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    private static boolean attack(Unit v, Infantry g){
        if(Dice.hasHitted(v)){
            if(Dice.hasWounded(v, g)){
                if(Dice.armorSaveSuccess(v, g)){
                    System.out.println("Good ol' helmet");
                    return false;
                }else{
                    System.out.println("Tot");
                    return true;
                }
            }else{
                System.out.println("Resisted");
                return false;
            }
        }else{
            System.out.println("Missed");
            return false;
        }
    }
    
    private static void angriff(){
        for (int i = 0; i < s1.getUnits().length; i++) {
            if(s1.getUnits()[i] != null){
                for (int j = 0; j < s2.getUnits().length; j++) {
                    if(s2.getUnits()[j] != null){
                        if(attack(s1.getUnits()[i], (Infantry)s2.getUnits()[j])){
                            s2.getUnits()[j] =  null;
                            b--;
                        }
                        break;
                    }
                }
            }
        }
        allied.setText("Allied: " + b);
        System.out.println(" ");
        System.out.println("------------------------");
        System.out.println(" ");
    }
    
    private static void attack(){
        for (int i = 0; i < s2.getUnits().length; i++) {
            if(s2.getUnits()[i] != null){
                for (int j = 0; j < s1.getUnits().length; j++) {
                    if(s1.getUnits()[j] != null){
                        if(attack(s2.getUnits()[i], (Infantry)s1.getUnits()[j])){
                            s1.getUnits()[j] =  null;
                            a--;
                        }
                        break;
                    }
                }
            }
        }
        axis.setText("Axis: " + a);
        System.out.println(" ");
        System.out.println("------------------------");
        System.out.println(" ");
    }
}
