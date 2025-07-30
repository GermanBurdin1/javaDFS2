package model;

import java.time.LocalDate;

public class DatedTask extends Task {
    private LocalDate dueDate;

    public DatedTask(String title, String description, User user, LocalDate dueDate) {
        super(title, description, user);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (Due: " + dueDate + ")";
    }
}
