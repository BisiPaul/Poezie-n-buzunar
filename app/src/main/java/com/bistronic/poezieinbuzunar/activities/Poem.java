package com.bistronic.poezieinbuzunar.activities;

/**
 * Created by Larisa on 15.09.2017.
 */
public class Poem implements Comparable<Poem>{
    public String title;
    public String author;
    public String text;

    public Poem(String t,String a, String text){
        this.title = t;
        this.author = a;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public int compareTo(Poem o) {
        return this.author.compareTo(o.getAuthor()); // author sort in ascending order
        //return o.getAuthor().compareTo(this.author); // use this line for author name sort in descending order
    }
}
