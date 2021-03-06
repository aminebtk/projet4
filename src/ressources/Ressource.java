package ressources;

public class Ressource {
	
	private int port;
	private String ip;
	@SuppressWarnings("unused")
	private ReceptionRessource reception;
	private InterfaceRessource interfaceR;
	
	public Ressource(String ip , int port ){
		
		this.port =port;

		interfaceR = new InterfaceRessource();
		reception = new ReceptionRessource(port,interfaceR);

		
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "Ressource [port=" + port + ", ip=" + ip + "]";
	}
	
	
	
	

}
