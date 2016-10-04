package practica4.ej1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CompositorDeMelodias implements ActionListener {
	private JFrame frame;
	private String tempo;
	private JButtonNotas ultimoBoton;
	
	public CompositorDeMelodias(){
		this.setTempo(null);
		this.setFrame(new JFrame("Compositor de Melodías"));
		this.setUltimoBoton(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButtonNotas) {
			JButtonNotas b = (JButtonNotas) e.getSource();
			if (this.getUltimoBoton() != null){
				this.getUltimoBoton().setBackground(Color.WHITE);
			}
			this.setUltimoBoton(b);
			b.setBackground(Color.CYAN);
			this.setTempo(b.getNombre());
		}
	}
	
	public void comenzar() throws IOException{
		this.getFrame().setSize(700, 600);  
		this.getFrame().setResizable(false);  
		this.getFrame().setLocationRelativeTo(null); //para centralizar la ventana en la pantalla
		
		this.getFrame().setLayout(new BorderLayout());
		
		JPanel panelFigurasMusicales = new JPanel();
		panelFigurasMusicales.setBackground(Color.DARK_GRAY);
				
		for(FigurasMusicales f: FigurasMusicales.values()){
			JButtonNotas button = new JButtonNotas(f.getValor());
			button.setBackground(Color.WHITE);
			button.addActionListener(this);
			button.setIcon(new ImageIcon(f.getImage()));
			panelFigurasMusicales.add(button);		
		}
		
		this.getFrame().getContentPane().add(panelFigurasMusicales, BorderLayout.NORTH);
		
		JPanel panelPentagrama = new JPanel();
		panelPentagrama.setBackground(Color.GRAY);
		
		Image img = ImageIO.read(getClass().getResource("compositor-imagenes/pentagrama.gif"));
		JLabel jl = new JLabel(new ImageIcon(img));
		panelPentagrama.add(jl);
		
		MouseAdapter mouse;
		
		panelPentagrama.addMouseListener(mouse = new MouseAdapter(){
			
				 public void mouseClicked(MouseEvent evt) {
					if(tempo != null){ 
						JPanel panelContenedor= (JPanel) evt.getComponent();
						// obtengo el JPanel panelMelodia no encontre otra forma mas prolija de hacerlo
						JPanel panelText = (JPanel)panelContenedor.getRootPane().getContentPane().getComponent(2);
						//  Del panelMelodia me quedo con el texField y escribo en el
						JTextField text = (JTextField) panelText.getComponent(0);
						String nota = this.calcularNota(evt.getY());
						String imprimir = nota+"5"+tempo;
						text.setText(text.getText() + " " + imprimir);
					}
		             
		         }
		
				private String calcularNota(int y) {
					if(((y >= 429) || ((y < 220) && (y >= 180))) || (y < 25)){
						return "C";
					}else if(((y > 400) && (y <= 429)) || ((y < 180) && (y >= 156))) {
						return "D";
					}else if(((y < 429 ) && (y >= 370)) || ((y < 156) && (y >= 105))){
						return "E";
					}else if(((y < 370 ) && (y >= 325)) || ((y < 105) && (y >= 82))){
						return "F";
					}else if(((y < 325 ) && (y >= 300)) || ((y < 85) && (y >= 57))){
						return "G";
					}else if(((y < 300 ) && (y >= 256)) || ((y < 57) && (y >= 40))){
						return "A";
					}else if(((y < 256 ) && (y >= 220)) || ((y < 40) && (y >= 25))){
						return "B";
					}
					return null;
				}
		
		});
					
		this.getFrame().getContentPane().add(panelPentagrama, BorderLayout.CENTER);
		
		JPanel panelMelodia = new JPanel();
		panelMelodia.setBackground(Color.DARK_GRAY);
		
		JTextField textField = new JTextField(40);
		textField.setEditable(false);
		panelMelodia.add(textField);
		
		JCheckBox checkbox = new JCheckBox("MODE", true);
		checkbox.addItemListener(new ItemListener() {
			String texto;
			public void itemStateChanged(ItemEvent e) {				
				if(checkbox.isSelected()){
					panelPentagrama.addMouseListener(mouse);
					textField.setText(texto);
					textField.setEditable(false);
				}
				else{
					panelPentagrama.removeMouseListener(mouse);
					texto = textField.getText();
					textField.setText("");
					textField.setEditable(true);
				}				
			}
		});
		panelMelodia.add(checkbox);
		
		JButton clear_button = new JButton("CLEAR");
		clear_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				
			}
		});
		clear_button.setBackground(Color.RED);
		clear_button.setForeground(Color.YELLOW);
		panelMelodia.add(clear_button);
		
		JButton play_button = new JButton("PLAY");
		play_button.addActionListener(new ButtonPlayListener(panelMelodia));
		play_button.setBackground(Color.ORANGE);
		play_button.setForeground(Color.WHITE);
		panelMelodia.add(play_button);
		
		this.getFrame().getContentPane().add(panelMelodia, BorderLayout.SOUTH);
		
		this.getFrame().setVisible(true);
	}
	
	public static void main(String[] args) throws IOException {
		CompositorDeMelodias cm = new CompositorDeMelodias();
		cm.comenzar();
	}

	public JFrame getFrame() {
		return frame;
	}

	private void setFrame(JFrame frame) {
		this.frame = frame;
	} 	
	
	public String getTempo() {
		return tempo;
	}

	private void setTempo(String tempo) {
		this.tempo = tempo;
	}
	
	public JButtonNotas getUltimoBoton() {
		return ultimoBoton;
	}

	private void setUltimoBoton(JButtonNotas ultimoBoton) {
		this.ultimoBoton = ultimoBoton;
	}
}
