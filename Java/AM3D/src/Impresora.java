
public class Impresora {
	private String codi_impresora;
	private String nom_assignat;
	private String estat;
	private String marca;
	private String model;
	private String bobina_carregada;
	
	
	public Impresora(String codi_impresora, String nom_assignat, String estat, String marca, String model, String bobina_carregada) {
		super();
		this.codi_impresora = codi_impresora;
		this.nom_assignat = nom_assignat;
		this.estat = estat;
		this.marca = marca;
		this.model = model;
		this.bobina_carregada = bobina_carregada;
	}

	public String getCodi_impresora() {return codi_impresora;}
	public void setCodi_impresora(String codi_impresora) {this.codi_impresora = codi_impresora;}
	
	public String getNom_assignat() {return nom_assignat;}
	public void setNom_assignat(String nom_assignat) {this.nom_assignat = nom_assignat;}
	
	public String getEstat() {return estat;}
	public void setEstat(String estat) {this.estat = estat;}
	
	public String getMarca() {return marca;}
	public void setMarca(String marca) {this.marca = marca;}
	
	public String getModel() {return model;}
	public void setModel(String model) {this.model = model;}
	
	public String getBobina_carregada() {return bobina_carregada;}
	public void setBobina_carregada(String bobina_carregada) {this.bobina_carregada = bobina_carregada;}

	@Override
	public String toString() {
		

		String espai1 = "";
		if (getNom_assignat().length()<=20){
			int length = 22-getNom_assignat().length();
			for(int i=0; i<length;i++){
				espai1 = espai1 + " ";
			}
		}
		String espai3 = "";
		if (getMarca().length()<=18){
			int length = 20-getMarca().length();
			for(int i=0; i<length;i++){
				espai3 = espai3 + " ";
			}
		}
		String espai4 = "";
		if (getModel().length()<=14){
			int length = 16-getModel().length();
			for(int i=0; i<length;i++){
				espai4 = espai4 + " ";
			}
		}
		return codi_impresora + "\t" + nom_assignat + espai1 + estat + "\t" + marca + espai3 + model + espai4 + bobina_carregada;
		
	}
	

}
