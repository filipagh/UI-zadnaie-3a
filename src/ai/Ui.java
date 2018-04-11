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
		  populacia = 500;		//urcuje pocet jedincov 
		  tmap = new TreeMap<Integer, Stav>();		// 	datova struktura pre "ruletu"			 
		 akt_generacia = new ArrayList<Stav>();		// list jedincov aktualnej gen				
		 
		 int generacia=1;	//ciselnik gen
		 vytvor(populacia); //metoda na vytvorenie 1. generacie  
		 int sum=0; //celkova hodnota pre ruletu

		 int max_skore=0;  // max hodnota fitnes f. 
		 do
		 {
			 
			 if (max_skore!=0)  // generovanie dalsej generacie
			 {
				generacia++;    // zvys pocitadlo
				
				if (generacia==100000)   // limit 
				{
					System.out.println("nenaslo sa riesenie do 10000 generacii");
					return;
				}
				
				
				  nova_generacia =new ArrayList<Stav>();	//list jedincov novej generacie  
				 for (int i=0;i < populacia;i++)			// generovanie novych jednincov
				 {
					 Stav prvy=null;  // otec
					 Stav druhy=null; // matka
					

					 Random rand =new Random();
					

					 if (generacia % 2 == 0)  // RULETA
					 {
						 int otec = rand.nextInt(sum);   // nahodna hodnota pre otca
						 int mama =  rand.nextInt(sum);  // nahodna hodnota pre mamku
						 Set set = tmap.entrySet();		// struhtury pre vyber z rulety
						 Iterator iterator = set.iterator();
						 while(iterator.hasNext())     // Hladanie danych objektov ktore boly v rulete vybrate
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
					 }
					 else    // TURNAJ
					 {
						 //System.out.println("test 2gne alg"); 
						  List<Stav> otcovia = new ArrayList<Stav>();  // list otcov
						  List<Stav> matky = new ArrayList<Stav>();    // list matiek
						for (int p=0;p<(int) (akt_generacia.size() / 5);p++)   // vyber 20% z aktualnej generacie do otcov a do matiek
						{
						//	  Random rand =new Random();
							 otcovia.add(akt_generacia.get( rand.nextInt(akt_generacia.size()))) ;
							 matky.add(akt_generacia.get( rand.nextInt(akt_generacia.size()))) ;	
						}

						int najotec=0;
						int najmatka=0;
						for (int p=0;p<(int) (akt_generacia.size() / 5);p++)   // z kazdej skupiny najdy najlepsieho
						{ 
							if (otcovia.get(p).skore>najotec)
							{
								prvy = otcovia.get(p);
								najotec = otcovia.get(p).skore;
							}
							if (matky.get(p).skore>najmatka)
							{
								druhy = matky.get(p);
								najmatka = matky.get(p).skore;
							}
						}
						
					 }
					 vloznew(urobpotomka(prvy,druhy,10,80));     // vytvor potomka(otec,mama,kolko % mutacia,kolko & krizenie) a nasledne ho vloz do zoznamu nova gen
				 }
				 akt_generacia=nova_generacia;   // na konci generovania nova gen sa stava sucastna
				 tmap.clear(); // vycistenie rulety
			 }
		 
						 
	     max_skore=0; // reset max skore pre gen , aj sum pre ruletu
	     sum=0; 
	  		 Stav naj=null; // naj sluzi len na zobrazenie naj jedinca v gen
		 for(Stav n : akt_generacia) // prechadzaj generaciu
		 {
			 n.presun(); // kazdy jedinec si urobi fitnes funkciu
			 sum+=n.skore; // jej skore sa pripocita do sum rulety
			 vlozdostromu(n,sum); // a vlozi sa do stromu rulety
			 if (n.skore>max_skore) // ak je jedinec lepsi ako predosli zapise sa do "naj"
			 {
				 max_skore=n.skore;
				 naj=n;	 
			 }
		 }
		System.out.println("najlepsi z tejto generacie ma skore "+max_skore);   //vypis naj jedinca z gen
		 naj.zobraz(); // zobrazi jeho riesenie
		 } while (max_skore != 120);   // opakuj kym nemame sprvne riesenie
		 System.out.println("generacia cislo "+generacia);  
	}
	
	  public static Stav urobpotomka(Stav otec,Stav mama,int mutacia,int krizenie)  // generacia na vytvaranie potomka (otec, matka, % kolko mutacie,%kolko krizenie)
		{
		  Random rand =new Random(); 
		  int [] geny_exp=null; 
		  if (otec.skore>mama.skore)   // ak nieje krizenie tak vlozi sa lepsi z otca/matky
		  {
			  geny_exp =  otec.geny.clone();	
		  }
		  else
		  {
			  geny_exp =  mama.geny.clone();	  
		  }
		  
		  if ( rand.nextInt(100)<krizenie)			// ak je krizenie parne geny otca a neparne geny matky sa zapisu do noveho genomu
			 {
				  for (int i=0;i<14;i++)
					{
						geny_exp[i]=otec.geny[i*2];
						geny_exp[i+14]=mama.geny[i*2+1];
					} 
			 }
		  
		
		  
		
		  
		 if ( rand.nextInt(100)<mutacia)   // ak nastane mutacia tak nahodne miesto v genone sa upravi na nahodnu hodnotu vstupu
		 {
			 geny_exp[rand.nextInt(27)+1]=rand.nextInt(43)+1;
		 }
		 
		 
		  return new Stav(geny_exp);   // vrati novy stav 
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
