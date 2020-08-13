package com.lyssers.wheelofmisfortune;

import javafx.application.Application;

/**
 *
 * @author alyssa
 */
/*
  Fake Main Class To Fool Java 
  Into Running JavaFX Without Passing any special VM Parameters
  It is incredibly frustrating to have to resort to this dumb hack
  All just to make an Application usable for end-users

  This shouldn't cause any issues, 
  as JavaFX is bundled into the uber-jar with maven-shade for win,mac & linux

  If a better solution exists that packages all dependencies for all platforms and does not require anything on the part of the user
  other than JRE and the ability to double click a file and you tell me about it, free beer's on me.

*/
public class FakeMain {
     public static void main(String[] args) {
    Application.launch(MainClass.class);     
}
}
