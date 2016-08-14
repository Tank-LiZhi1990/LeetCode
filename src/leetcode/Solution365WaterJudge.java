package leetcode;

public class Solution365WaterJudge {
	public boolean canMeasureWater(int x, int y, int z) {
        
        if(x < 0 || y < 0 || z < 0){
            return false;
        }
        
        if(z == 0 || (x == 0 && z == y) || (y == 0) && (z == x)){
            return true;
        }
        
        if(x == 0 || y == 0){
            return false;
        }
        
        //求公约数
        int a, b;
        if(x > y){
            a = x;
            b = y;
        }else{
            a = y;
            b = x;
        }
        int r  = a % b;
        while(r != 0){
        	a = b;
            b = r;
            r = a % b;
        }
        
        long s = (long)x + (long)y;
        if(s > Integer.MAX_VALUE){
            s = Integer.MAX_VALUE;
        }
        
        if(b == 1){
            if(z <= s){
                return true;
            }else{
                return false;
            }
        }else{
            if(z <= s && z % b == 0){
                return true;
            }else{
                return false;
            }
        }
    }
	
	public static void main(String[] args) {
		System.out.println(new Solution365WaterJudge().canMeasureWater(34, 5, 6));
	}
}
