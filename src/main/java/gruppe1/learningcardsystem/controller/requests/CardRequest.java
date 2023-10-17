package gruppe1.learningcardsystem.controller.requests;

import lombok.Data;

@Data
public class CardRequest {
    private Integer successCount;
    private String question;
    private String answer; // Je nach Kartentyp (Text, Zahl, Multiple Choice) könnte das Antwortformat unterschiedlich sein.
    private String[] choices; // Dieses Feld ist für Multiple Choice-Karten erforderlich.
    private boolean[] answerCorrect; // Für Multiple Choice-Karten, um die richtigen Antworten zu kennzeichnen.
}
