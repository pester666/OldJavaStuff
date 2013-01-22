package filebrowserEarly;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.karneim.editor.gui.EditorButton;
import com.karneim.editor.gui.EditorCheckBox;
import com.karneim.editor.gui.EditorLabel;
import com.karneim.editor.gui.EditorPanel;
import com.karneim.editor.gui.EditorTextField;

@SuppressWarnings("serial")
public class Mainframe extends JFrame {

	private JPanel contentPane;
	private final Font smallFont = new Font("Courier New", Font.BOLD, 17);
	/**
	 * @wbp.nonvisual location=71,401
	 */
	private final MainEditor mainEd = new MainEditor();
	/**
	 * @wbp.nonvisual location=179,351
	 */
	private static void initLookAndFeel() {
        //PlasticLookAndFeel.setPlasticTheme(new SkyGreen());
        try {
            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
        } catch (Exception e) {
        }

    }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					initLookAndFeel();
					Mainframe frame = new Mainframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Mainframe() {
		setTitle("Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		EditorPanel checkboxPanel = new EditorPanel();
		contentPane.add(checkboxPanel, BorderLayout.WEST);
		checkboxPanel.setLayout(new BorderLayout());
		{
			EditorLabel editorLabel = new EditorLabel();
			editorLabel.setHorizontalAlignment(SwingConstants.CENTER);
			editorLabel.setPropertyName("header");
			editorLabel.setIEditor(mainEd);
			contentPane.add(editorLabel, BorderLayout.NORTH);
		}
		{
			EditorCheckBox cbZip = new EditorCheckBox();
			cbZip.setText("Zip-Dateien Einträge");
			cbZip.setPropertyName("isZip");
			cbZip.setIEditor(mainEd);
			checkboxPanel.add(cbZip, BorderLayout.NORTH);
		}
		{
			EditorCheckBox cbJar = new EditorCheckBox();
			cbJar.setText("JAR-Dateien Einträge");
			cbJar.setPropertyName("isJar");
			cbJar.setIEditor(mainEd);
			checkboxPanel.add(cbJar, BorderLayout.EAST);
		}
		{
			EditorTextField txtSuchbegriff = new EditorTextField();
			txtSuchbegriff.setPropertyName("suchBegriff");
			txtSuchbegriff.setFont(smallFont);
			txtSuchbegriff.setIEditor(mainEd);
			checkboxPanel.add(txtSuchbegriff, BorderLayout.SOUTH);
		}
		{
			EditorButton dtrbtnSuchen = new EditorButton();
			dtrbtnSuchen.setText("Suchen");
			dtrbtnSuchen.setOperationName("btnSuchen");
			dtrbtnSuchen.setIEditor(mainEd);
			dtrbtnSuchen.setMnemonic(KeyEvent.VK_S);

			contentPane.add(dtrbtnSuchen, BorderLayout.SOUTH);
		}
		{
			contentPane.add(mainEd.scrollPane, BorderLayout.CENTER);
		}
	}
}
