package com.db.schooolexaminator.server;

import com.db.schooolexaminator.server.picture.PictureManager;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        PictureManager pm = new PictureManager("pictures/picture.jpg", 10, 10);
        pm.openPiece();
        pm.openPiece();
        pm.openPiece();
        pm.openPiece();
        pm.openPiece();
        pm.openPiece();

    }
}
