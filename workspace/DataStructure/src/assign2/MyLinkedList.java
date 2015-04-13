package assign2;
import java.util.Iterator;
import java.util.NoSuchElementException;

class Node<T> {
	// FIXME implement this
	final T item;
	Node<T> next;
	
	public Node(T obj) {
		this.item = obj;
	}
}

public class MyLinkedList<T extends Comparable<T>> implements Iterable<T> {
	// FIXME implement this
	// Implement a linked list.
	// This linked list should maintain the items in a sorted order.
	// This linked list should discard a duplicate.

	Node<T> head = null;
	
	public MyLinkedList() {
		head = new Node<T>(null);
	}

	@Override
	public Iterator<T> iterator() {
		// This code does not have to be modified.
		// Implement MyLinkedListIterator instead.
		return new MyLinkedListIterator<T>(this);
	}

	public boolean add(T obj) {
		// FIXME implement this
		if(head.next==null){
			head.next= new Node<T>(obj);
			return true;
		}
		
		Node<T> temp = head;
		while(temp.next!=null){
			if(obj.compareTo(temp.next.item)<0){
				Node<T> newNode = new Node<T>(obj); 
				newNode.next=temp.next;
				temp.next=newNode;
				return true;
			} else if(obj.compareTo(temp.next.item)==0){
				return false;
			}  else if(obj.compareTo(temp.next.item)>0){
				temp=temp.next;
			}
		}
		temp.next=new Node<T>(obj);
		return true;
	}

	public boolean remove(T obj) {
		// FIXME implement this
		Node<T> temp = head;
		while(temp.next!=null){
			if(obj.compareTo(temp.next.item)==0){
				temp.next=temp.next.next;
				return true;
			}
			temp=temp.next;
		}
		return false;
//		throw new UnsupportedOperationException();
	}

	public int size() {
		// FIXME implement this
		int size=0;
		Node<T> temp = head.next;
		while(temp!=null){
			temp=temp.next;
			size++;
		}
		return size;
	}

	public T first() {
		// FIXME implement this
		// This is a helper method.
		// You do not necessarily have to implement this but still might be useful to do so.
		if (head.next != null)
			return head.next.item;
		else
			throw new NoSuchElementException();
	}

	public T last() {
		// FIXME implement this
		// This is a helper method.
		// You do not necessarily have to implement this but still might be useful to do so.
		throw new UnsupportedOperationException();
	}
}
