package de.test;
import com.karneim.editor.Editor;
import com.karneim.editor.Operation;
import com.karneim.editor.ValidationState;
import com.karneim.editor.property.EditorProperty;

public class MasterEd extends Editor {
	public MasterEd() {
		super();
		this.editorInit();
	}
 
	protected final EditorProperty liste = new EditorProperty("liste", TableSelectingListEd.class);
	protected final EditorProperty item = new EditorProperty("item", ItemEd.class);
 
	protected final Operation add = new Operation("add") {
		public void execute() throws Exception {
			MasterEd.this.add();
		}
	};
 
	protected final Operation remove = new Operation("remove") {
		public void execute() throws Exception {
			MasterEd.this.remove();
		}
	};
 
	private void editorInit() {
		this.add(this.liste);
		this.add(this.item);
		this.add(this.add);
		this.add(this.remove);
		this.item.setEditor(new ItemEd());
		this.liste.setEditor(new TableSelectingListEd());
	}
 
	public void add() {
		TableSelectingListEd tabelle = (TableSelectingListEd) this.liste.getEditor();
		ItemEd item = (ItemEd) this.item.getEditor();
		boolean invalid = false;
 
		// Sonst sind alle Einträge dieselbe Instanz, wenn man einen Eintrag in
		// der
		// Tabelle oder in den Eingabefeldern ändert, ändern sich alle anderen
		// Einträge auch !!!
		ItemEd fix = new ItemEd();
		invalid = isDuplicateKey(tabelle, item);
 
		if (invalid == false) {
			fix.id.setInt(item.id.toInt());
			fix.v1.setString(item.v1.getString());
			fix.v2.setString(item.v2.getString());
 
			fix.id.setWriteable(false);
			fix.v1.setWriteable(false);
			fix.v2.setWriteable(false);
 
			tabelle.setItem(fix);
		}
	}
 
	private boolean isDuplicateKey(TableSelectingListEd tabelle, ItemEd item) {
		ItemEd[] items = (ItemEd[]) tabelle.toArray(new ItemEd[tabelle.size()]);
		for (int i = 0; i < items.length; i++) {
			if (item.id.toInt() == items[i].id.toInt()) {
				item.id.setValidationState(new ValidationState("Gleiche ID"));
				return true;
			}
		}
		return false;
	}
 
	public void remove() {
		TableSelectingListEd tabelle = (TableSelectingListEd) this.liste
				.getEditor();
		tabelle.removeAll(tabelle.getSelectedEditors());
	}
}