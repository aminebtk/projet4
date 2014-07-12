package ressources;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.Timestamp;

import Util.Cts;
import beans.ProcTokenRingBean;


public class ReceptionRessource extends Thread {

	private BufferedReader in;
	private static Socket socket = null;
	private ServerSocket socketserver;
	private Ressource myBean;


	public ReceptionRessource(Ressource myBean){
		
		this.myBean = myBean;
		try {
			socketserver = new ServerSocket(myBean.getPort());
			System.out.println("J'ecoute : "+  myBean.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public void closePort(){
//		try {
//			socketserver.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	private void listen(){

		try {
			socket = socketserver.accept();
			System.out.println("Process connect� : 1" );
		} catch (SocketException e1) {
			System.out.println("Socket close");
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

	}
	public void run() {
		listen();
		String commandLine;
		try {
			while ((commandLine = in.readLine()) != null){
				String[] commandes = commandLine.split("#");
				int commandeType = Integer.valueOf(commandes[0]);
				switch (commandeType){
				case Cts.LIBERER_RESSOURCE :
					System.out.println("la ressource est liberee par  " + commandes[1]);
					break;
				case Cts.RESERVER_RESSOURCE :
					System.out.println("la ressource est reservee par  " + commandes[1]);
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
		}

	}

}