package ProcessusTokenRing;


import java.io.BufferedReader;
import java.io.PrintWriter;

import beans.ProcTokenRingBean;


public class ProcTokenRing {

	private BufferedReader in = null;
	private PrintWriter out = null;


	private ProcTokenRingBean myBean;
	private ProcTokenRingBean nextProcTokebRingBean;
	private ProcTokenRingBean previousProcTokebRingBean;
	private ReceptionTokenRing listnerProc;
	private EmissionProcTokenRing senderProc;

	public ProcTokenRing(int id, String AdresseIp, int Port, ProcTokenRingBean n, ProcTokenRingBean p){
		setNextProcTokebRing(n);
		setPreviousProcTokebRing(p);
		setMyBean(new ProcTokenRingBean(id, AdresseIp, Port));
		run();
	}

	public ProcTokenRing() {
		// TODO Auto-generated constructor stub
	}

	private void run() {

		Thread t3 = new Thread(listnerProc = new ReceptionTokenRing(in, myBean));
		t3.start();
		if(getNextProcTokebRing()!=null){
			createSender();
		}
	}


	public void createSender(){
		Thread t4 = new Thread(senderProc = new EmissionProcTokenRing(out, nextProcTokebRingBean));
		t4.start();	

	}
	public ReceptionTokenRing getListnerProc() {
		return listnerProc;
	}

	public EmissionProcTokenRing getSenderProc() {
		return senderProc;
	}

	public ProcTokenRingBean getNextProcTokebRing() {
		return nextProcTokebRingBean;
	}

	public void setNextProcTokebRing(ProcTokenRingBean nextProcTokebRing) {
		this.nextProcTokebRingBean = nextProcTokebRing;
	}

	public ProcTokenRingBean getPreviousProcTokebRing() {
		return previousProcTokebRingBean;
	}

	public void setPreviousProcTokebRing(ProcTokenRingBean previousProcTokebRing) {
		this.previousProcTokebRingBean = previousProcTokebRing;
	}

	public ProcTokenRingBean getMyBean() {
		return myBean;
	}

	public void setMyBean(ProcTokenRingBean myBean) {
		this.myBean = myBean;
	}

	// This void is called only for the first Proc,
	// because the first proc has no next and previous ProcBeans
	public void connectFirtProc(){
		run();
	}

}