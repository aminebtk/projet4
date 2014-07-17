package ressources;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.JList;
import javax.swing.border.LineBorder;


public class InterfaceRessource extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5023068492863942995L;
	DefaultListModel<String>   listModel;
	private JLabel lblPro;

	public InterfaceRessource(){

		listModel = new DefaultListModel<String> ();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("IMPRIMANTE PARTAGÉE");
		setBounds(10, 300, 254, 312);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 216, 251);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("L'imprimante est utilis\u00E9e par PROCESSUS :  ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label.setBounds(10, 11, 207, 14);
		panel.add(label);

		lblPro = new JLabel("AUCUN");
		lblPro.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPro.setForeground(Color.RED);
		lblPro.setBounds(10, 36, 201, 14);
		panel.add(lblPro);

		JLabel lblImpression = new JLabel("Historique d'utilisation :");
		lblImpression.setBounds(10, 61, 157, 14);
		panel.add(lblImpression);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 196, 156);
		panel.add(scrollPane);

		JList<String> list = new JList<String>(listModel);
		scrollPane.setViewportView(list);
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setVisible(true);

	}

	public void ajouterReservation(String string) {
		Date n = new Date();
		listModel.add(0,string + " - " +  n.toString()  );

		lblPro.setText(string);
	}

	public void libererReservation() {
		lblPro.setText("Aucun");
	}
}
