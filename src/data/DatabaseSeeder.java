package data;

import model.User;
import model.Task;

public class DatabaseSeeder {
    public static void seed(DatabaseAccess db) {
        User alice = new User("Alice");
        User bob = new User("Bob");

        db.addUser(alice);
        db.addUser(bob);

        db.addTask(new Task("Lire", "Lire un livre", alice));
        db.addTask(new Task("Coder", "Faire un projet Java", bob));
    }
}
