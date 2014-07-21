package agrawala.beans;



public class RequestRessource {


	private int ID;
	private int ID_req;
	private long time;
	
	public RequestRessource(int id, long l){
		this.ID = id;
		this.time = l;
	}

	public RequestRessource() {
		// TODO Auto-generated constructor stub
	}

	public long gettime() {
		return time;
	}

	public void settime(long time) {
		this.time = time;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getID_req() {
		return ID_req;
	}

	public void setID_req(int iD_req) {
		ID_req = iD_req;
	}

	
}