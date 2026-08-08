package utng.gtid2.dab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de ProductoDAO utilizando SQL Server.
 *
 * @author Juan Diego Aguilar Bautista
 */
public class ProductoDAOImpl implements ProductoDAO {

    /**
     * Inserta un producto en la base de datos.
     *
     * @param producto Producto que se desea insertar.
     * @return ID generado para el producto.
     */
    @Override
    public int insert(Producto producto) {

        String sql = """
                INSERT INTO Producto (Nombre, Precio, Stock)
                VALUES (?, ?, ?)
                """;

        try (Connection conexion = ConexionDB.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(
                     sql,
                     java.sql.Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Obtiene todos los productos registrados.
     *
     * @return Lista de productos.
     */
    @Override
    public List<Producto> findAll() {

        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT Id, Nombre, Precio, Stock FROM Producto";

        try (Connection conexion = ConexionDB.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Producto producto = new Producto(
                        rs.getInt("Id"),
                        rs.getString("Nombre"),
                        rs.getDouble("Precio"),
                        rs.getInt("Stock")
                );

                productos.add(producto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productos;
    }

    /**
     * Busca un producto por su código.
     *
     * @param codigo Código del producto.
     * @return Producto encontrado o vacío si no existe.
     */
    @Override
    public Optional<Producto> findByCodigo(int codigo) {

        String sql = "SELECT Id, Nombre, Precio, Stock FROM Producto WHERE Id = ?";

        try (Connection conexion = ConexionDB.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Producto producto = new Producto(
                            rs.getInt("Id"),
                            rs.getString("Nombre"),
                            rs.getDouble("Precio"),
                            rs.getInt("Stock")
                    );

                    return Optional.of(producto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * Actualiza el stock de un producto.
     *
     * @param codigo Código del producto.
     * @param stock Nuevo stock.
     */
    @Override
    public void updateStock(int codigo, int stock) {

        String sql = "UPDATE Producto SET Stock = ? WHERE Id = ?";

        try (Connection conexion = ConexionDB.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, stock);
            ps.setInt(2, codigo);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un producto por su código.
     *
     * @param codigo Código del producto.
     */
    @Override
    public void delete(int codigo) {

        String sql = "DELETE FROM Producto WHERE Id = ?";

        try (Connection conexion = ConexionDB.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, codigo);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}