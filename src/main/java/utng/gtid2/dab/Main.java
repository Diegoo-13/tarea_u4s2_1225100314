package utng.gtid2.dab;

public class Main {

    public static void main(String[] args) {

        ProductoDAO dao = new ProductoDAOImpl();

        // ===== AGREGAR =====
        // Producto nuevo = new Producto(0, "Teclado", 500.0, 10);
        // dao.agregarProducto(nuevo);

        // ===== BUSCAR =====
        // Producto encontrado = dao.buscarProducto(1);

        // ===== LISTAR =====
        // List<Producto> productos = dao.listarProductos();

        // ===== ACTUALIZAR =====
        // Producto actualizar = new Producto(1, "Mouse Gamer", 399.99, 15);
        // dao.actualizarProducto(actualizar);

        // ===== ELIMINAR =====
        // dao.eliminarProducto(1);
    }
}