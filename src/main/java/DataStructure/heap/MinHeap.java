package DataStructure.heap;

/**
 * @Created by mayuwan on 2019/3/16
 */
public class MinHeap {

    private int[] data;

    private int capacity;

    private int count;

    public MinHeap(int capacity){
        this.capacity = capacity;
        data = new int[capacity+1];
        count = 0;
    }

    public void put(int ele){
        rangeCheck();
        count++;
        data[count] = ele;
        shiftUp(count);
    }

    private void shiftUp(int index) {
        //父亲：i/2
        int faIndex;
        while((faIndex = index>>>2 ) > 0){
            if(data[index] < data[faIndex]){
                swap(data,index,faIndex);
            }
            index /=2;
        }
    }

    public int remove(){
        int min = data[1];
        swap(data,1,count);
        count--;
        shfitDown(1);
        return min;
    }

    private void shfitDown(int i) {
        int leftChild;
        while((leftChild=2*i) <=count){
            if(leftChild+1 <= count && data[leftChild] > data[leftChild+1]){
                leftChild = leftChild +1;
            }
            if(data[i] < data[leftChild]) {
                swap(data, i, leftChild);
                i = leftChild;
            }
        }
    }

    private void swap(int[] data, int datum, int datum1) {
        int temp = data[datum];
        data[datum] = data[datum1];
        data[datum1] = temp;
    }


    public int getMin(){
        return data[1];
    }
    public void rangeCheck() {
        if(count<0 || count>=capacity){
            throw new IndexOutOfBoundsException();
        }

    }


    public static void main(String[] args){
        int[] arr= {3,7,2,6,8,19};
        MinHeap minHeap = new MinHeap(arr.length);
        for (int a:arr){
            minHeap.put(a);
        }


        System.out.println(minHeap.remove());
    }

}
