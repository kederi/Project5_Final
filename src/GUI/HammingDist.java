package GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * The HammingDist class calculates the hammingdistance between two strings and also their total
 * node count out of the total number of stations in a Mesonet file
 * 
 * @author Kobi Ederi
 * @version 2019-02-14
 * 
 */
public class HammingDist 
{
	private String firstWord;
	private String secondWord;
	private ArrayList<String> stations;
	
	private int [] word1; 
	private int [] word2;
	private static final int NUM_STATIONS = 120;
	
	/*The HammingDist constructor initializes two words, creates an ArrayList of stations
	 * and reads the file while calculating the nodes for the two words
	 * @param firstWord the first station ID
	 * @param secondWord the second station ID
	 */
	public HammingDist(String firstWord, String secondWord) throws IOException
	{
		this.firstWord = firstWord;
		this.secondWord = secondWord;
		stations = new ArrayList<String>();
		readFile("Mesonet.txt");
		word1 = calcNodeDistance(firstWord);
		word2 = calcNodeDistance(secondWord);
	}
	
	/*readFile reads in a file and skips the first three lines and also pushes the first word of each line
	 * into the global ArrayList 
	 * @param fileName the name of the file to be read
	 */
	public void readFile(String fileName) throws IOException 
	{
		//Create BufferedReader to read the file 
		BufferedReader man = new BufferedReader (new FileReader(fileName));
		
		//Skips the first three lines of the file
		man.readLine();
		man.readLine();
		man.readLine();
		
		//Reads through the file splits each line, and adds first element into an ArrayList
		for(int row = 0; row < NUM_STATIONS; row++)
		{
			String[] line = man.readLine().split(" ");
			stations.add(line[1]);
			
		}
		
		//Test output of ArrayList
		//System.out.println(stations);
		
		//Close the buffered reader
		man.close();
	}
	
	/*this method calculates the hamming distance between two given strings 
	 * @param word1 the first station ID
	 * @param word2 the second station ID
	 * @return an int with the Hamming distance
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
	/*This method calculates and returns the nodes of a word in an Array of ints
	 * @param the station ID to read
	 * @return the int Array with the nodes
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
	/*toString method
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return the string
	 */
	@Override 
	public String toString()
	{
		//ArrayList <Integer> word1 = calcNodeDistance(firstWord);
		//ArrayList <Integer> word2 = calcNodeDistance(secondWord);
		int hammingDist = calcHammingDistance(firstWord, secondWord);
		String result = "The Hamming Distance of " + firstWord + " and " + secondWord + 
				": " + hammingDist + ".\n";
		result += "Out of 119, for " + firstWord + ", number of nodes are: " + word1[0] + ", " + word1[1] +
				", " + word1[2] + ", " + word1[3] + " and\n";
		result += "for " + secondWord + ", number of nodes are: " + word2[0] + ", " + word2[1] + ", " +
				word2[2] + ", " + word2[3] + " respectively.";
		
	
		return result;
		
	}
	
	
}