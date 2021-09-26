
/**
 * Clasa abstracta Tile care reprezinta o casuta din tabla de sah.
 *
 */
public abstract class Tile {
	int coordinate;
	public boolean isOccupied;
	
	Tile(int coordinate){
		this.coordinate = coordinate;
	}
	
	/** Metoda care returneaza piesa aflata pe casuta.
	 * @return piesa/null daca e ocupat/liber.
	 */
	public abstract Piece getPiece();
	

}
