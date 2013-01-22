package de.test;

import java.awt.EventQueue;

import javax.swing.JFrame;

import test.editor.TestFrame;

public class TestMain {
	public static void main(String args[]) {
		// final TestMain test = new TestMain();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(500, 275);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}