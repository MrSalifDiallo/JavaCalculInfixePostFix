import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
public class EvaluateurExpression {
    public EvaluateurExpression(){

    }

    // Vérifier si l'expression est valide
    public boolean estExpressionValide(String expression) {
        if (expression.equals(null)) {
            return false;
        }
        else{
                // Règles de validation de base : pas d'opérateurs consécutifs, parenthèses équilibrées, etc.
            String tabOperateur[]={"*","/","+","-"};
            for (String operateur1 : tabOperateur) {
                for (String operateur2 : tabOperateur) {
                    if (expression.contains(operateur1+operateur2)) {
                        return false;
                    }
                }
            }

            Stack<Character> stack = new Stack<>();
            for (char c : expression.toCharArray()) {
                if (c == '(') {
                    stack.push(c); //On ajoute a la pile
                    // System.out.println(stack);
                } 
                else if (c == ')') {
                    if (stack.isEmpty()) {
                        System.out.println("Parentheses non equilibre");
                        return false;
                    }
                    // System.out.println("Before Pop ");
                    // System.out.println(stack);
                    stack.pop(); //supprime le dernier objet qui est en haut
                    // System.out.println("After Pop ");
                    // System.out.println(stack);
                }
            }
            // System.out.println(stack.size());
            // System.out.println(stack.isEmpty());
        return stack.isEmpty();
        }   
    }

    // Évaluer l'expression arithmétique
    public  double evaluerExpressionGaucheDroite(String expression) {
        String expressionReecritGD = reecritureExpressionGD(expression);
        // System.out.println("Expression infixeversPostFix=>"+expressionReecritGD);
        return calculExpressionGaucheDroite(expressionReecritGD);
    }

    public String reecritureExpressionGD(String expression) {
        StringBuilder resultat = new StringBuilder();
        Stack<Character> pile = new Stack<>();
        for (int i =0 ; i <expression.length(); i++) {
            Character c = expression.charAt(i);
            if (Character.isDigit(c)) {
                StringBuilder tmp=new StringBuilder();
                tmp.append(c);
                // System.out.println(tmp);
                    while (i<=expression.length()-2 && Character.isDigit(expression.charAt(i+1)) ) {
                        tmp.append(expression.charAt(++i));
                        // System.out.println(tmp);
                    }
                // System.out.println("Fin");
                resultat.append(tmp);
            } 
            else if (c == '(') {
                pile.push(c);
            } 
            else if (c == ')') {
                while (!pile.isEmpty() && pile.peek() != '(') {
                    resultat.append(' ').append(pile.pop());
                }
                pile.pop(); // Enleve la parenthese ouvrante
            } 
            else if (estOperateur(c)) {
                resultat.append(' ');
                //System.out.println(pile);
                while (!pile.isEmpty() && aPriorite(c, pile.peek())) {
                    resultat.append(pile.pop()).append(' ');
                }
                // System.out.println("Ajout pile");
                pile.push(c); 
                // //System.out.println(pile);                               
            }
        }
        while (!pile.isEmpty()) {
            resultat.append(' ').append(pile.pop());
        }
        return resultat.toString();
    }

    // Évaluer une expression postfixée
    public  double calculExpressionGaucheDroite(String expression) {
        Stack<Double> pile = new Stack<>();
        try (Scanner scanner = new Scanner(expression)) {
            while (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    pile.push(scanner.nextDouble());
                } else {
                    double b = pile.pop();
                    double a = pile.pop();
                    switch (scanner.next()) {
                        case "+":
                            pile.push(a + b);
                            break;
                        case "-":
                            pile.push(a - b);
                            break;
                        case "*":
                            pile.push(a * b);
                            break;
                        case "/":
                            pile.push(a / b);
                            break;
                    }
                }
            }
        }
        return pile.pop();
    }

    // Vérifier si le caractère est un opérateur
    public static boolean estOperateur(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }




    


    public  String DroiteGauche(String expression) {
        StringBuilder resultat = new StringBuilder();
        Stack<Character> pile = new Stack<>();
        for (int i = expression.length()-1; i >=0; i--) {
            Character c = expression.charAt(i);
            if (Character.isDigit(c)) {
                StringBuilder tmp=new StringBuilder();
                tmp.append(c);
                while (i>=1 && Character.isDigit(expression.charAt(i-1)) ) {
                    tmp.append(expression.charAt(--i));
                }
                tmp.reverse();
                resultat.append(tmp);
            } 
            else if (c == ')') {
                pile.push(c);
            } 
            else if (c == '(') {
                while (!pile.isEmpty() && pile.peek() != ')') {
                    resultat.append(' ').append(pile.pop());
                }
                pile.pop(); // Enleve la parenthese fermante
            } 
            else if (estOperateur(c)) {
                if (i!=0) {
                    resultat.append(' ');
                }
                //System.out.println(pile);
                while (!pile.isEmpty() && aPriorite(c, pile.peek())) {
                    resultat.append(pile.pop()).append(' ');
                }
                // System.out.println("Ajout pile");
                pile.push(c); 
                // //System.out.println(pile);                               
            }
        }
        while (!pile.isEmpty()) {
            resultat.append(' ').append(pile.pop());
        }
        return resultat.toString();
    }


    // Vérifier la priorité des opérateurs
    public static boolean aPriorite(char opactuel, char opTopPile) {
        if (opTopPile == '(' || opTopPile == ')') {
            return false;
        }
        if ((opactuel == '*' || opactuel == '/') && (opTopPile == '+' || opTopPile == '-')) {
            return false;
        }
        return true;
    }

    public  double evaluerExpressionDroiteGauche(String expression) {
        // Convertir l'expression infixée en postfixée (notation polonaise inversée)
        String reecritureExpressionDG = DroiteGauche(expression);
        // System.out.println("Expression infixversPostFix=>"+reecritureExpressionDG);
        // Évaluer l'expression postfixée
        return evaluerExpressionDG(reecritureExpressionDG);
    }

        // Évaluer une expression postfixée
        public  double evaluerExpressionDG(String expression) {
            Stack<Double> pile = new Stack<>();
            try (Scanner scanner = new Scanner(expression)) {
                
                while (scanner.hasNext()) {
                    //Si on a un nombre 
                    if (scanner.hasNextDouble()) {
                        pile.push(scanner.nextDouble());
                    } 
                    else {
                        double b = pile.pop();
                        double a = pile.pop();
                        switch (scanner.next()) {
                            case "+":
                                // System.out.println("a="+a+"et b="+b);
                                pile.push(b + a);
                                break;
                            case "-":
                                // System.out.println("a="+a+"et b="+b);
                                pile.push(b - a);
                                break;
                            case "*":
                                // System.out.println("a="+a+"et b="+b);
                                pile.push(b * a);

                                break;
                            case "/":
                            // System.out.println("a="+a+"et b="+b);
                                pile.push(b / a);
                                break;
                        }
                    }
                }
            }
            return pile.pop();
        }

}
