package dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class Answers {
    @Id
    @GeneratedValue
    private Long id;
    private String firstAnswer;
    private String The2ndAnswer;
    private String The3thAnswer;

    public Answers() {
    }

    public Answers(String firstAnswer, String the2ndAnswer, String the3thAnswer) {
        this.firstAnswer = firstAnswer;
        The2ndAnswer = the2ndAnswer;
        The3thAnswer = the3thAnswer;
    }

    public String getFirstAnswer() {
        return firstAnswer;
    }

    public void setFirstAnswer(String firstAnswer) {
        this.firstAnswer = firstAnswer;
    }

    public String getThe2ndAnswer() {
        return The2ndAnswer;
    }

    public void setThe2ndAnswer(String the2ndAnswer) {
        The2ndAnswer = the2ndAnswer;
    }

    public String getThe3thAnswer() {
        return The3thAnswer;
    }

    public void setThe3thAnswer(String the3thAnswer) {
        The3thAnswer = the3thAnswer;
    }
}
