package observer;

import models.Pedido;

public interface Observer {
    void onNotify(EventType type, Pedido pedido, String extraInfo);
}
