package ai;

import java.util.Random;

public class Ui {

	public static int [][] zapis;
	
	  public static void main (String[] args)
	   	{
		System.out.println("test");
 
		 zapis=new int[10][12];
		 napln();
     
		 vytvor();
		
	}
	
	public static void vytvor()
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
	  
	   

	    
		Stav a = new Stav(zapis,geny_exp); 
	}
	  
	  
	  
	  
	
	
	
	
	public static void napln()
	{
		
		for (int i=0;i<10;i++ )
		{
			for (int k=0;k<12;k++)
			{
				zapis[i][k]=0;
				
			}
			
		}
	zapis[1][5]=-1;
	
	zapis[0][1]=-1;
	zapis[0][2]=-1;
	zapis[0][3]=-1;
	zapis[0][4]=-1;
	zapis[0][5]=-1;
	zapis[0][6]=-1;
	
	zapis[2][1]=-1;
	zapis[3][4]=-1;
	zapis[4][2]=-1;
	zapis[6][8]=-1;
	zapis[6][9]=-1;
		for (int i=0;i<10;i++ )
		{
			for (int k=0;k<12;k++)
			{
				System.out.print(zapis[i][k]+", ");
			}
			System.out.println();
		}
	}
	
	
	
}
