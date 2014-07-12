package ressources;

public class Ressource {
	
	private int port;
	private String ip;
	
	
	public Ressource(int port , String ip){
		this.port =port;
		this.ip = ip;
		
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "Ressource [port=" + port + ", ip=" + ip + "]";
	}
	
	
	
	

}
