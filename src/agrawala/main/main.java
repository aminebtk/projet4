package agrawala.main;
import mediatorProcessus.MediatorProcessus;
import Util.Cts;
import tokenRingRessources.InterfaceRessource;
import tokenRingRessources.ReceptionRessource;
import tokenRingRessources.Ressource;

public class main {

	public static void main(String[] args) {
		Thread t = new Thread(new MediatorProcessus());
		t.start();
		new AgrawalaProcManager();	
		//new Ressource(Cts.IP_RESSOURCE , Cts.PORT_RESSOURCE );

	}

}
