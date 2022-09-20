package main;

import database.DBHandler;

public class Main {
    public static void main(String[] args) {
        DBHandler handler = DBHandler.getInstance();
        handler.readData();
    }
}
