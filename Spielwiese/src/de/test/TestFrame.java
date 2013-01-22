package de.test;

import javax.swing.JFrame;

import com.karneim.editor.gui.EditorButton;
import com.karneim.editor.gui.EditorLabel;
import com.karneim.editor.gui.EditorTable;
import com.karneim.editor.gui.EditorTextField;
import com.karneim.gui.KScrollPane;

public class TestFrame extends JFrame {
	 
	private MasterEd masterEd;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5359340033357159090L;
 
	public TestFrame() {
		super();
		setTitle("Item Adder");
 
		masterEd = new MasterEd(); // @wb:location=11,386
		getContentPane().setLayout(null);
		setBounds(100, 100, 500, 375);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
		final KScrollPane scrollPane = new KScrollPane();
		scrollPane.setBounds(10, 10, 266, 173);
		getContentPane().add(scrollPane);
 
		final EditorTable editorTable = new EditorTable();
		editorTable.setPropertyName("liste");
		editorTable.setIEditor(masterEd);
		editorTable
				.setColumnDefinitions(new com.karneim.editor.gui.TableModelColumnDefinition[]{
						new com.karneim.editor.gui.TableModelColumnDefinition(
								"ID", "id", "", 100,
								javax.swing.SwingConstants.LEFT, false),
						new com.karneim.editor.gui.TableModelColumnDefinition(
								"Value 1", "v1", "", 100,
								javax.swing.SwingConstants.LEFT, false),
						new com.karneim.editor.gui.TableModelColumnDefinition(
								"Value 2", "v2", "", 100,
								javax.swing.SwingConstants.LEFT, false)});
		scrollPane.setViewportView(editorTable);
 
		final EditorButton addItemEditorButton = new EditorButton();
		addItemEditorButton.setIEditor(masterEd);
		addItemEditorButton.setOperationName("add");
		addItemEditorButton.setText("Add Item");
		addItemEditorButton.setBounds(350, 113, 115, 26);
		getContentPane().add(addItemEditorButton);
 
		final EditorButton editorButton_1 = new EditorButton();
		editorButton_1.setIEditor(masterEd);
		editorButton_1.setOperationName("remove");
		editorButton_1.setText("Remove Selected Items");
		editorButton_1.setBounds(10, 189, 176, 26);
		getContentPane().add(editorButton_1);
 
		final EditorTextField editorTextField = new EditorTextField();
		editorTextField.setPropertyName("item.id");
		editorTextField.setIEditor(masterEd);
		editorTextField.setBounds(351, 35, 114, 20);
		getContentPane().add(editorTextField);
 
		final EditorTextField editorTextField_1 = new EditorTextField();
		editorTextField_1.setPropertyName("item.v1");
		editorTextField_1.setIEditor(masterEd);
		editorTextField_1.setBounds(351, 61, 114, 20);
		getContentPane().add(editorTextField_1);
 
		final EditorTextField editorTextField_2 = new EditorTextField();
		editorTextField_2.setPropertyName("item.v2");
		editorTextField_2.setIEditor(masterEd);
		editorTextField_2.setBounds(351, 87, 114, 20);
		getContentPane().add(editorTextField_2);
 
		final EditorLabel idEditorLabel = new EditorLabel();
		idEditorLabel.setText("id");
		idEditorLabel.setBounds(282, 37, 18, 16);
		getContentPane().add(idEditorLabel);
 
		final EditorLabel wert1EditorLabel = new EditorLabel();
		wert1EditorLabel.setText("Value 1");
		wert1EditorLabel.setBounds(282, 61, 63, 16);
		getContentPane().add(wert1EditorLabel);
 
		final EditorLabel wert2EditorLabel = new EditorLabel();
		wert2EditorLabel.setText("Value 2");
		wert2EditorLabel.setBounds(282, 89, 63, 16);
		getContentPane().add(wert2EditorLabel);
 
		//
	}
}