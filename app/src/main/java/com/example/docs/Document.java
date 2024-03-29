package com.example.docs;

import com.google.firebase.firestore.Exclude;

public class Document {
    private  String fileName;
    private String content;
    private String docID;
    private String date;
    private Boolean isStarred = false;
    private Boolean onBin = false;
    public Document() {

    }

    public Document(String fileName, String content,String date) {
        this.fileName = fileName;
        this.date=date;
        this.content = content;
    }

    public Boolean getStarred() {
        return isStarred;
    }

    public void setStarred(Boolean starred) {
        isStarred = starred;
    }

    public Boolean getOnBin() {
        return onBin;
    }

    public void setOnBin(Boolean onBin) {
        this.onBin = onBin;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Exclude
    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
