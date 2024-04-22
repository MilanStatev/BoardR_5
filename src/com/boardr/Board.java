package com.boardr;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<BoardItem> items;

    public Board() {
        this.items = new ArrayList<>();
    }

    public List<BoardItem> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(BoardItem boardItem) {
        if (items.contains(boardItem)) {
            throw new IllegalArgumentException("Item already in the list");
        }

        items.add(boardItem);
    }

    public int totalItems(){
        return items.size();
    }
}
