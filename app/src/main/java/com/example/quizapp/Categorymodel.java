package com.example.quizapp;

public class Categorymodel {
    private String docID;
    private String name ;
    private int noOFTEXT;

    public Categorymodel(String docID, String name, int noOFTEXT) {
        this.docID = docID;
        this.name = name;
        this.noOFTEXT = noOFTEXT;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOFTEXT() {
        return noOFTEXT;
    }

    public void setNoOFTEXT(int noOFTEXT) {
        this.noOFTEXT = noOFTEXT;
    }
}

