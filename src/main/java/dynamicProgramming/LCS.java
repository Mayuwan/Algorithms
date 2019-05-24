package dynamicProgramming;

public class LCS {
    public char[][] lcsLength(char[] X,char[] Y){
        if(X==null || Y==null || X.length ==0 || Y.length ==0){
            return null;
        }

        int m = X.length;
        int n = Y.length;
        int[][] c = new int[m+1][n+1];
        char[][] b = new char[m][n];
        for(int i=1;i<m;i++){
            c[i][0] = 0;
        }
        for(int j=0;j<n;j++){
            c[0][j] = 0;
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(X[i-1] == Y[j-1]){
                    c[i][j] = c[i-1][j-1]+1;
                    b[i-1][j-1] = '1';
                }
                else if(c[i-1][j] >= c[i][j-1]){
                    c[i][j] =c[i-1][j];
                    b[i-1][j-1] = '2';}
                else {
                    c[i][j] =c[i][j-1];
                    b[i-1][j-1] = '3';}
            }

        }
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                System.out.print(c[i][j]+ " ");
            }
            System.out.println("\n");
        }
        return b;
    }


    public void printLCS(char[][] b,char[] X,int m,int  n){
        if( m<0 || n<0) return;
        if(b[m][n] =='1'){
            printLCS(b,X,m-1,n-1);
            System.out.print(X[m]+" ");
        }
        else if(b[m][n] =='2'){
            printLCS(b,X,m-1,n);
        }
        else if(b[m][n] =='3'){
            printLCS(b,X,m,n-1);
        }
    }

    public static void main(String[] args) {
        LCS lcs = new LCS();
        String X= "ABCBDAB";
        String Y= "BDCABA";
        lcs.printLCS(lcs.lcsLength(X.toCharArray(),Y.toCharArray()),X.toCharArray(),X.length()-1,Y.length()-1);
    }
}
