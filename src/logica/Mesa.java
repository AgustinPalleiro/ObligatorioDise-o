/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import excepciones.CartaException;
import excepciones.MesaException;
import excepciones.SaldoException;
import java.util.ArrayList;
import observador.Observable;


/**
 *
 * @author emili
 */
public class Mesa extends Observable{
    
    private int cantMinimaJugadores;
    private double apuestaMinima;
    private int comision;
    private static int ultimoNumeroDeMesa = 0;
    private int numeroDeMesa;
    private ArrayList<Jugador> cantJugadoresActual = new ArrayList();
    private ArrayList<Mano> manos = new ArrayList();
    private double recaudacion;
    private EstadoMesa estado = new EstadoMesaAbierta(this);
    private Mazo mazo;
    private boolean manoCreada = false;

    public enum Estados{Abierta, Iniciada, Finalizada}; 
    
    public enum EventosMesa{cambioEstadoMesa};

    public Mesa(int cantMinimaJugadores, double apuestaMinima, int comision) throws CartaException {
        this.cantMinimaJugadores = cantMinimaJugadores;
        this.apuestaMinima = apuestaMinima;
        this.comision = comision;
        ultimoNumeroDeMesa++;
        this.numeroDeMesa = ultimoNumeroDeMesa;
        this.mazo= new Mazo();
    }

    public int getCantMinimaJugadores() {
        return cantMinimaJugadores;
    }

    public double getApuestaMinima() {
        return apuestaMinima;
    }

    public int getComision() {
        return comision;
    }
    
    public int getNumeroDeMesa() {
        return numeroDeMesa;
    }

    public ArrayList<Jugador> getCantJugadoresActual() {
        return cantJugadoresActual;
    }

    public ArrayList<Mano> getListaManos() {
        return manos;
    }
    
    public int cantJugadoresEnMesa(){
        return cantJugadoresActual.size();
    }
    
    public int numeroManoActual(){
        return manos.size();
    }
    
    public Mano getManoActual(){
        if(!manos.isEmpty())
            return manos.get(manos.size()-1);
        return null;
    }
    
    public double montoTotalManos(){
        double montoTotal = 0;
        for(Mano m: manos){
            montoTotal += m.getPozo().getMonto();
        }
        return montoTotal;
    }

    public double montoTotalApostado() {
        double resultado=0;
        for(Mano m: manos){
            resultado+=m.getPozo().getMonto();
        }
        return resultado;
    }

    public boolean isManoCreada() {
        return manoCreada;
    }
    
    public void manoCreadaVerificar(){
        manoCreada = true;
    }
    
    public double montoRecaudado(){
        double montoTotalApostado = montoTotalApostado(); // Total apostado en todas las manos
        double porcentajeComision = (double) comision / 100; // Convierte la comisión a porcentaje
        return montoTotalApostado * porcentajeComision; // Calcula el monto recaudado
    }
    
    public EstadoMesa getEstado() {
        return estado;
    }
    
    
    //MANEJO DE ESTADOS
    
  /*  protected void cambiarEstado(EstadoMesa estadoNuevo){
        estado = estadoNuevo;
        avisar(EventosMesa.cambioEstadoMesa);
    }
    */
    
    protected void cambiarEstado(EstadoMesa estadoNuevo) {
    estado = estadoNuevo;  // Cambia el estado
    avisar(EventosMesa.cambioEstadoMesa);  // Notifica a los observadores
        /*if (estadoNuevo.getDescripcion().equals("Iniciada")) {
            try {
                crearMano();  // Solo la mesa crea una mano cuando inicia
            } catch (MesaException ex) {
            // Maneja el error de manera centralizada
            }
        }*/
    }      
    
    //MÉTODOS ACCESORIOS
    
    public void validar() throws MesaException{
        if(cantMinimaJugadores < 2 || cantMinimaJugadores > 5)
            throw new MesaException("Cantidad de jugadores inválida");
        if(apuestaMinima < 1)
            throw new MesaException("La apuesta mínima debe ser mayor a 1");
        if(comision < 1)
            throw new MesaException("La comision debe ser mayor a 1");
    }

    public Jugador jugadorGanadorDeUltimaMano() {
        if (manos.isEmpty()) {
            return null;
        }
        Mano ultimaMano = manos.get(manos.size() - 1);
        return ultimaMano.getJugadorGanador();
    }
    
    public String figuraGanadoraDeMano(){
        if (manos.isEmpty()) {
            return "No hay manos aún"; 
        }
        Mano ultimaMano = manos.get(manos.size() - 1);
        return ultimaMano.getFiguraGanadora();
    }
    
    public void agregarJugadorAMesa(Jugador jugador) throws MesaException{
        estado.agregarJugadorAMesa(jugador);
    }
    
    public void hacerAgregarJugadorAMesa(Jugador unJ) throws MesaException {
        if (cantJugadoresEnMesa() >= cantMinimaJugadores) {
            throw new MesaException("La mesa ya ha alcanzado el límite de jugadores.");
        }
        if (unJ.getSaldo() < (getApuestaMinima() * 10)) {
            throw new MesaException("Saldo insuficiente");
        }
        if (cantJugadoresActual.contains(unJ)) {
            throw new MesaException("Ya ha ingresado a una mesa");
        }
        cantJugadoresActual.add(unJ);
        if (cantJugadoresEnMesa() == cantMinimaJugadores) {
            crearMano();
            cambiarEstado(new EstadoMesaIniciada(this));
            Fachada.getInstancia().avisar(Eventos.eventos.cambioListaMesas);
        }
    }
    
    public void crearMano() throws MesaException{
        estado.crearMano(this);
    }
    
    public void hacerCrearMano(Mesa mesa) throws MesaException, CartaException, SaldoException {
        if (mesa.getCantJugadoresActual().isEmpty()) {
            throw new MesaException("No hay jugadores suficientes para iniciar una mano");
        }
        Mano nuevaMano = new Mano(mesa, mesa.getCantJugadoresActual());
       
        if (nuevaMano == null) {
            throw new MesaException("La mano no se ha creado correctamente");
        }
        
        manos.add(nuevaMano);
        if (mesa.getManoActual() == null) {
            throw new MesaException("La mano no se ha asignado a la mesa");
        }
    }
   
    
    
    
    
    
    
    
    
    
    
    
    public double ultimpoPozo() {
        if (manos == null || manos.size() < 2) {
            return 0;  // Si la lista es nula o tiene menos de 2 elementos, retorna 0
        }
        Mano laMano = manos.get(manos.size() - 2);  // Obtiene la penúltima mano
        return laMano.getPozo().getMonto();  // Retorna el monto del pozo de la penúltima mano
    }

    public void quitarJugador(Jugador jugador) throws MesaException{
        estado.quitarJugador(jugador);
    }
    
    public void hacerQuitarJugador(Jugador unJ){
        if(cantJugadoresActual.contains(unJ)){
            cantJugadoresActual.remove(unJ);
        Mano manoActual = getManoActual();
        if (manoActual != null)
            manoActual.quitarJugador(unJ);
        Fachada.getInstancia().avisar(Eventos.eventos.cambioListaMesas);
        }
    }
    
    public Jugador getJugador(Jugador unJ) {
        for (Jugador jugador : cantJugadoresActual) {
            if (jugador.getCedula().equals(unJ.getCedula())) {
                return jugador; 
            }
        }
        return null; 
    }
}