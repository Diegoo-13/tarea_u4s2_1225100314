package utng.gtid2.dab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para la clase Producto.
 *
 * @author Juan Diego Aguilar Bautista
 */
public class ProductoTest {

    /**
     * Verifica que el constructor cree correctamente un producto válido.
     */
    @Test
    void constructor_datosValidos_creaProductoCorrectamente() {

        // Arrange
        Producto producto = new Producto(
                1,
                "Mouse Logitech",
                299.99,
                20
        );

        // Assert
        assertAll(
                () -> assertEquals(
                        1,
                        producto.getCodigo(),
                        "El código debe coincidir con el proporcionado."
                ),
                () -> assertEquals(
                        "Mouse Logitech",
                        producto.getNombre(),
                        "El nombre debe coincidir con el proporcionado."
                ),
                () -> assertEquals(
                        299.99,
                        producto.getPrecio(),
                        "El precio debe coincidir con el proporcionado."
                ),
                () -> assertEquals(
                        20,
                        producto.getStock(),
                        "El stock debe coincidir con el proporcionado."
                )
        );
    }

    /**
     * Verifica que no se permita un precio negativo.
     */
    @Test
    void constructor_precioNegativo_lanzaIllegalArgumentException() {

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> new Producto(
                        1,
                        "Mouse Logitech",
                        -100.0,
                        20
                ),
                "Debe lanzarse una excepción cuando el precio sea negativo."
        );
    }

    /**
     * Verifica que no se permita un nombre nulo.
     */
    @Test
    void constructor_nombreNulo_lanzaIllegalArgumentException() {

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> new Producto(
                        1,
                        null,
                        299.99,
                        20
                ),
                "Debe lanzarse una excepción cuando el nombre sea nulo."
        );
    }

    /**
     * Verifica que un producto nuevo esté activo por defecto.
     */
    @Test
    void isActivo_productoNuevo_retornaTrue() {

        // Arrange
        Producto producto = new Producto(
                1,
                "Mouse Logitech",
                299.99,
                20
        );

        // Assert
        assertTrue(
                producto.isActivo(),
                "Un producto nuevo debe estar activo por defecto."
        );
    }
}