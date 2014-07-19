package tokenRingRessources;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import Util.Cts;


public class ReceptionRessource extends Thread {

	private BufferedReader in;
	private static Socket socket = null;
	private ServerSocket socketserver;

	private InterfaceRessource interfaceR;


	public ReceptionRessource(int port, InterfaceRessource interfaceR){
		this.interfaceR = interfaceR;
		try {
			socketserver = new ServerSocket(port);
			listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listen();
		
	}

	private void listen(){

		try {
			System.out.println("listen ressource:" );
			socket = socketserver.accept();
			System.out.println("Connect�e" );
		} catch (SocketException e1) {
			System.out.println("Socket close");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {

		String commandLine;
		try {
			while ((commandLine = in.readLine()) != null){
				System.out.println("Demande recue");
				String[] commandes = commandLine.split("#");
				int commandeType = Integer.valueOf(commandes[0]);
				switch (commandeType){
				case Cts.LIBERER_RESSOURCE :
					System.out.println("la ressource est liberee par  " + commandes[1]);
					interfaceR.libererReservation();
					socket.close();
					listen();
					break;
				case Cts.RESERVER_RESSOURCE :
					System.out.println("la ressource est reservee par  " + commandes[1]);
					interfaceR.ajouterReservation(commandes[1]);

					break;
				case Cts.PRINT_RESSOURCE :
					interfaceR.printMessage(commandes[1]);
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
