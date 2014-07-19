package agrawala.processus;

import java.io.BufferedReader;
import java.io.IOException;

import agrawala.beans.ProcAgrawalaBean;
import Util.Cts;


public class ReceptionProc implements Runnable {

	private BufferedReader in;
	private ConnexionMediator connexionMediator;

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
					default:
						System.out.println("Commande introuvable!");
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