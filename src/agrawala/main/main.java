package agrawala.main;
import Util.Cts;
import tokenRing.ressources.InterfaceRessource;
import tokenRing.ressources.ReceptionRessource;
import tokenRing.ressources.Ressource;

public class main {

	public static void main(String[] args) {
		new AgrawalaProcManager();	
		new Ressource(Cts.IP_RESSOURCE , Cts.PORT_RESSOURCE );

	}

}
