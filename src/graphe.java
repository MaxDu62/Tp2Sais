import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class graphe {
	public int taille;
	ArrayList<Noeud> noeuds=new ArrayList<Noeud>();
	TreeSet<Integer> var=new TreeSet<Integer>();
	public graphe(String nom_fichier) throws IOException {
		BufferedReader myfile=ouverture(nom_fichier);
		String line;
	
		try {
			while((line=myfile.readLine())!=null) {
				if(!(line.split(" ")[0].equals("p")||line.split(" ")[0].equals("c")))
					noeuds.add(new Noeud(line));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		for(Noeud s:noeuds) {
			Iterator<String> ss=s.ListAdj.iterator();
			while(ss.hasNext()) {
				var.add(Math.abs(Integer.parseInt(ss.next())));
				
			}
		}
		System.out.println(var.size());
		fermeture(myfile);

		
	}
	public BufferedReader ouverture(String nom_fichier)  {
		try {
			return new BufferedReader(new FileReader(nom_fichier));
		}catch(Exception e) {
			 e.printStackTrace();
		}
		return null;
	
	}
	public StringBuilder toSt() {
		StringBuilder t=new StringBuilder();
		for(Noeud s:noeuds) {
			t.append(" noeud adjacent: " +s.ListAdj.toString()+"\n");
			
		}
		return t;
	}
	
	public void fermeture(BufferedReader f) throws IOException {
			if(f!=null) {
				f.close();
			}
	}
	
	
	
}
