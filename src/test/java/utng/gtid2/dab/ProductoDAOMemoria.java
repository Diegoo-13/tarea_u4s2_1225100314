package utng.gtid2.dab;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación en memoria de ProductoDAO.
 *
 * Utiliza una lista para almacenar temporalmente los productos
 * y no requiere conexión con una base de datos.
 *
 * @author Juan Diego Aguilar Bautista
 */
public class ProductoDAOMemoria implements ProductoDAO {

    /**
     * Lista que almacena los productos en memoria.
     */
    private final List<Producto> productos = new ArrayList<>();

    /**
     * Código que se asignará al siguiente producto.
     */
    private int siguienteCodigo = 1;

    /**
     * Inserta un producto en la lista y le asigna un código consecutivo.
     *
     * @param producto Producto que se desea insertar.
     * @return Código asignado al producto.
     */
    @Override
    public int insert(Producto producto) {

        producto.setCodigo(siguienteCodigo);

        productos.add(producto);

        return siguienteCodigo++;
    }

    /**
     * Obtiene todos los productos almacenados.
     *
     * Se devuelve una copia de la lista para evitar que el código externo
     * pueda modificar directamente la colección interna.
     *
     * @return Copia de la lista de productos.
     */
    @Override
    public List<Producto> findAll() {

        return new ArrayList<>(productos);
    }

    /**
     * Busca un producto por su código utilizando Stream y Optional.
     *
     * @param codigo Código del producto.
     * @return Optional con el producto encontrado o vacío si no existe.
     */
    @Override
    public Optional<Producto> findByCodigo(int codigo) {

        return productos.stream()
                .filter(producto -> producto.getCodigo() == codigo)
                .findFirst();
    }

    /**
     * Actualiza el stock de un producto.
     *
     * @param codigo Código del producto.
     * @param stock Nuevo valor del stock.
     */
    @Override
    public void updateStock(int codigo, int stock) {

        findByCodigo(codigo).ifPresent(
                producto -> producto.setStock(stock)
        );
    }

    /**
     * Elimina un producto por su código.
     *
     * @param codigo Código del producto que se desea eliminar.
     */
    @Override
    public void delete(int codigo) {

        productos.removeIf(
                producto -> producto.getCodigo() == codigo
        );
    }
}