package util;

import java.util.Collection;
import java.util.HashSet;

/** Classe utilitaire permettant de faire des "requêtes" sur des collections d'éléments
 * Une propriétée définit un critère de validation permettant de faire un choix parmis les éléments de la collection.
 * @param <E>  le type des éléments sur lesquels portent la propriété
 * @see #validate
 */
public abstract class Property<E>{
	
	/** Méthode à redéfinir, exprimant le critère de séléction d'un élément.
	 * Cette méthode doit être :
	 * <ul>
	 * 	<li>Constante : divers appels à cette fonction sur un même élément doit renvoyer un résultat unique.</li>
	 * 	<li>Transitive par rapport à la méthode {@link Object#equals(Object)}, c'est à dire que deux éléments dits égaux doivent renvoyer le même résultat.</li>
	 * @param element  l'élément à étudier
	 * @return  <code>true</code> si l'élément valide le crière, <code>false</code> sinon
	 */
	protected abstract boolean validate(E element);
	
	/** Renvoie l'ensemble des éléments de la collection qui vérifient cette propriété.
	 * @param c  la collection d'éléments sur laquelle porte la requête
	 * @return  l'ensemble des éléments vérifiant cette propriété
	 */
	public final HashSet<E> select(Collection<E> c) {
		HashSet<E> elements  = new HashSet<E>();
		for(E e : c)
			if(validate(e))
				elements.add(e);
		return elements;
	}
	
	/** Renvoie un élément de la collection qui vérifie cette propriété.
	 * @param c  la collection d'éléments sur laquelle porte la requête
	 * @return  le premier des éléments trouvés vérifiant cette propriété, ou <code>null</code> si aucun élément n'est trouvé
	 */
	public final E find(Collection<E> c) {
		for(E e : c)
			if(this.validate(e))
				return e;
		return null;
	}
	
	/** Compte le nombre d'éléments de la collection qui vérifient cette propriété.
	 * @param c  la collection d'éléments sur laquelle porte la requête
	 * @return  le nombre d'éléments vérifiant cette propriété
	 */
	public final int count(Collection<E> c){
		int n = 0;
		for(E e : c)
			if(validate(e))
				n++;
		return n;
	}
	
}