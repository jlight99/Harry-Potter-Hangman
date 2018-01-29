import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

abstract class GamePanel extends JPanel {
	protected JFrame frame;
	protected GameData gameData;
	
	GamePanel(GameData gameData) {
		this.gameData = gameData;
		setLayout(null);
	}

	protected void proceed() {
		frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		this.setVisible(false);
	}
}
