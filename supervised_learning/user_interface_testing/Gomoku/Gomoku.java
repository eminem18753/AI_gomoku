import java.util.Set;
import java.util.HashSet;
import java.util.Stack;
import java.util.Iterator;
import java.util.Queue;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Random;

public class Gomoku
{
	private int[][] array;
	private int rowNumber;
	private int colNumber;
	private plotGraph plotGomoku;
	private int SLEEP_TIME=100;
	private int end=0;
	private int firstRow;
	private int firstCol;
	private int smartCheck=0;
	private int winPoint=0;
	public Gomoku(int[][] array,int rowNumber,int colNumber)
	{
		plotGomoku=new plotGraph(array,rowNumber,colNumber,1);
		try
		{
			Thread.sleep(SLEEP_TIME*2);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		this.array=array.clone();
		this.rowNumber=rowNumber;
		this.colNumber=colNumber;
		/*
		for(int i=0;i<147;i++)
		{
			System.out.println(readWeight.weights[i]);
		}
		*/
	}
	public void GomokuAlgorithm()
	{		
		Random rand=new Random();
		int firstRedRow=rand.nextInt(7);
		int firstRedColumn=rand.nextInt(7);
		array[firstRedRow][firstRedColumn]=1;
		firstRow=firstRedRow;
		firstCol=firstRedColumn;
		
		int firstBlueRow=rand.nextInt(7);
		int firstBlueColumn=rand.nextInt(7);
		
		while(firstBlueRow==firstRedRow&&firstRedColumn==firstBlueColumn)
		{
			firstBlueRow=rand.nextInt(7);
			firstBlueColumn=rand.nextInt(7);
		}
		
		while(plotGraph.flagKeyin==0)
		{
			System.out.println(plotGraph.flagKeyin);
		}
		System.out.println(plotGraph.flagKeyin);
		plotGraph.flagKeyin=0;
		point blueFirst=new point(plotGraph.xLocation,plotGraph.yLocation);
			
		array[blueFirst.getRow()][blueFirst.getCol()]=2;
		
		while(end!=1)
		{

			//plot graph
			try
			{
				Thread.sleep(SLEEP_TIME);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			plotGomoku=new plotGraph(array,rowNumber,colNumber); 
			//plot graph
			int countFilling=0;
			for(int i=0;i<rowNumber;i++)
			{
				for(int j=0;j<colNumber;j++)
				{
					if(array[i][j]!=0)
					{
						countFilling++;
					}
				}
			}
			if(countFilling==rowNumber*colNumber)
			{
				end=1;
				System.out.println("Tied");
				break;
			}
			
			//point redPlayer=checkState(1);
			point redPlayer=checkState(1);
			//System.out.print(redPlayer.getRow());
			//System.out.print(",");
			//System.out.println(redPlayer.getCol());
			
			array[redPlayer.getRow()][redPlayer.getCol()]=1;
			checkEnd();
			//plot graph
			try
			{
				Thread.sleep(SLEEP_TIME);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			plotGomoku=new plotGraph(array,rowNumber,colNumber); 
			//plot graph
			if(end==1)
			{
				break;
			}
			try
			{
				Thread.sleep(SLEEP_TIME);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			
			countFilling=0;
			for(int i=0;i<rowNumber;i++)
			{
				for(int j=0;j<colNumber;j++)
				{
					if(array[i][j]!=0)
					{
						countFilling++;
					}
				}
			}
			if(countFilling==rowNumber*colNumber)
			{
				end=1;
				System.out.println("Tied");
				break;
			}
			try
			{
				Thread.sleep(SLEEP_TIME);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			//point bluePlayer=checkState(2);
			while(plotGraph.flagKeyin==0)
			{
				System.out.println(plotGraph.flagKeyin);
			}
			System.out.println(plotGraph.flagKeyin);
			plotGraph.flagKeyin=0;
			point bluePlayer=new point(plotGraph.xLocation,plotGraph.yLocation);
			array[bluePlayer.getRow()][bluePlayer.getCol()]=2;			
			checkEnd();
			if(end==1)
			{
				break;
			}
			try
			{
				Thread.sleep(SLEEP_TIME);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			Thread.sleep(SLEEP_TIME);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		plotGomoku=new plotGraph(array,rowNumber,colNumber);
	}
	public point checkState(int player)
	{
		point answer=new point(firstRow,firstCol);
		//1:myself 4 points row
		for(int i=0;i<rowNumber;i++)
		{
			if(array[i][0]==player&&array[i][1]==player&&array[i][2]==player&&array[i][3]==player&&array[i][4]==0)
			{
				point result=new point(i,4);
				end=1;
				return result;
			}
		}

		for(int i=0;i<rowNumber;i++)
		{
			if(array[i][3]==player&&array[i][4]==player&&array[i][5]==player&&array[i][6]==player&&array[i][2]==0)
			{
				point result=new point(i,2);
				end=1;
				return result;
			}
		}
		
		for(int i=0;i<rowNumber;i++)
		{
			for(int j=1;j<3;j++)
			{
				if((array[i][j]==player&&array[i][j+1]==player&&array[i][j+2]==player&&array[i][j+3]==player)&&(array[i][j-1]==0||array[i][j+4]==0))
				{
					if(array[i][j-1]==0)
					{
						point result=new point(i,j-1);
						end=1;
						return result;
					}
					else if(array[i][j+4]==0)
					{
						end=1;
						point result=new point(i,j+4);
						return result;
					}
				}
			}
		}
		//1:myself 4 points row
		//1:myself 4 points column
		for(int j=0;j<colNumber;j++)
		{
			if(array[0][j]==player&&array[1][j]==player&&array[2][j]==player&&array[3][j]==player&&array[4][j]==0)
			{
				point result=new point(4,j);
				end=1;
				return result;
			}
		}

		for(int j=0;j<colNumber;j++)
		{
			if(array[3][j]==player&&array[4][j]==player&&array[5][j]==player&&array[6][j]==player&&array[2][j]==0)
			{
				point result=new point(2,j);
				end=1;
				return result;
			}
		}
		
		for(int j=0;j<colNumber;j++)
		{
			for(int i=1;i<3;i++)
			{
				if((array[i][j]==player&&array[i+1][j]==player&&array[i+2][j]==player&&array[i+3][j]==player)&&(array[i-1][j]==0||array[i+4][j]==0))
				{
					if(array[i+4][j]==0)
					{
						point result=new point(i+4,j);
						end=1;
						return result;
					}
					else if(array[i-1][j]==0)
					{
						point result=new point(i-1,j);
						end=1;
						return result;
					}
				}
			}
		}
		//1:myself 4 points column
		//1:myself 4 points diagonal
		for(int i=0;i<3;i++)//left 4
		{
			if(array[i][0]==player&&array[i+1][1]==player&&array[i+2][2]==player&&array[i+3][3]==player&&array[i+4][4]==0)
			{
				point result=new point(i+4,4);
				end=1;
				return result;
			}
		}
		for(int i=1;i<3;i++)
		{
			if(array[0][i]==player&&array[1][i+1]==player&&array[2][i+2]==player&&array[3][i+3]==player&&array[4][i+4]==0)
			{
				point result=new point(4,i+4);
				end=1;
				return result;
			}
		}
		for(int i=1;i<4;i++)
		{
			if(array[i][3]==player&&array[i+1][4]==player&&array[i+2][5]==player&&array[i+3][6]==player&&array[i-1][2]==0)
			{
				point result=new point(i-1,2);
				end=1;
				return result;
			}
		}
		for(int i=1;i<3;i++)
		{
			if(array[3][i]==player&&array[4][i+1]==player&&array[5][i+2]==player&&array[6][i+3]==player&&array[2][i-1]==0)
			{
				point result=new point(2,i-1);
				end=1;
				return result;
			}
		}
		for(int i=1;i<4;i++)
		{
			if(array[i][3]==player&&array[i+1][4]==player&&array[i+2][5]==player&&array[i+3][6]==player&&array[i-1][2]==0)
			{
				point result=new point(i-1,2);
				end=1;
				return result;
			}
		}
		for(int i=1;i<3;i++)
		{
			if((array[1][i]==player&&array[2][i+1]==player&&array[3][i+2]==player&&array[4][i+3]==player)&&(array[0][i-1]==0||array[5][i+4]==0))
			{
				if(array[0][i-1]==0)
				{
					point result=new point(0,i-1);
					end=1;
					return result;
				}
				else if(array[5][i+4]==0)
				{
					point result=new point(5,i+4);
					end=1;
					return result;
				}
			}		
		}
		for(int i=1;i<3;i++)
		{
			if((array[2][i]==player&&array[3][i+1]==player&&array[4][i+2]==player&&array[5][i+3]==player)&&(array[1][i-1]==0||array[6][i+4]==0))
			{
				if(array[1][i-1]==0)
				{
					point result=new point(1,i-1);
					end=1;
					return result;
				}
				else if(array[6][i+4]==0)
				{
					point result=new point(6,i+4);
					end=1;
					return result;
				}
			}		
		}
		for(int i=6;i>3;i--)
		{
			if(array[0][i]==player&&array[1][i-1]==player&&array[2][i-2]==player&&array[3][i-3]==player&&array[4][i-4]==0)
			{
				point result=new point(4,i-4);
				end=1;
				return result;
			}
		}
		for(int i=1;i<3;i++)
		{
			if(array[i][6]==player&&array[i+1][5]==player&&array[i+2][4]==player&&array[i+3][3]==player&&array[i+4][2]==0)
			{
				point result=new point(i+4,2);
				end=1;
				return result;
			}
		}
		for(int i=1;i<4;i++)
		{
			if(array[i][3]==player&&array[i+1][2]==player&&array[i+2][1]==player&&array[i+3][0]==player&&array[i-1][4]==0)
			{
				point result=new point(i-1,4);
				end=1;
				return result;
			}
		}
		for(int i=4;i<6;i++)
		{
			if(array[3][i]==player&&array[4][i-1]==player&&array[5][i-2]==player&&array[6][i-3]==player&&array[2][i+1]==0)
			{
				point result=new point(2,i+1);
				end=1;
				return result;
			}
		}
		for(int i=4;i<6;i++)
		{
			if((array[1][i]==player&&array[2][i-1]==player&&array[3][i-2]==player&&array[4][i-3]==player)&&(array[0][i+1]==0||array[5][i-4]==0))
			{
				if(array[0][i+1]==0)
				{
					point result=new point(0,i+1);
					end=1;
					return result;
				}
				else if(array[5][i-4]==0)
				{
					point result=new point(5,i-4);
					end=1;
					return result;
				}
			}
		}
		for(int i=4;i<6;i++)
		{
			if((array[2][i]==player&&array[3][i-1]==player&&array[4][i-2]==player&&array[5][i-3]==player)&&(array[1][i+1]==0||array[6][i-4]==0))
			{
				if(array[0][i+1]==0)
				{
					point result=new point(1,i+1);
					end=1;
					return result;
				}
				else if(array[5][i-4]==0)
				{
					point result=new point(6,i-4);
					end=1;
					return result;
				}
			}
		}
		//1:myself 4 points diagonal
		//***********************************************************************************
		for(int i=0;i<rowNumber;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==player&&array[i][j+1]==player&&array[i][j+2]==player&&array[i][j+4]==player&&array[i][j+3]==0)
				{
					point result=new point(i,j+3);
					end=1;
					return result;
				}
			}
		}
		for(int i=0;i<rowNumber;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==player&&array[i][j+1]==player&&array[i][j+3]==player&&array[i][j+4]==player&&array[i][j+2]==0)
				{
					point result=new point(i,j+2);
					end=1;
					return result;
				}
			}
		}
		for(int i=0;i<rowNumber;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==player&&array[i][j+2]==player&&array[i][j+3]==player&&array[i][j+4]==player&&array[i][j+1]==0)
				{
					point result=new point(i,j+1);
					end=1;
					return result;
				}
			}
		}

		for(int i=0;i<3;i++)
		{
			for(int j=0;j<colNumber;j++)
			{
				if(array[i][j]==player&&array[i+1][j]==player&&array[i+2][j]==player&&array[i+4][j]==player&&array[i+3][j]==0)
				{
					point result=new point(i+3,j);
					end=1;
					return result;
				}
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<colNumber;j++)
			{
				if(array[i][j]==player&&array[i+1][j]==player&&array[i+3][j]==player&&array[i+4][j]==player&&array[i+2][j]==0)
				{
					point result=new point(i+2,j);
					end=1;
					return result;
				}
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<colNumber;j++)
			{
				if(array[i][j]==player&&array[i+2][j]==player&&array[i+3][j]==player&&array[i+4][j]==player&&array[i+1][j]==0)
				{
					point result=new point(i+1,j);
					end=1;
					return result;
				}
			}
		}
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==player&&array[i+1][j+1]==player&&array[i+2][j+2]==player&&array[i+4][j+4]==player&&array[i+3][j+3]==0)
				{
					point result=new point(i+3,j+3);
					end=1;
					return result;
				}
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==player&&array[i+1][j+1]==player&&array[i+3][j+3]==player&&array[i+4][j+4]==player&&array[i+2][j+2]==0)
				{
					point result=new point(i+2,j+2);
					end=1;
					return result;
				}
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==player&&array[i+2][j+2]==player&&array[i+3][j+3]==player&&array[i+4][j+4]==player&&array[i+1][j+1]==0)
				{
					point result=new point(i+1,j+1);
					end=1;
					return result;
				}
			}
		}
		
		for(int i=0;i<3;i++)
		{
			for(int j=4;j<7;j++)
			{
				if(array[i][j]==player&&array[i+1][j-1]==player&&array[i+2][j-2]==player&&array[i+4][j-4]==player&&array[i+3][j-3]==0)
				{
					point result=new point(i+3,j-3);
					end=1;
					return result;
				}				
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=4;j<7;j++)
			{
				if(array[i][j]==player&&array[i+1][j-1]==player&&array[i+3][j-3]==player&&array[i+4][j-4]==player&&array[i+2][j-2]==0)
				{
					point result=new point(i+2,j-2);
					end=1;
					return result;
				}				
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=4;j<7;j++)
			{
				if(array[i][j]==player&&array[i+2][j-2]==player&&array[i+3][j-3]==player&&array[i+4][j-4]==player&&array[i+1][j-1]==0)
				{
					point result=new point(i+1,j-1);
					end=1;
					return result;
				}				
			}
		}
		//***********************************************************************************
		//2:enemy 4 points row
		for(int i=0;i<rowNumber;i++)
		{
			if(array[i][0]==3-player&&array[i][1]==3-player&&array[i][2]==3-player&&array[i][3]==3-player&&array[i][4]==0)
			{
				point result=new point(i,4);
				return result;
			}
		}

		for(int i=0;i<rowNumber;i++)
		{
			if(array[i][3]==3-player&&array[i][4]==3-player&&array[i][5]==3-player&&array[i][6]==3-player&&array[i][2]==0)
			{
				point result=new point(i,2);
				return result;
			}
		}
		
		for(int i=0;i<rowNumber;i++)
		{
			for(int j=1;j<3;j++)
			{
				if((array[i][j]==3-player&&array[i][j+1]==3-player&&array[i][j+2]==3-player&&array[i][j+3]==3-player)&&(array[i][j-1]==0||array[i][j+4]==0))
				{
					if(array[i][j-1]==0)
					{
						point result=new point(i,j-1);
						return result;
					}
					else if(array[i][j+4]==0)
					{
						point result=new point(i,j+4);
						return result;
					}
				}
			}
		}
		//2:enemy 4 points row
		//2:enemy 4 points column
		for(int j=0;j<colNumber;j++)
		{
			if(array[0][j]==3-player&&array[1][j]==3-player&&array[2][j]==3-player&&array[3][j]==3-player&&array[4][j]==0)
			{
				point result=new point(4,j);
				return result;
			}
		}

		for(int j=0;j<colNumber;j++)
		{
			if(array[3][j]==3-player&&array[4][j]==3-player&&array[5][j]==3-player&&array[6][j]==3-player&&array[2][j]==0)
			{
				point result=new point(2,j);
				return result;
			}
		}
		
		for(int j=0;j<colNumber;j++)
		{
			for(int i=1;i<3;i++)
			{
				if((array[i][j]==3-player&&array[i+1][j]==3-player&&array[i+2][j]==3-player&&array[i+3][j]==3-player)&&(array[i-1][j]==0||array[i+4][j]==0))
				{
					if(array[i+4][j]==0)
					{
						point result=new point(i+4,j);
						return result;
					}
					else if(array[i-1][j]==0)
					{
						point result=new point(i-1,j);
						return result;
					}
				}
			}
		}
		//2:enemy 4 points column
		//2:enemy 4 points diagonal
		for(int i=0;i<3;i++)//left 4
		{
			if(array[i][0]==3-player&&array[i+1][1]==3-player&&array[i+2][2]==3-player&&array[i+3][3]==3-player&&array[i+4][4]==0)
			{
				point result=new point(i+4,4);
				return result;
			}
		}
		for(int i=1;i<3;i++)
		{
			if(array[0][i]==3-player&&array[1][i+1]==3-player&&array[2][i+2]==3-player&&array[3][i+3]==3-player&&array[4][i+4]==0)
			{
				point result=new point(4,i+4);
				return result;
			}
		}
		for(int i=1;i<4;i++)
		{
			if(array[i][3]==3-player&&array[i+1][4]==3-player&&array[i+2][5]==3-player&&array[i+3][6]==3-player&&array[i-1][2]==0)
			{
				point result=new point(i-1,2);
				return result;
			}
		}
		for(int i=1;i<3;i++)
		{
			if(array[3][i]==3-player&&array[4][i+1]==3-player&&array[5][i+2]==3-player&&array[6][i+3]==3-player&&array[2][i-1]==0)
			{
				point result=new point(2,i-1);
				return result;
			}
		}
		for(int i=1;i<4;i++)
		{
			if(array[i][3]==3-player&&array[i+1][4]==3-player&&array[i+2][5]==3-player&&array[i+3][6]==3-player&&array[i-1][2]==0)
			{
				point result=new point(i-1,2);
				return result;
			}
		}
		for(int i=1;i<3;i++)
		{
			if((array[1][i]==3-player&&array[2][i+1]==3-player&&array[3][i+2]==3-player&&array[4][i+3]==3-player)&&(array[0][i-1]==0||array[5][i+4]==0))
			{
				if(array[0][i-1]==0)
				{
					point result=new point(0,i-1);
					return result;
				}
				else if(array[5][i+4]==0)
				{
					point result=new point(5,i+4);
					return result;
				}
			}		
		}
		for(int i=1;i<3;i++)
		{
			if((array[2][i]==3-player&&array[3][i+1]==3-player&&array[4][i+2]==3-player&&array[5][i+3]==3-player)&&(array[1][i-1]==0||array[6][i+4]==0))
			{
				if(array[1][i-1]==0)
				{
					point result=new point(1,i-1);
					return result;
				}
				else if(array[6][i+4]==0)
				{
					point result=new point(6,i+4);
					return result;
				}
			}		
		}
		for(int i=6;i>3;i--)
		{
			if(array[0][i]==3-player&&array[1][i-1]==3-player&&array[2][i-2]==3-player&&array[3][i-3]==3-player&&array[4][i-4]==0)
			{
				point result=new point(4,i-4);
				return result;
			}
		}
		for(int i=1;i<3;i++)
		{
			if(array[i][6]==3-player&&array[i+1][5]==3-player&&array[i+2][4]==3-player&&array[i+3][3]==3-player&&array[i+4][2]==0)
			{
				point result=new point(i+4,2);
				return result;
			}
		}
		for(int i=1;i<4;i++)
		{
			if(array[i][3]==3-player&&array[i+1][2]==3-player&&array[i+2][1]==3-player&&array[i+3][0]==3-player&&array[i-1][4]==0)
			{
				point result=new point(i-1,4);
				return result;
			}
		}
		for(int i=4;i<6;i++)
		{
			if(array[3][i]==3-player&&array[4][i-1]==3-player&&array[5][i-2]==3-player&&array[6][i-3]==3-player&&array[2][i+1]==0)
			{
				point result=new point(2,i+1);
				return result;
			}
		}
		for(int i=4;i<6;i++)
		{
			if((array[1][i]==3-player&&array[2][i-1]==3-player&&array[3][i-2]==3-player&&array[4][i-3]==3-player)&&(array[0][i+1]==0||array[5][i-4]==0))
			{
				if(array[0][i+1]==0)
				{
					point result=new point(0,i+1);
					return result;
				}
				else if(array[5][i-4]==0)
				{
					point result=new point(5,i-4);
					return result;
				}
			}
		}
		for(int i=4;i<6;i++)
		{
			if((array[2][i]==3-player&&array[3][i-1]==3-player&&array[4][i-2]==3-player&&array[5][i-3]==3-player)&&(array[1][i+1]==0||array[6][i-4]==0))
			{
				if(array[0][i+1]==0)
				{
					point result=new point(1,i+1);
					return result;
				}
				else if(array[5][i-4]==0)
				{
					point result=new point(6,i-4);
					return result;
				}
			}
		}
		//2:enemy 4 points diagonal
		//*********************************************************************
		for(int i=0;i<rowNumber;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==3-player&&array[i][j+1]==3-player&&array[i][j+2]==3-player&&array[i][j+4]==3-player&&array[i][j+3]==0)
				{
					point result=new point(i,j+3);
					return result;
				}
			}
		}
		for(int i=0;i<rowNumber;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==3-player&&array[i][j+1]==3-player&&array[i][j+3]==3-player&&array[i][j+4]==3-player&&array[i][j+2]==0)
				{
					point result=new point(i,j+2);
					return result;
				}
			}
		}
		for(int i=0;i<rowNumber;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==3-player&&array[i][j+2]==3-player&&array[i][j+3]==3-player&&array[i][j+4]==3-player&&array[i][j+1]==0)
				{
					point result=new point(i,j+1);
					return result;
				}
			}
		}

		for(int i=0;i<3;i++)
		{
			for(int j=0;j<colNumber;j++)
			{
				if(array[i][j]==3-player&&array[i+1][j]==3-player&&array[i+2][j]==3-player&&array[i+4][j]==3-player&&array[i+3][j]==0)
				{
					point result=new point(i+3,j);
					return result;
				}
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<colNumber;j++)
			{
				if(array[i][j]==3-player&&array[i+1][j]==3-player&&array[i+3][j]==3-player&&array[i+4][j]==3-player&&array[i+2][j]==0)
				{
					point result=new point(i+2,j);
					return result;
				}
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<colNumber;j++)
			{
				if(array[i][j]==3-player&&array[i+2][j]==3-player&&array[i+3][j]==3-player&&array[i+4][j]==3-player&&array[i+1][j]==0)
				{
					point result=new point(i+1,j);
					return result;
				}
			}
		}
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==3-player&&array[i+1][j+1]==3-player&&array[i+2][j+2]==3-player&&array[i+4][j+4]==3-player&&array[i+3][j+3]==0)
				{
					point result=new point(i+3,j+3);
					return result;
				}
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==3-player&&array[i+1][j+1]==3-player&&array[i+3][j+3]==3-player&&array[i+4][j+4]==3-player&&array[i+2][j+2]==0)
				{
					point result=new point(i+2,j+2);
					return result;
				}
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==3-player&&array[i+2][j+2]==3-player&&array[i+3][j+3]==3-player&&array[i+4][j+4]==3-player&&array[i+1][j+1]==0)
				{
					point result=new point(i+1,j+1);
					return result;
				}
			}
		}
		
		for(int i=0;i<3;i++)
		{
			for(int j=4;j<7;j++)
			{
				if(array[i][j]==3-player&&array[i+1][j-1]==3-player&&array[i+2][j-2]==3-player&&array[i+4][j-4]==3-player&&array[i+3][j-3]==0)
				{
					point result=new point(i+3,j-3);
					return result;
				}				
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=4;j<7;j++)
			{
				if(array[i][j]==3-player&&array[i+1][j-1]==3-player&&array[i+3][j-3]==3-player&&array[i+4][j-4]==3-player&&array[i+2][j-2]==0)
				{
					point result=new point(i+2,j-2);
					return result;
				}				
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=4;j<7;j++)
			{
				if(array[i][j]==3-player&&array[i+2][j-2]==3-player&&array[i+3][j-3]==3-player&&array[i+4][j-4]==3-player&&array[i+1][j-1]==0)
				{
					point result=new point(i+1,j-1);
					return result;
				}				
			}
		}
		//*********************************************************************
		//3:enemy 3 points row
		for(int i=0;i<rowNumber;i++)
		{
			for(int j=1;j<4;j++)
			{
				if(array[i][j]==3-player&&array[i][j+1]==3-player&&array[i][j+2]==3-player&&array[i][j-1]==0&&array[i][j+3]==0)
				{
					point result=new point(i,j-1);
					return result;
				}
			}
		}
		//3:enemy 3 points row
		//3:enemy 3 points column
		for(int i=1;i<4;i++)
		{
			for(int j=0;j<colNumber;j++)
			{
				if(array[i][j]==3-player&&array[i+1][j]==3-player&&array[i+2][j]==3-player&&array[i-1][j]==0&&array[i+3][j]==0)
				{
					point result=new point(i-1,j);
					return result;
				}
			}
		}
		//3:enemy 3 points column
		//3:enemy 3 points diagonal
		for(int i=1;i<4;i++)
		{
			for(int j=1;j<4;j++)
			{
				if(array[i][j]==3-player&&array[i+1][j+1]==3-player&&array[i+2][j+2]==3-player&&array[i-1][j-1]==0&&array[i+3][j+3]==0)
				{
					point result=new point(i-1,j-1);
					return result;
				}
			}
		}
		for(int i=1;i<4;i++)
		{
			for(int j=3;j<6;j++)
			{
				if(array[i][j]==3-player&&array[i+1][j-1]==3-player&&array[i+2][j-2]==3-player&&array[i-1][j+1]==0&&array[i+3][j-3]==0)
				{
					point result=new point(i-1,j+1);
					return result;
				}				
			}
		}
		//3:enemy 3 points diagonal
		//4:myself best moves
		point minPoint1=new point(0,0);
		int countNoneNeighbor1=0;
		int minNoneNeighbor1=10000;
		if(player!=1)
		{
			for(int i=0;i<rowNumber;i++)
			{
				for(int j=0;j<3;j++)
				{
					countNoneNeighbor1=0;
					if(array[i][j]!=3-player&&array[i][j+1]!=3-player&&array[i][j+2]!=3-player&&array[i][j+3]!=3-player&&array[i][j+4]!=3-player)
					{
						if(array[i][j]==0)
						{
							countNoneNeighbor1++;
						}
						else if(array[i][j+1]==0)
						{
							countNoneNeighbor1++;
						}
						else if(array[i][j+2]==0)
						{
							countNoneNeighbor1++;
						}
						else if(array[i][j+3]==0)
						{
							countNoneNeighbor1++;
						}
						else if(array[i][j+4]==0)
						{
							countNoneNeighbor1++;
						}
					}
					if(countNoneNeighbor1<=minNoneNeighbor1)
					{
						minNoneNeighbor1=countNoneNeighbor1;
						minPoint1.setRow(i);
						minPoint1.setCol(j);
					}
				}
			}
			point minPoint2=new point(0,0);
			int countNoneNeighbor2=0;
			int minNoneNeighbor2=10000;

			for(int i=0;i<3;i++)
			{
				for(int j=0;j<colNumber;j++)
				{
					countNoneNeighbor2=0;
					if(array[i][j]!=3-player&&array[i+1][j]!=3-player&&array[i+2][j]!=3-player&&array[i+3][j]!=3-player&&array[i+4][j]!=3-player)
					{
						if(array[i][j]==0)
						{
							countNoneNeighbor2++;
						}
						else if(array[i+1][j]==0)
						{
							countNoneNeighbor2++;
						}
						else if(array[i+2][j]==0)
						{
							countNoneNeighbor2++;
						}
						else if(array[i+3][j]==0)
						{
							countNoneNeighbor2++;
						}
						else if(array[i+4][j]==0)
						{
							countNoneNeighbor2++;
						}
					}				
					if(countNoneNeighbor2<=minNoneNeighbor2)
					{
						minNoneNeighbor2=countNoneNeighbor2;
						minPoint1.setRow(i);
						minPoint1.setCol(j);
					}
				}
			}
			point minPoint3=new point(0,0);
			int countNoneNeighbor3=0;
			int minNoneNeighbor3=10000;
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					countNoneNeighbor3=0;
					if(array[i][j]!=3-player&&array[i+1][j+1]!=3-player&&array[i+2][j+2]!=3-player&&array[i+3][j+3]!=3-player&&array[i+4][j+4]!=3-player)
					{
						if(array[i][j]==0)
						{
							countNoneNeighbor3++;
						}
						else if(array[i+1][j+1]==0)
						{
							countNoneNeighbor3++;
						}
						else if(array[i+2][j+2]==0)
						{
							countNoneNeighbor3++;
						}
						else if(array[i+3][j+3]==0)
						{
							countNoneNeighbor3++;
						}
						else if(array[i+4][j+4]==0)
						{
							countNoneNeighbor3++;
						}
						if(countNoneNeighbor3<=minNoneNeighbor3)
						{
							minNoneNeighbor3=countNoneNeighbor3;
							minPoint3.setRow(i);
							minPoint3.setCol(j);
						}
					}
				}
			}
			point minPoint4=new point(0,0);
			int countNoneNeighbor4=0;
			int minNoneNeighbor4=10000;
			for(int i=0;i<3;i++)
			{
				for(int j=4;j<7;j++)
				{
					countNoneNeighbor4=0;
					if(array[i][j]!=3-player&&array[i+1][j-1]!=3-player&&array[i+2][j-2]!=3-player&&array[i+3][j-3]!=3-player&&array[i+4][j-4]!=3-player)
					{
						if(array[i][j]==0)
						{
							countNoneNeighbor4++;
						}
						else if(array[i+1][j-1]==0)
						{
							countNoneNeighbor4++;
						}
						else if(array[i+2][j-2]==0)
						{
							countNoneNeighbor4++;
						}
						else if(array[i+3][j-3]==0)
						{
							countNoneNeighbor4++;
						}
						else if(array[i+4][j-4]==0)
						{
							countNoneNeighbor4++;
						}
						if(countNoneNeighbor4<=minNoneNeighbor4)
						{
							minNoneNeighbor4=countNoneNeighbor4;
							minPoint4.setRow(i);
							minPoint4.setCol(j);
						}
					}
				}
			}
			point minPoint=new point(0,0);
			int countNoneNeighbor=0;
			int choice=0;
			int minNoneNeighbor=10000;
			
			if(minNoneNeighbor1<=minNoneNeighbor)
			{
				minNoneNeighbor=minNoneNeighbor1;
				minPoint.setRow(minPoint1.getRow());
				minPoint.setCol(minPoint1.getCol());
				choice=1;
			}
			if(minNoneNeighbor2<=minNoneNeighbor)
			{
				minNoneNeighbor=minNoneNeighbor2;
				minPoint.setRow(minPoint2.getRow());
				minPoint.setCol(minPoint2.getCol());
				choice=2;
			}
			if(minNoneNeighbor3<=minNoneNeighbor)
			{
				minNoneNeighbor=minNoneNeighbor3;
				minPoint.setRow(minPoint3.getRow());
				minPoint.setCol(minPoint3.getCol());
				choice=3;
			}
			if(minNoneNeighbor4<=minNoneNeighbor)
			{
				minNoneNeighbor=minNoneNeighbor4;
				minPoint.setRow(minPoint4.getRow());
				minPoint.setCol(minPoint4.getCol());
				choice=4;
			}

			if(minNoneNeighbor!=10000)
			{
				if(choice==1)
				{
					if(array[minPoint.getRow()][minPoint.getCol()]==0)
					{
						point result=new point(minPoint.getRow(),minPoint.getCol());
						return result;
					}
					else if(array[minPoint.getRow()][minPoint.getCol()+1]==0)
					{
						point result=new point(minPoint.getRow(),minPoint.getCol()+1);
						return result;
					}
					else if(array[minPoint.getRow()][minPoint.getCol()+2]==0)
					{
						point result=new point(minPoint.getRow(),minPoint.getCol()+2);
						return result;
					}
					else if(array[minPoint.getRow()][minPoint.getCol()+3]==0)
					{
						point result=new point(minPoint.getRow(),minPoint.getCol()+3);
						return result;
					}
					else if(array[minPoint.getRow()][minPoint.getCol()+4]==0)
					{
						point result=new point(minPoint.getRow(),minPoint.getCol()+4);
						return result;
					}
				}
				else if(choice==2)
				{
					if(array[minPoint.getRow()][minPoint.getCol()]==0)
					{
						point result=new point(minPoint.getRow(),minPoint.getCol());
						return result;
					}
					else if(array[minPoint.getRow()+1][minPoint.getCol()]==0)
					{
						point result=new point(minPoint.getRow()+1,minPoint.getCol());
						return result;
					}
					else if(array[minPoint.getRow()+2][minPoint.getCol()]==0)
					{
						point result=new point(minPoint.getRow()+2,minPoint.getCol());
						return result;
					}
					else if(array[minPoint.getRow()+3][minPoint.getCol()]==0)
					{
						point result=new point(minPoint.getRow()+3,minPoint.getCol());
						return result;
					}
					else if(array[minPoint.getRow()+4][minPoint.getCol()]==0)
					{
						point result=new point(minPoint.getRow()+4,minPoint.getCol());
						return result;
					}
				}
				else if(choice==3)
				{
					if(array[minPoint.getRow()][minPoint.getCol()]==0)
					{
						point result=new point(minPoint.getRow(),minPoint.getCol());
					}
					else if(array[minPoint.getRow()+1][minPoint.getCol()+1]==0)
					{
						point result=new point(minPoint.getRow()+1,minPoint.getCol()+1);
					}
					else if(array[minPoint.getRow()+2][minPoint.getCol()+2]==0)
					{
						point result=new point(minPoint.getRow()+2,minPoint.getCol()+2);
					}
					else if(array[minPoint.getRow()+3][minPoint.getCol()+3]==0)
					{
						point result=new point(minPoint.getRow()+3,minPoint.getCol()+3);
					}
					else if(array[minPoint.getRow()+4][minPoint.getCol()+4]==0)
					{
						point result=new point(minPoint.getRow()+4,minPoint.getCol()+4);
					}
				}
				else if(choice==4)
				{
					if(array[minPoint.getRow()][minPoint.getCol()]==0)
					{
						point result=new point(minPoint.getRow(),minPoint.getCol());
					}
					else if(array[minPoint.getRow()+1][minPoint.getCol()-1]==0)
					{
						point result=new point(minPoint.getRow()+1,minPoint.getCol()-1);
					}
					else if(array[minPoint.getRow()+2][minPoint.getCol()-2]==0)
					{
						point result=new point(minPoint.getRow()+2,minPoint.getCol()-2);
					}
					else if(array[minPoint.getRow()+3][minPoint.getCol()-3]==0)
					{
						point result=new point(minPoint.getRow()+3,minPoint.getCol()-3);
					}
					else if(array[minPoint.getRow()+4][minPoint.getCol()-4]==0)
					{
						point result=new point(minPoint.getRow()+4,minPoint.getCol()-4);
					}				
				}
			}
			
			for(int i=0;i<3;i++)
			{
				for(int j=4;j<7;j++)
				{
					if(array[i][j]!=3-player&&array[i+1][j-1]!=3-player&&array[i+2][j-2]!=3-player&&array[i+3][j-3]!=3-player&&array[i+4][j-4]!=3-player)
					{
						if(array[i][j]==0)
						{
							point result=new point(i,j);
							return result;
						}
						else if(array[i+1][j-1]==0)
						{
							point result=new point(i+1,j-1);
							return result;
						}
						else if(array[i+2][j-2]==0)
						{
							point result=new point(i+2,j-2);
							return result;
						}
						else if(array[i+3][j-3]==0)
						{
							point result=new point(i+3,j-3);
							return result;
						}
						else if(array[i+4][j-4]==0)
						{
							point result=new point(i+4,j-4);
							return result;
						}
						
					}
				}
			}
		}
		else if(player==1)
		{
			point result=minimax(0,0,1);
			return result;
		}
		//4:myself best moves
		if(player!=1)
		{
			Random rand=new Random();
			while(array[answer.getRow()][answer.getCol()]!=0)
			{
				int row=rand.nextInt(7);
				int col=rand.nextInt(7);
				answer.setRow(row);
				answer.setCol(col);
			}
			return answer;
		}
		else
		{
			answer=minimax(0,0,1);
			return answer;
		}
	}
	public void checkEnd()
	{
		//Red wins
		for(int i=0;i<rowNumber;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==1&&array[i][j+1]==1&&array[i][j+2]==1&&array[i][j+3]==1&&array[i][j+4]==1)
				{
					end=1;
					System.out.println("Red wins");
					plotGraph.flagKeyin=2;
				}
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<colNumber;j++)
			{
				if(array[i][j]==1&&array[i+1][j]==1&&array[i+2][j]==1&&array[i+3][j]==1&&array[i+4][j]==1)
				{
					end=1;
					System.out.println("Red wins");
					plotGraph.flagKeyin=2;
				}				
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==1&&array[i+1][j+1]==1&&array[i+2][j+2]==1&&array[i+3][j+3]==1&&array[i+4][j+4]==1)
				{
					end=1;
					System.out.println("Red wins");
					plotGraph.flagKeyin=2;
				}
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=4;j<7;j++)
			{
				if(array[i][j]==1&&array[i+1][j-1]==1&&array[i+2][j-2]==1&&array[i+3][j-3]==1&&array[i+4][j-4]==1)
				{
					end=1;
					System.out.println("Red wins");
					plotGraph.flagKeyin=2;
				}
			}
		}
		//Red wins
		//Blue wins
		for(int i=0;i<rowNumber;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==2&&array[i][j+1]==2&&array[i][j+2]==2&&array[i][j+3]==2&&array[i][j+4]==2)
				{
					end=1;
					System.out.println("Blue wins");
					plotGraph.flagKeyin=3;
				}
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<colNumber;j++)
			{
				if(array[i][j]==2&&array[i+1][j]==2&&array[i+2][j]==2&&array[i+3][j]==2&&array[i+4][j]==2)
				{
					end=1;
					System.out.println("Blue wins");
					plotGraph.flagKeyin=3;
				}				
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(array[i][j]==2&&array[i+1][j+1]==2&&array[i+2][j+2]==2&&array[i+3][j+3]==2&&array[i+4][j+4]==2)
				{
					end=1;
					System.out.println("Blue wins");
					plotGraph.flagKeyin=3;
				}
			}
		}
		for(int i=0;i<3;i++)
		{
			for(int j=4;j<7;j++)
			{
				if(array[i][j]==2&&array[i+1][j-1]==2&&array[i+2][j-2]==2&&array[i+3][j-3]==2&&array[i+4][j-4]==2)
				{
					end=1;
					System.out.println("Blue wins");
					plotGraph.flagKeyin=3;
				}
			}
		}
		//Blue wins
	}
	public int evaluationFunction(int[][] array,int player)
	{	
		int evaluation=0;
		int i=0;
		double result=0;
		
		for(int r=0;r<7;r++)
		{
			for(int c=0;c<7;c++)
			{
				for(int k=0;k<3;k++)
				{
					result=readWeight.weights[r]*array[r][c];
					i++;
				}
			}
		}
		result=result*10000000;
		evaluation=(int)result;
		
		return evaluation;
	}
	public point minimax(int rowNow,int colNow,int player)
	{
		int evaluation1=100000000;
		int evaluation2=100000000;
		int evaluation3=100000000;
		//int min1=100000000;
		//int max2=0;
		//int min3=100000000;
		int max1=0;
		int min2=100000000;
		int max3=0;
		point final1=new point(0,0);
		point final2=new point(0,0);
		point final3=new point(0,0);
		GomokuState currentState=new GomokuState(array,rowNumber,colNumber,rowNow,colNow,player);
		for(Iterator<point> it=currentState.emptyLocations.iterator();it.hasNext();)
		{
			//System.out.print("Empty:");
			//System.out.println(currentState.emptyLocations.size());
			
			point emptyTemp=it.next();
			//System.out.print(emptyTemp.getRow());
			//System.out.print(",");
			//System.out.println(emptyTemp.getCol());
			
			GomokuState state1=new GomokuState(array,rowNumber,colNumber,rowNow,colNow,player);
			state1.moving(player,emptyTemp.getRow(),emptyTemp.getCol());
			min2=100000000;
			
			//stochastic start
			int N=3;
			int index=0;
			int firstSize=state1.emptyLocations.size();
			if(firstSize==0)
			{
				firstSize=firstSize+1;
			}
			if(N>state1.emptyLocations.size())
			{
				N=state1.emptyLocations.size();
			}
			int firstItem=new Random().nextInt(firstSize);
			Set<point> emptyFirst;
			emptyFirst=new HashSet<point>();
			for(point item:state1.emptyLocations)
			{
				int flag=0;
				for(Iterator<point> itMore=emptyFirst.iterator();itMore.hasNext();)
				{
					point tempItem=itMore.next();
					if(item.getRow()==tempItem.getRow()&&item.getCol()==tempItem.getCol())
					{
						flag=1;
					}
				}
				emptyFirst.add(item);
				firstItem=new Random().nextInt(firstSize);
				if(index>=N)
				{
					break;
				}
				if(flag==0)
				{
					index=index+1;
				}
			}
			
			for(Iterator<point> itMiddle=emptyFirst.iterator();itMiddle.hasNext();)
			{
				//System.out.print("Empty:");
				//System.out.println(state1.emptyLocations.size());
		
				point middleTemp=itMiddle.next();

				//System.out.print(middleTemp.getRow());
				//System.out.print(",");
				//System.out.println(middleTemp.getCol());
				
				GomokuState state2=new GomokuState(array,rowNumber,colNumber,rowNow,colNow,player);
				state2.moving(player,emptyTemp.getRow(),emptyTemp.getCol());
				state2.moving(3-player,middleTemp.getRow(),middleTemp.getCol());
				//evaluation2=evaluationFunction(state2.array,player);
				max1=0;

				int NMiddle=3;
				int indexMiddle=0;
				int secondSize=state2.emptyLocations.size();
				if(secondSize==0)
				{
					secondSize=secondSize+1;
				}
				if(NMiddle>state2.emptyLocations.size())
				{
					N=state2.emptyLocations.size();
				}
				int secondItem=new Random().nextInt(secondSize);
				Set<point> emptySecond;
				emptySecond=new HashSet<point>();
				for(point item:state2.emptyLocations)
				{
					int flag=0;
					for(Iterator<point> itFinal=emptyFirst.iterator();itFinal.hasNext();)
					{
						point tempItem=itFinal.next();
						if(item.getRow()==tempItem.getRow()&&item.getCol()==tempItem.getCol())
						{
							flag=1;
						}
					}
					emptySecond.add(item);
					secondItem=new Random().nextInt(secondSize);
					if(indexMiddle>=NMiddle)
					{
						break;
					}
					if(flag==0)
					{
						indexMiddle=indexMiddle+1;
					}
				}

				for(Iterator<point> itLast=emptySecond.iterator();itLast.hasNext();)
				{
					//System.out.print("Empty:");
					//System.out.println(state2.emptyLocations.size());
		
					point lastTemp=itLast.next();
					GomokuState state3=new GomokuState(array,rowNumber,colNumber,rowNow,colNow,player);
					state3.moving(player,emptyTemp.getRow(),emptyTemp.getCol());
					state3.moving(3-player,middleTemp.getRow(),middleTemp.getCol());
					state3.moving(player,lastTemp.getRow(),lastTemp.getCol());
					evaluation1=evaluationFunction(state3.array,player);
					//System.out.println(evaluation1);
					/*
					try
					{
						Thread.sleep(500);
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
					
					plotGomoku=new plotGraph(state3.array,rowNumber,colNumber); 
					*/
					if(evaluation1>=max1)
					{
						max1=evaluation1;
						final1=new point(lastTemp.getRow(),lastTemp.getCol());
						/*
						System.out.print("Max1:");
						System.out.println(max1);
						System.out.print(lastTemp.getRow());
						System.out.print(",");
						System.out.println(lastTemp.getCol());
						System.out.print("Min2:");
						System.out.println(min2);
						*/
					}
				}
				if(max1<=min2)
				{
					min2=max1;
					if(min2<=max3)
					{
						continue;
					}
					final2=new point(middleTemp.getRow(),middleTemp.getCol());
					//System.out.print("Min2:");
					//System.out.println(min2);
				}
			}
			if(max3<=min2)
			{
				max3=min2;
				final3=new point(emptyTemp.getRow(),emptyTemp.getCol());
				//System.out.print("Max3:");
				//System.out.println(max3);
			}
		}
		return final3;
	}
}