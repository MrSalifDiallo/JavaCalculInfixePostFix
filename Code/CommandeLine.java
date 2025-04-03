import java.io.IOException;
import java.awt.Dimension;
import java.awt.Toolkit;

public class CommandeLine {
    public void effacerConsole() {
        /**
         * Permet d'effacer la console
         */
        String osName = System.getProperty("os.name").toLowerCase();
        String commande;
        // Vérifier le système d'exploitation
        if (osName.contains("windows")) {
            // Système Windows
            commande = "cls"; // Commande pour effacer l'écran sur Windows
        } else if (osName.contains("linux") || osName.contains("unix") || osName.contains("mac")) {
            // Système Linux ou Unix
            commande = "clear"; // Commande pour effacer l'écran sur Linux/Unix et mac
        } else {
            // Autres systèmes d'exploitation
            System.out.println("Système d'exploitation non pris en charge.");
            return;
        }
        try {
            // Créer le processus
            ProcessBuilder processBuilder = new ProcessBuilder();
            if (osName.contains("windows")) {
                processBuilder.command("cmd", "/c", commande);
            } else {
                processBuilder.command(commande);
            }

            // Exécuter la commande et attendre sa terminaison
            Process process = processBuilder.inheritIO().start();
            process.waitFor();
            // Afficher un message une fois que la commande est terminée
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }



    public  void ouvrirConsolePleinEcran() {
        try {
            // Obtient les dimensions de l'écran
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = (int) screenSize.getWidth();
            int screenHeight = (int) screenSize.getHeight();

            // Calcule le nombre de colonnes et de lignes pour une console en plein écran
            int cols = screenWidth / 8; // Approximation du nombre de colonnes en fonction de la largeur de l'écran
            int lines = screenHeight / 16; // Approximation du nombre de lignes en fonction de la hauteur de l'écran

            // Taille minimale pour la console
            int minWidth = 80;
            int minHeight = 25;

            // Vérifie si les dimensions calculées sont inférieures aux dimensions minimales
            cols = Math.max(cols, minWidth);
            lines = Math.max(lines, minHeight);

            // Ouvre la console en plein écran avec les nouvelles dimensions calculées
            new ProcessBuilder("cmd", "/c", "mode con: cols=" + cols + " lines=" + lines).inheritIO().start().waitFor();

            // Centre la fenêtre de la console
            new ProcessBuilder("cmd", "/c", "powershell", "-command",
                    "$Width = (Get-Host).UI.RawUI.BufferSize.Width;" +
                            "$Height = (Get-Host).UI.RawUI.BufferSize.Height;" +
                            "$WindowWidth = $Host.UI.RawUI.WindowSize.Width;" +
                            "$WindowHeight = $Host.UI.RawUI.WindowSize.Height;" +
                            "$Left = [Math]::Max(($Width - $WindowWidth) / 2, 0);" +
                            "$Top = [Math]::Max(($Height - $WindowHeight) / 2, 0);" +
                            "$Host.UI.RawUI.WindowPosition = New-Object System.Management.Automation.Host.Coordinates($Left, $Top);"
            ).inheritIO().start().waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } 
    }


    public void effacerCaractere(int nombreCaracteres)
{
    for (int i = 0 ; i < nombreCaracteres ; i++)
    {
        System.out.print("\b \b"); // Efface caractère par caractère ce qui est imprimé sur la console selon le nombre de caractères
    }
}
}
