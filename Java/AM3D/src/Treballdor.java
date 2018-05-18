
public class Treballdor {
	private String dni;
	private String nom;
	private String cognoms;
	

	
	public Treballdor(String dni, String nom, String cognoms) {
		super();
		this.dni = dni;
		this.nom = nom;
		this.cognoms = cognoms;
		

	}

	public String getDni() {return dni;}
	public void setDni(String dni) {this.dni = dni;}

	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}

	public String getCognoms() {return cognoms;}
	public void setCognoms(String cognoms) {this.cognoms = cognoms;}
	



	@Override
	public String toString() {
		
		String espai1 = "";
		if (getNom().length()<=14){
			int length = 16-getNom().length();
			for(int i=0; i<length;i++){
				espai1 = espai1 + " ";
			}
		}
		String espai2 = "";
		if (getCognoms().length()<=20){
			int length = 22-getCognoms().length();
			for(int i=0; i<length;i++){
				espai2 = espai2 + " ";
			}
		}
		
		return dni + "  " +  nom + espai1 + cognoms;
	}
	
	
	

}
