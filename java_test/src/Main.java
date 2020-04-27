import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    static class Solution {
        public int waysToChange1(int n){
            int[] con={1,5,15,25};
            int[] con_value={1,6,20,34};
            int[][] dq=new int[5][n+1];
            for(int i=0;i<n+1;i++){
                dq[0][i]=0;
            }
            for(int i=0;i<con.length;i++){
                for(int j=0;j<=n;j++){
                    if(j>=con[i]){
                        dq[i+1][j]=Math.max(dq[i][j],dq[i][j-con[i]]+con_value[i]);
                    }
                    else {
                        dq[i+1][j]=dq[i][j];
                    }
                }
            }
            return dq[4][n];
        }

        public int waysTo_MAX_VALUE(int n){
            int[] con={0,1,5,15,25};
            int[] con_value={0,1,6,20,34};
            int[] con_num={0,2,1,4,5};
            int[] dq=new int[n+1];
            int[] dq_n=new int[n+1];
            List<Integer> c=new ArrayList();
            int maxx=0;
            dq[0]=0;
            dq_n[0]=con_num[0];
            for(int i=1;i<con.length;i++){
                for(int l=0;l<n;l++){
                    dq_n[l]=con_num[i];
                }
                maxx+=con_num[i]*con_value[i];
                for(int j=con[i];j<=n;j++) {
                    c.clear();
                    for (int k = 0; k <= j / con[i]; k++) {
                        if (k <= dq_n[j - con[i] * k]) {
                            c.add(dq[j - con[i] * k] + con_value[i] * k);
                        }
                    }
                    dq[j] = Collections.max(c);

                    dq_n[j] = dq_n[j - c.indexOf(Collections.max(c)) * con[i]] - c.indexOf(Collections.max(c));
                    if (j - maxx >0){
                        dq[j]=dq[j-1];
                    }
                }
            }
            return dq[n];
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int result = s.waysTo_MAX_VALUE(16);
        System.out.println(result);
    }
}
