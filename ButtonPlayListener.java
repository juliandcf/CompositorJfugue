package practica4.ej1;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfugue.player.Player;

public class ButtonPlayListener implements ActionListener {
	
	private JPanel panel;
	
	public ButtonPlayListener(JPanel panel) {
		super();
		// Me traigo el panel para poder tomar el valor de text
		this.setPanel(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//  Del panelMelodia me quedo con el texField y escribo en el
		JPanel p = (JPanel) this.getPanel();
		JTextField text = (JTextField) p.getComponent(0);
		Player player = new Player();
		player.play(text.getText());

	}
	
	public Component getPanel() {
		return panel;
	}

	public void setPanel(Component panel) {
		this.panel = (JPanel) panel;
	}
}
