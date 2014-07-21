package agrawala.processus;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import Util.Cts;
import agrawala.beans.ProcAgrawalaBean;
import agrawala.beans.RequestRessource;

public class ProcAgrawala implements ActionListener {

	private ProcAgrawalaBean myBean;
	private RequestRessource requestRessource;
	private String statut = "";
	private Boolean JAiLeToken = false;
	private Boolean JaiRessource = false;
	private ArrayList<ProcAgrawalaBean> listProc;
	public ArrayList<ProcAgrawalaBean> getListProc() {
		return listProc;
	}

	private ProcAgrawalaFrame procTokenRingFrame;
	private ConnexionMediator connexionMediator;

	public ProcAgrawala(int id, String AdresseIp, int Port){
		setMyBean(new ProcAgrawalaBean(id, AdresseIp, Port));
		listProc = new ArrayList<ProcAgrawalaBean>();
		procTokenRingFrame = new ProcAgrawalaFrame(getMyBean(), this);
		connexionMediator = new ConnexionMediator(Cts.INTERMEDIAIRE_IP, Cts.INTERMEDIAIRE_PORT , getMyBean(), this);
		procTokenRingFrame.update();
	}

	public ProcAgrawala() {
		// TODO Auto-generated constructor stub
	}

	public ProcAgrawalaBean getMyBean() {
		return myBean;
	}

	public void setMyBean(ProcAgrawalaBean myBean) {
		this.myBean = myBean;
	}

	public ProcAgrawalaFrame getInterface() {
		// TODO Auto-generated method stub
		return procTokenRingFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("demander")){
			connexionMediator.envoyerDemandeRes();	
		}
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

	public void addProc(ProcAgrawalaBean p) {
		System.out.println("i m " + getMyBean().getID() + " adding " +  p.getID());
		Boolean found = false;
		for(ProcAgrawalaBean p1 : listProc){
			if(p1.getID()==p.getID()){
				found = true;
				break;
			}
		}

		if(!found){
			listProc.add(p);
			procTokenRingFrame.updateListProc(listProc);
		}
	}

	public String getStatut() {
		return statut;
	}
	public void setStatut(String st) {
		statut = st;
	}

	public RequestRessource getRequest() {
		return requestRessource;
	}

	public RequestRessource createRequest() {
		Calendar lCDateTime = Calendar.getInstance();
		requestRessource =  new RequestRessource(getMyBean().getID(), lCDateTime.getTimeInMillis());
		return requestRessource;
	}
	
}
