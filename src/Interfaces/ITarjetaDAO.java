
package Interfaces;
import Models.Tarjeta;

import java.util.ArrayList;
import java.util.List;

public interface ITarjetaDAO {
    boolean guardar(Tarjeta tarjeta);
    ArrayList<Tarjeta> listar();
    Tarjeta buscar(int id);

    boolean eliminar(int id);
}
