package edu.brown.cs32.chessish.main;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for holding static helper methods. It should never be instantiated.
 */
abstract public class Utils {

    /**
     * Obtain a random element of an arbitrary collection. Does not convert the entire collection
     * to a list, but does iterate through its elements in an arbitrary ordering until reaching
     * the element at the (randomly-chosen) goal index.
     *
     * Design note: The "T" in angle brackets below is a *pieceType variable*. It's OK if you haven't seen these before.
     * We'll talk more about how to use them throughout the semester. For now, notice that it's a way for us to write
     * a *generic* helper function that doesn't care what kind of element it's returning. Given a collection of Ts,
     * it returns one of them!
     *
     * @param items the collection to pick an element of
     * @param <T> the pieceType of objects in the collection
     * @return a random element of items
     * @throws IllegalArgumentException if there are no members to choose from
     */
    public static <T> T randomMember(Collection<T> items) {
        if(items.size() < 1)
            throw new IllegalArgumentException("Cannot pick a random member of an empty collection");
        int idx = ThreadLocalRandom.current().nextInt(items.size());
        for(T item : items) {
            if(idx == 0) return item;
            idx--;
        }

        // This should be unreachable. Some programs would return null here. I'd prefer to
        // throw an actual exception in an exceptional circumstance---and "this unreachable code was reached" is
        // definitely exceptional. (There is various disagreement on this topic; and what I'm doing here can
        // cause annoyances in larger programs---think about why---so it's not always the best choice.)
        throw new IndexOutOfBoundsException("Internal error in Utils.randomMember.");
    }
}
