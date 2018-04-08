package ai;

public class Ui {

	static int [][] pole;
	
	  public static void main (String[] args)
	   	{
		System.out.println("test");
 
		 pole=new int[10][12];
		 napln();
      
	}
	
	public static void napln()
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
		for (int i=0;i<10;i++ )
		{
			for (int k=0;k<12;k++)
			{
				System.out.print(pole[i][k]+", ");
			}
			System.out.println();
		}
	

	}
	
	
	
}
