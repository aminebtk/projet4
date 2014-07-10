import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ProcessusTokenRing.ProcTokenRing;



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
			if(listTokenRingProc.isEmpty()){
				ProcTokenRing p = new ProcTokenRing((listTokenRingProc.size()+1), 
						interfaceCreator.getIP(), interfaceCreator.getPort(), null, null);
				
				listTokenRingProc.add(p);

			}else if (listTokenRingProc.size()==1) {

				ProcTokenRing p = new ProcTokenRing((listTokenRingProc.size()+1), 
						interfaceCreator.getIP(), interfaceCreator.getPort(), 
						listTokenRingProc.get(0).getMyBean() , listTokenRingProc.get(0).getMyBean());
				listTokenRingProc.get(0).setNextProcTokebRing(p.getMyBean());
				listTokenRingProc.get(0).createSender();
				listTokenRingProc.add(p);

			}else{


				ProcTokenRing pp = new ProcTokenRing();
				ProcTokenRing pn = new ProcTokenRing();
				pp = listTokenRingProc.get(listTokenRingProc.size()-1);
				pn = listTokenRingProc.get(0);

				ProcTokenRing p = new ProcTokenRing((listTokenRingProc.size()+1), 
						interfaceCreator.getIP(), interfaceCreator.getPort(),
						pn.getMyBean() , pp.getMyBean());

				pp.setNextProcTokebRing(p.getMyBean());
				pn.setPreviousProcTokebRing(p.getMyBean());

				listTokenRingProc.add(p);

			}

		}

	}
}
