package DataStructure.sort;

import java.util.Comparator;

/**
 * @Created by mayuwan on 2019/3/17
 */
public class QuickSort {
    /**基础快排*/
    public static void quickSort(String[] ele){
        quickSort(ele,0,ele.length-1);
    }
    public static void quickSort(String[] all, int start, int end){
        if(start >= end){return;}
        int mid = partation(all,start,end);
        quickSort(all,start,mid-1);
        quickSort(all,mid+1,end);
    }
    public static int partation(String[] all, int start, int end){
        swap(all,start,start+(int)(Math.random()*(end-start+1)));
        String V = all[start];
        int j=start;
        for(int i=start+1;i<=end;i++){
            if(all[i].compareTo(V) < 0){
                swap(all,i,++j);
            }
        }
        swap(all,start,j);
        return j;
    }
    public static void swap(String[] all, int i, int j){
        String temp  = all[i];
        all[i] = all[j];
        all[j] = temp;
    }
}
