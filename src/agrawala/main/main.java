package agrawala.main;
import agrawala.mediatorProcessus.MediatorProcessus;
import Util.Cts;
import tokenRing.tokenRingRessources.InterfaceRessource;
import tokenRing.tokenRingRessources.ReceptionRessource;
import tokenRing.tokenRingRessources.Ressource;

public class main {

	public static void main(String[] args) {
		Thread t = new Thread(new MediatorProcessus());
		t.start();
		new AgrawalaProcManager();	
		new Ressource(Cts.IP_RESSOURCE , Cts.PORT_RESSOURCE );
 	}

}
