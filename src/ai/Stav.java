package ai;

public class Stav {


	// tree map

	int [][] pole;
	int [] geny;
	int x;
	int y;
	int smerx;
	int smery;
	int skore;
	public Stav(int[] geny_imp)
	{
		pole=new int[10][12];			//pole na zapis zahrady
		vytvorpole();					// naplni dane pole
		geny=  geny_imp.clone();		// nacitanie genov

		// vypis genov 
		/*
		for (int i=0;i<28;i++)
		{
			System.out.print(geny[i]+", ");

		}
		System.out.println();
		 */
	}


	public boolean over_sur(int x, int y)  // metoda ktora skontroluje ci dane sur su v poly 
	{
		if ((y>=0 && y<10) && (x>=0 && x<12))
		{
			return true;
		}
		return false;
	}

	public void presun() 			// metoda na pocitenie FITNES 
	{
		int pocet_ciest=0;     //pocet ciest
		for (int i=1;i<28;i++)  // pre kazdy gen zacni hladat cestu
		{

			do
			{
				if (geny[i]<13)				// nastavenie smeru podla umiestnenia v poly
				{
					x=(geny[i]-1);
					y=0;
					smerx =0;
					smery =1;

					break;
				}
				else if (geny[i]<23)
				{
					x=11;
					y=geny[i]-13;
					smerx =-1;
					smery =0;
					break;
				}
				if (geny[i]<35)
				{
					x=(geny[i]-34)*-1;
					y=9;
					smerx =0;
					smery =-1;
					break;
				}
				else 
				{
					x=0;						
					y=(geny[i]-44)*-1;
					smerx =1;
					smery =0;
					break;
				}

			}while (true);
			
			
			if (pole[y][x]!= 0)	 // test ci mozem vstupit na dane policko // ci tam nieje kamen alebo ina cesta
			{
				//System.out.println(x+" "+y+" je zablokovana");
			}
			else
			{
				pocet_ciest++;			// zvys pocet ciest a zacni ju prehladavat

				hrabaj(pocet_ciest);
			}
		}
								// vypocet fitnes skore ... poscitam pocet 0 v zahrade
		skore = 10*12;
		for (int p=0;p<10;p++ )
		{
			for (int k=0;k<12;k++)
			{
				if (pole[p][k]==0)
				{
					skore--;	
				}
			}
		}
		//System.out.println(skore);
	}

	public void zobraz()		// metoda na vypisanie zahrady
	{
		for (int p=0;p<10;p++ )
		{
			for (int k=0;k<12;k++)
			{
				System.out.print(String.format("%2d", pole[p][k])+" ");
			}
			System.out.println();
		}
	}

	public void hrabaj(int i)     // metoda prechadzania zahradov PREDPOKLAD nachadzame sa na kraji zahrady 
	{


	//	pole[y][x]= i; 			// zapiseme cislo na zaciatocne miesto
		while (true)
		{
			if (over_sur(x+smerx,y+smery) && (pole[y+smery][x+smerx]==0)) 		// test vieme sa posunut dopredu ak ano 
			{
				pole[y][x]=i;
				y+=smery;
				x+=smerx;

			}
			else
			{
				if (over_sur(x+smerx,y+smery)==false) // zisti ci niesi na konci tahu //    start  1111111111<>  koniec
				{
					pole[y][x]=i;
					break;
					/*
					if ((over_sur(x+smery*-1,y+smerx*-1)) && (pole[y+smerx*-1][x+smery*-1]==0) )
					{
						pole[y+smerx*-1][x+smery*-1]=i;
						x+=smery*-1;
						y+=smerx*-1;
						smery=smery*-1;
						smerx=smerx*-1;
					}
					else if ((over_sur(x+smery,y+smerx)) && (pole[y+smerx][x+smery]==0))
					{
						pole[y+smerx][x+smery]=i;
						x+=smery;
						y+=smerx;
						smery=smery*-1;
						smerx=smerx*-1;
					}
					else
					{
						break;
					} 
					*/ 
				}
				else  // inac nastala situacia ze som do niecoho vrazil 
				{
					/*
				111111111
				    5-3 
					5-
					5-
						pozrieme sa v opacnom smere t.j. vlavo pravo (zamena smerx a smery) ci je jedno volne miest a za nim je obsadene (mame tah 5 narazili sme na 1 a porieme sa vpravo a vidime ze je tam volne a obsadene (3) ideme touto cestov)
					*/
					if ((over_sur(x+smery,y+smerx)) && (pole[y+smerx][x+smery]==0) && (over_sur(x+smery*2,y+smerx*2)) && (pole[y+smerx*2][x+smery*2]!=0))     
					{
						int temp=smery;
						smery=smerx;
						smerx=temp;
					}
					else if ((over_sur(x+smery*-1,y+smerx*-1)) && (pole[y+smerx*-1][x+smery*-1]==0) ) // inak poreme sa ci sa da ist opacne ak ano ideme tade
					{
						int temp=smery*-1;
						smery=smerx*-1;
						smerx=temp;
					}
					else if ((over_sur(x+smery,y+smerx)) && (pole[y+smerx][x+smery]==0))   // inak chod doprava ak sa da 
					{
						int temp=smery;
						smery=smerx;
						smerx=temp;
					}
					else
					{
						if ((over_sur(x+smery,y+smerx)==false) || (over_sur(x+smery*-1,y+smerx*-1)==false))  // inac over ci si na kraji (situacia ci si neskoncil v strede zahrady)
						{
							pole[y][x]=i;
						}
						break;
					}

				}	
			}

		}


	}

	public void vytvorpole()  		// METODA NA VYPLNENIE POLA TEDA ZAHRADY	
	{
		for (int i=0;i<10;i++ )
		{
			for (int k=0;k<12;k++)
			{
				pole[i][k]=0;
			}

		}
		pole[1][5]=-1;	
		pole[2][1]=-1;
		pole[3][4]=-1;
		pole[4][2]=-1;
		pole[6][8]=-1;
		pole[6][9]=-1;
	}

}
