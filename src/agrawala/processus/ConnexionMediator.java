package agrawala.processus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

import org.omg.CORBA.Request;

import agrawala.beans.ProcAgrawalaBean;
import agrawala.beans.RequestRessource;
import Util.Cts;


public class ConnexionMediator implements Runnable {

	private Socket socket = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private int port;
	private String ip;
	private EmissionProc emission;
	private ProcAgrawalaBean procAgrawalaBean;
	private ProcAgrawala procAgrawala;


	public ConnexionMediator(String AdresseIp, int Port, ProcAgrawalaBean procAgrawalaBean, ProcAgrawala procAgrawala){
		this.procAgrawala = procAgrawala;
		this.procAgrawalaBean = procAgrawalaBean;
		this.ip = AdresseIp;
		this.port = Port;
		run();
	}

	public void run() {

		try {
			socket = new Socket( ip,port);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		try {
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread t3 = new Thread(new ReceptionProc(in,procAgrawalaBean , this));
		t3.start();
		Thread t4 = new Thread(emission = new EmissionProc(out,procAgrawalaBean, this));
		t4.start();
	}

	public void EnvoyerMessage(String message){
		System.out.println("envoyermessage:" + message);
		emission.EnvoyerMessage(message);
	}

	public void envoyerDemandeRes() {

	if(!procAgrawala.getStatut().equals(Cts.AGRA_WANTED)) {// ne peut demander deux fois la ressource
		ArrayList<ProcAgrawalaBean> listProc1 = new ArrayList<ProcAgrawalaBean>();
		listProc1 = procAgrawala.getListProc();
		RequestRessource rr = new RequestRessource();
		rr =  procAgrawala.createRequest(); 
		for(ProcAgrawalaBean p : listProc1){
			String message  = Cts.WANT + "#" + p.getID() +
					"#" + rr.getID() + "#" + rr.gettime();
			EnvoyerMessage(message);
		}
	}

	}

	public void setMyBean(ProcAgrawalaBean p) {
		// TODO Auto-generated method stub

	}

	public void addProc(ProcAgrawalaBean p) {
		procAgrawala.addProc(p);
	}


	/*	private int getRandomDelay(){
		Random r = new Random();
		return r.nextInt(Cts.DELAY_TRANSFER_MAX -Cts.DELAYTRANSFER) + Cts.DELAYTRANSFER;
	}*/

}