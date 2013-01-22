package shipCreator.sidebars;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import client.lib.basics.Tile;

public class Entitypanel extends JPanel {

    JTree tree;
    Tile tile;
    
    public Entitypanel(){
        this.setBackground(Color.WHITE);
        this.setSize(224, 650);
        this.setPreferredSize(new Dimension(224, 650));
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Entities");
        createNodes(top);
        tree = new JTree(top);
        tree.addTreeSelectionListener(new Selectioner());
        JScrollPane treeView = new JScrollPane(tree);
        treeView.setPreferredSize(new Dimension(220, 590));
        this.add(treeView);
    }
    
    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;
        
        category = new DefaultMutableTreeNode("Rooms");
        top.add(category);
        
        book = new DefaultMutableTreeNode("Generator");
        category.add(book);
        
        book = new DefaultMutableTreeNode("Bridge");
        category.add(book);
        
        book = new DefaultMutableTreeNode("Floor");
        category.add(book);
        
        book = new DefaultMutableTreeNode("Oxygen");
        category.add(book);
        
        book = new DefaultMutableTreeNode("Utility");
        category.add(book);
        
        book = new DefaultMutableTreeNode("Weaponry");
        category.add(book);
        
        book = new DefaultMutableTreeNode("Cargo");
        category.add(book);
        
        book = new DefaultMutableTreeNode("Lazarett");
        category.add(book);
        
        
        
        
        category = new DefaultMutableTreeNode("Components");
        top.add(category);
        
        book = new DefaultMutableTreeNode("Generator");
        category.add(book);
        
        book = new DefaultMutableTreeNode("Oxygen");
        category.add(book);
        
        book = new DefaultMutableTreeNode("Shield");
        category.add(book);
        
        book = new DefaultMutableTreeNode("Turbines");
        category.add(book);
        
        book = new DefaultMutableTreeNode("Lazarett");
        category.add(book);

        
        
        
        category = new DefaultMutableTreeNode("Weapons");
        top.add(category);
        
        book = new DefaultMutableTreeNode("Laser");
        category.add(book);
        
        book = new DefaultMutableTreeNode("Ion-Canon");
        category.add(book);
        
        book = new DefaultMutableTreeNode("Lance");
        category.add(book);
    }
    
    private class Selectioner implements TreeSelectionListener{

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            tile = new Tile(0, 0, 0);
        }
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }
    
    
}
