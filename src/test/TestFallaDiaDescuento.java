package test;

import modelo.*;

import java.time.LocalDate;

public class TestFallaDiaDescuento {

    public static void main(String[] args) {
            try {
                    //Instanciamos lo necesario para un cliente los clientes y a los  clientes----------------------------------
                    Ubicacion ubicacionCliente1 = new Ubicacion(100, 100);
                    Contacto contactoCliente1 = new Contacto("cliente1@mail.com", "12341234", ubicacionCliente1);
                    Ubicacion ubicacionCliente2 = new Ubicacion(150, 150);
                    Contacto contactoCliente2 = new Contacto("cliente2@mail.com", "12341234", ubicacionCliente2);
                    Cliente cliente1 = new Cliente(1, contactoCliente1, "ApellidoCliente1", "NombreCliente1",
                            45123412);
                    Cliente cliente2 = new Cliente(2, contactoCliente2, "ApellidoCliente2", "NombreCliente2",
                            46123413);
                    //----------------------------------------------------------------------------------------------------------

                    //Instanciamos lo necesario para un comercio y al comercio--------------------------------------------------
                    Ubicacion ubicacionComercio = new Ubicacion(50, 50);
                    Contacto contactoComercio = new Contacto("Comercio@mail.com", "12341234", ubicacionComercio);
                    Comercio comercio = new Comercio(1, contactoComercio, "Almacen Granate", 20203457384l,
                            10, 10, 20, 40,
                            20);
                    //----------------------------------------------------------------------------------------------------------

                    //Se agregan articulos al comercio
                    comercio.agregarArticulo(1, "ArticuloA", "7790000000003", 10);
                    comercio.agregarArticulo(2, "ArticuloB", "7790000000013", 5);
                    comercio.agregarArticulo(3, "ArticuloC", "7790000000023", 15);
                    comercio.agregarArticulo(4, "ArticuloD", "7790000000043", 40);
                    //----------------------------------------------------------------------------------------------------------

                    //Se agreagan carritos al comercio -------------------------------------------------------------------------
                    comercio.agregarCarrito(cliente1);
                    comercio.agregarCarrito(cliente2);

                    Carrito carritoCliente1 = comercio.traerCarrito(cliente1.getId(), false).get(0);
                    Carrito carritoCliente2 = comercio.traerCarrito(cliente2.getId(), false).get(0);

                    //NOTA: Este set de entrega se hace para poder calcular el total en el estado actual del desarrollo
                    carritoCliente1.setEntrega(new Entrega(1, LocalDate.now(), true));
                    carritoCliente2.setEntrega(new Entrega(1, LocalDate.now(), false));
                    //----------------------------------------------------------------------------------------------------------


                    //Se agregan items a los carritos---------------------------------------------------------------------------
                    carritoCliente1.agregarItemCarrito(comercio.traerArticulo(1), 1);
                    carritoCliente1.agregarItemCarrito(comercio.traerArticulo(2), 2);
                    carritoCliente1.agregarItemCarrito(comercio.traerArticulo(3), 2);
                    carritoCliente1.agregarItemCarrito(comercio.traerArticulo(3), 3);

                    carritoCliente2.agregarItemCarrito(comercio.traerArticulo(1), 1);
                    carritoCliente2.agregarItemCarrito(comercio.traerArticulo(1), 2);
                    carritoCliente2.agregarItemCarrito(comercio.traerArticulo(3), 2);
                    //Se remueve 3 unidades del articulo con id 1 del carrito del cliente 2
                    carritoCliente2.removerItemCarrito(comercio.traerArticulo(1), 3);

                    carritoCliente2.agregarItemCarrito(comercio.traerArticulo(4), 2);
                    carritoCliente2.agregarItemCarrito(comercio.traerArticulo(4), 1);
                    //----------------------------------------------------------------------------------------------------------


                    System.out.println(comercio.getCarritos().get(0));
                    System.out.println("\n\n");
                    System.out.println(comercio.getCarritos().get(1));
                    System.out.println("\n\n");
                    System.out.println("Total a pagar del cliente 1: " + comercio.finalizarCompra(cliente1));
                    System.out.println("Total a pagar del cliente 2: " + comercio.finalizarCompra(cliente2));


            }catch (RuntimeException exception){
                    System.out.println("Exception: " + exception.getMessage());
            }

    }
}
