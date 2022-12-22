import java.util.ArrayList;
/**
 * Write a description of class Disciplina here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Disciplina
{
    private String nome;
    private ArrayList<Disciplina> prereqs = new ArrayList<Disciplina>();
    private Oferta[] ofertas = new Oferta[0];
    private String id;
    private int creditos;
    /**
     * Construtor do objeto Disciplina
     * @param nome
     * @param id
     * @param creditos
     */
    public Disciplina(String nome, String id, int creditos)
    {
        this.setNome(nome);
        this.setID(id);
        this.setCreditos(creditos);
    }
    
    public String toString(){
        /**Funçao toString do objeto Disciplina*/
        return this.getNome() + ". ID: " + this.getID();
    }
    
    public String getNome(){
        /** Retorna o nome da disciplina
           */
        return this.nome;
    }
    
    public ArrayList<Disciplina> getPrereqs(){
        /** Mostra os prerequisitos dessa disciplina */
        return this.prereqs;
    }
    
    public Disciplina[] getPrereqsList(){
        /**
         * Funçao que transforma o ArrayList de prerequisitos num array de Disciplina[], e retorna essa Array.
         */
        Disciplina[] prereqs = new Disciplina[this.getPrereqs().size()];
        for(int i=0; i<this.getPrereqs().size(); i++){
            prereqs[i] = this.getPrereqs().get(i);
        }
        return prereqs;
    }
    
    public String getID(){
        /** Retorna o ID da disciplina */
        return this.id;
    }
    
    private void setID(String id){
        /** Seta o ID da disciplina */
        this.id = id;
    }
    
    private void setNome(String nome){
        /** Seta o nome da disciplina */
        this.nome = nome;
    }
    
    public void setCreditos(int creditos){
        /** Seta os creditos da disciplina 
           @param creditos
           */
        this.creditos = creditos;
    }
    
    public int getCreditos(){
        /** Retorna os creditos dessa disciplina */
        return this.creditos;
    }
    
    public void addPrereq(Disciplina disciplina){
        /** Adiciona uma outra disciplina para os prerequisitos dessa disciplina 
           @param disciplina
           */
        prereqs.add(disciplina);
    }
    
    public void seePrereq(){
        /** Mostra os prerequisitos da disciplina atual */
        for (Disciplina cadeira: prereqs){
            System.out.println("---" + cadeira);
        }
    }
    
    public int prereqSize(){
        /** Retorna o tamanho da lista de prerequisitos (serve soh para checar se eh 0 ou nao, no metodo 
         * verCurriculo da classe Curso) */
        return this.prereqs.size();
    }
    
    public void addOferta(Oferta oferta){
        /** Adiciona uma oferta para uma dada disciplina. Eh preciso dividir oferta de disciplina, pois
         * a mesma disciplina pode ser oferecida para 2 cursos diferentes, em semestre diferentes.
           @param oferta
           */
        Oferta[] newOfer = new Oferta[this.ofertas.length+1]; // Cria uma lista de Ofertas nova, uma unidade maior
        for(int i=0; i<this.ofertas.length; i++){
            newOfer[i] = ofertas[i]; // coloca todas as ofertas na Array velha, na array nova
        }
        newOfer[this.ofertas.length] = oferta; // coloca a nova oferta na array nova
        this.ofertas = newOfer; // Atualiza a lista de prerequisitos dessa disciplina
    }
    
    public void rmOferta(Oferta oferta){
        Oferta[] newOfer = new Oferta[this.ofertas.length-1];
        for (int i=0; i<this.ofertas.length; i++){
            if (this.ofertas[i] == oferta){
                continue;
            }else{
                newOfer[i] = this.ofertas[i];
            }
        }
        this.ofertas = newOfer;
    }
    
    public void verOfertas(){
        /** Metodo que serve para mostrar as ofertas de uma dada disciplina
           (Exemplo: A cadeira 'Fis1' eh oferecida no semestre 1, de forma obrigatoria no curso de fisica,
           mas eh oferecida no semestre 2, de forma eletiva no curso de Matematica)*/
        for (int i=0; i<this.ofertas.length; i++){
            System.out.println(ofertas[i]);
        }
    }
}
