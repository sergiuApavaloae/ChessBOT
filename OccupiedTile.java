/**
 * Clasa care extinde clasa Tile si reprezinta o casuta ocupata din tabla de sah.
 *
 */
public class OccupiedTile extends Tile{
	Piece pieceOnOccupiedTile;
	/**	Construieste un EmptyTile si seteaza isOccupied ca fiind true.
	 * 
	 */	
	OccupiedTile(int coordinate, Piece pieceOnOccupiedTile){
		super(coordinate);
		this.pieceOnOccupiedTile = pieceOnOccupiedTile;
		this.isOccupied = true;
	}
	/** Metoda care returneaza piesa aflata pe casuta.
	 * @return piesa (pentru ca e ocupata).
	 */
	public Piece getPiece() {
		return this.pieceOnOccupiedTile;
	}
	
}
