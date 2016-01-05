import java.io.BufferedReader;
import java.io.*;
import java.util.*;

import core.game.*;
import core.board.Case;
import core.stage.BoardSquare;

public class Reader extends Game {
	private int speed;
	protected List<Soul> player1;
	protected List<Soul> player2;
	protected Queue<Case> movements;
	
	public Reader(File file,int speed) {
		super(new BoardSquare());
		if (speed<0)
			throw new IllegalArgumentException(Integer.toString(speed));
		this.speed = speed;
		super.subscribe( new InterfaceReader(this) ,0);
		super.subscribe( new InterfaceReader(this) ,1);
		try {
			this.read(file);
		} catch (IOException e) {
			throw new RuntimeException("Fichier illisible",e);
		}
	}
	
	public Reader(File file) {
		this(file,0);
	}
	
	private void read(File file) throws IOException {
		this.movements = new LinkedList<Case>();
		this.player1 = new ArrayList<Soul>(8);
		this.player2 = new ArrayList<Soul>(8);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Fichier introuvable",e);
		}
		String line = reader.readLine();
		while (!line.equals("# Player1")) {
			line = reader.readLine();
		}
		line = reader.readLine(); // "# Player1"
		while (!line.equals("# Player2")) {
			for (int i=line.length()-1;i>=0;i--) // on veut lire une ligne à l'endroit (voir apres)
				switch (line.charAt(i)) {
					case 'G': this.player1.add(0,Soul.SOUL_GOOD); break; // on veut lire les lignes à l'envers
					case 'B': this.player1.add(0,Soul.SOUL_BAD ); break; // en effet les cases sont par ordre lexicographique
				}
			line = reader.readLine();
		}
		line = reader.readLine(); // "# Player2"
		while (!line.equals("# Move")) {
			for (int i=line.length()-1;i>=0;i--) // on veut lire une ligne à l'endroit (voir apres)
				switch (line.charAt(i)) {
					case 'G': this.player2.add(0,Soul.SOUL_GOOD); break; // on veut lire les lignes à l'envers
					case 'B': this.player2.add(0,Soul.SOUL_BAD ); break; // en effet les cases sont par ordre lexicographique
				}
			line = reader.readLine();
		}
		line = reader.readLine(); // "# Move"
		while (line!=null) {
			line += " "; // pour prendre en compte la derniere case
			int i=0;
			while (i<line.length()&&line.charAt(i)!='-')
				i++;
			StringBuilder coord = new StringBuilder();
			for (i++;i<line.length();i++) {
				char c = line.charAt(i);
				if ((c>='0'&&c<='9') || (c>='a'&&c<='z'))
					coord.append(c);
				else if (coord.length()>0) { // case interceptee
					Case location = this.coordonnates(coord.toString());
					if (location==null)
						throw new RuntimeException("Fichier corrompu : coordonnee incomprehensible - "+coord);
					this.movements.add(location);
					coord = new StringBuilder();
				}
			}
			line = reader.readLine();
		}
	}
	
	List<Soul> getInitialSoul(int player) {
		switch (player) {
			case 0: return player1;
			case 1: return player2;
			default: throw new UnsupportedOperationException(Integer.toString(player));
		}
	}
	
	Case getNextCase() {
		return this.movements.remove();
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	private Case coordonnates(String s) {
		if (s==null)
			return null;
		int x = 0;
		int y = 0;
		int hash1 = 1;
		int hash2 = 0;
		boolean figureFound = false;
		for (int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if (c>='A' && c<='Z')
				c += 'a'-'A';
			if (c>='a' && c<='z' && !figureFound){
				hash1 *= 26;
				hash2 *= 26;
				hash2 += c-'a';				
			}
			else if (c>='0' && c<='9'){
				y= 10*y + (int)(c - '0');
				figureFound = true;				
			}
			else{
				return null;
			}
		}
		hash1 = (hash1-1)/(25);
		x = hash1+hash2;
		if(x == 0 || y == 0){
			return null;
		}
		return this.getBoard().getCase(this.getBoard().getXMin()+x-1,this.getBoard().getYMin()+y-1);
	}
	
}