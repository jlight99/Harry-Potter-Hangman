import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

abstract class GamePanel extends JPanel {
	JFrame frame;
	int guessMode;

	public void proceed() {
		frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		this.setVisible(false);
	}
}
