package offer;

import java.util.ArrayList;
import java.util.*;
/**把数组排成最小的数
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所
 * 有数字中最小的一个。
 * 例子说明：
 * 例如输入数组{3， 32, 321}，则扫描输出这3 个数字能排成的最小数字321323。
 *
 *
 * 要点：新的排序规则
 * */
public class Solution33 {

    public static void main(String[] args){
        int[] a = {3,5,1,4,2};
        System.out.println(PrintMinNumber(a));
    }
    public static String PrintMinNumber(int [] numbers) {
        if(numbers == null || numbers.length <= 0){
            return "";
        }
        String[] all = new String[numbers.length];
        for(int i=0;i< numbers.length;i++){
            all[i] = String.valueOf(numbers[i]);
            System.out.printf("%d ",i);
        }
        myComparator comparator = new myComparator();
        quickSort(all,0,all.length-1,comparator);
        String res = "";;
        for(String a: all){
            res+=a;
        }
        return res;
    }

    public static void quickSort(String[] all, int start, int end, Comparator comparator){
        if(start >= end){return;}
        int mid = partation(all,start,end,comparator);
        quickSort(all,start,mid-1,comparator);
        quickSort(all,mid+1,end,comparator);
    }


    public static int partation(String[] all, int start, int end, Comparator comparator){
        swap(all,start,start+(int)(Math.random()*(end-start+1)));
        String V = all[start];
        int j=start;
        for(int i=start+1;i<=end;i++){
            if(comparator.compare(all[i],V) < 0){
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
    private static class myComparator implements Comparator<String>{
        public int compare(String str1,String str2){
            if(str1 == null || str2 == null){
                throw new IllegalArgumentException();
            }
            String t1 = str1+str2;
            String t2 = str2+str1;
            return t1.compareTo(t2);
        }
    }
}