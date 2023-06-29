import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

// Branch-and-Bound solver
public class KnapsackBBSolver extends KnapsackBFSolver
{
	protected UPPER_BOUND ub;
	protected static KnapsackInstance instbb;
	protected static KnapsackSolution crntSolnbb;
	protected KnapsackSolution bestSolnbb;
	// UPPER_BOUND UB1 = UPPER_BOUND.UB1; 
	// UPPER_BOUND UB2 = UPPER_BOUND.UB2; 
	// UPPER_BOUND UB3 = UPPER_BOUND.UB3; 
	//protected KnapsackInstance inst;
	//protected KnapsackSolution solution;
	// static int sumoftotalval = 0;
	// static int sumofuntakenval=0;
	static int sumofundecidedfitval=0;
	static int takenvaluesum;	
	protected int[] indices;
	public int valuebb;
	public int weightbb;
	public int indexbb;
	protected int remCapa;
	public int[] allvalues;
	public int[] allweights;

	// public KnapsackBBSolver(int value, int weight, int index) {
	// 	this.valuebb = value;
	// 	this.weightbb = weight;
	// 	this.indexbb = index;
	// }
	
	public void FindBBSolns(int itemNum,UPPER_BOUND ub)
	{
		
		//indices=new int[allvalues.length];
		// for (int i = 0; i < allweights.length; i++) {
		// 	indices[i] = i;
		// }
		
		KnapsackBBSolver bbclass=new KnapsackBBSolver(ub);
		int itemCnt = instbb.GetItemCnt();	
		int capa=instbb.GetCapacity();			
		
		bbclass.inst=instbb;
		bbclass.crntSoln=crntSolnbb;
		bbclass.bestSoln=bestSolnbb;				
		int ubname=ub.ordinal();	
		// int itemCnt = instbb.GetItemCnt();	
		// int capa=instbb.GetCapacity();	
		//int condition;
		//KnapsackBFSolver bfclass=new KnapsackBFSolver();
		//int weight = 0;    		
		int remainingCap;
		//takenvaluesum=bestSolnbb.ComputeValue();

		if(ubname==0){
			// for (int i = 1; i <= itemCnt; i++)
			// {
			// 	sumoftotalval += instbb.GetItemValue(i);
			// 	if (bestSolnbb.isTaken[i] == false)
			// 	{
			// 		sumofuntakenval += crntSolnbb.GetValue();
			// 	}
			// }						
			 if (itemNum == itemCnt + 1)
			{
				bbclass.CheckCrntSoln();
				return;			
			}	
			if(crntSolnbb.sumoftotalval-crntSolnbb.sumofuntakenval<=bestSolnbb.GetValue()){
				return;
			} 
			crntSolnbb.DontTakeItem(itemNum);
			FindBBSolns(itemNum + 1,ub);
			crntSolnbb.UndoDontTake(itemNum);
			crntSolnbb.TakeItem(itemNum);
			FindBBSolns(itemNum + 1,ub);
			crntSolnbb.UndoTake(itemNum);
			// crntSolnbb.TakeItem(itemNum);
			// FindBBSolns(itemNum + 1,ub);
			// crntSolnbb.UndoTake(itemNum);
			// crntSolnbb.DontTakeItem(itemNum);
			// FindBBSolns(itemNum + 1,ub);
			// crntSolnbb.UndoDontTake(itemNum);			
		} 

		if(ubname==1){
			 if (itemNum == itemCnt + 1)
			 {
				 bbclass.CheckCrntSoln();
				 return;			
			 }
			 //int ub2=0;
			remainingCap=capa-crntSolnbb.takenweight;
			for (int i = itemNum; i <= itemCnt; i++)
			{			
				// if (crntSolnbb.isTaken[i] == false)
				// {				
				if(instbb.GetItemWeight(i)<=remainingCap){
					sumofundecidedfitval+=instbb.GetItemValue(i);
				}
				//}			
			}					
						 
			if(crntSolnbb.takenvalue+sumofundecidedfitval<=bestSolnbb.GetValue()){
				return;
			}	
			crntSolnbb.DontTakeItem(itemNum);
			FindBBSolns(itemNum + 1,ub);
			crntSolnbb.UndoDontTake(itemNum);
			crntSolnbb.TakeItem(itemNum);
			FindBBSolns(itemNum + 1,ub);
			crntSolnbb.UndoTake(itemNum);
			// crntSolnbb.TakeItem(itemNum);
			// FindBBSolns(itemNum + 1,ub);
			// crntSolnbb.UndoTake(itemNum);
			// crntSolnbb.DontTakeItem(itemNum);
			// FindBBSolns(itemNum + 1,ub);
			// crntSolnbb.UndoDontTake(itemNum);			
		} 
		
		if(ubname==2){		
			
			if (itemNum == itemCnt + 1)
			{
				bbclass.CheckCrntSoln();
				return;			
			}
			
			
			int totalvaluecal=0;
			remCapa = instbb.GetCapacity() - crntSolnbb.takenweight;
			totalvaluecal = crntSolnbb.takenvalue;	
			totalvaluecal+=fractional(itemNum, remCapa);	

			
			if(totalvaluecal<=bestSolnbb.takenvalue){
				return;
			}
			crntSolnbb.DontTakeItem(itemNum);
			FindBBSolns(itemNum + 1,ub);
			crntSolnbb.UndoDontTake(itemNum);
			crntSolnbb.TakeItem(itemNum);
			FindBBSolns(itemNum + 1,ub);
			crntSolnbb.UndoTake(itemNum);
			// crntSolnbb.TakeItem(itemNum);
			// FindBBSolns(itemNum + 1,ub);
			// crntSolnbb.UndoTake(itemNum);
			// crntSolnbb.DontTakeItem(itemNum);
			// FindBBSolns(itemNum + 1,ub);
			// crntSolnbb.UndoDontTake(itemNum);			
		}	
	}

	public static int fractional(int itemNum, int remcap){
		int totalvalue=0;
		int load=0;
		int capa=remcap;	
		int remaining,finalfracval;
		for(int i=itemNum;i<=instbb.GetItemCnt() && load<capa;i++) {
				if(instbb.GetItemWeight(itemNum)<=capa-load){	
					totalvalue=totalvalue+ instbb.GetItemValue(itemNum);
					load=load+instbb.GetItemWeight(itemNum);
				}
				else{
					remaining=instbb.GetItemValue(itemNum)/instbb.GetItemWeight(itemNum);
					finalfracval=remaining*(capa-load);	
					totalvalue=totalvalue+(int)finalfracval;
					load=capa;
					break;						
			}
		}
		return totalvalue;
	}

	public static void swap(int x , int y ,int data[])
	{
		int temp = data[x];
		data[x] = data[y];
	    data[y] = temp;
	}

	
	public static int Partition(int allvalues[],int allweights[], int lo, int hi){
		int pivot=(allvalues[hi]/allweights[hi]);	
		int i=lo-1;
		for(int j=lo;j<=hi-1;j++){
			if((allvalues[j]/allweights[j])<pivot){
				
				swap(j,i+1,allvalues);
				swap(j,i+1,allweights);
				i++;
			}
		}
		swap(i+1,hi,allvalues);	
		swap(i+1,hi,allweights);
		return i+1;
	}

	public void QuickSort(int allvalues[],int allweights[], int lo, int hi)
	{
		if(lo>=hi){	
			return;
		}				
		int mid = Partition(allvalues,allweights,lo,hi);		
		QuickSort(allvalues,allweights, lo, mid-1);
		QuickSort(allvalues,allweights, mid+1, hi);			
	}	

	public KnapsackBBSolver(UPPER_BOUND ub_)
	{
		ub = ub_;
	}
	public void close()
	{
    
	}
	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		instbb = inst_;
		bestSolnbb = soln_;
		crntSolnbb = new KnapsackSolution(instbb);
		FindBBSolns(1,ub);  	
		
	}
}
