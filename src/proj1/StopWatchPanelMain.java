package proj1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StopWatchPanelMain extends JPanel {
	private JMenuItem quitItem;
	private JCheckBox suspendCheckBox;
	

	public StopWatchPanelMain (JMenuItem quitItem) {
		this.quitItem = quitItem;

		JPanel panel = new JPanel();
		panel.add(new StopWatchPanel());
		panel.add(new StopWatchPanel());
		panel.add(new StopWatchPanel());
		//TO DO:  add two more StopWatchPanel to the panel

           suspendCheckBox = new JCheckBox("Suspend Timers");
           suspendCheckBox.setSelected(false);
           panel.add(suspendCheckBox);

           add(panel);


		quitItem.addActionListener(new Mylistener());
		suspendCheckBox.addActionListener(new Mylistener());
	}

	private class Mylistener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == quitItem)
				System.exit(1);

			if(e.getSource() == suspendCheckBox) {
				if (suspendCheckBox.isSelected()) {
					suspendCheckBox.setSelected(true);
						StopWatch.setSuspend(true);
					}
				 else {
					suspendCheckBox.setSelected(false);
					StopWatch.setSuspend(false);
				}
				}
			}
		}
	}


