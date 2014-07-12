import ressources.InterfaceRessource;
import ressources.ReceptionRessource;
import ressources.Ressource;

public class main {

	public static void main(String[] args) {
		
		ReceptionRessource rec = new ReceptionRessource(new Ressource(10000, "127.0.0.1"));
		new InterfaceRessource(rec);
		new TokenRingManager();

	}

}
