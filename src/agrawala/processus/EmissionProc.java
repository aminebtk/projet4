package agrawala.processus;

import java.io.PrintWriter;

import agrawala.beans.ProcAgrawalaBean;
import Util.Cts;


public class EmissionProc implements Runnable {

	private PrintWriter out;
	private ProcAgrawalaBean procAgrawalaBean;

	public EmissionProc(PrintWriter out, ProcAgrawalaBean procAgrawalaBean, ConnexionMediator connexionMediator) {
		this.procAgrawalaBean = procAgrawalaBean;
		this.out = out;
	}

	public void run() {
		String message = Cts.SUSCRIBE_PROC + "#" + procAgrawalaBean.getID() + 
				"#" + procAgrawalaBean.getIp() + 
				"#" + procAgrawalaBean.getPort() ;
		//System.out.println(message);
		out.println(message);  
		out.flush();
	}

	public void EnvoyerMessage(String message){
		out.println(message);  
		out.flush();
	}
}