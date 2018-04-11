package ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class Ui {

	
	public static List<Stav> akt_generacia; 
	public static List<Stav> nova_generacia;
	public static TreeMap<Integer, Stav> tmap;
	public static int populacia; 
	
	  public static void main (String[] args)
	   	{
		  populacia = 20;
		  tmap = new TreeMap<Integer, Stav>();
	
		
		  
		 akt_generacia = new ArrayList<Stav>();
		 
		 int generacia=1;
		 vytvor(populacia);
		 int sum=0;

		 int max_skore=0;
		 do
		 {
			 
			 if (max_skore!=0)
			 {
				generacia++;
				if (generacia == 2)
				{
					generacia++;
				}
				  nova_generacia =new ArrayList<Stav>();  
				 for (int i=0;i < populacia;i++)
				 {
					 Stav prvy=null; 
					 Stav druhy=null;
					

					 Random rand =new Random();
					 int otec = rand.nextInt(sum);
					 int mama =  rand.nextInt(sum);


					 Set set = tmap.entrySet();
					 Iterator iterator = set.iterator();
					 while(iterator.hasNext()) 
					 {
						 Map.Entry mentry = (Map.Entry)iterator.next();
						 if ((otec < (int) mentry.getKey()) && (prvy==null))
						 {
							 prvy = (Stav) mentry.getValue();
						 }
						 if ((mama < (int) mentry.getKey())&&(druhy==null))
						 {
							 druhy = (Stav) mentry.getValue();
						 }
						 
						 if ((prvy!=null)&&(druhy!=null))
						 {
							 break;
						 }


					 }

					 vloznew(urobpotomka(prvy,druhy,50));
				 }
				 akt_generacia=nova_generacia;
				 tmap.clear();
			 }
		 
						 
	     max_skore=0;
	     sum=0;
	  		 Stav naj=null;
		 for(Stav n : akt_generacia)
		 {
			 n.presun();
			 sum+=n.skore;
			 vlozdostromu(n,sum);
			 if (n.skore>max_skore)
			 {
				 max_skore=n.skore;
				 naj=n;	 
			 }
		 }
		System.out.println("najlepsi z tejto generacie ma skore "+max_skore);
		 naj.zobraz();
		 } while (max_skore != 120);
		 System.out.println("generacia cislo "+generacia);
	}
	
	  public static Stav urobpotomka(Stav otec,Stav mama,int mutacia)
		{
		  int [] geny_exp =  otec.geny.clone();	
		  for (int i=0;i<14;i++)
			{
				geny_exp[i]=otec.geny[i*2];
				geny_exp[i+14]=mama.geny[i*2+1];
			}
		  Random rand =new Random();
		 if ( rand.nextInt(100)<mutacia)
		 {
			 geny_exp[rand.nextInt(27)+1]=rand.nextInt(43)+1;
		 }
		 
		 
		  return new Stav(geny_exp);
		}
	  
		    
	  
	public static void vlozdostromu(Stav a,int skore)
	{
		tmap.put(skore,a);
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
	
	public static void vloznew(Stav imp)
	{
		nova_generacia.add(imp);
	}
	
	
	
	

	
	
}
