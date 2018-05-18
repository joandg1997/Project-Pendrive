
public class CompareNomTreballador implements java.util.Comparator<Treballdor>{
	@Override
	public int compare(Treballdor arg0, Treballdor arg1) {
		int ret=0;
		ret=arg0.getNom().compareTo(arg1.getNom());
	    return ret;
	}
}
