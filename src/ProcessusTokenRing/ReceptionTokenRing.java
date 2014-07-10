package ProcessusTokenRing;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import beans.ProcTokenRingBean;


public class ReceptionTokenRing implements Runnable {

	private BufferedReader in;
	private Socket socket;
	private ServerSocket socketserver;
	private ProcTokenRingBean myBean;

	public ReceptionTokenRing(BufferedReader in, ProcTokenRingBean myBean){
		this.myBean = myBean;
		listen();
	}


	public void listen(){

		System.out.println("J'ecoute : "+  myBean.toString());
		try {
			socketserver = new ServerSocket(myBean.getPort());
			socket = socketserver.accept();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.in = in;
		run();

	}
	public void run() {
		/*		String commandLine;
		try {
			while ((commandLine = in.readLine()) != null){
				String[] commandes = commandLine.split("#");
				int commandeType = Integer.valueOf(commandes[0]);
				switch (commandeType){
				case Cts.NEWIDSUCC :
					succursale.getSuccursaleBean().setIdSucc(Integer.valueOf(commandes[1]));
					succursale.getInterfaceSuccursale().refesh();
					succursale.ConnectToInterm();
					break;
				case Cts.SUSCRIBE_SUCCURSALE :
					SuccursaleBean s = new SuccursaleBean(Integer.valueOf(commandes[1])
							,(commandes[2])
							,Integer.valueOf(commandes[3])
							,Integer.valueOf(commandes[4]));
					//System.out.println(s.getIdSucc() + "|" + succursale.getSuccursaleBean().getIdSucc());
					succursale.addSuccusale(s);
					//System.out.println("Commande addSuccusale!");
					break;
				default:
					System.out.println("Commande introuvable!");
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Erreur NumberFormatException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erreur IOException 2");
			e.printStackTrace();
		}*/

	}

}