package ai;

public class Stav {
	
	
	// tree map
	
	int [][] pole;
	int [] geny;
	int x;
	int y;
	int smerx;
	int smery;
	public Stav(int [][] zapis,int[] geny_imp)
	{
		pole=zapis.clone();
		geny=  geny_imp.clone();// new int[] {0, 14, 4, 18, 26, 28, 43, 9, 11, 29, 23, 2, 27, 35, 13, 7, 30, 22, 39, 41, 1, 10, 5, 38, 34, 33, 40, 37};//
		for (int i=0;i<28;i++)
		{
			//System.out.print(geny[i]+", ");
		
		}
	//	System.out.println();
		presun();
	}
	
	public boolean over_sur(int x, int y)
	{
		if ((y>=0 && y<10) && (x>=0 && x<12))
		{
			return true;
		}
		return false;
	}
	
	public void presun()
	{
		int pocet_ciest=0;
		for (int i=1;i<28;i++)
		{
		
				do
				{
					if (geny[i]<13)
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
				//System.out.println(x+" ------------------- "+y);
				if (pole[y][x]!= 0)	
				{
					//System.out.println(x+" kamen "+y);
				}
				else
				{
					pocet_ciest++;
				
					hrabaj(pocet_ciest);

					for (int p=0;p<10;p++ )
					{
						for (int k=0;k<12;k++)
						{
							System.out.print(String.format("%2d", pole[p][k])+" ");
						}
						System.out.println();
					}
					System.out.println("************************************************************* hore je cesta cislo "+pocet_ciest);
				}
		}
		
	}

	public void hrabaj(int i)
	{
	
		
		pole[y][x]= i;
		while (true)
		{
			if (over_sur(x+smerx,y+smery) && (pole[y+smery][x+smerx]==0))
			{
				pole[y][x]=i;
				y+=smery;
				x+=smerx;
			
			}
			else
			{
				if (over_sur(x+smerx,y+smery)==false)
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
				else
				{

					if ((over_sur(x+smery,y+smerx)) && (pole[y+smerx][x+smery]==0) && (over_sur(x+smery*2,y+smerx*2)) && (pole[y+smerx*2][x+smery*2]!=0))
					{
						int temp=smery;
						smery=smerx;
						smerx=temp;
					}
					else if ((over_sur(x+smery*-1,y+smerx*-1)) && (pole[y+smerx*-1][x+smery*-1]==0) )
					{
						int temp=smery*-1;
						smery=smerx*-1;
						smerx=temp;
					}
					else if ((over_sur(x+smery,y+smerx)) && (pole[y+smerx][x+smery]==0))
					{
						int temp=smery;
						smery=smerx;
						smerx=temp;
					}
					else
					{
						if ((over_sur(x+smery,y+smerx)==false) || (over_sur(x+smery*-1,y+smerx*-1)==false))
						{
						pole[y][x]=i;
						}
						break;
					}

				}	
			}

		}
		
		
	}

	
}
