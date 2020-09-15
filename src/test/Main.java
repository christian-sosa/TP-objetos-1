package test;

import modelo.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n" +
                " ▄▄▄▄▄▄▄▄▄▄▄  ▄            ▄▄       ▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄        ▄       ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄        ▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░░▌▐░▌          ▐░░▌     ▐░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌      ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌      ▐░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                "▐░█▀▀▀▀▀▀▀█░▌▐░▌          ▐░▌░▌   ▐░▐░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀▀▀ ▐░▌░▌     ▐░▌     ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░▌░▌     ▐░▌▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀ ▐░█▀▀▀▀▀▀▀▀▀ \n" +
                "▐░▌       ▐░▌▐░▌          ▐░▌▐░▌ ▐░▌▐░▌▐░▌       ▐░▌▐░▌          ▐░▌          ▐░▌▐░▌    ▐░▌     ▐░▌          ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌▐░▌    ▐░▌▐░▌       ▐░▌     ▐░▌     ▐░▌          \n" +
                "▐░█▄▄▄▄▄▄▄█░▌▐░▌          ▐░▌ ▐░▐░▌ ▐░▌▐░█▄▄▄▄▄▄▄█░▌▐░▌          ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌ ▐░▌   ▐░▌     ▐░▌ ▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌▐░▌ ▐░▌   ▐░▌▐░█▄▄▄▄▄▄▄█░▌     ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░░▌▐░▌          ▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌▐░▌          ▐░░░░░░░░░░░▌▐░▌  ▐░▌  ▐░▌     ▐░▌▐░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌     ▐░▌     ▐░░░░░░░░░░░▌\n" +
                "▐░█▀▀▀▀▀▀▀█░▌▐░▌          ▐░▌   ▀   ▐░▌▐░█▀▀▀▀▀▀▀█░▌▐░▌          ▐░█▀▀▀▀▀▀▀▀▀ ▐░▌   ▐░▌ ▐░▌     ▐░▌ ▀▀▀▀▀▀█░▌▐░█▀▀▀▀█░█▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░▌   ▐░▌ ▐░▌▐░█▀▀▀▀▀▀▀█░▌     ▐░▌     ▐░█▀▀▀▀▀▀▀▀▀ \n" +
                "▐░▌       ▐░▌▐░▌          ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌          ▐░▌          ▐░▌    ▐░▌▐░▌     ▐░▌       ▐░▌▐░▌     ▐░▌  ▐░▌       ▐░▌▐░▌    ▐░▌▐░▌▐░▌       ▐░▌     ▐░▌     ▐░▌          \n" +
                "▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░▌       ▐░▌▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌     ▐░▐░▌     ▐░█▄▄▄▄▄▄▄█░▌▐░▌      ▐░▌ ▐░▌       ▐░▌▐░▌     ▐░▐░▌▐░▌       ▐░▌     ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ \n" +
                "▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌      ▐░░▌     ▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░▌       ▐░▌▐░▌      ▐░░▌▐░▌       ▐░▌     ▐░▌     ▐░░░░░░░░░░░▌\n" +
                " ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀        ▀▀       ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀         ▀  ▀        ▀▀  ▀         ▀       ▀       ▀▀▀▀▀▀▀▀▀▀▀ \n" +
                "                                                                                                                                                                                           \n");
        //Instanciamos lo necesario para un cliente
        Ubicacion ubicacionCliente = new Ubicacion(100, 100);
        Contacto contactoCliente = new Contacto("cliente@mail.com", "12341234", ubicacionCliente);
        Cliente cliente = new Cliente(1, contactoCliente, "Apellido", "Nombre", 12341234);

        //Instanciamos lo necesario para un comercio
        Ubicacion ubicacionComercio = new Ubicacion(50, 50);
        Contacto contactoComercio = new Contacto("Comercio@mail.com", "12341234", ubicacionComercio);
        DiaRetiro diaRetiro = new DiaRetiro(1, 2, LocalTime.of(9, 30),
                LocalTime.of(18, 0), 3);
        Comercio comercio = new Comercio(1, contactoComercio, "Nombre Comercio", 999999999,
                10, 10, 3, 10,
                10, new ArrayList<>());
        comercio.getDiaRetiros().add(diaRetiro);

        // Instanciamos una entrega
        Entrega entrega = new Entrega(1, LocalDate.now(), true);

        //Agregamos carrito al comercio

        Carrito carrito = new Carrito(1, LocalDate.now(), LocalTime.now(),
                false, 10, cliente, new ArrayList<>(), entrega);


        //Agregamos items al carrito
        Articulo articulo = new Articulo(1, "Articulo", "CodBarras", 12.3);

        Scanner in = new Scanner(System.in);
    }
}
