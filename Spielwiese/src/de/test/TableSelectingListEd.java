package de.test;

import com.karneim.editor.SelectingListEditor;

public class TableSelectingListEd extends SelectingListEditor {
	public TableSelectingListEd() {
		super();
		this.editorInit();
	}
 
	private void editorInit() {
		this.setItemClass(ItemEd.class);
	}
 
	public void setItem(ItemEd item) {
		this.add(item);
	}	
}