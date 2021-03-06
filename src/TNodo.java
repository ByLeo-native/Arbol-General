

import TDALista.ListaDobleEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class TNodo <E> implements Position <E> {
	protected E element;
	protected TNodo<E> ancestor;
	protected PositionList<TNodo<E>> descendant;
	
	public TNodo(E element, TNodo<E> ancestor) {
		this.element = element;
		this.ancestor = ancestor;
		this.descendant = new ListaDobleEnlazada<TNodo<E>>();
	}
	
	public TNodo(E element) {
		this(element, null);
	}
	
	public E element() {
		return this.element;
	}
	
	public PositionList<TNodo<E>> getHijos() {
		return this.descendant;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	
	public TNodo<E> getPadre() {
		return this.ancestor;
	}
	
	public void setPadre( TNodo<E> padre ) {
		this.ancestor = padre;
	}
}
