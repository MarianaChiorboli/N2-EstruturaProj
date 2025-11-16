//Beatriz Soares RA -10443916
//Mariana Agostinho RA -10435657
//Murilo Franciscon RA -10438787
public class Palavra{
    private String palavra;
    private int ocorrencias;
    private int numeroCaracteres;

    public Palavra(String palavra){
        this.palavra=palavra;
        this.ocorrencias=1;
        this.numeroCaracteres=palavra.length();
    }
    public Palavra(){}


    public String getPalavra() {
        return palavra;
    }
    public int getOcorrencias() {
        return ocorrencias;
    }
    public void setOcorrencias(int o) { 
        ocorrencias = o; 
    }
    public int getNumeroCaracteres() { 
        return numeroCaracteres; 
    }
}