package utng.gtid2.dab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para ProductoService.
 *
 * @author Juan Diego Aguilar Bautista
 */
public class ProductoServiceTest {

    private ProductoService service;

    /**
     * Inicializa el servicio utilizando el DAO en memoria.
     */
    @BeforeEach
    void setUp() {
        service = new ProductoService(new ProductoDAOMemoria());
    }

    /**
     * Verifica que registrar un producto válido
     * devuelva el código asignado.
     */
    @Test
    void registrar_productoValido_retornaCodigo() {

        // Arrange
        Producto producto = new Producto(
                0,
                "Mouse Logitech",
                299.99,
                20
        );

        // Act
        int codigo = service.registrar(producto);

        // Assert
        assertEquals(
                1,
                codigo,
                "El primer producto registrado debe recibir el código 1."
        );
    }

    /**
     * Verifica que registrar un producto nulo
     * lance IllegalArgumentException.
     */
    @Test
    void registrar_productoNulo_lanzaIllegalArgumentException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> service.registrar(null),
                "Registrar un producto nulo debe lanzar IllegalArgumentException."
        );
    }

    /**
     * Verifica que una venta con stock suficiente
     * reduzca correctamente el stock.
     */
    @Test
    void vender_stockSuficiente_reduceStockCorrectamente() {

        // Arrange
        Producto producto = new Producto(
                0,
                "Mouse Logitech",
                299.99,
                20
        );

        int codigo = service.registrar(producto);

        // Act
        service.vender(codigo, 5);

        // Assert
        Producto resultado = service.buscarProducto(codigo);

        assertNotNull(
                resultado,
                "El producto debe existir después de registrarlo."
        );

        assertEquals(
                15,
                resultado.getStock(),
                "El stock debe reducirse de 20 a 15 después de vender 5 unidades."
        );
    }

    /**
     * Verifica que una venta que supera el stock disponible
     * lance IllegalStateException.
     */
    @Test
    void vender_stockInsuficiente_lanzaIllegalStateException() {

        // Arrange
        Producto producto = new Producto(
                0,
                "Mouse Logitech",
                299.99,
                5
        );

        int codigo = service.registrar(producto);

        // Act & Assert
        assertThrows(
                IllegalStateException.class,
                () -> service.vender(codigo, 10),
                "Vender más unidades que el stock disponible debe lanzar IllegalStateException."
        );
    }
}