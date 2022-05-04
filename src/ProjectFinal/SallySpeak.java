package ProjectFinal;

import java.util.Scanner;

public class SallySpeak {
    Scanner tokenizer;
    String token;
    boolean legal;

    public SallySpeak(String proposedSentence)
    {
        tokenizer = new Scanner(proposedSentence);
        legal = true;
        nextToken();
    }

    void nextToken()
    {
        if(tokenizer.hasNext()) token = tokenizer.next();
        else token = "bite";
    }

    boolean isLegal()
    {
        reaction();
        expression();

        while (true) {
            if (!legal) break;
            if (token.equals("bite")) break;
            else expression();
        }

        return legal;
    }

    void reaction()
    {
        if (!legal) return;
        if (token.equals("earperk")) {
            nextToken();
            while (true) {
                if (token.equals("tailwag")) nextToken();
                else break;
            }
        }
        else legal = false;
    }

    void expression()
    {
        if (!legal) return;
        if (token.equals("bark"))
        {
            nextToken();
            whimperGrowl();
            reaction();
        }
        else legal = false;
    }

    void whimperGrowl()
    {
        if (!legal) return;
        if (token.equals("whimper") || token.equals("growl")) nextToken();
        else legal = false;
    }
}

