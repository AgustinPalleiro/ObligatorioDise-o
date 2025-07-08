package logica;

import excepciones.CartaException;
import excepciones.ManoException;
import excepciones.SaldoException;
import java.util.ArrayList;
import java.util.Random;
import observador.Observable;
import panelCartasPoker.CartaPoker;

public class Mano extends Observable{
    
    private static int ultimoNumeroMano = 0;
    private int numeroMano;
    private Pozo pozo;
    private ArrayList<Jugador> jugadoresEnMano;
    private ArrayList<Jugador> jugadorPasaron;
    private ArrayList<Figura> figurasPosibles;
    private Jugador jugadorGanador;
    private Figura figuraGanadora;
    private Mazo mazo;
    private Mesa mesa;
    private EstadoMano estado = new EstadoManoEsperandoApuesta(this);
    private Apuesta laApuesta;
    

    public Mano(Mesa mesa, ArrayList<Jugador> jugadoresEnMano) throws CartaException, SaldoException {
        this.mazo = new Mazo();
        this.mesa = mesa;
        this.pozo = new Pozo();
        this.jugadoresEnMano = jugadoresEnMano;
        this.figurasPosibles = cargarFiguras();
        this.numeroMano = ultimoNumeroMano++;
        this.jugadorPasaron= new ArrayList<Jugador>();
        asignarCartas();
        this.laApuesta= new Apuesta();
    }      

    public enum Estados{EsperandoApuesta, ApuestaIniciada, PidiendoCartas, Terminada};
    public enum Eventos{cambioEstadoMano};

    public int getNumeroMano() {
        return numeroMano;
    }
   
    public Pozo getPozo() {
        return pozo;
    }

    public Jugador getJugadorGanador() {
        return jugadorGanador;
    }
    
    public String getNombreJugadorGanador() {
        if(jugadorGanador == null)
            return "No hay ganador aún";
        return jugadorGanador.getNombreCompleto();
    }

    public ArrayList<Jugador> getJugadoresEnMano() {
        return jugadoresEnMano;
    }
    
    public int getCanitdadJugadoresEnMano() {
        return jugadoresEnMano.size();
    }

    public ArrayList<Figura> getFigurasPosibles() {
        return figurasPosibles;
    }

    public String getFiguraGanadora() {
        if(figuraGanadora == null)
            return "No hay ganador aún";
        return figuraGanadora.toString();
    }

    public Apuesta getLaApuesta() {
        return laApuesta;
    }

    public EstadoMano getEstado() {
        return estado;
    }
    
    public String estadoString(){
        return estado.toString();
    }

    public void setJugadorGanador(Jugador jugadorGanador) {
        this.jugadorGanador = jugadorGanador;
    }

    public void setFiguraGanadora(Figura figuraGanadora) {
        this.figuraGanadora = figuraGanadora;
    }
    
    public ArrayList<Figura> cargarFiguras(){
        ArrayList<Figura> figuras = new ArrayList<>();
        figuras.add(new Poker());
        figuras.add(new Escalera());
        figuras.add(new Pierna());
        figuras.add(new Par());
        figuras.add(new SinFigura());
        return figuras;
    }
    
    //Si el jugador ganardor es null se acumula el pozo para la siguiente mano
    
    //MANEJO DE ESTADOS
    
    protected void cambiarEstado(EstadoMano estadoNuevo){
        estado = estadoNuevo;
        avisar(Eventos.cambioEstadoMano);
    }

    //METODOS ACCESORIOS
    
    public void asignarCartas() throws SaldoException {
        for (Jugador jugador : jugadoresEnMano) {
            ArrayList<CartaPoker> cartasParaJugador = mazo.repartir5Cartas();
            jugador.agarrarCartas(cartasParaJugador); 
            setPozo(mesa.getApuestaMinima(),jugador);
        }
    }

    public int cantJugadoresEnMano(){
        return jugadoresEnMano.size();
    }
    
    public void setPozo(double apuestaMinima, Jugador unJ) throws SaldoException {
        double monto = apuestaMinima + (mesa.ultimpoPozo() / getCanitdadJugadoresEnMano());
        unJ.bajarSaldo(apuestaMinima);
        pozo.incremetarPozo(monto); 
    }
    
    public void setManoEnJugadores(){
        for(Jugador j: jugadoresEnMano){
            j.setManoActual(this);
        }
        cambiarSituacionJugador();
    }
    
    public void setManoEnJugador(Jugador j){
        j.setManoActual(this);
        cambiarSituacionJugador();
    }
    
    public void cambiarSituacionJugador(){
        for(Jugador unJ: jugadoresEnMano){
            unJ.cambiarSituacion();
        }
    }
    
    public void agregarAListaPaso(Jugador j) throws ManoException {
        System.out.println(numeroMano);
        System.out.println(estado.getDescripcion().toString());
        estado.agregaarAlistaPaso(j);
    }
    
        public void hacerAgregarAListaPaso(Jugador j) throws ManoException{
        if(jugadorPasaron.contains(j)){
            throw new ManoException("Ya pasaste");
        }else{
            jugadorPasaron.add(j);
            validarPasaron();
        }
    }
        
    private void validarPasaron() {
        if(laApuesta.getValor()>0){
            if(jugadorPasaron.size()+1==jugadoresEnMano.size()){
                this.jugadorGanador=laApuesta.getCreadorApuesta();
                this.jugadorGanador.incrementarSaldo(laApuesta.getValor()+this.pozo.getMonto());
                this.pozo.bajarPozo(this.pozo.getMonto());
                cambiarEstado(new EstadoManoTerminada(this));
                mesa.manoCreadaVerificar(); //PRUEBA
            }
            if(laApuesta.getApostaron().size()+this.jugadorPasaron.size()==this.cantJugadoresEnMano()){
                determinarGanador(laApuesta.getApostaron());
                System.out.println("Jugador : " + jugadorGanador.getNombreCompleto());
                this.jugadorGanador.incrementarSaldo(laApuesta.getValor()+this.pozo.getMonto());
                this.pozo.bajarPozo(this.pozo.getMonto());
                cambiarEstado(new EstadoManoTerminada(this));
                mesa.manoCreadaVerificar(); //PRUEBA
            }
            if(jugadorPasaron.size()==jugadoresEnMano.size()){
            cambiarEstado(new EstadoManoTerminada(this));
            mesa.manoCreadaVerificar(); //PRUEBA
            }
        }
    }

    public void jugadorBorrarCartas(){
        for(Jugador j: jugadoresEnMano){
            j.borrarCartas();
        }
    }
    
    public void iniciarApusta(Jugador unJ, Double monto) throws SaldoException{
        Jugador elapostador = mesa.getJugador(unJ);
        pozo.incrementarPozo(monto);
        elapostador.bajarSaldo(monto);
    }
    
    public void quitarJugador(Jugador jugador){
        jugadoresEnMano.remove(jugador);
    }
    
    public void determinarGanador(ArrayList<Jugador> jugadores) {
        if (jugadores != null || jugadores.isEmpty()) {
            Jugador ganador = null;
            //compararFiguras();
            int cartaAltaGanador = Integer.MIN_VALUE;

    for (Jugador jugador : jugadores) {
        Figura figura = jugador.getLaFigura();
        if (figura instanceof SinFigura) {
            // Comparar las cartas más altas de los jugadores sin figura
            int cartaAltaJugador = figura.cartaAlta(jugador.getCartas());
            if (cartaAltaJugador > cartaAltaGanador) {
                ganador = jugador;
                cartaAltaGanador = cartaAltaJugador;
            } else if (cartaAltaJugador == cartaAltaGanador) {
                ganador = desempatarPorKickers(ganador, jugador);
            }
        } else {

            if (ganador == null || figura.getValor() > ganador.getLaFigura().getValor()) {
                ganador = jugador;
            } else if (figura.getValor() == ganador.getLaFigura().getValor()) {
                int cartaAltaJugador = figura.cartaAlta(jugador.getCartas());
                int cartaAltaGanadorFigura = ganador.getLaFigura().cartaAlta(ganador.getCartas());
                if (cartaAltaJugador > cartaAltaGanadorFigura) {
                    ganador = jugador;
                }
            }
        }
    }

    this.jugadorGanador= ganador;
  
    }
}
 
 
    /*public void compararFiguras(){
        ArrayList<Jugador> jugadores = laApuesta.getApostaron();
        for(Jugador j : jugadores){
            if
        }
    }*/

   private Jugador desempatarPorKickers(Jugador jugador1, Jugador jugador2) {
        ArrayList<CartaPoker> cartasJugador1 = jugador1.getCartas();
        ArrayList<CartaPoker> cartasJugador2 = jugador2.getCartas();
        cartasJugador1.sort((c1, c2) -> Integer.compare(c2.getValorCarta(), c1.getValorCarta()));
        cartasJugador2.sort((c1, c2) -> Integer.compare(c2.getValorCarta(), c1.getValorCarta()));

        for (int i = 0; i < Math.min(cartasJugador1.size(), cartasJugador2.size()); i++) {
            int valorCartaJugador1 = cartasJugador1.get(i).getValorCarta();
            int valorCartaJugador2 = cartasJugador2.get(i).getValorCarta();
            if (valorCartaJugador1 > valorCartaJugador2) {
                return jugador1;
            } else if (valorCartaJugador1 < valorCartaJugador2) {
            return jugador2;
            }
        }

        return null;
   }
   
   public void crearApuesta() throws ManoException{
        estado.crearApuesta(this);
   }

   public void agregarApuesta(Jugador jugador, String monto) throws SaldoException {
        double importeApuesta = Double.parseDouble(monto);
        jugador.bajarSaldo(importeApuesta);
        
        Apuesta laApuesta = new Apuesta(importeApuesta,jugador);
        laApuesta.getApostaron().add(jugador);
        this.laApuesta=laApuesta;
        cambiarEstado(new EstadoManoApuestaIniciada(this));
   }

   public void igualarApuesta(Jugador jugador) throws SaldoException {
       if (laApuesta.getApostaron().contains(jugador)) {
            throw new SaldoException("El jugador ya ha realizado una apuesta.");
       }
       jugador.bajarSaldo(laApuesta.getValor());
       laApuesta.agregarAListaApostaron(jugador);
        validarPasaron();
   }

}
