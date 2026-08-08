package utng.gtid2.dab;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de acceso a los datos de productos.
 *
 * Define las operaciones necesarias para administrar productos.
 *
 * @author Juan Diego Aguilar Bautista
 */
public interface ProductoDAO {

    /**
     * Inserta un producto.
     *
     * @param producto Producto que se desea insertar.
     * @return ID asignado al producto.
     */
    int insert(Producto producto);

    /**
     * Obtiene todos los productos.
     *
     * @return Lista de productos.
     */
    List<Producto> findAll();

    /**
     * Busca un producto por su código.
     *
     * @param codigo Código del producto.
     * @return Producto encontrado, si existe.
     */
    Optional<Producto> findByCodigo(int codigo);

    /**
     * Actualiza el stock de un producto.
     *
     * @param codigo Código del producto.
     * @param stock Nuevo stock.
     */
    void updateStock(int codigo, int stock);

    /**
     * Elimina un producto por su código.
     *
     * @param codigo Código del producto.
     */
    void delete(int codigo);
}