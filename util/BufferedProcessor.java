package util;

import java.util.Collection;
import java.util.ArrayList;

import java.util.Map;
import java.util.Hashtable;

/** Une classe utilitaire permettant de stocker en mémoire les résultats d'opérations coûteuses en temps.
 * <br><br>
 * La méthode {@link #process(Object)} doit être définie de telle sorte qu'elle renvoie le résultat du calcul avec l'objet argument.
 * <br><br>
 * Voici un exemple de son utilisation :
 * <br><pre><code>
 * BufferedProcessor<String,Boolean> urlValidator = new BufferedProcessor<String,Boolean>() {
 * 	public Boolean process(String url) {
 * 		return url.matches( "<\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]>" );
 * 	}
 * };
 * </code></pre>
 */
public abstract class BufferedProcessor<Input,Output> {
	private final Map<Input,Output> buffer;
	
	/** Construit un buffer de calcul vide.
	 */
	public BufferedProcessor() {
		this.buffer = new Hashtable<Input,Output>();
	}
	
	/** Calcule le résultat associé à l'argument fourni en entrée.
	 * Cette méthode doit impérativement renvoyer des résultats égaux ( selon la méthode {@link Object#equals(Object)} ) pour des arguments égaux.
	 * De plus, il est nécessaire que l'objet renvoyé ne soit pas <code>null</code>.
	 * <br>
	 * Cette méthode est appelée de manière automatique dans {@link #get(Object)} ; l'argument en entrée ne vaudra jamais <code>null</code>.
	 * @param arg  l'objet fourni en entrée pour le calcul
	 * @return  le résultat du calcul
	 */
	protected abstract Output process(Input arg);
	
	/** Renvoie le résultat associé à l'argument fourni en entrée.
	 * Cette méthode vérifie d'abbord que la requête n'a pas déjà été formulée auparavant ;
	 * si oui, elle renvoie le résultat calculé précédemment, si non, elle calcule le résultat et le stock pour une utilisation future.
	 * @param arg  l'objet fourni en entrée
	 * @return  le résultat du calcul
	 * @throws NullPointerException  si l'argument fourni en entrée vaut <code>null</code>
	 * @see #process(Object)
	 */
	public Output get(Input arg) {
		if (arg==null)
			throw new NullPointerException();
		Output out = this.buffer.get(arg);
		if (out==null) {
			out = this.process(arg);
			this.buffer.put(arg,out);
		}
		return out;
	}
	
	/** Exécute les calculs à l'avance pour chaque entrée spécifiée dans cette collection.
	 * Cela permet de gagner du temps de calcul pour les requêtes ultérieures pour ces entrées.
	 * @param args  la collection des entrées à traiter
	 * @throws NullPointerException  si la collection ou un de ces éléments est <code>null</code>
	 * @see #get(Object)
	 */
	public void preload(Collection<? extends Input> args) {
		if (args==null)
			throw new NullPointerException();
		for (Input in:args)
			if (in==null)
				throw new NullPointerException();
		for (Input in:args)
			this.get(in);
	}
	
	/** Exécute les calculs à l'avance pour chaque entrée spécifiée.
	 * Cela permet de gagner du temps de calcul pour les requêtes ultérieures pour ces entrées.
	 * @param args  le tableau des entrées à traiter
	 * @throws NullPointerException  si le tableau d'entrées ou un de ces éléments est <code>null</code>
	 * @see #get(Object)
	 */
	@SafeVarargs
	public final void preload(Input... args) {
		if (args==null)
			throw new NullPointerException();
		Collection<Input> a = new ArrayList<Input>(args.length);
		for (Input in:args)
			a.add(in);
		this.preload(a);
	}
	
}