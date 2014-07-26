package agrawala.processus;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import sun.awt.WindowClosingListener;
import Util.Cts;
import agrawala.beans.ProcAgrawalaBean;
import agrawala.beans.RequestRessource;

public class ProcAgrawala implements ActionListener, WindowListener {

	private ProcAgrawalaBean myBean;
	private RequestRessource requestRessource;
	private ArrayList<RequestRessource> listRequest;
	private String statut = "";
	private Boolean JAiLeToken = false;
	private Boolean JaiRessource = false;
	private ArrayList<ProcAgrawalaBean> listProc;
	public ArrayList<ProcAgrawalaBean> getListProc() {
		return listProc;
	}

	private ProcAgrawalaFrame procTokenRingFrame;
	private ConnexionMediator connexionMediator;
	private Timestamp timeCounter;

	public ProcAgrawala(int id, String AdresseIp, int Port){
		requestRessource = new RequestRessource(id,Long.MAX_VALUE);
		setMyBean(new ProcAgrawalaBean(id, AdresseIp, Port));
		listProc = new ArrayList<ProcAgrawalaBean>();
		listRequest = new ArrayList<RequestRessource>();
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
			Date date= new Date();
			timeCounter = new Timestamp(date.getTime());
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
			updateListProc();
		}
	}

	public void updateListProc(){
		procTokenRingFrame.updateListProc(listProc);
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String st) {
		statut = st;
		procTokenRingFrame.setStatut(st);
	}

	public RequestRessource getRequest() {
		return requestRessource;
	}

	public RequestRessource createRequest() {
		Calendar lCDateTime = Calendar.getInstance();
		requestRessource =  new RequestRessource(getMyBean().getID(), lCDateTime.getTimeInMillis());
		return requestRessource;
	}

	public void addRequest(RequestRessource r) {
		listRequest.add(r);
	}

	public void replayToAllProc() {
		requestRessource = new RequestRessource(getMyBean().getID(),Long.MAX_VALUE);
		
		for(RequestRessource r : listRequest){
			String message  = Cts.REPLY + "#" +r.getID_to() +
					"#" +r.getID_from() + "#" +r.gettime();
			connexionMediator.EnvoyerMessage(message);
		}
		
		listRequest.clear();
		setStatut(Cts.AGRA_RELEASED);
	}


	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("windowActivated");
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		String message  = Cts.REMOVE_PROC + "#" +((ProcAgrawalaFrame) e.getSource()).getIdProc();
		connexionMediator.EnvoyerMessage(message);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void setDelailAttente() {
		procTokenRingFrame.setDelaiAttente(timeCounter);
		
	}
	
}
