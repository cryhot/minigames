package core.game;

import java.util.Collection;
import java.util.HashSet;

public abstract class Property<E>{
	
	protected abstract boolean validate (E element);
	
	public final HashSet<E> select(Collection <E> c) {
		HashSet<E> elements  = new HashSet<E>();
		for(E e : c)
			if(validate(e))
				elements.add(e);
		return elements;
	}
	
	public final E find(Collection<E> c) {
		for(E e : c)
			if(this.validate(e))
				return e;
		return null;
	}
	
	public final int count(Collection<E> c){
		int n = 0;
		for(E e : c)
			if(validate(e))
				n++;
		return n;
	}
	
}