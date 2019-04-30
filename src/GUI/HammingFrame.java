package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.Collections;

import javafx.scene.shape.Box;

public class HammingFrame extends JFrame
{
	//Private class constants 
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 700;
	

	//Add the Components
	private JLabel hamming = new JLabel("Enter Hamming Dist:");
	private JSlider hammNum = new JSlider(1, 4);
	private JTextField num = new JTextField(2 + "");
	
	private JTextField bigBox = new JTextField();
	private JButton stationButt = new JButton("Show Station");
	
	private JComboBox dropDownStations = new JComboBox();
	
	private static final int NUM_STATIONS = 120;
	public ArrayList<String> stations = new ArrayList<String>();
	
	public HammingFrame() throws IOException
	{
		super("Hamming Distance");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLayout(new GridLayout(1,1));
		readFile("Mesonet.txt");
		
		
		//First Panel
		JPanel panel1 = new JPanel(new BorderLayout());
		
		//Second Panel
		JPanel panel2 = new JPanel (new BorderLayout());
		//Third Panel
		JPanel panel3 = new JPanel (new BorderLayout());
		//Four Panel
		JPanel panel4 = new JPanel (new BorderLayout());
		JPanel panel5 = new JPanel(new BorderLayout());
		
		JPanel panel6 = new JPanel(new BorderLayout());
		JPanel panel7 = new JPanel(new BorderLayout());
		//The Slider 
		hammNum.setSize(200, 50);
		hammNum.setMajorTickSpacing(1);
		hammNum.setPaintTicks(true);
		hammNum.setSnapToTicks(true);
		hammNum.setPaintLabels(true);
		
		hammNum.addChangeListener((e) ->
		{
			int val = 0;
			val = hammNum.getValue();
			num.setText(val + "");
		});
		
		
		//Action Listener for button
		stationButt.addActionListener((e) ->
		{
			//TODO
			//Display the Text box 
		});
		//Big Text Box
		bigBox.setPreferredSize(new Dimension(100, 40));
		
		//Call method to get stations that have the hamming distance
		//TODO
		Collections.sort(stations);
		
		//Saying its going Out of Bounds 
		for(int k = 0; k < stations.size(); k++)
		{
			dropDownStations.addItem(stations.get(k));
		}
		
		//Embedded Panels
		panel1.add(panel2, BorderLayout.WEST);
		panel1.add(panel3, BorderLayout.EAST);
		
		panel2.add(panel4, BorderLayout.CENTER);
		panel4.add(panel5, BorderLayout.CENTER);
		panel5.add(panel6, BorderLayout.CENTER);
		panel6.add(panel7, BorderLayout.EAST);
		panel2.add(hamming, BorderLayout.NORTH);
		panel4.add(num, BorderLayout.NORTH);
		panel5.add(hammNum, BorderLayout.NORTH);
		panel6.add(bigBox, BorderLayout.NORTH);
		panel7.add(dropDownStations, BorderLayout.NORTH);
		
		//Add to the Frame
		this.add(panel1);
		
		//Needed don't touch
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
       
	}

	public void readFile(String fileName) throws IOException 
	{
		//Create BufferedReader to read the file 
		BufferedReader man = new BufferedReader (new FileReader(fileName));
		
		//Reads the file
		for(int row = 0; row < NUM_STATIONS; row++)
		{
			stations.add(man.readLine());
			
		}
		
		//Close the buffered reader
		man.close();
	}
	
	/*
	 * Method to return an ArrayList of Strings given the HammingDistance
	 */
	public ArrayList<String> stationCalc(int hamm)
	{
		
	}

	//Create A JPanel to add all components 
	
	public static void main (String [] args) throws IOException
	{
		new HammingFrame();
	}
	
}
