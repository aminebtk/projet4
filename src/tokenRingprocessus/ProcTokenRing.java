package tokenRingprocessus;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import tokenRingBeans.ProcTokenRingBean;
import Util.Cts;



public class ProcTokenRing implements ActionListener {


	private ProcTokenRingBean myBean;
	private Boolean JeVeuxToken = false;
	private Boolean JAiLeToken = false;
	private Boolean JaiRessource = false;
	private Timestamp timeCounter;
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
		listnerProc = new ReceptionTokenRing(getMyBean(), this);
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
		if(jeVeuxToken()){
			setJAiLeToken(true);	
			e = new  EmissionProcRessource();
			e.reserverRessource(getMyBean().getID());
			setJaiRessource(true);
			procTokenRingFrame.setDelaiAttente(timeCounter);
			simulerUtilisation();
			setJAiLeToken(false);	
			if(getJaiRessource())
				e.libererRessource(getMyBean().getID());
			procTokenRingFrame.libererToker();


		}else{
			setJAiLeToken(true);	
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setJAiLeToken(false);	
		}

		String message = String.valueOf(Cts.TOKEN)+"#"+getMyBean().getID();
		getSenderProc().EnvoyerMessage(message);		
	}

	private void simulerUtilisation() {
		Random randomNum = new Random();
		int min1 = 4; 
		int max1 =8;
		int attente = randomNum.nextInt((max1 - min1) + 1) + min1;

		try {
			Thread.sleep(attente*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ProcTokenRingFrame getInterface() {
		// TODO Auto-generated method stub
		return procTokenRingFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("JeVeuxToken")){
			if(procTokenRingFrame.getIsTokenChecked()){
				Date date= new Date();
				timeCounter = new Timestamp(date.getTime());
			}else{
				timeCounter =null;
			}
		}
	}

	public Boolean jeVeuxToken(){
		return procTokenRingFrame.getIsTokenChecked();
	}

	public Boolean getJAiLeToken() {
		return JAiLeToken;
	}

	public void setJAiLeToken(Boolean jAiLeToken1) {
		JAiLeToken = jAiLeToken1;
		procTokenRingFrame.setLabelJAiLeToken(jAiLeToken1);
	}

	public Boolean getJaiRessource() {
		return JaiRessource;
	}

	public void setJaiRessource(Boolean jaiRessource) {
		JaiRessource = jaiRessource;
	}

}
