package code;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadTweetObj {
	@SuppressWarnings("resource")
	public static void main(String[] args){
		ArrayList<SerClass> ctList = new ArrayList<SerClass>();
		CleanTweet ct = new CleanTweet();
		int count = 0;
		String tt = "temp";
		ct.createObj(tt+".txt");
		try{
			File fileO = new File("cleandata/"+tt+".txt");
			FileWriter fw = null;
			fw = new FileWriter(fileO.getAbsoluteFile());
			//final PrintWriter bw = new PrintWriter(fw);
			InputStream file = new FileInputStream("day11.ser");
		    InputStream buffer = new BufferedInputStream(file);
		    ObjectInput input = new ObjectInputStream (buffer);
		    SerClass obj = null;
		    try{
		    	obj = (SerClass) input.readObject();
		    }catch(EOFException e){
		    	System.exit(0);
		    }
		    String followers="0";
		    String friends="0";
		    while(obj != null){
		    	ctList.add((SerClass) obj);
		    	try{
//		    		System.out.println(obj.userId + "Other: "+ obj.other+"Tags: " + obj.hashTags.toString() + "RT: " + obj.userIdRT);
		    		String str2[] = obj.other.split("\\|");
		    		//System.out.println(str2.length);
		    		if(str2.length == 2){
		    			followers = "foll:"+str2[0];
		    			friends = "frnd:" + str2[1];
		    		}else{
		    			followers = "foll:"+"0";
		    			friends="frnd:" +"0";
		    		}
		    		String strTemp = "@" + obj.userId + " " + followers + " " + friends + " ";
		    		String strTemp3 = "";
		    		String[] tempStr = obj.sentence.split(" ");
		    		for(int i = 0; i < tempStr.length; i++){
		    			strTemp3 += tempStr[i] + " ";
		    		}
		    		if(obj.userIdRT.isEmpty()){
		    			strTemp += strTemp3;
		    		}else{
		    			strTemp += strTemp3 + "RT" + obj.userIdRT;
		    		}
		    		fw.write(strTemp + "\n");
		    		count++;
		    		System.out.println(obj.userId);
		    		obj = (SerClass) input.readObject();
		    		//System.out.println(obj.userId + "Tags: " + obj.hashTags.toString() + "RT: " + obj.userIdRT);
		    		
		    	}catch(EOFException e){
		    		System.exit(0);
		    	}
		    }
		    File file9 = new File("day11.ser");
		    file9.delete();
		    fw.close();
		}
		catch(IOException ex){
			System.out.println("IO Exception");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    System.out.println("Total Tweets: " + count);
		System.out.println("--Task Complete--");
	}
}

