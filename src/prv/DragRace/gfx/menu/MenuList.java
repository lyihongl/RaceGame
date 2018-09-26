package prv.DragRace.gfx.menu;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MenuList implements Iterable<MenuModule> {

	private MenuModule first, last;
	private int size = 0;

	/**
	 * Double way linked list of menuModules
	 */
	public MenuList() {
		first = null;
		last = null;
	}
	
	/**
	 * adds a MenuModule to the end of the list
	 * @param m MenuModule that would like to be added
	 */
	public void push(MenuModule m) {
		MenuModule n = m;

		if (first == null) {
			first = n;
			last = n;
		} else {
			last.setNext(n);
			n.setPrevious(last);
			last = n;
		}
		size++;
	}
	
	/**
	 * Removes the last menuModule off the list
	 */
	public void pop() {

		if (last != first) {
			last = last.getPrevious();
			last.setNext(null);
		} else {
			last = null;
			first = null;
		}

		if (size > 0)
			size--;

	}

	// public void pop(int data) {
	// Node n = find(data);
	// if (n != null) {
	// n.getPrevious().setNext(n.getNext());
	// n.getNext().setPrevious(n.getPrevious());
	// size--;
	// }
	// }
	//
	public MenuModule find(int i) {
		MenuModule n = first;
		for (int x = 0; x < i; x++) {
			n = n.getNext();
		}
		return n;
	}

	public MenuModule find(MenuModule m) {
		MenuModule n = first;
		for (int i = 0; i < size; i++) {
			if (n == m)
				return n;
			n = n.getNext();
		}
		return null;
	}
	
	/**
	 * Swap the location of 2 nodes in the list
	 * @param node1 The location index of the first item that would like to be swapped
	 * @param node2 The location index of the second item that would like to be swapped
	 */
	public void swap(int node1, int node2) {

		MenuModule temp1 = first;
		MenuModule temp2 = first;

		if (node1 > node2) {
			node1 += node2;
			node2 = node1 - node2;
			node1 -= node2;
		}

		if (node1 <= size && node2 <= size) {
			for (int i = 0; i < node1; i++) {
				temp1 = temp1.getNext();
			}

			for (int i = 0; i < node2; i++) {
				temp2 = temp2.getNext();
			}
		}
		if (temp1 != first && temp2 != last) {
			// System.out.println("a");
			temp1.getPrevious().setNext(temp2);
			temp2.getNext().setPrevious(temp1);

			if (node2 - node1 > 1) {
				temp1.getNext().setPrevious(temp2);
				temp2.getPrevious().setNext(temp1);
			}

			temp1.setNext(temp2.getNext());
			temp2.setPrevious(temp1.getPrevious());

			if (node2 - node1 > 1) {
				temp1.setPrevious(temp2.getNext());
				temp2.setNext(temp1.getNext());
			} else {

				temp1.setPrevious(temp2);
				temp2.setNext(temp1);
			}
		} else if (temp2 == last) {

			temp2 = temp2.getPrevious();

			last.setPrevious(temp1.getPrevious());
			temp1.setPrevious(last);

			last.getPrevious().setNext(last);
			last.setNext(temp1);

			temp1.setNext(null);

			last = temp1;
		}
	}

	@Override
	public Iterator<MenuModule> iterator() {

		Iterator<MenuModule> it = new Iterator<MenuModule>() {

			MenuModule temp = null;

			@Override
			public boolean hasNext() {
				if (temp == null && first != null) {
					return true;
				} else if (temp != null) {
					return temp.getNext() != null;
				}
				throw new NoSuchElementException();
			}

			@Override
			public MenuModule next() {
				if(temp == null){
					temp = first;
					return temp;
				}else{
					temp = temp.getNext();
					return temp;
				}
			}

		};

		return it;
	}

	// public void display() {
	// MenuModule n = first;
	// for (int i = 0; i < size; i++) {
	// System.out.print(n.getData() + " ");
	// n = n.getNext();
	// }
	// System.out.println();
	// n = last;
	// for (int i = size; i > 0; i--) {
	// System.out.print(n.getData() + " ");
	// n = n.getPrevious();
	// }
	// System.out.println();
	// }

}
