package proj1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Write a description  here.
 * 
 * @author Ferguson
 * @version April 7, 2020
 */
public class StopWatchPanel extends JPanel{
	
	private StopWatch watch;
    private Timer javaTimer;
    private String resetPoint;

	JButton startButton, loadButton, addButton, subtractButton,
			newButton, continueButton, stopButton, saveButton;
	JTextField minField, secField, milliField, addField,
			subtractField, newField;

	JLabel lblTime;

	public StopWatchPanel(){ 

		// create the game object as well as the GUI1024 Frame
		watch = new StopWatch();
		javaTimer = new Timer(5, new TimerListener());

		setLayout(new GridLayout(10,2));
		setBackground(Color.lightGray);

		add(new JLabel("Minutes:"));
		minField = new JTextField("0", 3);
		add(minField);

		add(new JLabel("Seconds:"));
		secField = new JTextField("0", 3);
		add(secField);

		add(new JLabel("Miliseconds:"));
		milliField = new JTextField("0", 3);
		add(milliField);

		//Adding buttons and some text fields for add, subtract, and new
		startButton = new JButton("Start");
		add(startButton);

		stopButton = new JButton("Stop");
		add(stopButton);

		loadButton = new JButton("Load");
		add(loadButton);

		saveButton = new JButton("Save");
		add(saveButton);

		addButton = new JButton("Add");
		add(addButton);
		addField = new JTextField("0", 3);
		add(addField);

		subtractButton = new JButton("Subtract");
		add(subtractButton);
		subtractField = new JTextField("0", 3);
		add(subtractField);

		newButton = new JButton("New");
		add(newButton);
		newField = new JTextField("0:0:0", 3);
		add(newField);

		continueButton = new JButton("Continue");
		add(continueButton);

		add (new JLabel(" "));

		add(new JLabel("Time:"));
		lblTime = new JLabel();
		lblTime.setText(watch.toString());
		add(lblTime);

		//Action Listeners for all buttons
		startButton.addActionListener(new ButtonListener());
		stopButton.addActionListener(new ButtonListener());
		loadButton.addActionListener(new ButtonListener());
		saveButton.addActionListener(new ButtonListener());
		addButton.addActionListener(new ButtonListener());
		subtractButton.addActionListener(new ButtonListener());
		newButton.addActionListener(new ButtonListener());
		continueButton.addActionListener(new ButtonListener());


	}

	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent event) {

			int mins, sec, milli, p;
			if (!StopWatch.isSuspended()) {
				if (event.getSource() == startButton) {
					if (!minField.getText().isEmpty()
							&& !secField.getText().isEmpty()
							&& !milliField.getText().isEmpty()) {
						try {
							mins = Integer.parseInt(minField.getText());
							sec = Integer.parseInt(secField.getText());
							milli = Integer.parseInt(milliField.getText());
							watch = new StopWatch(mins, sec, milli);
							javaTimer.start();
						} catch (NumberFormatException io) {
							JOptionPane.showMessageDialog(null,
									"Enter an integer in all fields");
						} catch (IllegalArgumentException e) {
							JOptionPane.showMessageDialog(
									null, "Error in field");
						}
					} else if (minField.getText().isEmpty()
							&& !secField.getText().isEmpty()
							&& !milliField.getText().isEmpty()) {
						try {
							sec = Integer.parseInt(secField.getText());
							milli = Integer.parseInt(milliField.getText());
							watch = new StopWatch(sec, milli);
							javaTimer.start();
						} catch (NumberFormatException io) {
							JOptionPane.showMessageDialog(null,
									"Enter an integer in all fields");
						} catch (IllegalArgumentException e) {
							JOptionPane.showMessageDialog(
									null, "Error in field");
						}
					} else if (minField.getText().isEmpty()
							&& secField.getText().isEmpty()
							&& !milliField.getText().isEmpty()) {
						try {

							milli = Integer.parseInt(milliField.getText());
							watch = new StopWatch(milli);
							javaTimer.start();
						} catch (NumberFormatException io) {
							JOptionPane.showMessageDialog(null,
									"Enter an integer in all fields");
						} catch (IllegalArgumentException e) {
							JOptionPane.showMessageDialog(
									null, "Error in field");
						}
					}
					//consider adding methods to tell the integers in each
					//field for proper constructor (ex. if nothing in mins
					resetPoint = watch.toString();
				}

				if (event.getSource() == stopButton) {
					javaTimer.stop();
				}

				if (event.getSource() == loadButton) {

					// create File Chooser so that it starts at the current directory
					String userDir = System.getProperty("user.dir");
					JFileChooser fc = new JFileChooser(userDir);

					// show File Chooser and wait for user selection
					int returnVal = fc.showOpenDialog(null);

					// did the user select a file?
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						String filename = fc.getSelectedFile().getName();
						watch.load(filename);
					}
				}

				if (event.getSource() == saveButton) {
					try {
						String fileName = JOptionPane.showInputDialog("Name the file");
						watch.save(fileName);
					}catch (IllegalArgumentException e){
						JOptionPane.showMessageDialog(null,"Canceled Save");
					}

				}

				if (event.getSource() == addButton) {
					try {
						if (addField.getText().indexOf(":") == -1) {
							watch.add(Integer.parseInt(addField.getText()));
						} else if (addField.getText().indexOf(":") > -1) {
							watch.add(new StopWatch(addField.getText()));
						}

					} catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(null, "Error");
					}

				}

				if (event.getSource() == subtractButton) {
					try {
						if (subtractField.getText().indexOf(":") == -1) {
							watch.sub(Integer.parseInt(subtractField.getText()));
						} else if (subtractField.getText().indexOf(":") > -1) {
							watch.sub(new StopWatch(subtractField.getText()));
						}

					} catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(null, "Error");
					}

				}

				if (event.getSource() == newButton) {
					try {
						watch = new StopWatch(newField.getText());
					} catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}

				if (event.getSource() == continueButton) {
					try {
						javaTimer.start();
					} catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}

				lblTime.setText(watch.toString());
			}
		}


	}
	
	private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	watch.add(1);
			watch.add(1);
			watch.add(1);
			watch.add(1);
			watch.add(1);
        	lblTime.setText(watch.toString());
        }
	}
}
