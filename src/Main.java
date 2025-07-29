//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Demander le prénom
        System.out.print("Quel est ton prénom ? ");
        String name = scanner.nextLine();
        System.out.println("Hello " + name + "!");

        // 2. Créer un tableau de tâches
        String[] tasks = new String[] {
                "Faire les devoirs",
                "Aller au sport",
                "Lire un livre"
        };

        // 3. Afficher les tâches existantes
        System.out.println("\nListe des tâches actuelles :");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }

        // 4. Ajouter une nouvelle tâche
        System.out.print("\nAjouter une nouvelle tâche : ");
        String newTask = scanner.nextLine();

        // 5. Créer un nouveau tableau avec la nouvelle tâche
        String[] updatedTasks = new String[tasks.length + 1];
        for (int i = 0; i < tasks.length; i++) {
            updatedTasks[i] = tasks[i];
        }
        updatedTasks[tasks.length] = newTask;

        // 6. Réafficher la liste mise à jour
        System.out.println("\nListe mise à jour des tâches :");
        for (int i = 0; i < updatedTasks.length; i++) {
            System.out.println((i + 1) + ". " + updatedTasks[i]);
        }

        scanner.close();
    }
}
