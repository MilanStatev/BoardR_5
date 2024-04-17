import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        BoardItem item = new BoardItem("Registration doesn't work", LocalDate.now().plusDays(2));
        item.advanceStatus();
        BoardItem anotherItem = new BoardItem("Encrypt user data", LocalDate.now().plusDays(10));

        Board board = new Board();

        board.getItems().add(item);
        board.getItems().add(anotherItem);

        for (BoardItem boardItem : board.getItems()) {
            boardItem.advanceStatus();
        }

        for (BoardItem boardItem : board.getItems()) {
            System.out.println(boardItem.viewInfo());
        }
    }
}