package agrawala.main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import agrawala.processus.ProcAgrawala;




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
			ProcAgrawala p = new ProcAgrawala((listAgrawalaProc.size()+1), 
					interfaceCreator.getIP(), interfaceCreator.getPort());
			listAgrawalaProc.add(p);
			interfaceCreator.setPort(Integer.valueOf(interfaceCreator.getPort())+1);
		}
	}
}
