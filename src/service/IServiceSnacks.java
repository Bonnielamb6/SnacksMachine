package service;

import domain.Snack;

import java.util.List;

public interface IServiceSnacks {
    void addSnack(Snack snack);

    void showSnacks();

    List<Snack> getSnacks();
}
