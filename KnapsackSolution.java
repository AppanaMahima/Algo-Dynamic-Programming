import java.util.*;

public class KnapsackSolution implements java.io.Closeable
{
	public boolean [] isTaken;
	public int value;
	public int weight;
	public int takenweight;
	public int takenvalue;
	public int wght;
	private KnapsackInstance inst;	
	public int sumoftotalval;
	public int sumofuntakenval;
	public int itemCnt;
	public KnapsackSolution(KnapsackInstance inst_)
	{
		//int i;
		itemCnt = inst_.GetItemCnt();
    
		inst = inst_;
		isTaken = new boolean[itemCnt + 1];
		value = DefineConstants.INVALID_VALUE;
		//int remainingCap;
		//int capa=inst.GetCapacity();
		for (int i = 1; i <= itemCnt; i++)
		{
			isTaken[i] = false;
			sumoftotalval += inst.GetItemValue(i);
		}
		
	}
	public void close()
	{
		isTaken = null;
	}

	public void TakeItem(int itemNum)
	{		
		takenvalue += inst.GetItemValue(itemNum);
		takenweight += inst.GetItemWeight(itemNum);
		isTaken[itemNum] = true;
	}
	public void DontTakeItem(int itemNum)
	{		
		sumofuntakenval +=inst.GetItemValue(itemNum) ;
		isTaken[itemNum] = false;
	}

	public void UndoDontTake(int itemNum){
		sumofuntakenval -= inst.GetItemValue(itemNum);
	}
	public void UndoTake(int itemNum){
		takenweight -= inst.GetItemWeight(itemNum);
		takenvalue -= inst.GetItemValue(itemNum);
	}

	// public void BBDontTakeItem(int itemNum)
	// {
	// 	isTaken[itemNum] = false;
	// 	sumofuntakenval += inst.GetItemValue(itemNum); 
	// }

	// public void BBTakeItem(int itemNum){
	// 	isTaken[itemNum] = true;
	// 	takenvalue += inst.GetItemValue(itemNum);
	// 	takenweight += inst.GetItemWeight(itemNum);
	// }

	

	public int ComputeValue()
	{
		int i;
		int itemCnt = inst.GetItemCnt();
    
		value=0;
		weight=0;
		for (i = 1; i <= itemCnt; i++)
		{
			if (isTaken[i] == true)
			{
				weight += inst.GetItemWeight(i);
				wght=weight;
				takenweight=weight;
				if (weight > inst.GetCapacity())
				{
					value = DefineConstants.INVALID_VALUE;
					break;
				}
				value += inst.GetItemValue(i);
				takenvalue=value;
			}
		}
		return value;
	}
	
	public int GetValue()
	{
		return value;
	}
	public void Print(String title)
	{
		int i;
		int itemCnt = inst.GetItemCnt();
    
		System.out.printf("\n%s: ",title);
		for (i = 1; i <= itemCnt; i++)
		{
			if (isTaken[i] == true)
			{
				System.out.printf("%d ",i);
			}
		}
		System.out.printf("\nValue = %d\n",value);
    
	}
	public void Copy(KnapsackSolution otherSoln)
	{
		int i;
		int itemCnt = inst.GetItemCnt();
    
		for (i = 1; i <= itemCnt; i++)
		{
			isTaken[i] = otherSoln.isTaken[i];
		}
		value = otherSoln.value;
	}
	public boolean equalsTo (KnapsackSolution otherSoln)
	{
		return value == otherSoln.value;
	}
	
	public void dispose()
	{
		isTaken = null;
	}	
}