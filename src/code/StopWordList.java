package code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class StopWordList {
	public static void main(String[] args){
		BufferedReader br1 = null;
		try {
			br1 = new BufferedReader(new FileReader("stop_word_list.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String stopList="";
		String s1="";
		try{
			while ((s1 = br1.readLine()) != null){
				stopList+= "\"" + s1 +"\"" + " ,"; 
			}
			System.out.println(stopList);
		}catch(Exception e){
			System.out.println("Error");
		}
	}

}
