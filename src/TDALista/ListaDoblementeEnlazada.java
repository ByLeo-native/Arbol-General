package TDALista;

import java.util.Iterator;
import Auxiliar.*;

public class ListaDoblementeEnlazada <E> implements PositionList <E> {
	protected int tama?o;
	protected DNodo<E> head, tail;
	
	public ListaDoblementeEnlazada() {
		head = new DNodo<E>(null);
		tail = new DNodo<E>(null);
		head.setPrev(null);
		head.setNext(tail);
		tail.setPrev(head);
		tail.setNext(null);
		tama?o = 0;
	}
	
	/**
	 * Consulta la cantidad de elementos de la lista.
	 * @return Cantidad de elementos de la lista.
	 */
	public int size() {
		return tama?o;
	}
	
	/**
	 * Consulta si la lista est? vac?a.
	 * @return Verdadero si la lista est? vac?a, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	/**
	 * Devuelve la posici?n del primer elemento de la lista. 
	 * @return Posici?n del primer elemento de la lista.
	 * @throws EmptyListException si la lista est? vac?a.
	 */
	public Position<E> first() throws EmptyListException {
		if (this.isEmpty()) {
			throw new EmptyListException("Lista vacia");
		} else {
			return head.getNext(); 
		}
	}
	
	/**
	 * Devuelve la posici?n del ?ltimo elemento de la lista. 
	 * @return Posici?n del ?ltimo elemento de la lista.
	 * @throws EmptyListException si la lista est? vac?a.
	 */
	public Position<E> last() throws EmptyListException {
		if(this.isEmpty()) {
			throw new EmptyListException("Lista vacia");
		} 
		return tail.getPrev();
	}
	
	/**
	 * Devuelve la posici?n del elemento siguiente a la posici?n pasada por par?metro.
	 * @param p Posici?n a obtener su elemento siguiente.
	 * @return Posici?n del elemento siguiente a la posici?n pasada por par?metro.
	 * @throws InvalidPositionException si el posici?n pasada por par?metro es inv?lida o la lista est? vac?a.
	 * @throws BoundaryViolationException si la posici?n pasada por par?metro corresponde al ?ltimo elemento de la lista.
	 */
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {	
		if (this.isEmpty()) {
			throw new InvalidPositionException("Lista vacia.");
		}
		DNodo<E> pos = null;
		try {
			pos = checkPosition(p);
			if (p == tail.getPrev()) {
				throw new BoundaryViolationException("No existe un siguiente al ultimo elemento.");
			}
			return pos.getNext();
		} catch (InvalidPositionException e) {
			e.fillInStackTrace();
		}
		return null;		
	}
	
	/**
	 * Devuelve la posici?n del elemento anterior a la posici?n pasada por par?metro.
	 * @param p Posici?n a obtener su elemento anterior.
	 * @return Posici?n del elemento anterior a la posici?n pasada por par?metro.
	 * @throws InvalidPositionException si la posici?n pasada por par?metro es inv?lida o la lista est? vac?a.
	 * @throws BoundaryViolationException si la posici?n pasada por par?metro corresponde al primer elemento de la lista.
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if (this.isEmpty()) {
			throw new InvalidPositionException("Lista vacia.");
		}
		try {
			DNodo<E> pos;
			pos = checkPosition(p);
			if (pos == this.head.getNext()) {
				throw new BoundaryViolationException("No existe el previo al primer elemento");
			} else {
				return pos.getPrev();
			}
		} catch (InvalidPositionException e) {
			e.fillInStackTrace();
		}
		return null;
	}
	
	/**
	 * Inserta un elemento al principio de la lista.
	 * @param element Elemento a insertar al principio de la lista.
	 */
	public void addFirst(E element) {
		DNodo<E> nuevo = new DNodo<E> (element);
		//A nuevo le establezco como siguiente al actual primer elemento de la lista
		nuevo.setNext(head.getNext());
		//Al nuevo primer elemento le establezco como previo a head
		nuevo.setPrev(head);
		//Establezco como el siguiente de head al nuevo elemento que sera el primer elemento
		head.setNext(nuevo);
		//Al siguiente de nuevo le establezco como previo al nuevo primer elemento
		nuevo.getNext().setPrev(nuevo);
		//Aumento el tama?o de la lista
		tama?o++;
	}
	
	/**
	 * Inserta un elemento al final de la lista.
	 * @param element Elemento a insertar al final de la lista.
	 */
	public void addLast(E element) {
		DNodo<E> nuevo = new DNodo<E> (element);
		//A nuevo le establezco como siguiente a la tail de la lista
		nuevo.setNext(tail);
		//Al nuevo ultimo elemento le establezco como previo al previo de la tail
		nuevo.setPrev(tail.getPrev());
		//Establezco como el previo de tail al nuevo elemento que sera el ultimo elemento
		tail.setPrev(nuevo);
		//Al previo de nuevo le establezco como su siguiente al nuevo ultimo elemento
		nuevo.getPrev().setNext(nuevo);
		//Aumento el tama?o de la lista
		tama?o++;
	}
	
	/**
	 * Inserta un elemento luego de la posici?n pasada por par?matro.
	 * @param p Posici?n en cuya posici?n siguiente se insertar? el elemento pasado por par?metro.
	 * @param element Elemento a insertar luego de la posici?n pasada como par?metro.
	 * @throws InvalidPositionException si la posici?n es inv?lida o la lista est? vac?a.
	 */
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		if(this.isEmpty()) {
			throw new InvalidPositionException("Lista vacia");
		} else {
			try {
				DNodo<E> pos = checkPosition(p);
				DNodo<E> nuevo = new DNodo<E>(element);
				nuevo.setNext(pos.getNext());
				nuevo.setPrev(pos);
				nuevo.getNext().setPrev(nuevo);
				pos.setNext(nuevo);
				tama?o++;
			} catch (InvalidPositionException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Inserta un elemento antes de la posici?n pasada como par?metro.
	 * @param p Posici?n en cuya posici?n anterior se insertar? el elemento pasado por par?metro. 
	 * @param element Elemento a insertar antes de la posici?n pasada como par?metro.
	 * @throws InvalidPositionException si la posici?n es inv?lida o la lista est? vac?a.
	 */
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		if (this.isEmpty()) {
			throw new InvalidPositionException("Lista vacia");
		} else {
			try {
				DNodo<E> pos = checkPosition(p);
				DNodo<E> nuevo = new DNodo<E>(element);
				nuevo.setNext(pos);
				nuevo.setPrev(pos.getPrev());
				nuevo.getPrev().setNext(nuevo);
				pos.setPrev(nuevo);
				tama?o++;
			} catch (InvalidPositionException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Remueve el elemento que se encuentra en la posici?n pasada por par?metro.
	 * @param p Posici?n del elemento a eliminar.
	 * @return element Elemento removido.
	 * @throws InvalidPositionException si la posici?n es inv?lida o la lista est? vac?a.
	 */	
	public E remove(Position<E> p) throws InvalidPositionException {
		if(this.isEmpty()) {
			throw new InvalidPositionException("Lista vacia.");
		} else {
			E aux = null;
			try {
				DNodo<E> pos = checkPosition(p);
				aux = pos.element();
				pos.getPrev().setNext(pos.getNext());
				pos.getNext().setPrev(pos.getPrev());
				pos.setElement(null);
				pos.setPrev(null);
				pos.setNext(null);
				tama?o--;
			} catch (InvalidPositionException e) {
				System.out.println(e.getMessage());
			}
			return aux;
		}
	}
	
	/**
	 * Establece el elemento en la posici?n pasados por par?metro. Reemplaza el elemento que se encontraba anteriormente en esa posici?n y devuelve el elemento anterior.
	 * @param p Posici?n a establecer el elemento pasado por par?metro.
	 * @param element Elemento a establecer en la posici?n pasada por par?metro.
	 * @return Elemento anterior.
	 * @throws InvalidPositionException si la posici?n es inv?lida o la lista est? vac?a.	 
	 */
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if (this.isEmpty()) {
			throw new InvalidPositionException("Lista vacia");
		} else {
			try {
				DNodo<E> pos = checkPosition(p);
				E aux = pos.element();
				pos.setElement(element);
				return aux;
			} catch (InvalidPositionException e) {
				e.fillInStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Devuelve un un iterador de todos los elementos de la lista.
	 * @return Un iterador de todos los elementos de la lista.
	 */
	public Iterator<E> iterator() {
		ElementIterator<E> it = null;
		try {
			it = new ElementIterator<E>(this);
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			System.out.println("Entro en un excepcion");
		}
		return it;
	}
	
	/**
	 * Devuelve una colecci?n iterable de posiciones.
	 * @return Una colecci?n iterable de posiciones.
	 */
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> p = new ListaDobleEnlazada<Position<E>>();
		if (!this.isEmpty()) {
			try {
				Position<E> pos = this.first();
				while(pos != this.last()) {
					p.addLast(pos);
					pos = this.next(pos);
				}
				//Ya cuando a la salida del while, pos sera la ultima posicion y hay que a?adirla
				p.addLast(pos);
			} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}
	
	public void insertarOrdenado(E element) {
		if (this.isEmpty()) {
			this.addFirst(element);
		} else {	
			DefaultComparator<E> comp = new DefaultComparator<E>();
			try {
				Position<E> p = this.first(), ultima = this.last();
				if ( comp.compare(element, p.element()) < 0) {
					this.addFirst(element);
				} else {
					boolean encontrePosicion = false;
					while ( p!= null && !encontrePosicion) {
						System.out.println(p.element());
						System.out.println(ultima.element());
						int c = comp.compare(element, p.element());
						//Si element es mayor al elemento en p
						if ( c >= 0) {
							//entonces, Si p no es el ultimo elemento entonces pasa al siguiente, de lo contrario se null
							p = (p!=ultima)? this.next(p) : null;
						} else {
							encontrePosicion = true;
						}
					}
					//Como el elemento de n es menor que element
					//Creo un nuevo nodo con elemento element, previo a n (es el primero q es mas chico)
					// y siguiente al siguiente de n
					if(encontrePosicion) {
						this.addBefore(p, element);
					} else {
						this.addAfter(ultima, element);
					}
				}	
			} catch (EmptyListException | BoundaryViolationException | InvalidPositionException e) {
				e.fillInStackTrace();
			}
		}
	}

	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if (p == null) {
			throw new InvalidPositionException("Posicion p con referencia nula");
		} else {
			DNodo<E> aRetornar;
			try {
				aRetornar = (DNodo<E>)p;
			} catch ( ClassCastException e) {
				throw new InvalidPositionException("Posicion invalida");
			}
			return aRetornar;
		}
	}
}

