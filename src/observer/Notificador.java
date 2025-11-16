package observer;

import models.Pedido;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Notificador {
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    public void suscribir(Observer o) { observers.add(o); }
    public void desuscribir(Observer o) { observers.remove(o); }

    public void notificar(EventType type, Pedido pedido, String extraInfo) {
        for (Observer o : observers) {
            o.onNotify(type, pedido, extraInfo);
        }
    }
}
