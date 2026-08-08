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

    @Test
    void calcularTotalConDescuento_descuento10Porciento_retorna90() {

        // Arrange
        Producto producto = new Producto(
                1,
                "Producto prueba",
                100.0,
                10
        );

        // Act
        double resultado = service.calcularTotalConDescuento(
                producto,
                0.10
        );

        // Assert
        assertEquals(
                90.0,
                resultado,
                0.001,
                "El precio con 10% de descuento debe ser 90.0."
        );
    }

    @Test
    void calcularTotalConDescuento_productoNulo_lanzaIllegalArgumentException() {

        assertThrows(
                IllegalArgumentException.class,
                () -> service.calcularTotalConDescuento(null, 0.10),
                "Debe lanzar IllegalArgumentException cuando el producto sea nulo."
        );
    }

    @Test
    void calcularTotalConDescuento_descuentoNegativo_lanzaIllegalArgumentException() {

        Producto producto = new Producto(
                1,
                "Producto prueba",
                100.0,
                10
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> service.calcularTotalConDescuento(producto, -0.10),
                "Debe lanzar IllegalArgumentException cuando el descuento sea menor que 0."
        );
    }

    @Test
    void calcularTotalConDescuento_descuentoMayorQueUno_lanzaIllegalArgumentException() {

        Producto producto = new Producto(
                1,
                "Producto prueba",
                100.0,
                10
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> service.calcularTotalConDescuento(producto, 1.10),
                "Debe lanzar IllegalArgumentException cuando el descuento sea mayor que 1."
        );
    }
}