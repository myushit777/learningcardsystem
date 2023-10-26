package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class Card {
    private Long id;
    private Integer successCount = 0;
    //LocalDateTime Datentyp nimmt die aktuelle Zeit unserer Zeitzone
    private LocalDateTime creationDate = LocalDateTime.now();
    private LocalDateTime nextDueDate = LocalDateTime.now();
    //draft ist true da nicht direkt jede KArte abgefragt werden soll
    private boolean isDraft = true;
    private String question;

    //default Constructor
    public Card() {

    }

    //Constructor
    public Card(Long id, Integer successCount, LocalDateTime creationDate, LocalDateTime nextDueDate, boolean isDraft, String question) {
        this.id = id;
        this.successCount = successCount;
        this.creationDate = creationDate;
        this.nextDueDate = nextDueDate;
        this.isDraft = isDraft;
        this.question = question;
    }
}