
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

Class principale d'intermediaire qui cree
les deux objects PrintWriter et BufferedReader pour la succursale connectée

 ******************************************************/
package mediatorProcessus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import agrawala.beans.ProcAgrawalaBean;



public class GestionnaireConnexionintermediaire {

	private Socket socket = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String login = "zero";
	private MediatorProcessus connexionEcouteur;
	private ProcAgrawalaBean procBean;
	private EmissionIntermediaire emission;

	public ProcAgrawalaBean getProcBean() {
		return procBean;
	}


	public GestionnaireConnexionintermediaire(Socket s, MediatorProcessus connexionEcouteur){

		this.socket = s;
		this.connexionEcouteur = connexionEcouteur;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());

			Thread t3 = new Thread(new ReceptionIntermediaire(out , in, this ));
			t3.start();
			Thread t4 = new Thread(emission = new EmissionIntermediaire(out));
			t4.start();

		} catch (IOException e) {
			System.err.println(login +"s'est déconnecté ");
		}

	}

	public MediatorProcessus getConnexionEcouteur() {
		return connexionEcouteur;
	}


	public void setProcBean(ProcAgrawalaBean p){
		procBean = p;
		connexionEcouteur.informerLesAutresProc();
	}


	public void envoyerMessage(String message) {
		emission.EnvoyerMessage(message);
	}

}