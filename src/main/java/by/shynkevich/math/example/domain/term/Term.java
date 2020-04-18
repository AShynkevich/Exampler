package by.shynkevich.math.example.domain.term;

import java.io.Serializable;

/**
 * Represents a term of any example.
 * <p>
 * It's a part of any math statement what contains <b>value</b>
 * and <b>hidden</b> flag what says systems that term should be hidden and needs
 * a clarification from trainee.
 * </p>
 *
 * @param <T>
 */
public interface Term<T> extends Serializable {

    boolean isHidden();

    T getValue();

    String show();

    boolean equals(String obj);
}
