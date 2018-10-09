package ua.com.foxminded.calculator.service;

public interface BiFunction<T, U, R> {
	R apply(T t, U u);
}
