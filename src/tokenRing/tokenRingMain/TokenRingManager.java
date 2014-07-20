package tokenRing.tokenRingMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import tokenRing.tokenRingprocessus.ProcTokenRing;



public class TokenRingManager implements ActionListener {

	private TokenRingProcCreator interfaceCreator;
	private ArrayList<ProcTokenRing> listTokenRingProc;


	public TokenRingManager(){
		interfaceCreator = new TokenRingProcCreator(this);
		listTokenRingProc = new ArrayList<ProcTokenRing>();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getActionCommand().equals("ajouterProc")){
			interfaceCreator.getBtnLancerToken().setEnabled(false);
			if(listTokenRingProc.isEmpty()){
				
				ProcTokenRing p = new ProcTokenRing((listTokenRingProc.size()+1), 
						interfaceCreator.getIP(), interfaceCreator.getPort(), null, null);
				listTokenRingProc.add(p);

			}else if (listTokenRingProc.size()==1) {
				interfaceCreator.getBtnLancerToken().setEnabled(true);
				ProcTokenRing pp = new ProcTokenRing();
				pp =listTokenRingProc.get(0);

				ProcTokenRing p = new ProcTokenRing((listTokenRingProc.size()+1), 
						interfaceCreator.getIP(), interfaceCreator.getPort(), 
						pp.getMyBean() , pp.getMyBean());
				
				pp.setNextProcTokebRing(p.getMyBean());
			//	pp.setPreviousProcTokebRing(p.getMyBean());
				p.startReception();
				pp.startReception();
				p.startEmission();
				pp.startEmission();
				listTokenRingProc.add(p);

			}else{
				interfaceCreator.getBtnLancerToken().setEnabled(true);
				ProcTokenRing pp = new ProcTokenRing();
				ProcTokenRing pn = new ProcTokenRing();
				pp = listTokenRingProc.get(listTokenRingProc.size()-1);
				pn = listTokenRingProc.get(0);

				ProcTokenRing p = new ProcTokenRing((listTokenRingProc.size()+1), 
						interfaceCreator.getIP(), interfaceCreator.getPort(),
						pn.getMyBean() , pp.getMyBean());

				pp.setNextProcTokebRing(p.getMyBean());
				p.setNextProcTokebRing(pn.getMyBean());
				pn.startReception();
				p.startReception();
				pp.startReception();
				pp.startEmission();
				p.startEmission();
				listTokenRingProc.add(p);

			}
			interfaceCreator.setPort(Integer.valueOf(interfaceCreator.getPort())+1);
		}else if(arg0.getActionCommand().equals("lancerToken")){
			if(listTokenRingProc.size()>1)
				listTokenRingProc.get(0).sendTokenToNeext();
			}
		
		

	}
}
