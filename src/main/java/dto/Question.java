package dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.List;
@Entity
public class Question {
    @Id
    @GeneratedValue
    private int questionId;
    private String TheQuestion;
    @OneToOne
    private Answers TheAnswers;
    private String correctAnswer;

    public Question() {
    }

    public Question(String theQuestion, Answers theAnswers, String correctAnswer) {
        TheQuestion = theQuestion;
        TheAnswers = theAnswers;
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getTheQuestion() {
        return TheQuestion;
    }

    public void setTheQuestion(String theQuestion) {
        TheQuestion = theQuestion;
    }

    public Answers getTheAnswers() {
        return TheAnswers;
    }

    public void setTheAnswers(Answers theAnswers) {
        TheAnswers = theAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }




}
