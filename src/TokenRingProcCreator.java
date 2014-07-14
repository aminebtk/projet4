/******************************************************
	Cours :           LOG730
	Session :         Été 2010
	Groupe :          01
	Projet :          Laboratoire #3
	Date création :   2014-07-01
	Etudiant(e)(s) :    Mohamed Zibouli
						Amine Boutkhil
						Hicham Ouchker
	Code(s) perm. : 	ZIBM29108400
						BOUA20088103
						OUCH16047600
 ******************************************************

Interface graphique offrant la possibilité a l'utilisateur d'interagir avec l'application


 ******************************************************/ 

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TokenRingProcCreator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIP;
	private JTextField textFieldPort;
	private JLabel labelError;
	private JButton btnLancerToken;

	public TokenRingProcCreator(ActionListener controller) {
		initUI(controller);
	}

	private void initUI(ActionListener controller) {

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setToolTipText("A Panel container");
		panel.setLayout(null);

		JLabel lblListeDesSuccursales = new JLabel("Cr\u00E9ateur processus :");
		lblListeDesSuccursales.setBounds(10, 11, 174, 19);
		lblListeDesSuccursales.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblListeDesSuccursales);

		textFieldIP = new JTextField();
		textFieldIP.setText("127.0.0.1");
		textFieldIP.setBounds(152, 45, 169, 27);
		panel.add(textFieldIP);
		textFieldIP.setColumns(10);

		textFieldPort = new JTextField();
		textFieldPort.setText("9002");
		textFieldPort.setColumns(10);
		textFieldPort.setBounds(152, 75, 57, 27);
		panel.add(textFieldPort);

		JLabel lblServeurDeLa = new JLabel("Processus Token Ring");
		lblServeurDeLa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblServeurDeLa.setBounds(10, 44, 142, 27);
		panel.add(lblServeurDeLa);

		JLabel lblPort = new JLabel("Port :");
		lblPort.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPort.setBounds(112, 74, 35, 27);
		panel.add(lblPort);

		JButton btnNewButton = new JButton("Créer Processus");
		btnNewButton.addActionListener(controller);
		btnNewButton.setActionCommand("ajouterProc");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.setBounds(150, 106, 134, 27);
		panel.add(btnNewButton);

		labelError = new JLabel("");
		labelError.setForeground(Color.RED);
		labelError.setFont(new Font("Tahoma", Font.BOLD, 10));
		labelError.setBounds(10, 184, 365, 16);
		panel.add(labelError);
		
		btnLancerToken = new JButton("Lancer Token");
		btnLancerToken.addActionListener(controller);
		btnLancerToken.setActionCommand("lancerToken");
		btnLancerToken.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnLancerToken.setBounds(150, 146, 134, 27);
		btnLancerToken.setEnabled(false);
		panel.add(btnLancerToken);
		setTitle("Créer Processus");
		setSize(401, 238);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	public static boolean isInteger(String s) {
		int i=-1;
		try { 
			i = Integer.parseInt(s); 
			if(i<0)
				return false;
		} catch(NumberFormatException e) { 
			return false; 
		}
		return true;
	}

	@SuppressWarnings("unused")
	private void showError(String s){
		labelError.setText(s);
	}

	public String getIP() {
		return textFieldIP.getText();
	}

	public int getPort() {
		return Integer.valueOf(textFieldPort.getText());
	}

	public void setPort(int i) {
		textFieldPort.setText(String.valueOf(i));
	}

	public JButton getBtnLancerToken( ) {
		return this.btnLancerToken ;
	}
	

}

