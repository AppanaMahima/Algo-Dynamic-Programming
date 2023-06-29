import java.util.*;

// Backtracking solver
public class KnapsackBTSolver extends KnapsackBFSolver
{
	//private static 
	public KnapsackSolution crntSolnbt;
	public KnapsackSolution bestSolnbt;
	public KnapsackInstance instbt;
	public int takenweight;
	public int crntVal;
	//int takenweight=btclass.takenweight;

	public void FindSolnsBT(int itemNum)
	{		
		KnapsackBTSolver btclass=new KnapsackBTSolver();
		btclass.inst=instbt;
		btclass.crntSoln=crntSolnbt;
		btclass.bestSoln=bestSolnbt;
		int itemCntbt = instbt.GetItemCnt();
		if(crntSolnbt.takenweight>instbt.GetCapacity()){
			return;
		}
		//btclass.FindSolns(itemNum);    
		if (itemNum == itemCntbt + 1)
		{
			btclass.CheckCrntSoln();
			return;
		}
		// crntSolnbt.DontTakeItem(itemNum);
		// FindSolnsBT(itemNum + 1);
		// crntSolnbt.UndoDontTake(itemNum);
		// crntSolnbt.TakeItem(itemNum);
		// FindSolnsBT(itemNum + 1);
		// crntSolnbt.UndoTake(itemNum);
		crntSolnbt.DontTakeItem(itemNum);
		FindSolnsBT(itemNum + 1);
		crntSolnbt.UndoDontTake(itemNum);
		crntSolnbt.TakeItem(itemNum);
		FindSolnsBT(itemNum + 1);
		crntSolnbt.UndoTake(itemNum);
				
	}

	public KnapsackBTSolver()
	{
		crntSolnbt = null;
	}
	public void close()
	{
		if (crntSolnbt != null)
		{
			crntSolnbt = null;
		}
	}
	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		instbt = inst_;
		bestSolnbt = soln_;
		crntSolnbt = new KnapsackSolution(instbt);
		FindSolnsBT(1);    
	}
}