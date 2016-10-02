package practica4.ej1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CompositorDeMelodias implements ActionListener {
	private JFrame frame;
	private String tempo;
	private Map<String, String> figuras= new HashMap<String, String>();
	private JButtonNotas ultimoBoton;
	
	public JButtonNotas getUltimoBoton() {
		return ultimoBoton;
	}

	public void setUltimoBoton(JButtonNotas ultimoBoton) {
		this.ultimoBoton = ultimoBoton;
	}

	public CompositorDeMelodias(){
		this.setTempo(null);
		this.setFrame(new JFrame("Compositor de Melodías"));
		this.inicializarFiguras();
		this.setUltimoBoton(null);
	}
	
	private void inicializarFiguras() {
		figuras.put("REDONDA", "w" );
		figuras.put("BLANCA", "h" );
		figuras.put("NEGRA", "q");
		figuras.put("CORCHEA", "i");
		figuras.put("SEMICORCHEA", "s");
		figuras.put("FUSA", "t");
		figuras.put("SEMIFUSA", "x");
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
		frame.setSize(700, 600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //para centralizar la ventana en la pantalla
		
		frame.setLayout(new BorderLayout());
		
		JPanel panelFigurasMusicales = new JPanel();
		panelFigurasMusicales.setBackground(Color.DARK_GRAY);
				
		for(FigurasMusicales f: FigurasMusicales.values()){
			JButtonNotas button = new JButtonNotas(figuras.get(f.name()));
			button.setBackground(Color.WHITE);
			button.addActionListener(this);
			button.setIcon(new ImageIcon(f.getImage()));
			panelFigurasMusicales.add(button);		
		}
		
		frame.getContentPane().add(panelFigurasMusicales, BorderLayout.NORTH);
		
		JPanel panelPentagrama = new JPanel();
		panelPentagrama.setBackground(Color.GRAY);
		
		Image img = ImageIO.read(getClass().getResource("compositor-imagenes/pentagrama.gif"));
		JLabel jl =new JLabel(new ImageIcon(img));
		panelPentagrama.add(jl);
		panelPentagrama.addMouseListener(new MouseAdapter(){
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
					
		frame.getContentPane().add(panelPentagrama, BorderLayout.CENTER);
		
		JPanel panelMelodia = new JPanel();
		panelMelodia.setBackground(Color.DARK_GRAY);
		
		JTextField textField = new JTextField(40);
		panelMelodia.add(textField);
		
		JButton button = new JButton("play");
		button.setBackground(Color.ORANGE);
		button.setForeground(Color.WHITE);
		panelMelodia.add(button);
		
		frame.getContentPane().add(panelMelodia, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	} 
	
	public static void main(String[] args) throws IOException {
		CompositorDeMelodias lc = new CompositorDeMelodias();
		lc.comenzar();
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}



}
