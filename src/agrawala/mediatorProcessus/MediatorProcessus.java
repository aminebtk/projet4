
/******************************************************
	Cours :           LOG730
	Session :         Été 2010
	Groupe :          01
	Projet :          Laboratoire #3
	Date création :   2010-05-21
	Etudiant(e)(s) :    Mohamed Zibouli
						Amine Boutkhil
						Hicham Ouchker
	Code(s) perm. : 	ZIBM29108400
						BOUA20088103
						OUCH16047600
 ******************************************************

Class qui creer les thread pour chaque succursale qui demande une connextion.

 ******************************************************/
package agrawala.mediatorProcessus;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import agrawala.beans.ProcAgrawalaBean;
import Util.Cts;


public class MediatorProcessus implements Runnable{

	private ServerSocket socketserver = null;
	private Socket socket = null;
	private ArrayList<GestionnaireConnexionintermediaire> gestConProcList;
	public Thread t1;

	public MediatorProcessus(){
		try {
			socketserver = new ServerSocket(Cts.INTERMEDIAIRE_PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gestConProcList = new ArrayList<GestionnaireConnexionintermediaire>();
	}

	public ArrayList<GestionnaireConnexionintermediaire> getGestionnaireConnexionBanqueList() {
		return gestConProcList;
	}

	public void run() {

		try {
			while(true){
				System.out.println("Le serveur intermediaire est pret pour accepter les connexions !");
				socket = socketserver.accept();
				System.out.println("Inter:Un precessus s'est connecté");
				gestConProcList.add(new GestionnaireConnexionintermediaire(socket, this));
			}
		} catch (IOException e) {
			System.err.println("Erreur serveur");
		}

	}

	/*
	public void doTransfer(Transfer t) {
		String message;
		for (GestionnaireConnexionintermediaire g : gestConProcList){
			if(g.getSuccursale().getIdSucc()==t.getTo()){
				message = Cts.TRANSFER_SUCCURSALE + "#"
							+ t.getFrom() + "#"
							+t.getMontant();
				g.envoyerMessage(message);
				break;
			}
		}
	}*/


	public void EnvoyerMessage(String message , String commandes) {
		int desti = Integer.valueOf(commandes);
		for (GestionnaireConnexionintermediaire g : gestConProcList){

		}
	}

	public void informerLesAutresProc() {
		for (GestionnaireConnexionintermediaire g : gestConProcList){
			for (GestionnaireConnexionintermediaire g1 : gestConProcList){
				if(g.getProcBean().getID()!=g1.getProcBean().getID()){
					System.out.println("je suis " + g.getProcBean().getID() + " Send to " + g1.getProcBean().getID());
					String message = Cts.SUSCRIBE_PROC +"#"+g.getProcBean().getID() + 
							"#" + g.getProcBean().getIp() + 
							"#" + g.getProcBean().getPort() ;
					g1.envoyerMessage(message);
				}
			}
		}

	}
}