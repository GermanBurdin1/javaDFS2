import data.DatabaseAccess;
import model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseAccess db = DatabaseAccess.getInstance();

        // Affichage des utilisateurs disponibles
        System.out.println("Utilisateurs enregistrés :");
        for (User user : db.getUsers()) {
            System.out.println("- " + user.getId() + " : " + user.getFirstName());
        }

        // Authentification basique
        System.out.print("\nVeuillez entrer l'ID utilisateur pour commencer : ");
        String userId = scanner.nextLine();

        User currentUser;
        try {
            currentUser = db.findUserById(userId);
        } catch (NotFoundException e) {
            System.out.println("Utilisateur introuvable. Fermeture du programme.");
            return;
        }

        // Boucle principale
        while (true) {
            System.out.println("\nMenu principal");
            System.out.println("1. Voir mes tâches");
            System.out.println("2. Ajouter une tâche");
            System.out.println("3. Supprimer une tâche");
            System.out.println("4. Modifier une tâche");
            System.out.println("5. Quitter");
            System.out.print("> ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.println("Tâches associées à " + currentUser.getFirstName() + " :");
                    for (Task task : db.getTasks()) {
                        if (task.getUser().getId().equals(currentUser.getId())) {
                            System.out.println("- " + task.getId() + " : " + task);
                        }
                    }
                }

                case "2" -> {
                    System.out.print("Titre de la tâche : ");
                    String title = scanner.nextLine();
                    System.out.print("Description : ");
                    String description = scanner.nextLine();

                    Task task = new TaskBuilder()
                            .setTitle(title)
                            .setDescription(description)
                            .setUser(currentUser)
                            .build();

                    db.addTask(task);
                    System.out.println("Tâche ajoutée avec succès.");
                }

                case "3" -> {
                    System.out.print("ID de la tâche à supprimer : ");
                    String idToDelete = scanner.nextLine();
                    boolean deleted = db.deleteTaskById(idToDelete);
                    if (deleted) {
                        System.out.println("Suppression réussie.");
                    } else {
                        System.out.println("Aucune tâche trouvée avec cet ID.");
                    }
                }

                case "4" -> {
                    System.out.print("ID de la tâche à modifier : ");
                    String idToEdit = scanner.nextLine();
                    try {
                        Task task = db.findTaskById(idToEdit);

                        if (!task.getUser().getId().equals(currentUser.getId())) {
                            System.out.println("Vous ne pouvez modifier que vos propres tâches.");
                            break;
                        }

                        System.out.print("Nouveau titre : ");
                        task.setTitle(scanner.nextLine());
                        System.out.print("Nouvelle description : ");
                        task.setDescription(scanner.nextLine());
                        System.out.print("Est-ce terminé ? (true/false) : ");
                        task.setDone(Boolean.parseBoolean(scanner.nextLine()));

                        System.out.println("Tâche mise à jour.");
                    } catch (NotFoundException e) {
                        System.out.println("Tâche introuvable.");
                    }
                }

                case "5" -> {
                    System.out.println("Fin du programme.");
                    return;
                }

                default -> System.out.println("Commande inconnue.");
            }
        }
    }
}
