package com.example.bookborrowingapp;

public class MainModel {
    String bookName, bookAuthor, bookUrl, renteeName, renteePhone, renteeUID;
    MainModel(){

    }

    public MainModel(String bookName, String bookAuthor, String bookUrl, String renteeName, String renteePhone, String renteeUID) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookUrl = bookUrl;
        this.renteeName = renteeName;
        this.renteePhone = renteePhone;
        this.renteeUID = renteeUID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getRenteeName() {
        return renteeName;
    }

    public void setRenteeName(String renteeName) {
        this.renteeName = renteeName;
    }

    public String getRenteePhone() {
        return renteePhone;
    }

    public void setRenteePhone(String renteePhone) {
        this.renteePhone = renteePhone;
    }

    public String getRenteeUID() {
        return renteeUID;
    }
    public void setRenteeUID(String renteeUID) {
        this.renteeUID = renteeUID;
    }
}
