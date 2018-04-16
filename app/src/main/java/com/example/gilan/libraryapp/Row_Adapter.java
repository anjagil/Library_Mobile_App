package com.example.gilan.libraryapp;

public class Row_Adapter {

    public int icon;
    public String title;
    public String author;
    public String genre;




    public Row_Adapter(int ic_menu_send, String zbrodnia_i_kara, String s, String dramat) {
        this.icon = ic_menu_send;
        this.title = zbrodnia_i_kara;
        this.author = s;
        this.genre = dramat;
    }
}
