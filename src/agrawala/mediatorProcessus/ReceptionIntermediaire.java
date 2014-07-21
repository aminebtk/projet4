
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

Class ayant pour reponsabilité d'ecouter le port d'echange entre les succursales.

 ******************************************************/
package agrawala.mediatorProcessus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import agrawala.beans.ProcAgrawalaBean;
import Util.Cts;

public class ReceptionIntermediaire implements Runnable {

	private BufferedReader in;
	private PrintWriter out;
	private GestionnaireConnexionintermediaire getGestionnaireConnexion;
	
	public ReceptionIntermediaire(PrintWriter out, BufferedReader in, GestionnaireConnexionintermediaire gestionnaireConnexionBanque){
		this.out = out;
		this.in = in;
		this.getGestionnaireConnexion = gestionnaireConnexionBanque;
	}
	
	public void run() {
		
		String commandLine;
		try {
			while ((commandLine = in.readLine()) != null){
				
				String[] commandes = commandLine.split("#");
				int commandeType = Integer.valueOf(commandes[0]);
				switch (commandeType){
				case Cts.SUSCRIBE_PROC :
					ProcAgrawalaBean p;
					p = new ProcAgrawalaBean(Integer.valueOf(commandes[1]), commandes[2], Integer.valueOf(commandes[3]));
					getGestionnaireConnexion.setProcBean(p);
					break;
				case Cts.WANT :
					for(GestionnaireConnexionintermediaire g : getGestionnaireConnexion.getMediatorProcessus().getGestionnaireConnexionBanqueList() ){
						if(Integer.valueOf(commandes[1])==g.getProcBean().getID()){
							g.envoyerMessage(commandLine);
						}
					}
					break;
				case Cts.REPLY :
					for(GestionnaireConnexionintermediaire g : getGestionnaireConnexion.getMediatorProcessus().getGestionnaireConnexionBanqueList() ){
						if(Integer.valueOf(commandes[2])==g.getProcBean().getID()){
							g.envoyerMessage(commandLine);
						}
					}
					break;
				case Cts.REMOVE_PROC :
					System.out.println("REMOVE_PROC " + commandes[1]);
					ArrayList<GestionnaireConnexionintermediaire> t = getGestionnaireConnexion.getMediatorProcessus().getGestionnaireConnexionBanqueList();
					
					for(GestionnaireConnexionintermediaire g : t ){
						if(Integer.valueOf(commandes[1])!=g.getProcBean().getID()){
							g.envoyerMessage(commandLine);
						}else{
							getGestionnaireConnexion.getMediatorProcessus().getGestionnaireConnexionBanqueList().remove(g);
							break;
						}
					}
					break;
				default:
					System.out.println("Commande introuvable!");
				}
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}