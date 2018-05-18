import java.util.Comparator;

public class CompareEstatImpresores implements Comparator<Impresora> {

	@Override	
	public int compare(Impresora o1, Impresora o2) {
		int ret=0;
	    ret=o1.getEstat().compareTo(o2.getEstat());
		return ret;
	}

}
