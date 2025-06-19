package aszteroida_banyaszat;

/**
 * Feliratkozik az esemenykezelokre tovabba tarolja az aktualis telepest es a valasztott nyersanyagot.
 */
public class Controller {

	/**
	 * Egy referencia a menu gombok esemenykezelojere.
	 */
	protected MenuButtonListener menuBListener = new MenuButtonListener();
	
	/**
	 * Egy referencia a  muveletek gombjainak esemenykezelojere.
	 */
	protected MuveletsorListener muveletsorListener = new MuveletsorListener();
	
	/**
	 * Egy referencia az egerkattint es esemenykezelojere.
	 */
	protected PalyaEgerKezelo palyaEgerKezelo = new PalyaEgerKezelo();
	
	/**
	 * Ez egy flag, amely true eseten azt jelzi, hogy a palyan valo kattintasokat lekezeljuk.
	 */
	protected boolean palyaAktiv;
	
	
	/**
	 * Megadja az aktualisan kivalasztott nyersanyagot.
	 */
	protected Nyersanyag valasztottNy;
	
	/**
	 * A lenyomott muveletsori gomb funkciojat reprezentalja szovegkent.
	 */
	protected String aktivGomb;
	
}
