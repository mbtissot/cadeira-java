
/**
 * Write a description of class Oferta here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Oferta
{
    private int etapa;
    private Disciplina cadeira;
    private String carater;
    private Curso curso;
    private Disciplina[] prereqs;
    
    public Oferta(Disciplina cadeira, int etapa, String carater, Curso curso, Disciplina[] prereqs){
        /** 
         * Construtor de uma Oferta
         * @param cadeira
         * @param etapa
         * @param carater
         * @param curso
         * @param prereqs
         */
        this.setEtapa(etapa);
        this.setDisciplina(cadeira);
        this.setCarater(carater);
        this.setCurso(curso);
        this.setPrereqs(prereqs);
    }
    
    /**
     * Setters e Getters dos parametros da classe Oferta
       */
    private void setPrereqs(Disciplina[] prereqs){
        
        this.prereqs = prereqs;
    }
    
    private void setCurso(Curso curso){
        this.curso = curso;
    }
    
    private Curso getCurso(){
        return this.curso;
    }
    
    private void setEtapa(int etapa){
        this.etapa = etapa;
    }
    
    public int getEtapa(){
        return this.etapa;
    }
    
    private void setDisciplina(Disciplina cadeira){
        this.cadeira = cadeira;
    }
    
    public Disciplina getDisciplina(){
        return this.cadeira;
    }
    
    private void setCarater(String carater){
        this.carater = carater;
    }
    
    public String getCarater(){
        return this.carater;
    }
    /**
       Fim dos getters e setters da classe Oferta
       */
    public String toString(){
        /**
         * Funçao toString da classe Oferta
         */
        return this.getDisciplina() + " eh oferecida pelo curso de " + this.getCurso() + " no " + this.getEtapa() + " semestre com carater " + this.getCarater();
    }
}
