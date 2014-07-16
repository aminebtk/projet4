package agrawala.Processus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import agrawala.beans.ProcAgrawalaBean;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.border.LineBorder;

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
	private JTextArea textArea;
	private JButton btnPrint;

	/**
	 * Create the frame.
	 */
	public ProcAgrawalaFrame(ProcAgrawalaBean mybrean, ActionListener controller) {
		this.mybrean = mybrean;
		setTitle("Processus : " + mybrean.getID());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, -167+ (((mybrean.getID() < 5) ? mybrean.getID() : 1 )*177), 617, 290);
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
		lblMessage.setBounds(10, 215, 404, 16);
		panel.add(lblMessage);

		textFieldID = new JTextField();

		textFieldID.setColumns(10);
		textFieldID.setBounds(152, 11, 57, 19);
		panel.add(textFieldID);

		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(117, 10, 35, 20);
		panel.add(lblId);

		JLabel lblProcSuivant = new JLabel("Etat du processus");
		lblProcSuivant.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProcSuivant.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProcSuivant.setBounds(0, 87, 132, 20);
		panel.add(lblProcSuivant);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 119, 273, 85);
		panel.add(textArea);
		
		btnPrint = new JButton("Imprimer");
		btnPrint.addActionListener(controller);
		btnPrint.setBounds(293, 138, 91, 49);
		panel.add(btnPrint);
		
		JRadioButton rdbtnHeld = new JRadioButton("Held");
		rdbtnHeld.addActionListener(controller);
		rdbtnHeld.setBounds(146, 87, 83, 23);
		panel.add(rdbtnHeld);
		
		JRadioButton rdbtnRelease = new JRadioButton("Release");
		rdbtnRelease.addActionListener(controller);
		rdbtnRelease.setBounds(231, 87, 90, 23);
		panel.add(rdbtnRelease);
		
		JRadioButton rdbtnWait = new JRadioButton("Wait");
		rdbtnWait.addActionListener(controller);
		rdbtnWait.setBounds(323, 86, 57, 23);
		panel.add(rdbtnWait);
		
		 ButtonGroup group = new ButtonGroup();
		group.add(rdbtnHeld);
		group.add(rdbtnRelease);
		group.add(rdbtnWait);
		
		
		DefaultListModel<String> listModel = new DefaultListModel<String> ();
		JList<String> list = new JList<String>(listModel);
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list.setBounds(394, 58, 194, 154);
		panel.add(list);
		
		JLabel lblListOfProcessus = new JLabel("List of processus");
		lblListOfProcessus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblListOfProcessus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblListOfProcessus.setBounds(395, 33, 132, 20);
		panel.add(lblListOfProcessus);
		
		disablePrint();

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

	public String gettxtAreaMessage(){
		return textArea.getText().toString();
	}




	public void enablePrint() {
		textArea.setEnabled(true);
		btnPrint.setEnabled(true);
		
	}

	public void disablePrint() {
		textArea.setText("");
		textArea.setEnabled(false);
		btnPrint.setEnabled(false);
		
	}
	
	public void resetAreaText() {
		textArea.setText("");
		
	}
}
