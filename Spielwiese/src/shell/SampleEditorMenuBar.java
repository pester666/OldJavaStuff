package shell;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.KeyStroke;

import com.karneim.editor.Editor;
import com.karneim.editor.IEditorMenuEd;
import com.karneim.editor.IMember;
import com.karneim.editor.Operation;
import com.karneim.editor.ValidationState;
import com.karneim.editor.gui.EditorMenuBar;
import com.karneim.editor.property.EditorProperty;

public class SampleEditorMenuBar {
	 
	  public static void main( String[] args) {
	 
	    final JFrame frame = new JFrame();
	    frame.setTitle("Demo Menu mit EditorFramework");
	    frame.getContentPane().setLayout( new BorderLayout());
	 
	    class MenuEd extends Editor implements IEditorMenuEd {
	      protected final Operation hello = new Operation("hello") {
	        public void execute() throws Exception {
	          MenuEd.this.hello();
	        }
	        public ValidationState validate() {
	          if ( state) return new ValidationState("Operation nicht möglich");
	          return null;
	        }
	      };
	      protected final Operation world = new Operation("world") {
	        public void execute() throws Exception {
	          MenuEd.this.world();
	        }
	        public ValidationState validate() {
	          if ( !state) return new ValidationState("Operation nicht möglich");
	          return null;
	        }
	      };
	      private boolean state;
	      public MenuEd() {
	        this.editorInit();
	      }
	      private void editorInit() {
	 
	        this.add( this.hello); 
	        this.add( this.world);
	 
	        this.hello.setDisplayName("Hello");
	        this.world.setDisplayName("World");
	 
	        this.hello.setMnemonic('H');
	        this.world.setMnemonic('W');
	 
	        this.hello.setKeyStroke("ctrl H");
	        this.world.setKeyStroke( KeyStroke.getKeyStroke( 'W', KeyEvent.CTRL_MASK));
	 
	        this.setModified( false);
	        this.revalidate();
	      }
	      public void hello() {
	        this.hello.check();
	        state = true;
	        javax.swing.JOptionPane.showMessageDialog( frame, "Hello");
	        this.revalidate();
	      }
	      public void world() {
	        this.world.check();
	        state = false;
	        javax.swing.JOptionPane.showMessageDialog( frame, "World");
	        this.revalidate();
	      }

	      public IMember[] getSeparators() {
	        return new IMember[] { this.hello};
	      }
	    }
	 
	    class MenuBarEd extends Editor {

	    protected final EditorProperty menu = new EditorProperty("menu", MenuEd.class);
	 
	      public MenuBarEd() {
	        this.editorInit();
	      }
	      private void editorInit() {
	        this.add( this.menu);
	        this.menu.setEditor( new MenuEd());
	        this.menu.setDisplayName("Menu");
	        this.menu.setMnemonic('M');
	        this.setModified( false);
	        this.revalidate();
	      }
	    }
	 
	    EditorMenuBar editorMenuBar1 = new EditorMenuBar();
	    MenuBarEd menuBarEd1 = new MenuBarEd();
	    editorMenuBar1.setMenuBarEditor( menuBarEd1);
	    frame.setJMenuBar( editorMenuBar1);
	 
	    frame.pack();
	    frame.setBounds( 400, 200, 500, 100);
	    frame.setVisible( true);
	 
	  }
	}