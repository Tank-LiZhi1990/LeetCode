package leetcode;

public class Solution328OddEvenLinkedList {
	/*
	 * Given a singly linked list, group all odd nodes together followed by the
	 * even nodes. Please note here we are talking about the node number and not
	 * the value in the nodes.
	 * 
	 * You should try to do it in place. The program should run in O(1) space
	 * complexity and O(nodes) time complexity.
	 * 
	 * Example:
	 * Given 1->2->3->4->5->NULL,
	 * return 1->3->5->2->4->NULL.
	 * 
	 * Note:
	 * The relative order inside both the even and odd groups should remain as
	 * it was in the input.
	 * The first node is considered odd, the second node even and so on ...
	 */

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode oddEvenList(ListNode head) {
		int count = 1;
		// 指向奇数数组的当前节点
		ListNode curOdd = null;
		// 指向偶数数组的头结点
		ListNode even = null;
		// 指向偶数数组的下一个节点
		ListNode curEven = null;
		// 指向源链表的下一个节点
		ListNode next = null;

		if (head == null) {
			return head;
		} else {
			curOdd = head;
		}

		if (curOdd.next == null) {
			return head;
		} else {
			even = curOdd.next;
			curEven = curOdd.next;
		}

		// 必须要将当前节点的下一个节点置为空以防止其变成循环链表
		next = curEven.next;
		curEven.next = null;
		curOdd.next = null;

		count = 3;
		while (next != null) {
			if (count % 2 == 0) {
				curEven.next = next;
				curEven = curEven.next;
			} else {
				curOdd.next = next;
				curOdd = curOdd.next;
			}
			count++;
			// 同理,也需要在更新的了节点以后将当前节点的下一个节点置为空,以防止循环链表
			next = next.next;
			curEven.next = null;
			curOdd.next = null;
		}
		curOdd.next = even;

		return head;
	}
}
