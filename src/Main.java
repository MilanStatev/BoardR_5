import com.boardr.Task;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Task task = new Task("Test the application flow", "Pesho", LocalDate.now().plusDays(1));

        Task task1 = new Task("Test the application flow", "Pesho", LocalDate.now().plusDays(1));

        task1.setAssignee("Tosho");

        task.advanceStatus();

        task.advanceStatus();

        task.setAssignee("Gosho");

        task.displayHistory();
        task1.displayHistory();
    }
}