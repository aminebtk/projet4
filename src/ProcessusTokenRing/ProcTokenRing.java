package ProcessusTokenRing;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Util.Cts;
import beans.ProcTokenRingBean;



public class ProcTokenRing implements ActionListener {


	private ProcTokenRingBean myBean;
	private Boolean JeVeuxToken = false;
	private Boolean JAiLeToken = false;
	private Boolean JaiRessource = false;

	private ProcTokenRingBean nextProcTokebRingBean;
	//private ProcTokenRingBean previousProcTokebRingBean;
	private ReceptionTokenRing listnerProc;
	private EmissionProcTokenRing senderProc;
	private ProcTokenRingFrame procTokenRingFrame;
	private EmissionProcRessource e;

	public ProcTokenRing(int id, String AdresseIp, int Port, ProcTokenRingBean n, ProcTokenRingBean p){
		setMyBean(new ProcTokenRingBean(id, AdresseIp, Port));
		procTokenRingFrame = new ProcTokenRingFrame(getMyBean(), this);
		procTokenRingFrame.update();
		setNextProcTokebRing(n);
		//setPreviousProcTokebRing(p);
		System.out.println(getMyBean().toString());
	}

	public ProcTokenRing() {
		// TODO Auto-generated constructor stub
	}

	public void startReception() {

		if(listnerProc!=null)
			closePort();
		listnerProc = new ReceptionTokenRing(myBean, this);
		listnerProc.start();
	}

	public void startEmission(){
		senderProc = new EmissionProcTokenRing(nextProcTokebRingBean);
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
		if(nextProcTokebRing!=null)
			procTokenRingFrame.setNextProcID(nextProcTokebRing.getID());
		this.nextProcTokebRingBean = nextProcTokebRing;
	}


	public ProcTokenRingBean getMyBean() {
		return myBean;
	}

	public void setMyBean(ProcTokenRingBean myBean) {
		this.myBean = myBean;
	}

	public void closePort() {

		if(listnerProc!=null)
			listnerProc.closePort();

	}

	public void sendTokenToNeext() {
		if(!jeVeuxToken()){

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String message = String.valueOf(Cts.TOKEN)+"#"+getMyBean().getID();
			getSenderProc().EnvoyerMessage(message);
			setJAiLeToken(false);	
			if(getJaiRessource())
				e.libererRessource(getMyBean().getID());

		}else{
			e = new  EmissionProcRessource();
			e.reserverRessource(getMyBean().getID());
			setJaiRessource(true);
		}

	}

	public ProcTokenRingFrame getInterface() {
		// TODO Auto-generated method stub
		return procTokenRingFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("JeVeuxToken")){
			JeVeuxToken = procTokenRingFrame.getIsTokenChecked();
		}

		if(!JeVeuxToken && getJAiLeToken())
			sendTokenToNeext();

	}

	public Boolean jeVeuxToken(){
		return JeVeuxToken;
	}

	public Boolean getJAiLeToken() {
		return JAiLeToken;
	}

	public void setJAiLeToken(Boolean jAiLeToken1) {
		JAiLeToken = jAiLeToken1;
		procTokenRingFrame.setLabelJAiLeToken(JAiLeToken);
	}

	public Boolean getJaiRessource() {
		return JaiRessource;
	}

	public void setJaiRessource(Boolean jaiRessource) {
		JaiRessource = jaiRessource;
	}

}