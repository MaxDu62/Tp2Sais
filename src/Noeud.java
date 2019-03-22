import java.awt.List;
import java.util.ArrayList;

public class Noeud {
	ArrayList<String> ListAdj=new ArrayList<String>();

	public Noeud(String Lis) {
			
			remplir(Lis);
	}
	void remplir(String Lis) {
		String[] toto=Lis.split(" ");

			for(int i=0;i<toto.length-1;i++) {
				ListAdj.add(toto[i]);
			}
	}
	void affiche() {
		System.out.println("liste des noeuds ");
		for(String s:ListAdj) {
			System.out.print(s+",");
		}
	}
}
