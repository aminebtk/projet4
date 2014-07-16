package agrawala.main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import agrawala.Processus.ProcAgrawala;




public class AgrawalaProcManager implements ActionListener {

	private AgrawalaProcCreator interfaceCreator;
	private ArrayList<ProcAgrawala> listAgrawalaProc;


	public AgrawalaProcManager(){
		interfaceCreator = new AgrawalaProcCreator(this);
		listAgrawalaProc = new ArrayList<ProcAgrawala>();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getActionCommand().equals("ajouterProc")){
			interfaceCreator.getBtnLancerToken().setEnabled(false);
			if(listAgrawalaProc.isEmpty()){
				
				ProcAgrawala p = new ProcAgrawala((listAgrawalaProc.size()+1), 
						interfaceCreator.getIP(), interfaceCreator.getPort());

				listAgrawalaProc.add(p);

			}else if (listAgrawalaProc.size()==1) {
				interfaceCreator.getBtnLancerToken().setEnabled(true);
				ProcAgrawala p = new ProcAgrawala((listAgrawalaProc.size()+1), 
						interfaceCreator.getIP(), interfaceCreator.getPort());
				
//				pp.setNextProcTokebRing(p.getMyBean());
//			//	pp.setPreviousProcTokebRing(p.getMyBean());
//				p.startReception();
//				pp.startReception();
//				p.startEmission();
//				pp.startEmission();
				listAgrawalaProc.add(p);

			}else{
				interfaceCreator.getBtnLancerToken().setEnabled(true);


				ProcAgrawala p = new ProcAgrawala((listAgrawalaProc.size()+1), 
						interfaceCreator.getIP(), interfaceCreator.getPort());

//				pp.setNextProcTokebRing(p.getMyBean());
//				p.setNextProcTokebRing(pn.getMyBean());
//				pn.startReception();
//				p.startReception();
//				pp.startReception();
//				pp.startEmission();
//				p.startEmission();
				listAgrawalaProc.add(p);

			}
			interfaceCreator.setPort(Integer.valueOf(interfaceCreator.getPort())+1);
		}else if(arg0.getActionCommand().equals("lancerToken")){
			//if(listAgrawalaProc.size()>1)
				//listAgrawalaProc.get(0).sendTokenToNeext();
		}
		
		

	}
}
