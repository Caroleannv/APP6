/** @author Ahmed Khoumsi */

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {
// Attributs

    String chaine;
    int index;    //Variable avec l'expression à analyser
    int longueur; //Variable avec la longueur de l'expression

    /** Constructeur pour l'initialisation d'attribut(s)
     */
    public AnalLex(String chaine) {  // arguments possibles
        chaine = chaine;
        longueur = chaine.length(); //donne la longueur de la string
        index = 0;
    }


    /** resteTerminal() retourne :
     false  si tous les terminaux de l'expression arithmetique ont ete retournes
     true s'il reste encore au moins un terminal qui n'a pas ete retourne
     */
    public boolean resteTerminal( ) {
        if(indez >= longueur)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    /** prochainTerminal() retourne le prochain terminal
     Cette methode est une implementation d'un AEF
     */
    public Terminal prochainTerminal( ) {
        t = new Terminal();
        int etat = 0;
        char carc_courant = chaine;

        if (etat == 0)
        {
            if (carc_courant == '(')
            {
                etat = 1;
                index++;
                //Passe au carac suivant
            }
        }

        if (etat == 1)
        {
            if (carc_courant >= 'A' && carc_courant <= 'Z')
            {
                etat = 2;
                index++;
                //Passe au carac suivant
            }
            else if (carc_courant == '+' | carc_courant == '-' | carc_courant == '*'| carc_courant == '/')
            {
                etat = 3;
                index++;
                //Passe au carac suivant
            }
        }

        if (etat == 2)
        {
            if (carc_courant >= 'A' && carc_courant <= 'Z'| carc_courant >= 'a' && carc_courant <= 'z' | carc_courant == '_')
            {
                etat = 2;
                index++;
                //Passe au carac suivant
            }
            else if (carc_courant == '+' | carc_courant == '-' | carc_courant == '*'| carc_courant == '/')
            {
                etat = 3;
                index++;
                //Passe au carac suivant
            }
            else if (carc_courant == ')')
            {
                etat = 1;
                index++;
            }
            else if (carc_courant == '--')
            {
                ErreurLex("erreur");
            }
        }

        if (etat == 3)
        {
            if (carc_courant >= 'A' && carc_courant <= 'Z')
            {
                etat = 2;
                index++;
                //Passe au carac suivant
            }
            else if (carc_courant >= '0' && carc_courant <= '9')
            {
                etat = 4;
                //Retourne le résultat de l'expression??
            }
            else if (carc_courant >= 'A' && carc_courant <= 'Z')
            {
                etat = 2;
                index++;
            }
        }
        return null;
    }




    /** ErreurLex() envoie un message d'erreur lexicale
     */
    public void ErreurLex(String s) {
        this.s = s;
    }


    //Methode principale a lancer pour tester l'analyseur lexical
    public static void main(String[] args) {
        String toWrite = "";
        System.out.println("Debut d'analyse lexicale");
        if (args.length == 0){
            args = new String [2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatLexical.txt";
        }
        Reader r = new Reader(args[0]);

        AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

        // Execution de l'analyseur lexical
        Terminal t = null;
        while(lexical.resteTerminal()){
            t = lexical.prochainTerminal();
            toWrite +=t.chaine + "\n" ;  // toWrite contient le resultat
        }				   //    d'analyse lexicale
        System.out.println(toWrite); 	// Ecriture de toWrite sur la console
        Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
        System.out.println("Fin d'analyse lexicale");
    }
}