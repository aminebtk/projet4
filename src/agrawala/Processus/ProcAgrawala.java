package agrawala.Processus;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Util.Cts;
import agrawala.beans.ProcAgrawalaBean;



public class ProcAgrawala implements ActionListener {


	private ProcAgrawalaBean myBean;
	private Boolean JeVeuxToken = false;
	private Boolean JAiLeToken = false;
	private Boolean JaiRessource = false;

	private ProcAgrawalaBean nextProcTokebRingBean;
	//private ProcTokenRingBean previousProcTokebRingBean;
	private ReceptionfromProc listnerProc;
	private EmissionBetweenProc senderProc;
	private ProcAgrawalaFrame procTokenRingFrame;
	private EmissionProcToRessource e;

	public ProcAgrawala(int id, String AdresseIp, int Port){
		setMyBean(new ProcAgrawalaBean(id, AdresseIp, Port));
		procTokenRingFrame = new ProcAgrawalaFrame(getMyBean(), this);
		procTokenRingFrame.update();
		//setNextProcTokebRing(n);
		//setPreviousProcTokebRing(p);
		System.out.println(getMyBean().toString());
	}

	public ProcAgrawala() {
		// TODO Auto-generated constructor stub
	}

	public void startReception() {

		if(listnerProc!=null)
			closePort();
		listnerProc = new ReceptionfromProc(myBean, this);
		listnerProc.start();
	}

	public void startEmission(){
		senderProc = new EmissionBetweenProc(nextProcTokebRingBean);
	}

	public ReceptionfromProc getListnerProc() {
		return listnerProc;
	}

	public EmissionBetweenProc getSenderProc() {
		return senderProc;
	}

	public ProcAgrawalaBean getNextProcTokebRing() {
		return nextProcTokebRingBean;
	}




	public ProcAgrawalaBean getMyBean() {
		return myBean;
	}

	public void setMyBean(ProcAgrawalaBean myBean) {
		this.myBean = myBean;
	}

	public void closePort() {

		if(listnerProc!=null)
			listnerProc.closePort();

	}



	public ProcAgrawalaFrame getInterface() {
		// TODO Auto-generated method stub
		return procTokenRingFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("JeVeuxToken")){
			
			procTokenRingFrame.enablePrint();
	
		}

	}

	public Boolean jeVeuxToken(){
		return JeVeuxToken;
	}

	public Boolean getJAiLeToken() {
		return JAiLeToken;
	}



	public Boolean getJaiRessource() {
		return JaiRessource;
	}

	public void setJaiRessource(Boolean jaiRessource) {
		JaiRessource = jaiRessource;
		

	}

}
