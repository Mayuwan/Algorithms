package offer;

import java.util.ArrayList;
import java.util.Collections;


/**字符串全排列
 *
 * 要点：将字符串分为两部分，第一部位为首字符，剩余为第二部分
 *
 * */
public class Solution28 {
    public static void main(String[] args){
        Solution28 p = new Solution28();
        for (String abc : p.Permutation("abc")) {
            System.out.println(abc);
        }
    }
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList();
        if(str != null && str.length() != 0){
            Permutation(str.toCharArray(),0,res);
        }
        Collections.sort(res);
        return res;
    }
    public void Permutation(char[] str,int fixedIndex, ArrayList<String> res) {
        if(fixedIndex==str.length-1){
            String string = String.valueOf(str);
            if(!res.contains(string)){
                res.add(string);
            }
            return;
        }

        //fixedIndex当前第一个字符
        for(int j=fixedIndex;j<str.length;j++){
            swap(str,fixedIndex,j);//交换
            Permutation(str,fixedIndex+1,res);//求剩余部分
            swap(str,fixedIndex,j);//交换
        }
    }
    public void swap(char[] str,int i,int j){
        char temp  = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}