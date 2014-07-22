package agrawala.processus;
/******************************************************
	Cours :           LOG730
	Session :         �t� 2010
	Groupe :          01
	Projet :          Laboratoire #3
	Date cr�ation :   2014-07-01
	Etudiant(e)(s) :    Mohamed Zibouli
						Amine Boutkhil
						Hicham Ouchker
	Code(s) perm. : 	ZIBM29108400
						BOUA20088103
						OUCH16047600
******************************************************

Class succursale reponsable du traitement 
des messages recus via le port d'ecoute.
a l'instanciantation de la class, elle envoi a la banque une demande pour avoir son ID.

******************************************************/ 

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import agrawala.beans.ProcAgrawalaBean;
import Util.Cts;


public class EmissionProcRessource extends Thread{

	private static EmissionProcRessource instance;
	private PrintWriter out;
	private Socket socket;
	private ConnexionMediator connexionMediator;
	//private Succursale succursale;

	public EmissionProcRessource(ConnexionMediator connexionMediator) {
		this.connexionMediator = connexionMediator;
		try {
			socket = new Socket( Cts.IP_RESSOURCE ,Cts.PORT_RESSOURCE);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		String message = Cts.RESERVER_RESSOURCE + "#" + connexionMediator.getProcAgrawala().getMyBean().getID() ;
		out.println(message);  
		out.flush();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		libererRessource( connexionMediator.getProcAgrawala().getMyBean().getID());
	}

	public void libererRessource(int idP){
		String message = Cts.LIBERER_RESSOURCE + "#" + idP ;
		out.println(message);  
		out.flush();
		connexionMediator.getProcAgrawala().replayToAllProc();
	}
	
}