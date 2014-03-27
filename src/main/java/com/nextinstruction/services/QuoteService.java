package com.nextinstruction.services;

import com.nextinstruction.model.Quote;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class QuoteService {
    private Random rand = new Random();

    // taken from: http://www.brainyquote.com/quotes/topics/topic_funny.html
    private static final Quote[] QUOTES= {
            new Quote("I believe that if life gives you lemons, you should make lemonade... "    +
                    "And try to find somebody whose life has given them vodka, and have a party." ,
                    "Ron White"),
            new Quote("Do not take life too seriously. You will never get out of it alive.",
                    "Elbert Hubbard"),
            new Quote("Wine is constant proof that God loves us and loves to see us happy.",
                    "Benjamin Franklin"),
            new Quote("A day without sunshine is like, you know, night.",
                    "Steve Martin"),
            new Quote("A woman's mind is cleaner than a man's: She changes it more often.",
                    "Oliver Herford"),
            new Quote("If the facts don't fit the theory, change the facts.",
                    "Albert Einstein"),
            new Quote("I always wanted to be somebody, but now I realize I should have been more specific.",
                    "Lily Tomlin")
    };

    public Quote getRandomQuote() {
        return QUOTES[rand.nextInt(QUOTES.length)];
    }
}
