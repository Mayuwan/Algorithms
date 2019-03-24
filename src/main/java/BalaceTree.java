/**
 * @Created by mayuwan on 2019/3/17
 *
 *判断是否为平衡二叉树
 * 左右子树深度不超过1
 */
public class BalaceTree {
    /**方法1*
    /**计算左右子树深度,相差<1，返回true*/
    /**节点会被重复遍历多次，不推荐*/
    public static boolean isBalaceTree1(Node root){
        if(root == null){
            return true;
        }
        int leftDepth = treeDepth(root.left);
        int rightDepth = treeDepth(root.right);
        if(Math.abs(leftDepth -rightDepth) >1){
            return false;
        }
        return isBalaceTree1(root.left) && isBalaceTree1(root.right);
    }
    /**递归计算二叉树的深度*/
    public static int treeDepth(Node root){
        if(root == null){
            return 0;
        }

        int leftDepth = treeDepth(root.left);
        int rightDepth = treeDepth(root.right);

        return 1+(leftDepth > rightDepth ? leftDepth : rightDepth);
    }

    /**方法2*/
    /**使用后序遍历，只遍历一回，遍历同时判断是否为平衡二叉树，是则记录深度*/
//    static int depth=0;
    public static boolean isBalaceTree2(Node root){
        return isBalaceTree2(root,new int[1]);
    }
    public static boolean isBalaceTree2(Node root,int[] depth){
        if(root == null){
            depth[0] = 0;
            return true;
        }
        int[] leftdepth = new int[1], rightdepth = new int[1];
        if(isBalaceTree2(root.left,leftdepth) && isBalaceTree2(root.right,rightdepth)){
             if(Math.abs(leftdepth[0]-rightdepth[0]) <= 1){
                 depth[0] = 1+( leftdepth[0] > rightdepth[0] ? leftdepth[0] : rightdepth[0]);
                 return true;
             }
        }
        return false;
    }

    private static class Node{
        int val;
        Node left;
        Node right;

        public Node(int val){
            this.val = val;
        }
    }
}
