import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bread = new BufferedReader(isr);
        String input;
        CommandeLine affichage = new CommandeLine();
        EvaluateurExpression evaluation = new EvaluateurExpression();
        affichage.effacerConsole();
        while (true) {
            try {
                System.out.print("A Toi:");
                input = bread.readLine().trim();
                if (input.equals(".")) {
                    break;
                }
                else{
                    if (evaluation.estExpressionValide(input)) {
                        try {
                            double resultat = evaluation.evaluerExpressionGaucheDroite(input);
                            System.out.printf("La Syntaxe de L'expression est Correcte\nSa Valeur est(Gauche->Droite) : %.2f%n", resultat);
                            double resultatDroiteGauche=evaluation.evaluerExpressionDroiteGauche(input);
                            System.out.printf("Sa Valeur est(Droite->Gauche) : %.2f%n", resultatDroiteGauche);
                        } catch (Exception e) {
                            System.out.println("La syntaxe de l'expression est erronée");
                            // System.out.println(e);
                        }
                    } else {
                        System.out.println("La syntaxe de l'expression est erronée");
                    }
                }   
            } catch (IOException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                System.err.println("Erreur!");
            }
        }
        System.out.println("Au Revoir...");
    }
}
