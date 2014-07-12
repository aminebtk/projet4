package ProcessusTokenRing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import beans.ProcTokenRingBean;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class ProcTokenRingFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIP;
	private JTextField textFieldPort;
	private JTextField textFieldID;
	private JLabel labelIDProcN;
	private ProcTokenRingBean mybrean;
	private JLabel lblMessage;
	private JCheckBox chckbxJeVeuxLe;
	private JLabel lblJaiToken;
	private JLabel labelJAiLeToken;


	/**
	 * Create the frame.
	 */
	public ProcTokenRingFrame(ProcTokenRingBean mybrean, ActionListener controller) {
		this.mybrean = mybrean;
		setTitle("Processus : " + mybrean.getID());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, -167+ (((mybrean.getID() < 5) ? mybrean.getID() : 1 )*177), 450, 177);
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
		textFieldIP.setBounds(152, 34, 169, 19);
		panel.add(textFieldIP);
		
		textFieldPort = new JTextField();

		textFieldPort.setColumns(10);
		textFieldPort.setBounds(152, 57, 57, 19);
		panel.add(textFieldPort);
		
		JLabel label_1 = new JLabel("Processus Token Ring");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(10, 33, 142, 20);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Port :");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(107, 56, 35, 20);
		panel.add(label_2);
		
		lblMessage = new JLabel("Message : ");
		lblMessage.setForeground(Color.RED);
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblMessage.setBounds(10, 110, 404, 16);
		panel.add(lblMessage);
		
		textFieldID = new JTextField();

		textFieldID.setColumns(10);
		textFieldID.setBounds(152, 11, 57, 19);
		panel.add(textFieldID);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(117, 10, 35, 20);
		panel.add(lblId);
		
		JLabel lblProcSuivant = new JLabel("Proc Suivant :");
		lblProcSuivant.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProcSuivant.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProcSuivant.setBounds(10, 88, 86, 20);
		panel.add(lblProcSuivant);
		
		labelIDProcN = new JLabel("0");
		labelIDProcN.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelIDProcN.setBounds(107, 87, 35, 20);
		panel.add(labelIDProcN);
		
		chckbxJeVeuxLe = new JCheckBox("Je veux le Token");
		chckbxJeVeuxLe.setFont(new Font("Tahoma", Font.PLAIN, 10));
		chckbxJeVeuxLe.addActionListener(controller);
		chckbxJeVeuxLe.setActionCommand("JeVeuxToken");
		
		chckbxJeVeuxLe.setBounds(276, 0, 138, 23);
		panel.add(chckbxJeVeuxLe);
		
		lblJaiToken = new JLabel("J'ai Token :");
		lblJaiToken.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJaiToken.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblJaiToken.setBounds(134, 88, 86, 20);
		panel.add(lblJaiToken);
		
		labelJAiLeToken = new JLabel("");
		labelJAiLeToken.setHorizontalAlignment(SwingConstants.CENTER);
		labelJAiLeToken.setForeground(Color.RED);
		labelJAiLeToken.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelJAiLeToken.setBounds(231, 87, 35, 20);
		panel.add(labelJAiLeToken);
		setVisible(true);
		
	}


	public void setLabelJAiLeToken(Boolean labelJAiLeToken1) {
		if(labelJAiLeToken1){
			labelJAiLeToken.setForeground(Color.green);
			labelJAiLeToken.setText(labelJAiLeToken1.toString());
		}else{
			labelJAiLeToken.setForeground(Color.red);
			labelJAiLeToken.setText(labelJAiLeToken1.toString());
		}
			
	}


	public void setNextProcID(int id) {
		labelIDProcN.setText(String.valueOf(id));
	}

	public void update(){
		textFieldID.setText(String.valueOf(mybrean.getID()));
		textFieldIP.setText(mybrean.getIp());
		textFieldPort.setText(String.valueOf(mybrean.getPort()));
	}
	public void setMessage(String message){
		lblMessage.setText("Message : " + message);
	}


	public Boolean getIsTokenChecked() {
		// TODO Auto-generated method stub
		return chckbxJeVeuxLe.isSelected() ;
	}
}
