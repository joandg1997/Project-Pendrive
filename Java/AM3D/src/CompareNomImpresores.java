
public class CompareNomImpresores implements java.util.Comparator<Impresora>{
	@Override
	public int compare(Impresora arg0, Impresora arg1) {
		int ret=0;
		ret=arg0.getNom_assignat().compareTo(arg1.getNom_assignat());
	    return ret;
	}

}
