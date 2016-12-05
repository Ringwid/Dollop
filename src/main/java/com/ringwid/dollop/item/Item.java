package com.ringwid.dollop.item;

public interface Item {

    String getName();

    String getAddress();

    enum ItemType {
        BuyItNow, Auction, Mixed
    }

    ItemType getType();

}
