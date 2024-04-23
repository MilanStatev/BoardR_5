package com.models;

import com.models.contracts.Printable;

import java.util.ArrayList;
import java.util.List;

public class Board implements Printable {
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

    @Override
    public void displayHistory() {
        items.forEach(BoardItem::displayHistory);
    }
}
