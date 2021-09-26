import java.util.*; 
/**
 * Clasa abstracta Piece care reprezinta o piesa.
 *
 */
public abstract class Piece {
	protected boolean moved;
	protected int position;
	protected int value;
	protected Color color;
	protected Tile tile;
	protected  String pieceType;
	public boolean enPassant;
	public int enPassantDest;
	public int score;
	public List<Moves> moves;
	public int[] bonusuri;
	Piece(){
		this.tile = new EmptyTile(position);
	}
	
	Piece(int position,Color color){
		this.color = color;
		this.position = position;
		this.tile = new OccupiedTile(position, this);
		this.moved = false;
		this.score = 0;
		this.moves = new ArrayList<Moves>();
	}
	public  void updatePosition(int x) {
		this.position=x;
	}
	public abstract void tryMoveDown(Board b,int unavailablePiece[]);
	public abstract void tryMoveUp(Board b,int unavailablePiece[]);
	public abstract void setEnPassant(int destPosP1);
	public abstract void setEnPassant2(int destPosP1);

}
