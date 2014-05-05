package code;

public class ProcessHashTags {
	public String checkTags(String str){
		String hTag = str;
		String tagList[] = null;
		if(hTag.contains("#")){
			tagList = hTag.split("#");
		}
		//System.out.println("Length:" + tagList.length);
		String finalTags = "";
		for(int i=0; i < tagList.length; i++ ){
			if(!tagList[i].isEmpty()){
				if(tagList[i].contains("'")){
					//System.out.println("***************************"+tagList[i]);
					String[] rmQuotes = tagList[i].split("\\'");
					if(rmQuotes.length > 0){
						if(!rmQuotes[0].isEmpty()){
							finalTags+= "#" + rmQuotes[0].toUpperCase()+" ";
						}
					}
				}else{
					finalTags+= "#" + tagList[i].toUpperCase()+" ";
				}
				//System.out.println(finalTags);
			}
		}
		//System.out.println(finalTags);
		return finalTags;
	}

}
