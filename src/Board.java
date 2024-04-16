import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<BoardItem> items;

    public Board() {
        this.items = new ArrayList<>();
    }

    public List<BoardItem> getItems() {
        return items;
    }
}
