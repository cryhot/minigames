

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

import core.game.*;


public class Reader {
	
	public ArrayList<Soul> getInitialPosition(int n){
		ArrayList<Character> getPosition = new ArrayList<Character>();
		ArrayList<Soul> initialisedPosition = new ArrayList<Soul>();
		String s;
		char c;
		try{
			BufferedReader bis = new BufferedReader(new FileReader(new File("Reader.txt")));
			if(n==1){
				getPosition.add('1');
				s =bis.readLine();
				while(!s.equals("# Player1")){
					s = bis.readLine();
				}
				c = (char)(bis.read());
				while(c!='#'){
				if(c=='G'||c=='B')
					getPosition.add(c);
				c = (char)(bis.read());
				}
				for(int i=getPosition.size();i>(getPosition.size()/2);i--){
					getPosition.add(getPosition.get(getPosition.size()-1));
					getPosition.remove(getPosition.size()-1);
				}
				
			}
			if(n==2){
				getPosition.add('2');
				s =bis.readLine();
				while(!s.equals("# Player2")){
					s = bis.readLine();
				}
				c = (char)(bis.read());
				while(c!='#'){
				if(c=='G'||c=='B')
					getPosition.add(c);
				c = (char)(bis.read());
				}
				for(int i=getPosition.size();i>(getPosition.size()/2);i--){
					getPosition.add(0,getPosition.get(getPosition.size()-1));
					getPosition.remove(getPosition.size()-1);
				}
			}
			
			for(char character : getPosition){
				if(character=='B')
					initialisedPosition.add(Soul.SOUL_GOOD);
				if(character=='G')
					initialisedPosition.add(Soul.SOUL_BAD);
			}
			return initialisedPosition; 
		}
		catch(Exception e){
			return null;
		}
		
	}
	
	public ArrayList<String> getMoveOfReader(){
		
		ArrayList<String> coordonnates = new ArrayList<String>();
		ArrayList<Character> getByte = new ArrayList<Character>();
		String s;
		char c;
		try{
			BufferedReader bis = new BufferedReader(new FileReader(new File("Reader.txt")));
			s =bis.readLine();
			while(!s.equals("# Move")){
				s = bis.readLine();
			}
			while((char)(bis.read())=='-'){
				c =(char)(bis.read());
			}
			while(!((char)(bis.read())=='2')){
				c =(char)(bis.read());
				if((c>='0'&&c<='9')||(c>='a'&& c<='z')){
					getByte.add(c);
				}
			}
			boolean end = false;
			while(!end){
				try{
					c =(char)(bis.read());
					if((c>='0'&&c<='9')||(c>='a'&& c<='z'))
						getByte.add(c);
					
				}
				catch(Exception e){
					end = true;
				}
				
			}
			for(int i=0;i<getByte.size();i+=2){
				if(i==0){
					coordonnates.add("1");
				}
				if(i==(getByte.size()/2)){
					coordonnates.add("2");
				}
				String str = (Character.toString(getByte.get(i))).concat(Character.toString(getByte.get(i+1)));
				coordonnates.add(str);						
			}
			return coordonnates;			
		}
		catch(Exception e){
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	
}