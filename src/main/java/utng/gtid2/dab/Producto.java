package utng.gtid2.dab;

/**
 * Representa un producto de la tienda.
 * Contiene la información básica de un producto, como su identificador,
 * nombre, precio y cantidad disponible en stock.
 *
 * @author Juan Diego Aguilar Bautista
 */
public class Producto {

    private int codigo;
    private String nombre;
    private double precio;
    private int stock;
    private boolean activo;

    /**
     * Constructor vacío.
     * El producto se crea activo por defecto.
     */
    public Producto() {
        this.activo = true;
    }

    /**
     * Inicializa un producto con todos sus atributos.
     *
     * @param codigo Identificador único del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param stock Cantidad disponible en inventario.
     * @throws IllegalArgumentException si el nombre es nulo
     *         o si el precio es negativo.
     */
    public Producto(int codigo, String nombre, double precio, int stock) {

        if (nombre == null) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo.");
        }

        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.activo = true;
    }

    /**
     * Obtiene el identificador del producto.
     *
     * @return Identificador del producto.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return Precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Obtiene la cantidad disponible en stock.
     *
     * @return Stock del producto.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Indica si el producto está activo.
     *
     * @return true si el producto está activo.
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Asigna el identificador del producto.
     *
     * @param codigo Nuevo identificador del producto.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Asigna el nombre del producto.
     *
     * @param nombre Nuevo nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Asigna el precio del producto.
     *
     * @param precio Nuevo precio del producto.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Asigna la cantidad disponible del producto.
     *
     * @param stock Nuevo stock del producto.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Cambia el estado activo del producto.
     *
     * @param activo Nuevo estado del producto.
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}