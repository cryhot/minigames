package userinterface.util;

import java.util.Collection;

/** Une classe utilitaire permettant de stocker en mémoire les transformations possibles d'un objet.
 * Une instance de cette classe est construite à partir d'un objet source qui peut être transformé selon un objet, donnant une variante de l'objet source.
 * <br><br>
 * La méthode {@link #transform(Trans)} doit être définie de telle sorte qu'elle renvoie la variante de l'objet source selon la variation donnée en paramètre.
 * <br><br>
 * Voici un exemple de son utilisation :
 * <br><pre><code>
 * BufferedTransformation<String,Integer> substring = new BufferedTransformation<String,String,Boolean>(myRegex) {
 * 	public String transform(Integer length) {
 * 		return this.getSource().substring(0,length);
 * 	}
 * };
 * </code></pre>
 */
public abstract class BufferedTransformation<Src,Trans> extends BufferedProcessor<Trans,Src> {
	private final Src source;
	
	/** Construit un buffer de transformation vide.
	 * @param source  l'objet source de la transformation, qui peut être <code>null</code>
	 */
	public BufferedTransformation(Src source) {
		super();
		this.source = source;
	}
	
	/** Calcule le résultat de la transformation de l'objet source.
	 * Cette méthode doit impérativement renvoyer des résultats égaux ( selon la méthode {@link Object#equals(Object)} ) pour des arguments égaux.
	 * De plus, il est nécessaire que l'objet renvoyé ne soit pas <code>null</code>.
	 * <br>
	 * Cette méthode est appelée de manière automatique dans {@link #get(Input)} ; l'argument en entrée ne vaudra jamais <code>null</code>.
	 * <br><br>
	 * Pour se référer à l'objet source dans les classes héritées, utilisez {@link #getSource()}.
	 * @param arg  l'objet correspondant à la transformation
	 * @return  la version transformée de l'objet source
	 */
	protected abstract Src transform(Trans transformation);
	
	/** Implémentation interne de la méthode {@link BufferedProcessor#process(Input)}.
	 * Cette implémentation appelle directement {@link #transform(Trans)}.
	 * @param arg  l'objet correspondant à la transformation
	 * @return  la version transformée de l'objet source
	 * @see #transform(Trans)
	 */
	@Override
	protected final Src process(Trans transformation) {
		return this.transform(transformation);
	}
	
	/** Renvoie le résultat de la transformation de l'objet source.
	 * Cette méthode vérifie d'abbord que la requête n'a pas déjà été formulée auparavant ;
	 * si oui, elle renvoie le résultat calculé précédemment, si non, elle calcule le résultat et le stock pour une utilisation future.
	 * @param arg  l'objet fourni en entrée
	 * @return  le résultat du calcul ; renvoie <code>null</code> si l'objet source est <code>null</code>, et renvoie l'objet source lui-même si la transformation vaut <code>null</code>
	 * @see #transform(Trans)
	 */
	@Override
	public Src get(Trans transformation) {
		if (this.source==null)
			return null;
		if (transformation==null)
			return this.source;
		return super.get(transformation);
	}
	
	/** Calcule les variantes à l'avance pour chaque transformation spécifiée dans cette collection.
	 * Cela permet de gagner du temps de calcul pour les requêtes ultérieures pour ces entrées.
	 * @param args  la collection des transformations à traiter
	 * @throws NullPointerException  si la collection est <code>null</code>
	 * @see #get(Input)
	 */
	@Override
	public void preload(Collection<? extends Trans> transformations) {
		if (transformations==null)
			throw new NullPointerException();
		for (Trans t:transformations)
			this.get(t);
	}
	
	/** Renvoie l'objet à la source de la transformation.
	 * @return  l'objet source de la transformation
	 */
	public final Src getSource() {
		return this.source;
	}
	
}