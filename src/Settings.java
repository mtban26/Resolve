import java.util.*;

public class Settings {
	// variables used
	Task head;
	
	public void add(String as, int pr) {
		
		Task node = new Task();
		
		node.assignment = as;
		node.status = pr;
		node.next = null;
		
		if (head == null) {
			head = node;
		}
		else {
			Task temp = head;
			
			while (temp.next != null) 
				temp = temp.next;
			
			temp.next = node;
		}
	}
	
	public void delete(String tsk) {
		
		Task temp, prev;
		
		if (tsk.compareTo(head.assignment) == 0) {
			head = head.next;
			return;
		}
		else {
			temp = head;
			prev = head;
			while (temp != null) {
				if (tsk.compareTo(temp.assignment) == 0) {
					prev.next = temp.next;
					return;
				}
				else {
					prev = temp;
					temp = temp.next;
				}
			}
		}
		
	}
	
	public void edit(String oldtsk, String newtsk, int newsts) {
		
		if (head == null)
			return;
		
		Task temp = head;
		while (temp != null) {
			if (oldtsk.compareTo(temp.assignment) == 0) {
				temp.assignment = newtsk;
				temp.status = newsts;
			}
			temp = temp.next;
		}
	}

	public Task getList() {
		return head;
	}
	
	public void sort(Task begin, Task end) {
		
		if (begin == end || begin == null || begin == end.next)
			return;
		
		// split list and recursively partition list
		Task pivot = partition(begin, end);
		sort(begin, pivot);
		
		if (pivot != null && pivot == begin)
			sort(pivot.next, end);
		else if (pivot != null && pivot.next != null)
			sort(pivot.next.next, end);
	}
	
	public Task partition(Task begin, Task end) {
		
		if (begin == null || end == null || begin == end)
			return begin;
		
		Task pivot = begin;
		Task curr = begin;
		int p_status = end.status;
		
		while (begin != end) {
			
			if (begin.status < p_status) {
				
				// remember previously modified objects
				pivot = curr;
				int t_status = curr.status;
				curr.status = begin.status;
				begin.status = t_status;
				curr = curr.next;
			}
			begin = begin.next;
		}
		
		// swap position of curr
		int t_status = curr.status;
		curr.status = p_status;
		end.status = t_status; 
		
		return pivot;
	}
}