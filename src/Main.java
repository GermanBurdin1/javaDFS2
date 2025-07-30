import model.*;
import service.TaskService;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskService taskService = new TaskService();

        System.out.print("Entrez votre prénom : ");
        String name = scanner.nextLine();
        User user = new User(name);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Afficher les tâches");
            System.out.println("2. Ajouter une tâche");
            System.out.println("3. Supprimer une tâche");
            System.out.println("4. Modifier une tâche");
            System.out.println("5. Quitter");
            System.out.print("> ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    for (Task t : taskService.getTasks()) {
                        System.out.println(t);
                    }
                }
                case "2" -> {
                    System.out.print("Titre: ");
                    String title = scanner.nextLine();
                    System.out.print("Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Ajouter une date ? (yyyy-mm-dd ou vide): ");
                    String dateStr = scanner.nextLine();

                    Task task = dateStr.isEmpty()
                            ? new Task(title, desc, user)
                            : new DatedTask(title, desc, user, LocalDate.parse(dateStr));

                    taskService.addTask(task);
                    System.out.println("✅ Tâche ajoutée !");
                }
                case "3" -> {
                    System.out.print("ID de la tâche à supprimer: ");
                    String id = scanner.nextLine();
                    if (taskService.deleteTask(id)) {
                        System.out.println("🗑️ Tâche supprimée.");
                    } else {
                        System.out.println("❌ Aucune tâche avec cet ID.");
                    }
                }
                case "4" -> {
                    System.out.print("ID de la tâche à modifier: ");
                    String id = scanner.nextLine();
                    taskService.findById(id).ifPresentOrElse(task -> {
                        System.out.print("Nouveau titre: ");
                        task.setTitle(scanner.nextLine());
                        System.out.print("Nouvelle description: ");
                        task.setDescription(scanner.nextLine());
                        System.out.print("Est-ce complété ? (true/false): ");
                        task.setDone(Boolean.parseBoolean(scanner.nextLine()));
                        System.out.println("✅ Tâche modifiée !");
                    }, () -> System.out.println("❌ Tâche introuvable."));
                }
                case "5" -> {
                    System.out.println("👋 À bientôt !");
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }
}

