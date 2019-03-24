package DataStructure.tree;

import java.util.LinkedList;
import java.util.Stack;

public class BinarySearchTree {
    private TreeNode root;

    /**插入节点
     *递归形式
     *
     * 向以node为根的二叉搜索树中,插入节点 value
     *  返回插入新节点后的二叉搜索树的根*/
    public void insert(int value){
        root = insert(root,value);
    }
    private TreeNode insert( TreeNode node,int value){
        if(node == null){
            return  new TreeNode(value) ;
        }
        if (value < node.value){
            node.left = insert(node.left,value);
        }
        else if(value > node.value){
            node.right = insert(node.right,value);
        }
        return node;
    }
    /**非递归形式
     * 待插入的节点也需要从根节点开始进行比较，小于根节点则与根节点左子树比较，
     * 反之则与右子树比较，直到左子树为空或右子树为空，则插入到相应为空的位置，
     * 在比较的过程中要注意保存父节点的信息
     * 及 待插入的位置是父节点的左子树还是右子树，才能插入到正确的位置。
     * */
    public void insert2(int value){
        if(root == null){
            root = new TreeNode(value);
            return;
        }
        else{
            TreeNode cur = root;
            TreeNode parent = null;
            while(cur != null){
                parent = cur;
                if(value<cur.value){
                    cur = cur.left;
                    if(cur == null){
                        parent.left = new TreeNode(value);
                        return;
                    }
                }
                else{
                    cur = cur.right;
                    if(cur == null){
                        parent.right = new TreeNode(value);
                        return;
                    }
                }
            }
        }
    }
    /**遍历递归形式
     */
    public void traverse(){
        traverse1(root);
    }
    private void traverse1(TreeNode node){
        if(node == null){return;}

        System.out.println(node.value);
        traverse1(node.left);
        //  System.out.println(node.value);
        traverse1(node.right);
        //  System.out.println(node.value);

    }

    /**非递归形式
     * 先序遍历
     * 使用栈保存根节点信息
     * */
    private Stack<TreeNode> stack= new Stack();
    public void preOrder(){
        if(root == null) {return;}

        TreeNode cur = root;

        while(cur != null || !stack.isEmpty()){
            //将左子树压入栈，当到叶子结点后，出栈
            while (cur != null) {
                System.out.println(cur.value);
                stack.push(cur);
                cur = cur.left;
            }
            //获取右子树，然后压入右子树的左子树
            if(!stack.isEmpty()){
                TreeNode df = stack.pop();
                cur = df.right;
            }
        }

    }

    /**中序遍历*/
    public void inOrder(){
        if(root == null){return;}

        TreeNode cur = root;
        while(cur!=null || ! stack.isEmpty()){
            while(cur !=null){
                stack.push(cur);
                cur = cur.left;
            }
            if(!stack.isEmpty()){
                TreeNode df = stack.pop();
                System.out.println(df.value);
                cur = df.right;
            }
        }
    }
    /**后序遍历
     * 使用两个栈，
     * 利用栈的特性，将根，右子树，左子树放入栈中，出栈的顺序就为左子树，右子树，根节点
     * 缺点：多使用一个栈保存所有树的节点
     * */
    public void postOrder1(){
        if(root == null){return;}

        TreeNode cur = root;
        Stack<TreeNode> s = new Stack();

        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                s.push(cur);
                stack.push(cur);
                cur = cur.right;
            }
            if(!stack.isEmpty()){
                TreeNode sd = stack.pop();
                cur = sd.left;
            }
        }
        while(!s.isEmpty()){
            System.out.println(s.pop().value);
        }
    }
    /**使用指针保存上一个节点的信息
     * 　若Prev为空(Curr节点是根节点)或者Prev是Curr的父节点，将Curr节点的左孩子和右孩子分别压入栈；
     * 　　　　若Prev是Curr的左儿子，则将Curr的右儿子压入栈；
     * 　　　　否则Prev是Curr的右儿子，访问Curr;*/
    public void postOrder2(){
        if(root == null){return;}

        TreeNode cur = null;
        TreeNode pre = null;
        stack.push(root);
        while(!stack.isEmpty()) {
            cur = stack.peek();
            //pre为cur父节点
            if (pre == null || pre.left == cur || pre.right == cur) {
                if (cur.left != null) stack.push(cur.left);
                else if (cur.right != null) stack.push(cur.right);

            } else if (pre == cur.left) {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
            } else {
                System.out.println(cur.value);
                stack.pop();
            }
            pre = cur;
        }
    }

    /**二叉树中的节点个数
     * 递归形式*/
    public int sum(){
        return sum(root);
    }
    private int sum(TreeNode node){
        if (node == null) {
            return 0;
        }
        return sum(node.left)+sum(node.right)+1;
    }
    /**非递归形式
     * 使用先序遍历方法
     * */
    public int sum2(){
        if(root == null){return 0;}
        int sum =0;
        TreeNode cur = root;
        while(cur!=null || !stack.isEmpty() ){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            if(!stack.isEmpty()){
                TreeNode d = stack.pop();
                sum++;
                cur = d.right;
            }
        }
        return sum;
    }
    /**二叉树深度
     * 递归形式*/
    public int depth(){
        return depth(root);
    }
    private int depth(TreeNode node){
        if(node == null){return 0;}
        int leftD = depth(node.left);
        int rightD = depth(node.right);
        return 1+ (leftD > rightD ? leftD : rightD);
    }
    /**广度优先遍历
     * 使用队列*/
    private LinkedList<TreeNode> queue = new LinkedList();
    public void levelTraverse(){
        if(root == null){return;}
        TreeNode cur = root;
        queue.offer(cur);

        while( queue.size() > 0){
            TreeNode d = queue.poll();
            System.out.println(d.value);
            if(d.left != null){queue.offer(d.left);}
            if(d.right != null){queue.offer(d.right);}
        }
    }




    /**
     * 二叉树某层中的节点数
     * 使用递归
     * */

    public int levelNum(int level){
        return levelNum(root,level);
    }
    private int levelNum(TreeNode node,int level){
        if(level<1 || node == null){
            return 0;
        }
        if(level == 1){
            return 1;
        }
        //递归：level层节点数 = 左子树level-1层节点数+右子树level-1层节点数
        return levelNum(node.left,level-1)+levelNum(node.right,level-1);
    }

    /**二叉树叶子节点数
     * 递归形式
     * */
    public int leafNum(){
        return leafNum(root);
    }
    private int leafNum(TreeNode node){
       if(node == null){return 0;}
       if(node.left == null && node.right ==null) {//叶子节点
           return 1;
       }
       return leafNum(node.left)+leafNum(node.right);
    }

    /**二叉树最大距离
     * */



    public static void main(String[] args){
        BinarySearchTree tree = new BinarySearchTree();
        int[] data = {3,6,8,4,7,9};
        for(int s:data){
            tree.insert(s);
        }
//        tree.traverse();//3，6，4，8，7，9
//        tree.preOrder();//3，6，4，8，7，9
//        tree.inOrder();//3,4,6,7,8,9
//        tree.postOrder2();//4,7,9,8,6,3
//        System.out.println(tree.sum2());//6
//        System.out.println(tree.depth());//4
//         tree.levelTraverse();//3,6,4,8,7,9
//        tree.levelNum(4);//2
//        System.out.println(tree.leafNum());;
    }
}
