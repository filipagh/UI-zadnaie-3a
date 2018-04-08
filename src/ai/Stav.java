package ai;

public class Stav {
	
	int [][] pole;
	int [] geny;
	int x;
	int y;
	int smerx;
	int smery;
	public Stav(int [][] zapis,int[] geny_imp)
	{
		pole=zapis.clone();
		geny=geny_imp.clone();
		for (int i=0;i<28;i++)
		{
			System.out.print(geny[i]+" ");
		
		}
		System.out.println();
		presun();
	}
	
	
	public void presun()
	{
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
				
				hrabaj(i);
		
		}
		
	}

	public void hrabaj(int i)
	{
		if (pole[y][x]!= 0)	
		{
			System.out.println(x+" kamen "+y);
			return;	
		}

		while (true)
		{
			if ((y+smery>=0 && y+smery<10) && (x+smerx>=0 && x+smerx<12) && (pole[y+smery][x+smerx]==0))
			{
				pole[y][x]=i;
				y+=smery;
				x+=smerx;
			
			}
			else
			{
				if ((y+smerx>=0 && y+smerx<10) && (x+smery>=0 && x+smery<12) && (pole[y][x])  )
				{
					
				}
			}
			
		}
		
		
	}

	
}
