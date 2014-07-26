package agrawala.processus;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import agrawala.beans.ProcAgrawalaBean;
import agrawala.beans.RequestRessource;
import agrawala.mediatorProcessus.GestionnaireConnexionintermediaire;
import Util.Cts;


public class ReceptionProc implements Runnable {

	private BufferedReader in;
	private ConnexionMediator connexionMediator;
	private int replyCount = 0;
	public ReceptionProc(BufferedReader in, ProcAgrawalaBean procAgrawalaBean, ConnexionMediator connexionMediator){
		this.in = in;
		this.connexionMediator = connexionMediator;
	}

	public void run() {
		String commandLine;
		try {
			while ((commandLine = in.readLine()) != null){
				String[] commandes = commandLine.split("#");
				if(!commandes[0].equals("") ){
					int commandeType = Integer.valueOf(commandes[0]);
					switch (commandeType){
					case Cts.SUSCRIBE_PROC :
						ProcAgrawalaBean p = new ProcAgrawalaBean(Integer.valueOf(commandes[1])
								,(commandes[2])
								,Integer.valueOf(commandes[3]));
						System.out.println("Add recu " + p.toString());
						connexionMediator.addProc(p);
						break;
					case Cts.WANT :
						RequestRessource r = new RequestRessource();
						r.setID_from(commandes[2]);
						r.setID_to(commandes[1]);
						r.settime(commandes[3]);
						if(connexionMediator.getStatut().equals(Cts.AGRA_HELD) || 
								connexionMediator.getProcAgrawala().getRequest().gettime()<Long.valueOf(commandes[3])){
							connexionMediator.getProcAgrawala().addRequest(r);	
						}else{
							String message  = Cts.REPLY + "#" +commandes[1] +
									"#" +commandes[2] + "#" +commandes[3];
							connexionMediator.EnvoyerMessage(message);
						}	
						break;
					case Cts.REPLY :
						replyCount ++;
						if(connexionMediator.getProcAgrawala().getListProc().size()==replyCount){
							connexionMediator.getProcAgrawala().setStatut(Cts.AGRA_HELD);
							connexionMediator.getProcAgrawala().setDelailAttente();
							EmissionProcRessource t = new EmissionProcRessource(connexionMediator);//connexionMediator.getProcAgrawala().getMyBean());
							t.start();
							replyCount=0;
						}
					
						System.out.println("REPLY RECU " +commandes[1]+"|"+commandes[2]+"|"+commandes[3]);
						break;
					case Cts.REMOVE_PROC :
						System.out.println("REMOVE_PROC s" + commandes[1]);
						for(ProcAgrawalaBean g : connexionMediator.getProcAgrawala().getListProc()){
							if(Integer.valueOf(commandes[1])==g.getID()){
								connexionMediator.getProcAgrawala().getListProc().remove(g);
								break;
							}
						}
						connexionMediator.getProcAgrawala().updateListProc();
						break;
					default:
						System.out.println("Commande introuvable!" + commandes);
					}
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