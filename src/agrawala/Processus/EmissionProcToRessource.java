package agrawala.Processus;
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

import Util.Cts;
import agrawala.beans.ProcAgrawalaBean;


public class EmissionProcToRessource {

	private static EmissionProcToRessource instance;
	private PrintWriter out;
	private Socket socket;
	//private Succursale succursale;

	public EmissionProcToRessource() {
		
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
		//out.println(Cts.AJOUT_SUCCURSALE+"#"+succursale.getSuccursaleBean().getIp()+"#"+succursale.getSuccursaleBean().getPortEcoute()+"#"+succursale.getSuccursaleBean().getMontantDepart());  
		//out.flush();
	}

	public void reserverRessource(int idP){
		String message = Cts.RESERVER_RESSOURCE + "#" + idP ;
		out.println(message);  
		out.flush();
	}

	public void libererRessource(int idP){
		String message = Cts.LIBERER_RESSOURCE + "#" + idP ;
		out.println(message);  
		out.flush();
	}
	
	public void printInRessource(String message){
		String message2 = Cts.PRINT_RESSOURCE + "#" + message ;
		out.println(message2);  
		out.flush();
	}
	
	
	public static EmissionProcToRessource getInstance(){
		if(instance==null)
				instance = new EmissionProcToRessource();
		return instance;
	} 
	
}