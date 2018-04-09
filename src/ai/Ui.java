package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ui {

	
	public static List<Stav> akt_generacia; 
	
	  public static void main (String[] args)
	   	{
		System.out.println("test");
 
		
		
		  
		 akt_generacia = new ArrayList<Stav>();
		 
		 
		 vytvor(30);
		 
		 int max_skore=0;
		 Stav naj=null;
		 for(Stav n : akt_generacia)
		 {
			 n.presun();
			 
			 if (n.skore>max_skore)
			 {
				 max_skore=n.skore;
				 naj=n;
				 
			 }
		 }
		System.out.println("najlepsi z tejto generacie ma skore "+max_skore);
		 naj.zobraz();
		
	}
	
	public static void vytvor(int n)
	{
		for (int p=0;p<n;p++)
		{
			int[] geny =new int[45];
			int[] geny_exp = new int[28];
			for (int i=1;i<45;i++)
			{
				geny[i]=i;
			}
			Random rand =new Random();
			for (int i=0;i<200;i++)
			{
				int a =rand.nextInt(44)+1;
				int b =rand.nextInt(44)+1;
				int c;
				c=geny[a];
				geny[a]=geny[b];
				geny[b]=c;
			}


			geny_exp=new int[28];

			for (int i=1;i<28;i++)
			{
				geny_exp[i]=geny[i];
			}

			vloz(new Stav(geny_exp));

			//Stav a = new Stav(zapis,geny_exp);
		}
	}
	  
	  
	  
	public static void vloz(Stav imp)
	{
		akt_generacia.add(imp);
	}
	
	
	
	

	
	
}
