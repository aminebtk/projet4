package agrawala.processus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Util.Cts;
import agrawala.beans.ProcAgrawalaBean;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class ProcAgrawalaFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldIP;
	private JTextField textFieldPort;
	private JTextField textFieldID;
	private ProcAgrawalaBean mybrean;
	private JLabel lblMessage;
	private DefaultListModel<String> listModel;
	private JLabel labelStatus;
	private JLabel lblDelai;

	/**
	 * Create the frame.
	 */
	public ProcAgrawalaFrame(ProcAgrawalaBean mybrean, ActionListener controller) {
		this.mybrean = mybrean;
		setTitle("Processus : " + mybrean.getID());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, -167+ (((mybrean.getID() < 5) ? mybrean.getID() : 1 )*177), 388, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setToolTipText("A Panel container");
		contentPane.add(panel, BorderLayout.CENTER);

		textFieldIP = new JTextField();

		textFieldIP.setColumns(10);
		textFieldIP.setBounds(55, 33, 86, 19);
		panel.add(textFieldIP);

		textFieldPort = new JTextField();

		textFieldPort.setColumns(10);
		textFieldPort.setBounds(55, 56, 57, 19);
		panel.add(textFieldPort);

		JLabel lblProcessusAgrawala = new JLabel("IP :");
		lblProcessusAgrawala.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProcessusAgrawala.setBounds(10, 33, 49, 20);
		panel.add(lblProcessusAgrawala);

		JLabel label_2 = new JLabel("Port :");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(10, 56, 35, 20);
		panel.add(label_2);

		lblMessage = new JLabel("Message : ");
		lblMessage.setForeground(Color.RED);
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblMessage.setBounds(10, 176, 347, 16);
		panel.add(lblMessage);

		textFieldID = new JTextField();

		textFieldID.setColumns(10);
		textFieldID.setBounds(55, 10, 57, 19);
		panel.add(textFieldID);

		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(10, 10, 35, 20);
		panel.add(lblId);

		JLabel lblProcSuivant = new JLabel("Etat du processus");
		lblProcSuivant.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProcSuivant.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProcSuivant.setBounds(0, 87, 132, 20);
		panel.add(lblProcSuivant);

		ButtonGroup group = new ButtonGroup();

		listModel = new DefaultListModel<String> ();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(184, 66, 173, 99);
		panel.add(scrollPane);
		JList<String> list = new JList<String>(listModel);
		scrollPane.setViewportView(list);
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		JLabel lblListOfProcessus = new JLabel("Liste des processus");
		lblListOfProcessus.setHorizontalAlignment(SwingConstants.LEFT);
		lblListOfProcessus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblListOfProcessus.setBounds(184, 45, 145, 19);
		panel.add(lblListOfProcessus);

		labelStatus = new JLabel("");
		labelStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelStatus.setBounds(24, 102, 117, 20);
		panel.add(labelStatus);

		JButton btnNewButton = new JButton("Demander ressource");
		btnNewButton.addActionListener(controller);
		btnNewButton.setActionCommand("demander");
		btnNewButton.setBounds(183, 9, 169, 28);
		panel.add(btnNewButton);
		
		lblDelai = new JLabel("");
		lblDelai.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDelai.setBounds(24, 145, 117, 20);
		panel.add(lblDelai);
		
		JLabel lblDlaiDattente = new JLabel("D\u00E9lai d'attente :");
		lblDlaiDattente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDlaiDattente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDlaiDattente.setBounds(0, 130, 132, 20);
		panel.add(lblDlaiDattente);

		addWindowListener((WindowListener) controller);

		setVisible(true);

	}



	public void update(){
		textFieldID.setText(String.valueOf(mybrean.getID()));
		textFieldIP.setText(mybrean.getIp());
		textFieldPort.setText(String.valueOf(mybrean.getPort()));

	}
	public void setMessage(String message){
		lblMessage.setText("Message : " + message);
	}



	public void updateListProc(ArrayList<ProcAgrawalaBean> listProc) {
		listModel.removeAllElements();
		for(ProcAgrawalaBean p : listProc){
			listModel.addElement(p.toString());
		}
		setMessage("");
	}


	public int getIdProc(){
		return Integer.valueOf(textFieldID.getText());
	}
	public void setStatut(String s) {
		labelStatus.setText(s); 
		if(s.equals(Cts.AGRA_HELD))
			labelStatus.setForeground(Color.GRAY);
		if(s.equals(Cts.AGRA_RELEASED))
			labelStatus.setForeground(Color.BLUE);
		if(s.equals(Cts.AGRA_WANTED))
			labelStatus.setForeground(Color.RED);
	}
	
	public void setDelaiAttente(Timestamp timeCounter){
		Date date= new Date();
		Timestamp t = new Timestamp(date.getTime());
		long o =   t.getTime() - timeCounter.getTime();
		lblDelai.setText("Attente : " + String.valueOf(o));
		System.out.println("Agrawala Delai;" + mybrean.getID() + ";" + String.valueOf(o) );
	}
}
