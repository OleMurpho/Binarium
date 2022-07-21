package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class Frame {
	private static char[] asciiArray = {'\s','!','"','#','$','%','&','\'','(',')',
										'*','+',',','-','.','/','0','1','2','3',
										'4','5','6','7','8','9',':',';','<','=',
										'>','?','@','A','B','C','D','E','F','G',
										'H','I','J','K','L','M','N','O','P','Q',
										'R','S','T','U','V','W','X','Y','Z','[',
										'\\',']','^','_','`','a','b','c','d','e',
										'f','g','h','i','j','k','l','m','n','o',
										'p','q','r','s','t','u','v','w','x','y',
										'z','{','|','}','~'};
	private static String[] binaryArray = {"00100000","00100001","00100010","00100011","00100100",
										   "00100101","00100110","00100111","00101000","00101001",
							               "00101010","00101011","00101100","00101101","00101110",
							               "00101111","00110000","00110001","00110010","00110011",
							               "00110100","00110101","00110110","00110111","00111000",
							               "00111001","00111010","00111011","00111100","00111101",
							               "00111110","00111111","01000000","01000001","01000010",
							               "01000011","01000100","01000101","01000110","01000111",
							               "01001000","01001001","01001010","01001011","01001100",
							               "01001101","01001110","01001111","01010000","01010001",
							               "01010010","01010011","01010100","01010101","01010110",
							               "01010111","01011000","01011001","01011010","01011011",
							               "01011100","01011101","01011110","01011111","01100000",
							               "01100001","01100010","01100011","01100100","01100101",
							               "01100110","01100111","01101000","01101001","01101010",
							               "01101011","01101100","01101101","01101110","01101111",
							               "01110000","01110001","01110010","01110011","01110100",
							               "01110101","01110110","01110111","01111000","01111001",
							               "01111010","01111011","01111100","01111101","01111110"};
	private static String[] hexArray = {"20","21","22","23","24","25","26","27","28","29",
										"2A","2B","2C","2D","2E","2F","30","31","32","33",
										"34","35","36","37","38","39","3A","3B","3C","3D",
										"3E","3F","40","41","42","43","44","45","46","47",
										"48","49","4A","4B","4C","4D","4E","4F","50","51",
										"52","53","54","55","56","57","58","59","5A","5B",
										"5C","5D","5E","5F","60","61","62","63","64","65",
										"66","67","68","69","6A","6B","6C","6D","6E","6F",
										"70","71","72","73","74","75","76","77","78","79",
										"7A","7B","7C","7D","7E"};
	private static String[] errorMessages = {"ASCII Error 01: Invalid Character Entry.",
											 "Hexadecimal Error 01: Invalid Character Entry.",
											 "Binary Error 01: Invalid Character Entry.",
											 "No Input!"};
	private static String[] tempArray;
	private static StringBuilder toBinary, toHex, toAscii, noSpaces, tempAppend, tempCheck;
	private static JFrame windowFrame;
	private static Container mainPanel;
	private static GridBagConstraints panelConstraints;
	private static JLabel topLabel, middleLabel, bottomLabel;
	private static JTextArea topInput, middleInput, bottomInput;
	private static JScrollPane topScroll, middleScroll, bottomScroll;
	private static JButton convertInput, resetInput;
	private static JOptionPane errorBox;
	private static boolean top, mid, bot;
	private static int controlConvert;
	private static int[] holdConvert;
	public static void main(String args[]) {
		buildFrame();
	}
	//buildFrame
	
	private static void buildFrame() {
		windowFrame = new JFrame();
		windowFrame.setTitle("Binarium");
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildPanel(windowFrame.getContentPane());
		windowFrame.pack();
		windowFrame.setVisible(true);
	}
	//buildPanel
	private static void buildPanel(Container contentPane) {
		mainPanel = contentPane;
		mainPanel.setLayout(new GridBagLayout());
		panelConstraints = new GridBagConstraints();
		buildContents();
	}
	//buildContents
	private static void buildContents() {	
		topLabel = new JLabel("ASCII", SwingConstants.CENTER);
		topLabel.setPreferredSize(new Dimension(40,50));
		panelConstraints.gridwidth = 1;
		panelConstraints.ipady = 25;
		panelConstraints.ipadx = 50;
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 0;
		mainPanel.add(topLabel, panelConstraints);
		
		middleLabel = new JLabel("Hexadecimal", SwingConstants.CENTER);
		middleLabel.setPreferredSize(new Dimension(40,50));
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 1;
		mainPanel.add(middleLabel, panelConstraints);
		
		bottomLabel = new JLabel("Binary", SwingConstants.CENTER);
		bottomLabel.setPreferredSize(new Dimension(40,50));
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 2;
		mainPanel.add(bottomLabel, panelConstraints);
		
		topInput = new JTextArea();
		topScroll = new JScrollPane(topInput);
		topScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		topScroll.setPreferredSize(new Dimension(600, 50));
		panelConstraints.fill = GridBagConstraints.HORIZONTAL; 
		topInput.setLineWrap(true);
		topInput.setWrapStyleWord(true);
		panelConstraints.gridx = 1;
		panelConstraints.gridy = 0;
		mainPanel.add(topScroll, panelConstraints);
		
		middleInput = new JTextArea();
		middleScroll = new JScrollPane(middleInput);
		middleScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		middleScroll.setPreferredSize(new Dimension(600, 50));
	    panelConstraints.fill = GridBagConstraints.HORIZONTAL;
		middleInput.setLineWrap(true);
		middleInput.setWrapStyleWord(true);
		panelConstraints.gridx = 1;
		panelConstraints.gridy = 1;
		mainPanel.add(middleScroll, panelConstraints);
		
		bottomInput = new JTextArea();
		bottomScroll = new JScrollPane(bottomInput);
		bottomScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		bottomScroll.setPreferredSize(new Dimension(600, 50));
	    panelConstraints.fill = GridBagConstraints.HORIZONTAL;
		bottomInput.setLineWrap(true);
		bottomInput.setWrapStyleWord(true);
		panelConstraints.gridx = 1;
		panelConstraints.gridy = 2;
		mainPanel.add(bottomScroll, panelConstraints);
	     
		convertInput = new JButton("Convert");
		panelConstraints.fill = GridBagConstraints.HORIZONTAL;
		panelConstraints.gridwidth = 1;
		panelConstraints.ipady = 50;
		panelConstraints.gridx = 1;
		panelConstraints.gridy = 3;
		mainPanel.add(convertInput, panelConstraints);
		
		resetInput = new JButton("Reset");
		panelConstraints.fill = GridBagConstraints.HORIZONTAL;
		panelConstraints.gridwidth = 1;
		panelConstraints.ipady = 50;
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 3;
		mainPanel.add(resetInput, panelConstraints);
		
		errorBox = new JOptionPane();
		errorBox.setName("Error");
		errorBox.setVisible(false);
		mainPanel.add(errorBox);
		addListeners();
	}
	//addListeners
	private static void addListeners() {
		convertInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (top == false && mid == false && bot == false) {
					JOptionPane.showMessageDialog(mainPanel, errorMessages[3]);
				}
				else if (top == true) {
					tempCheck = new StringBuilder();
					for (int lengthVar = 0; lengthVar < topInput.getText().length(); lengthVar++) {
						for (int arrayVar = 0; arrayVar < asciiArray.length; arrayVar++) {
							if (topInput.getText().toString().charAt(lengthVar) == (asciiArray[arrayVar])) {
								tempCheck.append(topInput.getText().charAt(lengthVar));
							}
						}
					}
					if (tempCheck.toString().matches(topInput.getText())) {
						convertInput(topInput.getText(), controlConvert);
					}
					else if (tempCheck.toString() != topInput.getText()) {
						JOptionPane.showMessageDialog(mainPanel, errorMessages[0]);
						resetInput.doClick();
					}
				}
				else if (mid == true) {
					removeSpaces(controlConvert, middleInput.getText());
				}
				else if (bot == true) {
					removeSpaces(controlConvert, bottomInput.getText());
				}
			}
		});
		resetInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				topInput.setText("");
				middleInput.setText("");
				bottomInput.setText("");
				topInput.setBackground(Color.white);
				middleInput.setBackground(Color.white);
				bottomInput.setBackground(Color.white);
				topInput.setEditable(true);
				middleInput.setEditable(true);
				bottomInput.setEditable(true);
			}
		});
		topInput.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
			    if (topInput.getText().isBlank() != true) {
			    	bottomInput.setEditable(false);
			    	bottomInput.setBackground(Color.lightGray);
			    	middleInput.setEditable(false);
			    	middleInput.setBackground(Color.lightGray);
			    	controlConvert = 1;
			    	top = true;
			    	mid = false;
			    	bot = false;
			    }
			    else if (topInput.getText().isBlank() == true 
			    		&& middleInput.getText().isBlank() == true 
			    		&& bottomInput.getText().isBlank() == true ) {
					    	bottomInput.setEditable(true);
					    	bottomInput.setBackground(Color.white);
					    	middleInput.setEditable(true);
					    	middleInput.setBackground(Color.white);
					    	controlConvert = 0;
					    	top = false;
					    	mid = false;
					    	bot = false;
			    }
			}
		});
		middleInput.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
			    if (middleInput.getText().isBlank() != true) {
			    	bottomInput.setEditable(false);
			    	bottomInput.setBackground(Color.lightGray);
			    	topInput.setEditable(false);
			    	topInput.setBackground(Color.lightGray);
			    	controlConvert = 2;
			    	top = false;
			    	mid = true;
			    	bot = false;
			    }
			    else if (topInput.getText().isBlank() == true 
			    		&& middleInput.getText().isBlank() == true 
			    		&& bottomInput.getText().isBlank() == true ) { 
			    	        bottomInput.setEditable(true);
					    	bottomInput.setBackground(Color.white);
					    	topInput.setEditable(true);
					    	topInput.setBackground(Color.white);
					    	controlConvert = 0;
					    	top = false;
					    	mid = false;
					    	bot = false;
			    }
			}
		});
		bottomInput.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
			    if (bottomInput.getText().isBlank() != true) {
			    	topInput.setEditable(false);
			    	topInput.setBackground(Color.lightGray);
			    	middleInput.setEditable(false);
			    	middleInput.setBackground(Color.lightGray);
			    	controlConvert = 3;
			    	top = false;
			    	mid = false;
			    	bot = true;
			    }
			    else if (topInput.getText().isBlank() == true 
			    		&& middleInput.getText().isBlank() == true 
			    		&& bottomInput.getText().isBlank() == true ) {
					    	topInput.setEditable(true);
					    	topInput.setBackground(Color.white);
					    	middleInput.setEditable(true);
					    	middleInput.setBackground(Color.white);
					    	controlConvert = 0;
					    	top = false;
					    	mid = false;
					    	bot = false;
			    }
			}
		});
	}
	//removeSpaces
	private static void removeSpaces(int control, String input) {
		if (control == 2) {
			noSpaces = new StringBuilder();
			for (int lengthVar = 0; lengthVar < input.length(); lengthVar++) {
				if (input.charAt(lengthVar) == ' ') {
					lengthVar++;
				}
				noSpaces.append(input.charAt(lengthVar));
			}
			
			if (noSpaces.length() % 2 != 0) {
				JOptionPane.showMessageDialog(mainPanel, errorMessages[1]);
				resetInput.doClick();
			}
			else {
				toArray(noSpaces, controlConvert);
			}
		}
		else if (control == 3) {
			noSpaces = new StringBuilder();
			for (int lengthVar = 0; lengthVar < input.length(); lengthVar++) {
				if (input.charAt(lengthVar) == ' ') {
					lengthVar++;
				}
				noSpaces.append(input.charAt(lengthVar));
			}
			for (int lengthVar = 0; lengthVar < noSpaces.length(); lengthVar++) {
				if (noSpaces.toString().charAt(lengthVar) != '1' && noSpaces.toString().charAt(lengthVar) != '0') {
					JOptionPane.showMessageDialog(mainPanel, errorMessages[2]);
					resetInput.doClick();
					break;
				}
			}
			if (noSpaces.length() % 8 != 0) {
				JOptionPane.showMessageDialog(mainPanel, errorMessages[2]);
				resetInput.doClick();
			}
			else {
				toArray(noSpaces, controlConvert);
			}
		}
	}
	//toArray
	private static void toArray(StringBuilder noSpaces, int control) {
		if (control == 2) {
			tempArray = new String[(noSpaces.length()+1)/2];
			tempAppend = new StringBuilder();
			int loopVar = 0;
			while (loopVar < tempArray.length) {
				for (int lengthVar = 0; lengthVar < 2; lengthVar++) {
					tempAppend.append(noSpaces.charAt(lengthVar));
				}
				tempArray[loopVar] = tempAppend.toString().toUpperCase();
				noSpaces.delete(0, 2);
				tempAppend.delete(0, 2);
				loopVar++;
			}
			convertInput(tempArray, control);
		}
		else if (control == 3) {
			tempArray = new String[(noSpaces.length()+1)/8];
			tempAppend = new StringBuilder();
			int loopVar = 0;
			while (loopVar < tempArray.length) {
				for (int lengthVar = 0; lengthVar < 8; lengthVar++) {
					tempAppend.append(noSpaces.charAt(lengthVar));
				}
				tempArray[loopVar] = tempAppend.toString();
				noSpaces.delete(0, 8);
				tempAppend.delete(0, 8);
				loopVar++;
			}
			convertInput(tempArray, control);
		}
	}
	//convertInput (From ASCII)
	private static void convertInput(String input, int control) {
		if (control == 1) {
			holdConvert = new int[input.length()];
			for (int lengthVar = 0; lengthVar < input.length(); lengthVar++) {
				for (int arrayVar = 0; arrayVar < asciiArray.length; arrayVar++) {
					if (input.charAt(lengthVar) == asciiArray[arrayVar]) {
						holdConvert[lengthVar] = arrayVar;
					}
					else if (input.charAt(lengthVar) != asciiArray[arrayVar]) {
						
					}
				}
			}
			toHex = new StringBuilder();
			toBinary = new StringBuilder();
			for (int lengthVar = 0; lengthVar < holdConvert.length; lengthVar++) {
				toHex.append(hexArray[holdConvert[lengthVar]]+" ");
			}
			middleInput.setText(toHex.toString());
			for (int lengthVar = 0; lengthVar < holdConvert.length; lengthVar++) {
				toBinary.append(binaryArray[holdConvert[lengthVar]]+" ");
			}
			bottomInput.setText(toBinary.toString());
		}
	}
	//convertInput (From Hex & Binary)
	private static void convertInput(String[] tempArray, int control) {
		if (control == 2) {
			holdConvert = new int[tempArray.length];
			for (int lengthVar =0; lengthVar < tempArray.length; lengthVar++) {
				for (int arrayVar = 0; arrayVar < hexArray.length; arrayVar++) {
					if (tempArray[lengthVar].matches(hexArray[arrayVar]) ) {
						holdConvert[lengthVar] = arrayVar;
					}
				}
			}
			toAscii = new StringBuilder();
			toBinary = new StringBuilder();
			for (int lengthVar = 0; lengthVar < holdConvert.length; lengthVar++) {
				toAscii.append(asciiArray[holdConvert[lengthVar]]);
			}
			topInput.setText(toAscii.toString());
			for (int lengthVar = 0; lengthVar < holdConvert.length; lengthVar++) {
				toBinary.append(binaryArray[holdConvert[lengthVar]]+" ");
			}
			bottomInput.setText(toBinary.toString());
		}
		else if (control == 3) {
			holdConvert = new int[tempArray.length];
			for (int lengthVar =0; lengthVar < tempArray.length; lengthVar++) {
				for (int arrayVar = 0; arrayVar < binaryArray.length; arrayVar++) {
					if (tempArray[lengthVar].matches(binaryArray[arrayVar]) ) {
						holdConvert[lengthVar] = arrayVar;
					}
				}
			}
			toHex = new StringBuilder();
			toAscii = new StringBuilder();
			for (int lengthVar = 0; lengthVar < holdConvert.length; lengthVar++) {
				toAscii.append(asciiArray[holdConvert[lengthVar]]);
			}
			topInput.setText(toAscii.toString());
			for (int lengthVar = 0; lengthVar < holdConvert.length; lengthVar++) {
				toHex.append(hexArray[holdConvert[lengthVar]]+" ");
			}
			middleInput.setText(toHex.toString());
		}
	}
}