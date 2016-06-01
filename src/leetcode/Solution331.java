package leetcode;

public class Solution331 {
	/*
	 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

		     _9_
		    /   \
		   3     2
		  / \   / \
		 4   1  #  6
		/ \ / \   / \
		# # # #   # #
		For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
		
		Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.
		
		Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
		
		You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
		
		Example 1:
		"9,3,4,#,#,1,#,#,2,#,6,#,#"
		Return true
		
		Example 2:
		"1,#"
		Return false
		
		Example 3:
		"9,#,#,1"
		Return false
	 */
	
	public boolean isValidSerialization(String preorder) {
        int count = 0;
        if(preorder==null||"".equals(preorder)){
            return false;
        }
        /*
	            整个序列中，正确的序列#的个数比数字个数多1，且最后一个位置一定是#，
	            所以指定一个计数器，除去最后一个位置，遇到数字，计数器+1，遇到#，计数器-1，
	            在循环过程中，如果计数器为负数，则一定不是正确的序列
	            若最后一个位置不是#，则一定不是正确的序列
        */
        String[] arrs = preorder.split(",");
        for(int i = 0;i<arrs.length-1;i++){
            if(arrs[i].equals("#")){
                count--;
                if(count<0){
                    return false;
                }
            }else{
                count++;
            }
        }
        if(count==0){
            if(arrs[arrs.length-1].equals("#")){
                return true;
            }
        }
        return false;
    }
}
