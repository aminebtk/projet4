import Util.Cts;
import ressources.InterfaceRessource;
import ressources.ReceptionRessource;
import ressources.Ressource;

public class main {

	public static void main(String[] args) {
		
		ReceptionRessource rec = new ReceptionRessource(new Ressource(Cts.PORT_RESSOURCE , Cts.IP_RESSOURCE ));
		new InterfaceRessource(rec);
		new TokenRingManager();

	}

}
