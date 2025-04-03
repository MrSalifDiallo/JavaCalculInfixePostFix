import java.io.IOException;

public class LireUtile {
    private StringBuilder expression = new StringBuilder();

    public LireUtile() {
        // Initialisation
    }

    public String lire_Utile() {
        expression.setLength(0); // Réinitialise l'expression
        System.out.print("A Toi:");
        try {
            int caractere = System.in.read();
            expression.append(caractere);
            // Arrêter immédiatement si le premier caractère est un point
            if (caractere == '.') {
                expression.append('.');
                return expression.toString();
            }

            boolean dernierCaractereNombre = false; // Indicateur pour vérifier si le dernier caractère lu était un nombre

            while (true) {
                caractere = System.in.read();

                // Vérifier si le caractère est '='
                if (caractere == '=') {
                    break;
                }

                char charActuel = (char) caractere;

                if (Character.isDigit(charActuel)) {
                    // Si le caractère actuel est un chiffre
                    expression.append(charActuel);
                    dernierCaractereNombre = true;
                } else if (charActuel == '+' || charActuel == '-' || charActuel == '*' || charActuel == '/' || charActuel == '(' || charActuel == ')') {
                    // Si le caractère est un opérateur ou une parenthèse
                    expression.append(charActuel);
                    dernierCaractereNombre = false;
                } else if (Character.isWhitespace(charActuel)) {
                    // Si le caractère est un espace
                    if (dernierCaractereNombre) {
                        // S'il y a un espace après un nombre sans opérateur entre eux, sortir de la boucle
                        System.out.println("Erreur de syntaxe : espace après un nombre sans opérateur.");
                        expression.setLength(0); // Effacer l'expression
                        return "Erreur de syntaxe : espace après un nombre sans opérateur.";
                    }
                } else {
                    // Caractère invalide
                    System.out.println("Erreur de syntaxe : caractère invalide '" + charActuel + "'.");
                    expression.setLength(0); // Effacer l'expression
                    return "Erreur de syntaxe : caractère invalide '" + charActuel + "'.";
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture d'entrée : " + e.getMessage());
            return "Erreur de lecture d'entrée : " + e.getMessage();
        }
        return expression.toString();
    }

    public String getExpression() {
        return expression.toString();
    }
}
