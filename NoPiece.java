import java.util.List;
/**
 * Clasa care extinde clasa Piece si reprezinta o piesa nula.
 *
 */
public class NoPiece extends Piece{
	/**	Construieste o piesa nula si ii asociaza o casuta goala.
	 * 
	 */	
	NoPiece(){
		this.tile = new EmptyTile(position);
		this.pieceType = "";
	}
	
	public void setEnPassant(int destPosP1){}
	public void setEnPassant2(int destPosP1){}

	public  void tryMoveDown(Board b,int unavailablePiece[])
	{
		
	}
	public void tryMoveUp(Board b,int unavailablePiece[])
	{
		
	}

}
