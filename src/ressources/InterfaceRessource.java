package ressources;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;


public class InterfaceRessource extends JFrame{
	
	public InterfaceRessource(ReceptionRessource recRessource){
		getContentPane().setLayout(null);
		setTitle("IMPRIMANTE PARTAGÉE");
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 251);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("L'imprimante est utilis\u00E9e par PROCESSUS :  ");
		label.setBounds(10, 11, 207, 14);
		panel.add(label);
		
		JLabel lblPro = new JLabel("PRO");
		lblPro.setBounds(254, 11, 46, 14);
		panel.add(lblPro);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 63, 394, 177);
		panel.add(textArea);
		
		JLabel lblImpression = new JLabel("Log impression ");
		lblImpression.setBounds(10, 38, 75, 14);
		panel.add(lblImpression);
		setVisible(true);
		
	}
}
