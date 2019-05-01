package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	private JLabel compare = new JLabel("Compare With:");
	
	//Class things 
	private static final int NUM_STATIONS = 120;
	private static int hammingDist = 0;
	public ArrayList<String> stations = new ArrayList<String>();
	private String stationSelected;
	
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
		JPanel panel8 = new JPanel(new BorderLayout());
		JPanel panel9 = new JPanel(new FlowLayout());
		//The Slider 
		hammNum.setSize(200, 50);
		hammNum.setMajorTickSpacing(1);
		hammNum.setPaintTicks(true);
		hammNum.setSnapToTicks(true);
		hammNum.setPaintLabels(true);
		
		hammNum.addChangeListener((e) ->
		{
			
			hammingDist = hammNum.getValue();
			num.setText(hammingDist + "");
		});
		
		
		//Call method to get stations that have the hamming distance
		//TODO
		Collections.sort(stations);
		
		//Saying its going Out of Bounds 
		for(int k = 0; k < stations.size(); k++)
		{
			dropDownStations.addItem(stations.get(k));
		}
		dropDownStations.addActionListener((e) ->
		{
			//TODO
			stationSelected = (String)dropDownStations.getSelectedItem();
		});
		//Action Listener for button
		stationButt.addActionListener((e) ->
		{
			//TODO
			//Display the Text box (Call the method to get ArrayList
			//GIVING A NULL POINTER
			ArrayList <String> stat = stationCalc(hammingDist, stationSelected);
			bigBox.setText(stat.toString());
			
		});
		//Big Text Box
		bigBox.setPreferredSize(new Dimension(100, 40));
		

		
		//Two Main panels
		panel1.add(panel2, BorderLayout.WEST);
		panel1.add(panel3, BorderLayout.EAST);
		
		//Sub Panels of Panel2
		panel2.add(panel4, BorderLayout.CENTER);
		panel4.add(panel5, BorderLayout.CENTER);
		panel5.add(panel6, BorderLayout.CENTER);
		panel6.add(panel7, BorderLayout.EAST);
		panel6.add(panel8, BorderLayout.CENTER);
		panel8.add(panel9, BorderLayout.CENTER);
		
		//Add the Components to panel
		panel2.add(hamming, BorderLayout.NORTH);
		panel4.add(num, BorderLayout.NORTH);
		panel5.add(hammNum, BorderLayout.NORTH);
		panel6.add(bigBox, BorderLayout.NORTH);
		panel9.add(compare);
		panel9.add(dropDownStations);
		panel8.add(stationButt, BorderLayout.NORTH);
		//Add Main panel to the Frame
		this.add(panel1);
		
		//Needed don't touch
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
       
	}
	/*
	 * Method to read from the file
	 */
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
	public ArrayList<String> stationCalc(int hamm, String station)
	{
		ArrayList <String> stationsHamm = new ArrayList<String>();
		for(int i = 0; i < stations.size(); i++)
		{
			int dist = calcHammingDistance(station, stations.get(i));
			if(dist == hamm)
			{
				stationsHamm.add(stations.get(i));
			}
		}
		return stationsHamm;
		
	}
	/*
	 * Helper method to calculate the hamming distance
	 */
	public int calcHammingDistance(String word1, String word2)
	{
		int hammingDistance = 0;
		for(int k = 0; k < 4; k++)
		{
			char firstChar = word1.charAt(k);
			char secondChar = word2.charAt(k);
			if (!(firstChar == secondChar))
			{
				hammingDistance++;
			}
		}
		
		//Test to see if Hamming dist is outputted
		//System.out.print(hammingDistance);
		return hammingDistance;
	}

	//Create A JPanel to add all components 
	
	public static void main (String [] args) throws IOException
	{
		new HammingFrame();
	}
	
}
