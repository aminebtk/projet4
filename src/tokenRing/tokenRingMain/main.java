package tokenRing.tokenRingMain;
import Util.Cts;
import tokenRing.tokenRingRessources.InterfaceRessource;
import tokenRing.tokenRingRessources.ReceptionRessource;
import tokenRing.tokenRingRessources.Ressource;

public class main {

	public static void main(String[] args) {
		new TokenRingManager();	
		new Ressource(Cts.IP_RESSOURCE , Cts.PORT_RESSOURCE );


	}

}
