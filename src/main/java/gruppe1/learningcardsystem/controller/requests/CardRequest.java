package gruppe1.learningcardsystem.controller.requests;

import lombok.Data;

//diese Klasse beinhaltet alle Attribute die eine Request benötigt
@Data
public class CardRequest {
    private Integer successCount; //wie oft wurde eine Karte richtig beantwortet -> wird automatisch bei Erstellung 0
    private String question; // die Frage einer Card
    private String answer; // Je nach Kartentyp (Text, Zahl, Multiple Choice) könnte das Antwortformat unterschiedlich sein.
    private String[] choices; // Dieses Feld ist für Multiple Choice-Karten erforderlich.
    private boolean[] answerCorrect; // Für Multiple Choice-Karten, um die richtigen Antworten zu kennzeichnen.
    private String userAnswer; //Antwort für alle Karten da wir sonst mehrere userAnswers mit anderen Datentypen bräuchten (ist eleganter mMn)

    public CardRequest(){

    }
}
