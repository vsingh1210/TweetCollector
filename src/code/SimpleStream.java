package code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class SimpleStream {
	static int count = 1;
	
	public static void main(String[] args) {
		Date date = new Date();
    	File file = new File("temp.txt");
    	
    	
    	FileWriter fw = null;
    	try {
			fw = new FileWriter(file.getAbsoluteFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final PrintWriter bw = new PrintWriter(fw);
		String strdate = date.toString() + "\n";
		bw.write(strdate);
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("lueXa1fwhkRKnQUmnS3BqA");
        cb.setOAuthConsumerSecret("lh9L4nuraZosK0VgsC9fAIJvdVRfUC5OiwbMnabkMk");
        cb.setOAuthAccessToken("1882349227-uhlOky6DZqFDrLKxXjyCftEzXepJfslSrs1jRo8");
        cb.setOAuthAccessTokenSecret("xnR6tG4QxCKBbAVtsWxdFYjXzxNk2eRvr9vjYYN31Ep4f");

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

        EnglishStatusListener listener = new EnglishStatusListener() {
        
            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStatus(Status status) {
                User user = status.getUser();
                
                // gets Username
                String username = status.getUser().getScreenName();
                //System.out.println(username);
                String profileLocation = user.getLocation();
                //System.out.println(profileLocation);
                //long tweetId = status.getId(); 
                //System.out.println(tweetId);
                String content = status.getText();
                //System.out.println(content +"\n");
                String temp = "data~:" + username + " data~:" + status.getUser().getFollowersCount() + "|"+status.getUser().getFriendsCount() + " data~:" + profileLocation + " data~:" + content + "\n";
                //System.out.println(status.getUser());
                bw.write(temp);
                bw.flush();
                count++;
                if(count > 100000){
                	System.exit(0);
                }
                //bw.close();
            }
            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub
            }
        };
        FilterQuery fq = new FilterQuery();
        //String keywords[] = {"#BostonStrong","#MH192","#GoodFriday","#NBAFinalsPick","#PrayForSouthKorea","#ACDC","#worldstoughestjob","#MileyCyrus","#MTVMovieAwards","#SnowInApril","#bloodmoon","PurpleWedding","#starwars","#gameofthrones","#WWE","#Tetris","#mh370","#shameless","#MickeyRooney","#EASPORTS","#ThankYouTaker","#FDNY #NYPD","#BattlestarGalactica","#ken","#RuinARomCom","#HeartBleed",""};
        String keywords[] = {"#"};
        fq.track(keywords);
        
        twitterStream.addListener(listener);
        twitterStream.filter(fq);  
    }    
}