/**
 * Clasa care extinde clasa Tile si reprezinta o casuta goala din tabla de sah.
 *
 */
public class EmptyTile extends Tile{	
	/**	Construieste un EmptyTile si seteaza isOccupied ca fiind false.
	 * 
	 */	
	EmptyTile(int coordinate){
		super(coordinate);
		this.isOccupied = false;
	}
	/** Metoda care returneaza piesa aflata pe casuta.
	 * @return null (pentru ca e goala).
	 */
	public Piece getPiece() {
		return null;
	}	
}
