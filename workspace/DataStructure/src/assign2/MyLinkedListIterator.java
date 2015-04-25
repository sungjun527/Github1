package assign2;
import java.util.Iterator;

public class MyLinkedListIterator<T extends Comparable<T>> implements Iterator<T> {
	// FIXME implement this
	// Implement the iterator for MyLinkedList.
	// You have to maintain the current position of the iterator.

	// Remove below annotation(@SuppressWarnings("unused")) after you implement this iterator.
	// This annotation tells compiler that "Do not warn me about this variable not being used".
	private final MyLinkedList<T> l;
	private Node<T> cursor;

	public MyLinkedListIterator(MyLinkedList<T> myLinkedList) {
		this.l = myLinkedList;
		cursor=l.head;
	}

	@Override
	public boolean hasNext() {
		if(cursor.next==null)
			return false;
		return true;
	}

	@Override
	public T next() {

			cursor=cursor.next;
			
			return cursor.item;

	}

	@Override
	public void remove() {
		// This code does not have to be modified.
		throw new UnsupportedOperationException();
	}
}
