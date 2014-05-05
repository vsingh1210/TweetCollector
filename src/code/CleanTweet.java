package code;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class CleanTweet {
	public void createObj(String filePath){
		SerClass tempCl = new SerClass();
		BufferedReader br1 = null;
		try {
			br1 = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s1 = null;
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		try{
			      OutputStream file = new FileOutputStream("day11.ser");
			      OutputStream buffer = new BufferedOutputStream(file);
			      ObjectOutput output = new ObjectOutputStream(buffer);
		try {
			String[] str = new String[5];
			SerClass ct = new SerClass();
			while ((s1 = br1.readLine()) != null){
				if(s1.isEmpty()) continue;
				if(flag3 || !s1.contains("data~:")){
					flag3 = false;
					if(flag1) 
					{
						if(s1.contains("data~:")){
							String[] temp = s1.split("data~:");
							ct.text += " " + temp[1];
						}else{
							ct.text += " " + s1;
						}
					}
				}else
				{	
					tempCl = ct;
					if(flag2){
						ct.processText();
						ct.findRT();
						try{
							output.writeObject(tempCl);
							ct = new SerClass();
						}catch(Exception e){e.printStackTrace();}						
					}
					flag1 = false;
					flag2 = false;
					Arrays.fill(str, "");
					str = s1.split("data~:");
					if(s1.split("data~:").length < 5){
						flag3 = true;
					}
					try{
						//System.out.println(s1);
						ct.userId=str[1];
						ct.other=str[2];
						ct.date=" ";
						try{
							ct.location=str[3];
						}catch(Exception e){ct.location = "";}
						try{
							ct.text = str[4];
						}catch(Exception e){
							ct.text = "";
						}
						flag1 = true;
						flag2 = true;
					}catch(Exception e){
						e.printStackTrace();System.exit(0);
						System.out.println("1");
					}			
				}
				tempCl = ct;
			}
			tempCl.processText();
			tempCl.findRT();
			output.writeObject(tempCl);
			output.close();
		}catch(Exception e) {e.printStackTrace();System.exit(0);}
	}catch(Exception e){e.printStackTrace();System.exit(0);}
	System.out.println("---Task Complete---");
}
}
