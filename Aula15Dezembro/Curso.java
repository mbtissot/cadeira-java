import java.util.ArrayList;
import java.util.Collections;
/**
 * A classe curso eh o que define um curso no nosso sistema universitario.
 * Cada curso possui um nome (input do usuario), um curriculo (criado a partir de ofertas de disciplinas)
 * e um numero de creditos (tambem criado dinamicamente a partir das ofertas)
 *
 * @author Matheus B. Tissot. 00305657
 * @version 15/Dezembro
 */
public class Curso
{
    private String nome;
    private int credObrigatorios;
    private int credEletivos;
    private int credAdicionais;
    private Oferta[] curriculo = new Oferta[0];
    private ArrayList<Integer> etapas = new ArrayList<Integer>();
    /**
     * Construtor da classe Curso. Precisamos somente do nome
     * @param nome
     */
    public Curso(String nome)
    {
        this.setNome(nome);
    }

    private void setNome(String nome){
        /**
         * Setter do nome do curso.
         * @param nome
         */
        this.nome = nome;
    }
    
    public String getNome(){
        /**
         * Getter do nome do Curso
         */
        return this.nome;
    }
    
    public String toString(){
        /**
         * Funçao toString do Curso
         */
        return "Curso: "+ this.getNome();
    }
    
    private void setCredito(Disciplina disciplina, String tipo, String oqfazer){
        /**
         * Setter de creditos do curso. Recebe uma disciplina, o tipo que ela eh (Obri/Eletiva/Adic), e oq fazer
         * (adicionar creditos, ou diminuir)
         * @param disciplina
         * @param tipo
         * @param oqfazer
         */
        
        switch (tipo){
            case "Obrigatoria":
                // Adiciona creditos quando oqfazer=='add', e remove quando oqfazer=='rm'
                if(oqfazer == "add"){
                    this.credObrigatorios += disciplina.getCreditos();
                    break;
                } else{
                    if (oqfazer=="rm"){
                       this.credObrigatorios -= disciplina.getCreditos();
                        break; 
                    }
                }
            case "Eletiva":
                if(oqfazer == "add"){
                    this.credEletivos += disciplina.getCreditos();
                    break;
                } else{
                    if (oqfazer=="rm"){
                       this.credEletivos -= disciplina.getCreditos();
                        break; 
                    }
                }
            case "Adicional":
                if(oqfazer == "add"){
                    this.credAdicionais += disciplina.getCreditos();
                    break;
                } else{
                    if (oqfazer=="rm"){
                       this.credAdicionais -= disciplina.getCreditos();
                        break; 
                    }
                }
            default:
                System.out.println("Faltou inserir um tipo de disciplina");
                break;
        }
    }
    
    private Oferta[] addOferta(Oferta oferta){
        /**
         * Adiciona uma oferta ao vetor de Oferta[] 'curriculo'.
         * @param oferta
         */
        Oferta[] newOfer = new Oferta[this.curriculo.length+1];
        for(int i=0; i<this.curriculo.length; i++){
            newOfer[i] = this.curriculo[i];
        }
        newOfer[newOfer.length-1] = oferta;
        this.setCredito(oferta.getDisciplina(), oferta.getCarater(), "add");
        return newOfer;
    }
    
    public void fazerOferta(Disciplina cadeira, int etapa, String carater, Disciplina[] prereqs){
        /** 
         * Esta funçao e uma intermediaria para realmente realizar uma oferta.
         * Essa funçao testa se a oferta daquela disciplina ja existe para aquele curso
         * E aceita uma lista de Disciplinas para os prerequisitos da cadeira sendo ofertada.
         * @param cadeira
         * @param etapa
         * @param carater
         * @param prereqs
         */
        Oferta oferta = new Oferta(cadeira, etapa, carater, this, prereqs);
        String nomeofer = cadeira.getNome();
        boolean check = true;
        for (int i=0; i<this.curriculo.length; i++){
            if(this.curriculo[i].getDisciplina().getNome()==nomeofer){
                check = false;
            }
        }
        if (check){
            this.curriculo = addOferta(oferta);
            cadeira.addOferta(oferta);
            for (Disciplina prereq: prereqs){
                if (!cadeira.getPrereqs().contains(prereq)){
                    cadeira.addPrereq(prereq);}
            }
            if (!this.etapas.contains(etapa)){
                this.etapas.add(etapa);
                Collections.sort(this.etapas);}
        }
        
    }
    
    public void delOferta(Disciplina cadeira){
        /**
         * Deleta uma oferta de cadeira no curso.
         * @param cadeira
         */
        Oferta[] newCur = new Oferta[this.curriculo.length-1];
        int j=0;
        Oferta ofer;
        for(int i=0; i<this.curriculo.length; i++){
            if (this.curriculo[i].getDisciplina()!=cadeira){
                newCur[j] = this.curriculo[i];
                j = j+1;
            } else{
                ofer = this.curriculo[i];
                this.setCredito(ofer.getDisciplina(), ofer.getCarater(), "rm");
                cadeira.rmOferta(ofer);
            }
        }
        this.curriculo = newCur;
    }
    
    public void verCurriculo(){
        /**
         * Mostra o curriculo do curso, mostrando as cadeiras por etapa, os prerequisitos de cada uma, e seu carater
         */
        System.out.println("-----   Curriculo do curso de " + this.getNome() + "   -----");
        for(int i: etapas){
            System.out.println("Etapa " + i);
            for (int j=0; j<this.curriculo.length; j++){
                if (this.curriculo[j].getEtapa()==i){
                    System.out.println(this.curriculo[j].getDisciplina() + " -- " + this.curriculo[j].getCarater());
                    if (this.curriculo[j].getDisciplina().prereqSize()!=0){
                        this.curriculo[j].getDisciplina().seePrereq();
                }
            }
        }
    }
        this.creditos();
    }
    
    public void creditos(){
        /** 
         * Mostra os creditos do curso atual
         */
        System.out.println("-----------------------------------");
        System.out.println("Creditos do curso " + this.getNome());
        System.out.println("Obrigatorios: " + this.getCredito("Obrigatoria"));
        System.out.println("Eletivas: " + this.getCredito("Eletiva"));
        System.out.println("Adicionais: " + this.getCredito("Adicional"));
        System.out.println("-----------------------------------");
        }
        
    public int getCredito(String tipo){
        /**
         * Funçao que retorna os creditos de um dado tipo do curso
         * @param tipo
         */
        int retorno; 
        switch (tipo){
            case "Obrigatoria":
                retorno = this.credObrigatorios;
                break;
            case "Eletiva":
                retorno = this.credEletivos;
                break;
            case "Adicional":
                retorno = this.credAdicionais;
                break;
            default:
                retorno = 0;
                System.out.println("Faltou um tipo valido: 'Obrigatorio', 'Eletiva', 'Adicional'");
        }
        return retorno;
    }
    }
    

