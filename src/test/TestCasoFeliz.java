package test;

import modelo.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestCasoFeliz {

    public static void main(String[] args) {

        //Instanciamos lo necesario para los clientes y a los  clientes----------------------------------
        Ubicacion ubicacion = new Ubicacion(100, 100);

        Contacto contactoCliente1 = new Contacto("cliente1@mail.com", "11111111", ubicacion);
        Contacto contactoCliente2 = new Contacto("cliente2@mail.com", "22222222", ubicacion);
        Contacto contactoCliente3 = new Contacto("cliente3@mail.com", "33333333", ubicacion);
        Contacto contactoCliente4 = new Contacto("cliente4@mail.com", "44444444", ubicacion);

        Cliente cliente1 = new Cliente(1, contactoCliente1, "ApellidoCliente1", "NombreCliente1",
                45123412); //Envio
        Cliente cliente2 = new Cliente(2, contactoCliente2, "ApellidoCliente2", "NombreCliente2",
                32123413);
        Cliente cliente3 = new Cliente(3, contactoCliente3, "ApellidoCliente3", "NombreCliente3",
                25123412);
        Cliente cliente4 = new Cliente(4, contactoCliente4, "ApellidoCliente4", "NombreCliente4",
                42122334);


        //----------------------------------------------------------------------------------------------------------

        //Instanciamos lo necesario para un comercio y al comercio--------------------------------------------------
        Ubicacion ubicacionComercio = new Ubicacion(50, 50);
        Contacto contactoComercio = new Contacto("Comercio@mail.com", "12341234", ubicacionComercio);
        Comercio comercio = new Comercio(1, contactoComercio, "Almacen Granate", 20346127172l,
                10, 10, 3, 40,
                20);

        //Se intenta agregar un comercio con cuit invalido



        comercio.agregarDiaRetiro(1, LocalTime.of(9, 0), LocalTime.of(18, 0)
                , 30);
        comercio.agregarDiaRetiro(2, LocalTime.of(10, 0), LocalTime.of(15, 0)
                , 30);
        comercio.agregarDiaRetiro(3, LocalTime.of(9, 30), LocalTime.of(16, 0)
                , 30);



        comercio.generarTurnos(LocalDate.of(2020, 10, 7));
        comercio.generarTurnos(LocalDate.of(2020, 10, 6));
        comercio.generarTurnos(LocalDate.of(2020, 10, 5));
        System.out.println("Traer turnos libres del lunes" + comercio.traerTurnos(LocalDate.of(2020, 10, 7),
                false, LocalTime.of(9, 30), LocalTime.of(16, 00)));
        //----------------------------------------------------------------------------------------------------------

        //Se agregan articulos al comercio
        comercio.agregarArticulo(1, "ArticuloA", "7790000000003", 10);
        comercio.agregarArticulo(2, "ArticuloB", "7790000000013", 5);
        comercio.agregarArticulo(3, "ArticuloC", "7790000000023", 15);
        comercio.agregarArticulo(4, "ArticuloD", "7790000000043", 40);


        //----------------------------------------------------------------------------------------------------------

        //Se agregan carritos al comercio -------------------------------------------------------------------------
        comercio.agregarCarrito(cliente1);
        comercio.agregarCarrito(cliente2);
        comercio.agregarCarrito(cliente3);
        comercio.agregarCarrito(cliente4);

        //----------------------------------------------------------------------------------------------------------
        Carrito carritoCliente1 = comercio.traerCarrito(cliente1.getId(), false).get(0);
        Carrito carritoCliente2 = comercio.traerCarrito(cliente2.getId(), false).get(0);
        Carrito carritoCliente3 = comercio.traerCarrito(cliente3.getId(), false).get(0);
        Carrito carritoCliente4 = comercio.traerCarrito(cliente4.getId(), false).get(0);
        System.out.println("\n*C A R R I T O S   R E C I E N   C R E A D O S*");
        System.out.println("\nEstado del carrito del cliente con id " + cliente1.getId() + " antes de agregar items "
                + carritoCliente1);
        System.out.println("\nEstado del carrito del cliente con id " + cliente2.getId() + " antes de agregar items "
                + carritoCliente2);
        System.out.println("\nEstado del carrito del cliente con id " + cliente3.getId() + " antes de agregar items "
                + carritoCliente3);
        System.out.println("\nEstado del carrito del cliente con id " + cliente4.getId() + " antes de agregar items "
                + carritoCliente4);

        //----------------------------------------------------------------------------------------------------------


        //Se agregan items a los carritos---------------------------------------------------------------------------
        carritoCliente1.agregarItemCarrito(comercio.traerArticulo(1), 1);
        carritoCliente1.agregarItemCarrito(comercio.traerArticulo(2), 2);
        carritoCliente1.agregarItemCarrito(comercio.traerArticulo(3), 2);
        carritoCliente1.agregarItemCarrito(comercio.traerArticulo(3), 3);

        carritoCliente2.agregarItemCarrito(comercio.traerArticulo(1), 1);
        carritoCliente2.agregarItemCarrito(comercio.traerArticulo(2), 2);
        carritoCliente2.agregarItemCarrito(comercio.traerArticulo(3), 2);
        carritoCliente2.agregarItemCarrito(comercio.traerArticulo(3), 3);

        carritoCliente3.agregarItemCarrito(comercio.traerArticulo(1), 1);
        carritoCliente3.agregarItemCarrito(comercio.traerArticulo(2), 2);
        carritoCliente3.agregarItemCarrito(comercio.traerArticulo(3), 2);
        carritoCliente3.agregarItemCarrito(comercio.traerArticulo(3), 3);

        carritoCliente4.agregarItemCarrito(comercio.traerArticulo(1), 1);
        carritoCliente4.agregarItemCarrito(comercio.traerArticulo(2), 2);
        carritoCliente4.agregarItemCarrito(comercio.traerArticulo(3), 2);
        carritoCliente4.agregarItemCarrito(comercio.traerArticulo(3), 3);




        System.out.println("\n*C A R R I T O S   C O N   I T E M S   S I N   E N T R E G A S   P R O G R A M A D A S*");
        System.out.println("\nEstado del carrito del cliente con id " + cliente1.getId() + " antes de programar entrega"
                + carritoCliente1);
        System.out.println("\nEstado del carrito del cliente con id " + cliente2.getId() + " antes de programar entrega "
                + carritoCliente2);
        System.out.println("\nEstado del carrito del cliente con id " + cliente3.getId() + " antes de programar entrega "
                + carritoCliente3);
        System.out.println("\nEstado del carrito del cliente con id " + cliente4.getId() + " antes de programar entrega "
                + carritoCliente4);



        //SE AGREGAN LOS TIPOS DE ENTREGA------------------------------------------------------------------------------
        //envio y no efectivo
        comercio.agregarEntrega(cliente1, LocalDate.of(2020, 10, 2), false,
                LocalTime.of(10, 30), LocalTime.of(11, 0), true);

        //Envio y  efectivo
        comercio.agregarEntrega(cliente2, LocalDate.of(2020, 10, 5), true,
                LocalTime.of(10, 30), LocalTime.of(11, 0), true);

        //Retiro y efectivo
        comercio.agregarEntrega(cliente3, LocalDate.of(2020, 10, 7), true,
                LocalTime.of(11, 30), LocalTime.of(12, 0), false);

        //Retiro y no efectivo
        comercio.agregarEntrega(cliente4, LocalDate.of(2020, 10, 5), false,
                LocalTime.of(12, 30), LocalTime.of(13, 0), false);

        System.out.println("\n*C A R R I T O S   C O N   I T E M S   C O N    E N T R E G A S   P R O G R A M A D A S*");
        System.out.println("\nEstado del carrito del cliente con id " + cliente1.getId() + " antes de calcular el total"
                + carritoCliente1);
        System.out.println("\nEstado del carrito del cliente con id " + cliente2.getId() + " antes de calcular el total"
                + carritoCliente2);
        System.out.println("\nEstado del carrito del cliente con id " + cliente3.getId() + " antes de calcular el total"
                + carritoCliente3);
        System.out.println("\nEstado del carrito del cliente con id " + cliente4.getId() + " antes de calcular el total"
                + carritoCliente4);

        //SE CALCULAN LOS TOTALES
        System.out.println("\n*C A L C U L O   T O T A L E S   Y   C I E R R E   D E   C A R R I T O S *");
        System.out.println("\n Total del carrito del cliente con id "  + cliente1.getId() + ": "
                + comercio.totalAPagar(cliente1.getId()));
        System.out.println("\n Total del carrito del cliente con id "  + cliente2.getId() + ": "
                + comercio.totalAPagar(cliente2.getId()));
        System.out.println("\n Total del carrito del cliente con id "  + cliente3.getId() + ": "
                + comercio.totalAPagar(cliente3.getId()));
        System.out.println("\n Total del carrito del cliente con id "  + cliente4.getId() + ": "
                + comercio.totalAPagar(cliente4.getId()));


        //Excepciones---------------------------------------------------------------------------------------------------
        System.out.println("\n*E X C E P C I O N E S*");
        try {
            comercio = new Comercio(1, contactoComercio, "Almacen Granate", 203461172l,
                    10, 10, 6, 40,
                    20);
        } catch (RuntimeException exception) {
            System.out.println("Se espera excepcion por cuit invalido--> " + exception.getMessage());
        }

        try {
            comercio.agregarDiaRetiro(8, LocalTime.of(9, 0), LocalTime.of(18, 0), 30);
        } catch (RuntimeException exception) {
            System.out.println("Se espera excepcion por dia de retiro invalido--> " + exception.getMessage());
        }

        try {
            comercio.agregarArticulo(3, "ArticuloC", "77900023", 15);
        } catch (RuntimeException exception) {
            System.out.println("Se espera excepcion por codigo de barras no valido--> " + exception.getMessage());
        }
        try {
            comercio.agregarArticulo(3, "ArticuloC", "7790000000003", 15);
        } catch (RuntimeException exception) {
            System.out.println("Se espera excepcion por item existente--> " + exception.getMessage());
        }

        try{
            comercio.traerArticulo(999);
        }catch (RuntimeException exception ){
            System.out.println("Se espera excepcion articulo no existe--> " + exception.getMessage());
        }

    }
}
