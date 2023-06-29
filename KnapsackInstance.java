import java.util.*;

public class KnapsackInstance implements java.io.Closeable
{
	public int itemCnt; //Number of items
	private int cap; //The capacity
	public int[] weights; //An array of weights
	public int[] values; //An array of values

	public KnapsackInstance(int itemCnt_)
	{
		itemCnt = itemCnt_;

		weights = new int[itemCnt + 1];
		values = new int[itemCnt + 1];
		cap = 0;
	}
	public void close()
	{
		weights = null;
		values = null;
	}

	public void Generate()
	{
		int i;
        int wghtSum;

		weights[0] = 0;
		values[0] = 0;

		wghtSum = 0;
		for(i=1; i<= itemCnt; i++)
		{
			weights[i] = Math.abs(RandomNumbers.nextNumber()%100 + 1);
			values[i] = weights[i] + 10;
			wghtSum += weights[i];
		}
		//QuickSort(values, weights, 1,itemCnt );
		cap = wghtSum/2;
	}

	// public static void swap(int x , int y ,int data[])
	// {
	// 	int temp = data[x];
	// 	data[x] = data[y];
	//     data[y] = temp;
	// }

	
	// public static int Partition(int values[],int weights[], int lo, int hi){
			
	// 	//Code for random pivot
	// 	// random(data, lo, hi);
	// 	int pivot=(values[hi]/weights[hi]);	

	// 	int i=lo-1;
	// 	for(int j=lo;j<=hi-1;j++){
	// 		if((values[j]/weights[j])<pivot){				
	// 			swap(j,i+1,values);
	// 			swap(j,i+1,weights);
	// 			i++;
	// 		}
	// 	}
	// 	swap(i+1,hi,values);	
	// 	swap(i+1,hi,weights);	
	// 	return i+1;
	// }

	// public static void QuickSort(int values[],int weights[], int lo, int hi)
	// {
		
	// 	//Write your code here
	// 	//You may create other functions if needed 
	// 	//System.out.println("QuickSort");
	// 	// int childDepth;		
	// 	//childDepth = parentDepth + 1;	
	// 	if(lo>=hi){	
	// 		return;
	// 	}	
	// 	// // if(hi-lo+1<=40){
	// 	// // 	InsertionSort(data,lo,hi);
	// 	// // 	return childDepth-1;
	// 	// // }
	// 	// else{
			
	// 	int mid = Partition(values,weights,lo,hi);		
	// 	QuickSort(values,weights, lo, mid-1);
	// 	QuickSort(values,weights, mid+1, hi);	
	// 	//return Math.max(left,right);
	// }

	public int GetItemCnt()
	{
		return itemCnt;
	}
	public int GetItemWeight(int itemNum)
	{
		return weights[itemNum];
	}
	public int GetItemValue(int itemNum)
	{
		return values[itemNum];
	}
	public int GetCapacity()
	{
		return cap;
	}
	public void Print()
	{
		int i;

		System.out.printf("Number of items = %d, Capacity = %d\n",itemCnt, cap);
		System.out.print("Weights: ");
		for (i = 1; i <= itemCnt; i++)
		{
			System.out.printf("%d ",weights[i]);
		}
		System.out.print("\nValues: ");
		for (i = 1; i <= itemCnt; i++)
		{
			System.out.printf("%d ",values[i]);
		}
		System.out.print("\n");
	}
}
