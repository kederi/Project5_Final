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
	private JTextArea bigBox = new JTextArea();
	
	private JButton stationButt = new JButton("Show Station");
	private JComboBox dropDownStations = new JComboBox();
	
	
	
	private JLabel compare = new JLabel("Compare With ");
	
	//Part 2 Components
	private JButton hd = new JButton("Calculate HD:");
	
	
	//HD Labels
	private JLabel dist0 = new JLabel("Distance 0 ");
	private JLabel dist1 = new JLabel("Distance 1 ");
	private JLabel dist2 = new JLabel("Distance 2 ");
	private JLabel dist3 = new JLabel("Distance 3 ");
	private JLabel dist4 = new JLabel("Distance 4 ");
	
	
	//TextField for corrosponding label
	private JTextField label0 = new JTextField(0 + "");
	private JTextField label1 = new JTextField(0 + "");
	private JTextField label2 = new JTextField(0 + "");
	private JTextField label3 = new JTextField(0 + "");
	private JTextField label4 = new JTextField(0 + "");
	
	
	private JButton addStation = new JButton("Add Station");
	private JTextField addArea = new JTextField("Enter a Station" + "");
	//Class things 
	private static final int NUM_STATIONS = 120;
	private static int hammingDist = 0;
	public ArrayList<String> stations = new ArrayList<String>();
	private String stationSelected;
	
	public HammingFrame() throws IOException
	{
		super("Hamming Distance");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLayout(new GridLayout(1,2));
		readFile("Mesonet.txt");
		
		
		//First Panel
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		
		//Second Panel
		JPanel panel3 = new JPanel (new BorderLayout());
		//Third Panel
		JPanel panel4 = new JPanel (new BorderLayout());
		
		JPanel panel5 = new JPanel (new BorderLayout());
		JPanel panel6 = new JPanel (new BorderLayout());
		JPanel panel7 = new JPanel(new GridLayout(5,2));
		JPanel panel8 = new JPanel(new BorderLayout());
		//Four Panel
		/*JPanel panel4 = new JPanel (new BorderLayout());
		JPanel panel5 = new JPanel(new BorderLayout());
		
		JPanel panel6 = new JPanel(new BorderLayout());
		JPanel panel7 = new JPanel(new BorderLayout());
		JPanel panel8 = new JPanel(new BorderLayout());
		JPanel panel9 = new JPanel(new BorderLayout());
		JPanel panel10 = new JPanel(new FlowLayout());
		*/
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
			bigBox.setText(null);
			ArrayList <String> stat = stationCalc(hammingDist, stationSelected);
			for(int k = 0; k < stat.size(); k++)
			{
				bigBox.append(stat.get(k) + "\n");
			}
			//bigBox.setText(stat.toString());
			
		});
		
		hd.addActionListener((e) ->
		{
			//TODO
		});
		//Big Text Box
		bigBox.setPreferredSize(new Dimension(200, 150));

		
		//Add to the Main panel
		panel1.add(panel3);
		panel1.add(panel4);
		panel1.add(panel5);
		panel1.add(panel6);
		panel1.add(panel7);
		panel1.add(panel8);
		
		 
		
		//Add the Components to panel
		panel3.add(hamming, BorderLayout.WEST);
		panel3.add(num, BorderLayout.EAST);
		panel3.add(hammNum, BorderLayout.SOUTH);

		
		panel4.add(stationButt, BorderLayout.NORTH);
		panel4.add(bigBox, BorderLayout.SOUTH);
		panel5.add(compare, BorderLayout.WEST);
		panel5.add(dropDownStations, BorderLayout.EAST);
		panel6.add(hd, BorderLayout.NORTH);
		
		//Add to grid panel
		panel7.add(dist0);
		panel7.add(label0);
		panel7.add(dist1);
		panel7.add(label1);
		panel7.add(dist2);
		panel7.add(label2);
		panel7.add(dist3);
		panel7.add(label3);
		panel7.add(dist4);
		panel7.add(label4);
		
		panel8.add(addStation, BorderLayout.WEST);
		panel8.add(addArea, BorderLayout.EAST);
		//Add Main panel to the Frame
		this.add(panel1);
		this.add(panel2);

		
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
	/*
	 * 
	 */
	public int [] calcNodeDistance(String word)
	{
		int [] nodes = {0, 0, 0, 0};
		for (int k = 0; k < stations.size(); ++k)
		{
			String words = stations.get(k);
			int hamming = calcHammingDistance(word, words);
			if(hamming == 4)
			{
				if (!words.equals(word))
					nodes[3]++;
			}
			else if (hamming == 3)
			{
				if (!words.equals(word))
					nodes[2]++;
			}
			else if (hamming == 2)
			{
				if(!words.equals(word))
					nodes[1]++;
			}
			else if (hamming == 1)
			{
				if(!words.equals(word))
					nodes[0]++;
			}
		}
		//Test to see if nodes are put in Array
		//System.out.println(nodes);
		return nodes;
		
	}

	//Create A JPanel to add all components 
	
	public static void main (String [] args) throws IOException
	{
		new HammingFrame();
	}
	
}
