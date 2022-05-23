package TDACola;

import Auxiliar.EmptyQueueException;

public class QueueConArreglo <E> implements Queue <E>{
	protected E datos [];
	protected int f,r, tamaño;
	
	public QueueConArreglo (int n) {
		tamaño = 0;
		f = r = 0;
		datos = (E[]) new Object [n];
	}
	
	public QueueConArreglo () {
		this(10);
	}
	
	public void enqueue (E e) {
		if ( size() != datos.length ) {
			datos [r] = e;
			r = ((r+1) % maxSize());
			tamaño++;
		}
	}
	
	public E dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Cola vacia");
		} else {
			E temp = datos [f];
			datos[f] = null;
			f = (f+1) % maxSize();
			tamaño--;
			return temp;
		}
	}
	
	public E front() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Cola vacia");
		} else {
			return datos[f];
		}
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return tamaño;
	}
	
	public int maxSize() {
		return datos.length;
	}
}
