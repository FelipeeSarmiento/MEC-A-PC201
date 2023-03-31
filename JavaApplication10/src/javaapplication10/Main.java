import java.util.*;

public class Main {
   
    public static void main(String[] args) {
       
    
        Estacion estacionA = new Estacion("Estación A");
        Estacion estacionB = new Estacion("Estación B");
        Estacion estacionC = new Estacion("Estación C");
        Estacion estacionD = new Estacion("Estación D");
        Estacion estacionE = new Estacion("Estación E");
        Estacion estacionF = new Estacion("Estación F");
       
        Ruta ruta1 = new Ruta("Ruta 1");
        ruta1.agregarEstacion(estacionA);
        ruta1.agregarEstacion(estacionB);
        ruta1.agregarEstacion(estacionC);
        ruta1.agregarEstacion(estacionD);
        ruta1.agregarEstacion(estacionE);
       
        Ruta ruta2 = new Ruta("Ruta 2");
        ruta2.agregarEstacion(estacionC);
        ruta2.agregarEstacion(estacionD);
        ruta2.agregarEstacion(estacionE);
        ruta2.agregarEstacion(estacionF);
       

        Bus bus1 = new Bus("A01", ruta1);
        Bus bus2 = new Bus("B02", ruta2);
       
      
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la estación de destino (A, B, C, D, E o F): ");
        String estacionDestino = scanner.nextLine();
       
   
        Tarjeta tarjeta = new Tarjeta("1234", 10000);
        Usuario usuario = new Usuario(nombre, tarjeta, estacionA);
       
    
        System.out.println("Hora de salida del bus: " + bus1.horaSalida(estacionA, estacionB));
        usuario.tomarBus(bus1, estacionDestino);
       
       
        usuario.bajarBus();
       
  
        System.out.println("Saldo actual de la tarjeta: " + tarjeta.getSaldo());
    }
}

class Estacion {
   
    private String nombre;
   
    public Estacion(String nombre) {
        this.nombre = nombre;
    }
   
    public String getNombre() {
        return nombre;
    }
   
}

class Ruta {
   
    private String nombre;
    private List<Estacion> estaciones;
   
    public Ruta(String nombre) {
        this.nombre = nombre;
        this.estaciones = new ArrayList<Estacion>();
    }
   
    public String getNombre() {
        return nombre;
    }
   
    public void agregarEstacion(Estacion estacion) {
        estaciones.add(estacion);
    }
   
    public int tiempoViaje(Estacion origen, Estacion destino) {
        int tiempo = 0;
        boolean encontradaOrigen = false;
        for (Estacion estacion : estaciones) {
            if (estacion == origen) {
                encontradaOrigen = true;
            } else if (encontradaOrigen) {
                tiempo += 5; 
                if (estacion == destino) {
                    break;
                }
            }
        }
        return tiempo;
    }
   
}

class Bus {
private String nombre;
    private Ruta ruta;
   
    public Bus(String nombre, Ruta ruta) {
        this.nombre = nombre;
        this.ruta = ruta;
    }
   
    public String getNombre() {
        return nombre;
    }
   
    public Ruta getRuta() {
        return ruta;
    }
   
    public String horaSalida(Estacion origen, Estacion destino) {
        int tiempoViaje = ruta.tiempoViaje(origen, destino);
        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.MINUTE, tiempoViaje);
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        return String.format("%02d:%02d", hora, minutos);
    }
   
}

class Tarjeta {
   
    private String numero;
    private int saldo;
   
    public Tarjeta(String numero, int saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }
   
    public String getNumero() {
        return numero;
    }
   
    public int getSaldo() {
        return saldo;
    }
   
    public void descontarSaldo(int monto) {
        saldo -= monto;
    }
   
}

class Usuario {
   
    private String nombre;
    private Tarjeta tarjeta;
    private Estacion estacionActual;
   
    public Usuario(String nombre, Tarjeta tarjeta, Estacion estacionActual) {
        this.nombre = nombre;
        this.tarjeta = tarjeta;
        this.estacionActual = estacionActual;
    }
   
    public void tomarBus(Bus bus, String estacionDestino) {
        Estacion destino = null;
        switch (estacionDestino.toUpperCase()) {
            case "A":
                destino = new Estacion("Estación A");
                break;
            case "B":
                destino = new Estacion("Estación B");
                break;
            case "C":
                destino = new Estacion("Estación C");
                break;
            case "D":
                destino = new Estacion("Estación D");
                break;
            case "E":
                destino = new Estacion("Estación E");
                break;
            case "F":
                destino = new Estacion("Estación F");
                break;
            default:
                System.out.println("Estación de destino inválida");
                return;
        }
        int monto = bus.getRuta().tiempoViaje(estacionActual, destino) * 200;
        if (tarjeta.getSaldo() < monto) {
            System.out.println("Saldo insuficiente");
        } else {
            tarjeta.descontarSaldo(monto);
            estacionActual = destino;
            System.out.println("Ha llegado a la " + estacionActual.getNombre());
        }
    }
   
    public void bajarBus() {
        System.out.println("Ha bajado del bus en la " + estacionActual.getNombre());
    }
    
}