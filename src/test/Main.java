package test;

import modelo.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Instanciamos lo necesario para un cliente
        Ubicacion ubicacionCliente = new Ubicacion(100, 100);
        Contacto contactoCliente = new Contacto("cliente@mail.com", "12341234", ubicacionCliente);
        Cliente cliente = new Cliente(1, contactoCliente, "Apellido", "Nombre", 12341234);

        //Instanciamos lo necesario para un comercio
        Ubicacion ubicacionComercio = new Ubicacion(50, 50);
        Contacto contactoComercio = new Contacto("Comercio@mail.com", "12341234", ubicacionComercio);
        Comercio comercio = new Comercio(1, contactoComercio, "Nombre Comercio", 999999999,
                10, 10, 3, 10,
                10);
        comercio.agregarArticulo(1, "ArticuloA", "7790000000003", 23);
        comercio.agregarArticulo(2, "ArticuloB", "7790000000013", 23);

        Carrito carrito=  comercio.generarCarrito(cliente, false).get(0);

        carrito.agregarItemCarrito(comercio.traerArticulo(1),1);
        carrito.agregarItemCarrito(comercio.traerArticulo(2),2);
        carrito.agregarItemCarrito(comercio.traerArticulo(2),2);

        System.out.println(carrito);

    }
}
