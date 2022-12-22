import java.util.Scanner;

/**
 * Write a description of class Sistema here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sistema
{
    public static void main(String[] args){
        Curso[] cursos = new Curso[0];
        Disciplina[] disciplinas = new Disciplina[0];
        Scanner entrada = new Scanner(System.in);
        
        boolean quit = false;
        while (!quit){
            System.out.println("O que deseja fazer?");
            System.out.println("(a) Criar curso"+"\n(b) Criar disciplina"+"\n(c) Adicionar prerequisito"+"\n(d) Criar oferta");
            System.out.println("(e) Excluir oferta"+"\n(f) Listar cursos"+"\n(g) Listar curriculo de algum curso");
            System.out.println("(h) Listar ofertas de alguma disciplina"+"\n(q) Sair");
            char choice = entrada.next().charAt(0);
            
            switch(choice){
                case 'q':
                    quit = true;
                    break;
                case 'a':
                    /*
                     * Criaçao de curso: Pede para o usuario o nome do curso, e cria um curso com este nome
                       */
                    System.out.println("Qual o nome do curso que quer criar?");
                    entrada.nextLine();
                    String nomeCurso = entrada.nextLine();
                    Curso curso = new Curso(nomeCurso);
                    System.out.println("Curso " + curso.getNome() + " criado.");
                    cursos = Sistema.addCurso(cursos, curso);
                    break;
                    
                case 'b':
                    /*
                     * Criaçao de disciplina: pede pro usuario o nome da disciplina, o ID da disciplina,
                     * e quantos creditos ela tem
                       */
                    System.out.println("Qual o nome da disciplina que deseja criar?");
                    entrada.nextLine();
                    String nomeDisc = entrada.nextLine();
                    System.out.println("Qual a ID da disciplina que deseja criar?");
                    String idDisc = entrada.nextLine();
                    System.out.println("Quantos creditos essa disciplina tem?");
                    int creditosDisc = entrada.nextInt();
                    Disciplina disciplina = new Disciplina(nomeDisc, idDisc, creditosDisc);
                    System.out.println("Disciplina "+disciplina.getNome() + " criada com ID e creditos " + disciplina.getID() + " e " + disciplina.getCreditos());
                    disciplinas = Sistema.addDisc(disciplinas, disciplina);
                    break;
                    
                case 'c':
                    /*
                       Adiciona prerequisito a uma disciplina ja criada, e recebe outra disciplina como parametro
                       */
                    System.out.println("Deseja adicionar pre requisito para qual disciplina? ");
                    for (int i=0; i<disciplinas.length; i++){
                        System.out.print(disciplinas[i].getNome() + "  \t");
                    }
                    System.out.print("\n");
                    for (int i=0; i<disciplinas.length; i++){
                        System.out.print(i+"  \t");
                    }
                    int discIndex = entrada.nextInt();
                    
                    if (!(discIndex<disciplinas.length) || !(discIndex>=0)){
                        Sistema.erroIndex();
                        break;
                    }
                    
                    System.out.println("Deseja adicionar qual disciplina como pre requisito de " + disciplinas[discIndex].getNome() + "? (-1 para parar de adicionar prereqs)");
                    for (int i=0; i<disciplinas.length; i++){
                        if(i!=discIndex){
                        System.out.print(disciplinas[i].getNome() + "\t");}
                    }
                    System.out.print("\n");
                    for (int i=0; i<disciplinas.length; i++){
                        if(i!=discIndex){
                        System.out.print(i+"\t  ");}
                    }
                    
                    int test=0;
                    while(test!=-1){
                        test = entrada.nextInt();
                        if(test<disciplinas.length && test!=-1){
                            disciplinas[discIndex].addPrereq(disciplinas[test]);
                        }
                    }
                    break;
                    
                case 'd':
                    /*
                       Realiza a oferta de uma disciplina
                       Primeiro pede pro usuario o curso que ele deseja fazer a oferta
                       Depois, pede pro usuario a disciplina que ele deseja ofertar
                       E em seguida, a etapa e o carater da disciplina neste curso
                       */
                    System.out.println("Deseja ofertar para qual curso? (Escolha o indice abaixo do curso q deseja)");
                    for (int i=0; i<cursos.length; i++){
                        System.out.print(cursos[i].getNome() + "\t");
                    }
                    System.out.print("\n");
                    for (int i=0; i<cursos.length; i++){
                        System.out.print(i+"\t");
                    }
                    int indexCurso = entrada.nextInt();
                    if(!(indexCurso<cursos.length) || !(indexCurso>=0)){
                        Sistema.erroIndex();
                        break;
                    }
                    
                    System.out.println("Deseja ofertar qual disciplina? (Escolha o indice abaixo da disciplina q deseja)");
                    for (int i=0; i<disciplinas.length; i++){
                        System.out.print(disciplinas[i].getNome() + "\t");
                    }
                    System.out.print("\n");
                    for (int i=0; i<disciplinas.length; i++){
                        System.out.print(i+"\t\t");
                    }
                    int indexDisc = entrada.nextInt();
                    if(!(indexDisc<disciplinas.length) || !(indexDisc>=0)){
                        Sistema.erroIndex();
                        break;
                    }
                    
                    // Escolha de qual semestre ofertar
                    System.out.println("Deseja ofertar em qual semestre?");
                    int semestre = entrada.nextInt();
                    
                    //Escolha de carater para ofertar
                    System.out.println("Qual o carater da disciplina? (Obrigatoria, Eletiva, Adicional)");
                    entrada.nextLine();
                    String carater = entrada.nextLine();
                    switch (carater){
                        case "Obrigatoria":
                        case "Eletiva":
                        case "Adicional":
                            cursos[indexCurso].fazerOferta(disciplinas[indexDisc], semestre, carater, disciplinas[indexDisc].getPrereqsList());
                            break;
                        default:
                            Sistema.erroIndex();
                            break;
                    }  
                    break;
                    
                case 'e':
                    /*
                       Exclui a oferta de uma disciplina ofertada em algum curso
                       Primeiro pede o curso que a disciplina foi ofertada
                       Depois, qual disciplina a pessoa quer excluir a oferta
                       E por fim, exclui a oferta da disciplina escolhida no curso escolhido
                       */
                    System.out.println("Deseja excluir oferta de qual curso?");
                    for (int i=0; i<cursos.length; i++){
                        System.out.print(cursos[i].getNome() + "\t");
                    }
                    System.out.print("\n");
                    for (int i=0; i<cursos.length; i++){
                        System.out.print(i+"\t");
                    }
                    int indexExcluirOferta = entrada.nextInt();
                    
                    if (!(indexExcluirOferta<cursos.length) || !(indexExcluirOferta>=0)){
                        Sistema.erroIndex();
                        break;
                    }
                    System.out.println("Deseja excluir oferta de qual disciplina?");
                    for (int i=0; i<disciplinas.length; i++){
                        System.out.print(disciplinas[i].getNome() + "\t");
                    }
                    System.out.print("\n");
                    for (int i=0; i<disciplinas.length; i++){
                        System.out.print(i+"\t");
                    }
                    int indexExcluirOfertaDisc = entrada.nextInt();
                    
                    if (indexExcluirOfertaDisc<disciplinas.length || indexExcluirOferta>=0){
                        cursos[indexExcluirOferta].delOferta(disciplinas[indexExcluirOfertaDisc]);
                        break;
                    }else{Sistema.erroIndex();
                        break;}
                
                case 'f':
                    /*
                     * Lista os cursos que estao salvos no sistema
                       */
                    System.out.println("Listando cursos:");
                    for (int i=0; i<cursos.length; i++){
                        System.out.print(cursos[i].getNome() + "\t");
                    }
                    break;
                    
                case 'g':
                    /*
                     * Lista o curriculo de um curso
                     * Primeiro pede o curso que o usuario quer ver o curriculo, e depois imprime o curriculo
                       */
                    System.out.println("Deseja ver o curriculo de qual curso?");
                    for (int i=0; i<cursos.length; i++){
                        System.out.print(cursos[i].getNome() + "\t");
                    }
                    System.out.print("\n");
                    for (int i=0; i<cursos.length; i++){
                        System.out.print(i+"\t");
                    }
                    int indexVerCurriculo = entrada.nextInt();
                    
                    if (indexVerCurriculo<cursos.length || indexVerCurriculo>=0){
                        cursos[indexVerCurriculo].verCurriculo();
                        break;
                    }else{
                        Sistema.erroIndex();
                        break;}
                    
                case 'h':
                    /*
                     * Ve as ofertas de uma mesma disciplina nos diferentes cursos
                       */
                    System.out.println("Deseja ver as ofertas de qual disciplina?");
                    for (int i=0; i<disciplinas.length; i++){
                        System.out.print(disciplinas[i].getNome() + "\t  ");
                    }
                    System.out.print("\n");
                    for (int i=0; i<disciplinas.length; i++){
                        System.out.print(i+"\t  ");
                    }
                    int indexVerOfertas = entrada.nextInt();
                    
                    if (indexVerOfertas<disciplinas.length || indexVerOfertas>=0){
                        disciplinas[indexVerOfertas].verOfertas();
                        break;
                    }else{
                        Sistema.erroIndex();
                        break;}
            }
        }
    }
    
    public static Curso[] addCurso(Curso[] cursos, Curso curso){
        /**
         * Funçao que adiciona um curso a lista de cursos do Sistema
         * @param cursos
         * @param curso
         */
        Curso[] newCursos = new Curso[cursos.length+1];
        for (int i=0; i<cursos.length; i++){
            newCursos[i] = cursos[i];
        }
        newCursos[cursos.length] = curso;
        return newCursos;
    }
    
    public static Disciplina[] addDisc(Disciplina[] disciplinas, Disciplina disciplina){
        /**
         * Funçao que adiciona uma disciplina a lista de disciplinas do Sistema
         * @param disciplinas
         * @param disciplina
         */
        Disciplina[] newDiscs = new Disciplina[disciplinas.length + 1];
        for (int i=0; i<disciplinas.length; i++){
            newDiscs[i] = disciplinas[i];
        }
        newDiscs[disciplinas.length] = disciplina;
        return newDiscs;
    }
    
    public static void listarCursos(Curso[] cursos){
        /**
         * Funçao que lista os cursos do sistema
         * @param cursos
         */
        for (Curso curso: cursos){
            System.out.println(curso);
        }
    }
    
    public static void erroIndex(){
        /**
         * Funçao para avisar o usuario quando colocou um indice errado
         */
        System.out.println("Indice fora de alcance, favor conferir os indices permitidos e tentar novamente");
    }
}
