package com.example.quiz;

import java.util.List;

public class Quiz {
    private List<Question> questions;
    public Quiz(List<Question> questions){
        this.questions = questions;
    }
    public boolean checkAnswer(boolean answer, int currentQuestion){
        if(questions.get(currentQuestion).isAnswer() == answer){
            return true;
        }
        else
            return false;


    }
    public String getQuestion(int currentQuestion){
        return questions.get(currentQuestion).getQuestion();
    }
}
