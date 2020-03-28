package by.shynkevich.math.example.domain.term;

public interface Term<T> {

    boolean isHidden();

    T getValue();

    String show();
}
