
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

class du serveur intermediare reponsable d'envoi de message a la succursale connectée

 ******************************************************/
package agrawala.mediatorProcessus;

import java.io.BufferedReader;
import java.io.PrintWriter;


public class EmissionIntermediaire implements Runnable {

	private PrintWriter out;
	public EmissionIntermediaire(PrintWriter out) {
		this.out = out;
	}

	public void run() {
		
	}
	
	public void EnvoyerMessage(String message){
		out.println(message);  
		out.flush();
	}
}