package agrawala.beans;



public class RequestRessource {


	private int id_from;
	private int ID_to;
	private long time;
	
	public RequestRessource(int id_from, long l){
		this.id_from = id_from;
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
	public void settime(String time) {
		this.time =  Long.valueOf(time);
	}
	public int getID_from() {
		return id_from;
	}

	public void setID_from(int iD) {
		id_from = iD;
	}

	public void setID_from(String iD) {
		id_from = Integer.valueOf(iD);
	}
	public int getID_to() {
		return ID_to;
	}

	public void setID_to(int iD_req) {
		ID_to = iD_req;
	}

	public void setID_to(String iD_req) {
		ID_to = Integer.valueOf(iD_req);
	}
	
}