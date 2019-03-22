import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class AnalyseDimacFile {
	BufferedReader f;
	File sortie;
	int nbBinaryClause=0;
	int nbHornClause=0;
	int nbReverseHornClause=0;
	StringBuilder myDimac=new StringBuilder();
	public AnalyseDimacFile() {
		begin();
		
		
	}
	public void begin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("saisir le nom de votre fichier dimac :");
		String name=sc.nextLine();
		f=ouverture(name);
		try {
			graphe t=new graphe(name);
			System.out.println("nb ligne fichier" +t.noeuds.size());
			System.out.println(t.toSt());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
		String line;
		FileWriter SortFile=ecriture("Result.txt");
		try {
			SortFile.write("nom de l'instance :"+name+"\r\n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while((line=f.readLine())!=null) {
				System.out.println("toto");
				myDimac.append(line+" \r\n");
				if(line.split(" ")[0].compareToIgnoreCase("p")==0) {
					SortFile.write(nbClause(line)+nbVar(line));
				}else if(line.split(" ")[0].compareToIgnoreCase("c")!=0) {
					testBinaryclause(line);
					testReverseHornClause(line);
					testHornClause(line);
				}
				
			}
			SortFile.write("nombre de clause binaire :"+nbBinaryClause+"\r\n");
			SortFile.write("nombre de clause de Horn :"+nbHornClause+"\r\n");
			SortFile.write("nombre de clause inverse de Horn :"+nbReverseHornClause+"\r\n");
			SortFile.write(myDimac.toString());
			SortFile.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public BufferedReader ouverture(String nom_fichier)  {
		try {
			return new BufferedReader(new FileReader(nom_fichier));
		}catch(Exception e) {
			 e.printStackTrace();
		}
		return null;
	
	}
	public String nbVar(String toto) {
		String[] n=toto.split(" ");
		return new String("nombre de variables: "+n[2]+"\r\n");
	}
	public String nbClause(String toto) {
		String[] n=toto.split(" ");
		return new String("nombre de clause: "+n[3]+"\r\n");
	}
	public FileWriter ecriture(String name) {
		try {
			FileWriter f=new FileWriter(name);
			return f;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void testBinaryclause(String s) {
		if(s.split(" ").length==3)
			nbBinaryClause+=1;
	}
	public void testHornClause(String s) {
		String[]tab = s.split(" ");
		int count=0;
		for(int i=0;i<tab.length-1;i++){
			if(Integer.parseInt(tab[i])>0)
				count++;
		}
		if(count<=1)
			nbHornClause++;
	}
	public void testReverseHornClause(String s) {
		String[]tab = s.split(" ");
		int count=0;
		for(int i=0;i<tab.length-1;i++){
			if(Integer.parseInt(tab[i])<0)
				count++;
		}
		if(count<=1)
			nbReverseHornClause++;
	}
	
}
