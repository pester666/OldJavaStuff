package de.test;

import com.karneim.editor.Editor;
import com.karneim.editor.property.IntProperty;
import com.karneim.editor.property.StringProperty;

public class ItemEd extends Editor {
	public ItemEd() {
		super();
		this.editorInit();
	}
 
	private void editorInit() {
		this.add(this.id);
		this.id.enableThousandPoints(false);
		this.add(this.v1);
		this.add(this.v2);
	}
 
	protected final IntProperty id = new IntProperty("id");
	protected final StringProperty v1 = new StringProperty("v1");
	protected final StringProperty v2 = new StringProperty("v2");
}