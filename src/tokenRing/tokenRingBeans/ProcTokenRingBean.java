package tokenRing.tokenRingBeans;



public class ProcTokenRingBean {

	private int port;

	private String ip;
	private int ID;
	
	public ProcTokenRingBean(int id, String AdresseIp, int Port){
		this.ID = id;
		this.ip = AdresseIp;
		this.port = Port;
		
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

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		return "ProcTokenRingBean [port=" + port + ", ip=" + ip + ", ID=" + ID
				+ "]";
	}

	
}