import java.util.*;

// Dynamic programming solver
public class KnapsackDPSolver implements java.io.Closeable
{
	protected KnapsackInstance inst;
	protected KnapsackSolution soln;
	protected KnapsackSolution bestsoln;

	public void DPKnapsack(KnapsackInstance inst){		
		int cap=inst.GetCapacity();
		int itemcount=inst.GetItemCnt();		
		int[][] knapsacktable= new int[itemcount+1][cap+1];
		int j_cap=cap;

		for(int j=0;j<=cap;j++){
			knapsacktable[0][j]=0;
		}
		for(int i=1;i<=itemcount;i++){
			knapsacktable[i][0]=0;
			for(int j=1;j<=cap;j++){
				if(inst.GetItemWeight(i)>j){
					knapsacktable[i][j]=knapsacktable[i-1][j];
				}
				else{
					knapsacktable[i][j]=java.lang.Math.max(inst.GetItemValue(i)+knapsacktable[i-1][j-inst.GetItemWeight(i)],knapsacktable[i-1][j]);
				}
			}
		}

		for(int i=itemcount;i>0;i--){			
			if(knapsacktable[i][j_cap]>knapsacktable[i-1][j_cap]){
				soln.TakeItem(i);
				soln.ComputeValue();
				j_cap=j_cap-inst.GetItemWeight(i);
			}
		}

	}

	public KnapsackDPSolver()
	{
		//soln = null;
	}
	public void close()
	{
		// if (soln != null)
		// {
		// 	soln = null;
		// }
	}
	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		inst = inst_;
		soln = soln_;
		DPKnapsack(inst);
	}
}