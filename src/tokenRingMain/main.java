package tokenRingMain;
import Util.Cts;
import tokenRingRessources.InterfaceRessource;
import tokenRingRessources.ReceptionRessource;
import tokenRingRessources.Ressource;

public class main {

	public static void main(String[] args) {
		new TokenRingManager();	
		new Ressource(Cts.IP_RESSOURCE , Cts.PORT_RESSOURCE );


	}

}