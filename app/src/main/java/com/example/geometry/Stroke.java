package com.example.geometry;

public class Stroke {
    private String function;
    private String angle;
    private int answer=-2;

    public Stroke(String function, String angle) {
        this.function = function;
        this.angle = angle;
    }

    public String getFunction() {
        return function;
    }

    public String getAngle() {
        return angle;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
