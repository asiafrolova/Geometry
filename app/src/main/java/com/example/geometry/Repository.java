package com.example.geometry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Repository {
    private boolean night = true;
    private boolean newFunctions;
    private String[] answers = new String[3];
    private String[] correctAnswers = new String[3];
    private static Repository instance = null;

    public static Repository newInstance() {
        if (instance == null) {
            instance = new Repository();


        }
        return instance;

    }

    public boolean isNight() {
        return night;
    }

    public void setNight(boolean night) {
        this.night = night;
    }

    public boolean isNewFunctions() {
        return newFunctions;
    }

    public void setNewFunctions(boolean newFunctions) {
        this.newFunctions = newFunctions;
    }

    public String[] getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int i,String string) {
        correctAnswers[i] = string;
    }

    public void get(String answer, int i) {
        answers[i]=answer;
    }


    public String[] getRepository() {
        String[] answers2=new String[answers.length];
        System.arraycopy(answers, 0, answers2, 0, answers.length);
        return answers;
    }
}
