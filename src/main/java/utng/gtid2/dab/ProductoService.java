package utng.gtid2.dab;

import java.util.List;

/**
 * Clase de servicio encargada de gestionar las operaciones
 * relacionadas con los productos.
 *
 * Actúa como intermediario entre la aplicación y el DAO.
 *
 * @author Juan Diego Aguilar Bautista
 */
public class ProductoService {

    /**
     * DAO utilizado para acceder a los productos.
     */
    private ProductoDAO dao;

    /**
     * Constructor por defecto.
     *
     * Utiliza la implementación de SQL Server.
     */
    public ProductoService() {
        this.dao = new ProductoDAOImpl();
    }

    /**
     * Constructor utilizado para pruebas.
     *
     * Permite utilizar ProductoDAOMemoria como doble de prueba,
     * evitando depender de una base de datos real.
     *
     * @param dao Implementación del DAO.
     */
    public ProductoService(ProductoDAO dao) {
        this.dao = dao;
    }

    /**
     * Registra un producto.
     *
     * @param producto Producto que se desea registrar.
     * @return Código asignado al producto.
     * @throws IllegalArgumentException si el producto es nulo.
     */
    public int registrar(Producto producto) {

        if (producto == null) {
            throw new IllegalArgumentException(
                    "El producto no puede ser nulo."
            );
        }

        return dao.insert(producto);
    }

    /**
     * Vende una cantidad determinada de un producto.
     *
     * @param codigo Código del producto.
     * @param cantidad Cantidad que se desea vender.
     * @throws IllegalArgumentException si la cantidad es menor o igual a cero.
     * @throws IllegalStateException si no existe el producto
     *         o no hay suficiente stock.
     */
    public void vender(int codigo, int cantidad) {

        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero."
            );
        }

        Producto producto = dao.findByCodigo(codigo)
                .orElseThrow(() -> new IllegalStateException(
                        "El producto no existe."
                ));

        if (producto.getStock() < cantidad) {
            throw new IllegalStateException(
                    "Stock insuficiente."
            );
        }

        int nuevoStock = producto.getStock() - cantidad;

        dao.updateStock(codigo, nuevoStock);
    }

    /**
     * Busca un producto por su código.
     *
     * @param codigo Código del producto.
     * @return Producto encontrado o null si no existe.
     */
    public Producto buscarProducto(int codigo) {

        return dao.findByCodigo(codigo).orElse(null);
    }

    /**
     * Obtiene todos los productos.
     *
     * @return Lista de productos.
     */
    public List<Producto> listarProductos() {

        return dao.findAll();
    }

    /**
     * Elimina un producto.
     *
     * @param codigo Código del producto.
     */
    public void eliminarProducto(int codigo) {

        dao.delete(codigo);
    }

    public double calcularTotalConDescuento(Producto producto, double descuento) {
        if (producto == null) {
            throw new IllegalArgumentException(
                    "El producto no puede ser nulo."
            );
        }

        if (descuento < 0) {
            throw new IllegalArgumentException(
                    "El descuento no puede ser negativo."
            );
        }

        if (descuento > 1) {
            throw new IllegalArgumentException(
                    "El descuento no puede ser mayor que 1."
            );
        }

        return producto.getPrecio() * (1 - descuento);
    }
}