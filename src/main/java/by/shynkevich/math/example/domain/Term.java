package by.shynkevich.math.example.domain;

public interface Term<T> {

    boolean isHidden();

    T getValue();

    String show();
}
