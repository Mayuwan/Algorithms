/**
 * @Created by mayuwan on 2019/3/17
 *
 *
 * N皇后问题
 * 递归回溯：走不通就回退
 */
public class NQueens {

    private int[] arr ;
    private int n;
    private int count;

    public NQueens(int n){
        arr = new int[n];
        this.n= n;
        count++;
    }
    /**比较规则*/
    private boolean cmp(int row,int col){
        for (int i=0;i<row;i++){
            /**判断是否在同一列，或对角线*/
            if(col == arr[i] || Math.abs(row-i) == Math.abs(arr[i]-col)){
                return false;
            }
        }
       return true;
    }

    public void step(int row){
        if(row == n){
            count++;///
            return;
        }
        //row当前行
        for(int i=0;i<n;i++){
            if(cmp(row,i)){//成功
                arr[row] = i;//记录
                step(row+1);//找下一行
            }
        }
    }


    public static void main(String args[]){
        NQueens nQueen = new NQueens(8);
        nQueen.step(0);
        System.out.println(nQueen.count);
    }
}
