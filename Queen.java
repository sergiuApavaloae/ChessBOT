import java.util.List;
import java.util.ArrayList;
/**
 * Clasa care  Queen care extinde clasa Piece si reprezinta regina.
 *
 */
public class Queen extends Piece{
	/**	Construieste o regina si ii asociaza o casuta ocupata, valoarea 900 si 
	 * o matrice de bonusuri pt fiecare pozitie de pe tabla in parte, in functie de culoarea piesei curente.
	 * 
	 */	
	Queen(int position, Color color) {
		super(position, color);	
		this.pieceType = "QUEEN";
		this.tile = new OccupiedTile(position, this);
		this.value = 900;
		if(color.equals(Color.WHITE))
			this.bonusuri = new int[]{-20,-10,-10, -5, -5,-10,-10,-20,
									-10,  0,  0,  0,  0,  0,  0,-10,
									-10,  0,  5,  5,  5,  5,  0,-10,
 									-5,  0,  5,  5,  5,  5,  0, -5,
  									 0,  0,  5,  5,  5,  5,  0, -5,
									-10,  5,  5,  5,  5,  5,  0,-10,
									-10,  0,  5,  0,  0,  0,  0,-10,
									-20,-10,-10, -5, -5,-10,-10,-20};
		else
			this.bonusuri = new int[]{-20,-10,-10, -5, -5,-10,-10,-20,
									-10,  0,  0,  0,  0,  5,  0,-10,
									-10,  0,  5,  5,  5,  5,  5,-10,
 									 -5,  0,  5,  5,  5,  5,  0, 0,
  									 -5,  0,  5,  5,  5,  5,  0, 0,
									-10,  0,  5,  5,  5,  5,  0,-10,
									-10,  0,  0,  0,  0,  0,  0,-10,
									-20,-10,-10, -5, -5,-10,-10,-20};
	}

	public void setEnPassant(int destPosP1){}
	public void setEnPassant2(int destPosP1){}

	public final static int[] offset = {-9, -8, -7, -1, 1, 7, 8, 9}; 
	/** Verficam pentru fiecare offset in parte daca destinatia(reprezentata de pozitia curenta + offset)
	 * este una valida.
	 * In cazul in care destinatia e valida, se creaza o noua mutare avand ca parametrii pozitia curenta si 
	 * destinatia, mutare pe care o adaugam in moves; in caz contrar trecem la urmatorul offset.
	 */
	public void tryMoveDown( Board b,int unavailablePiece[]) {
		this.moves=new ArrayList<Moves>();
		int currentPos=this.position;
		System.out.println("MOVE DOWN REGINA");
		for(int i = 0; i < 8; i++){
			int off = offset[i];
			// Verificam intai conditia ca destinatia sa fie una valida.
				if(offset[i] == 1 && currentPos+off < 64 && currentPos+off < (currentPos/8 +1)*8){
					int destPos = currentPos+off;
					int saveCurr = currentPos;
					// Cazul in care la destinatie se afla o piesa a adversarului.
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(saveCurr, destPos));
					}
					// Cazul in care la destinatie se afla o piesa a noastra.
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					// Cazul in care la destinatie nu se afla nicio piesa, verificam toate pozitiile urmatoare la care putem muta piesa noatra.
					while(b.board.get(destPos).tile.getPiece() == null  && destPos < 64 && destPos < ((destPos-off)/8 +1)*8){
						moves.add(new Moves(saveCurr, destPos));
						destPos = destPos+off;
						if(destPos > 63)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color && destPos < ((destPos-off)/8 +1)*8)
							moves.add(new Moves(saveCurr, destPos));
						
					}
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == 8 && currentPos+off < 64){
					int destPos = currentPos+off;
					int saveCurr = currentPos;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(saveCurr, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					while(b.board.get(destPos).tile.getPiece() == null  && destPos < 64){
						moves.add(new Moves(saveCurr, destPos));
						destPos = destPos+off;
						if(destPos > 63)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color && destPos < 64)
							moves.add(new Moves(saveCurr, destPos));
					}
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == -1 && currentPos+off >= 0 && currentPos+off >= currentPos/8 *8){
					int destPos = currentPos+off;
					int saveCurr = currentPos;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(saveCurr, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					while(b.board.get(destPos).tile.getPiece() == null && destPos >= 0 && destPos >= (destPos-off)/8 *8){
						moves.add(new Moves(saveCurr, destPos));
						destPos = destPos+off;
						if(destPos < 0)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color &&  destPos >= (destPos-off)/8 *8 )
							moves.add(new Moves(saveCurr, destPos));
						
					}
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == -8 && currentPos+off >= 0){
					int destPos = currentPos+off;
					int saveCurr = currentPos;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(saveCurr, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					while(b.board.get(destPos).tile.getPiece() == null && destPos >= 0){
						moves.add(new Moves(saveCurr, destPos));
						destPos = destPos+off;
						if(destPos < 0)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color && destPos >= 0)
							moves.add(new Moves(saveCurr, destPos));
						
					}
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == 7 && currentPos+off < 64 && currentPos+off >= (currentPos/8 +1)*8){
					int destPos = currentPos+off;
					int saveCurr = currentPos;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(saveCurr, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					while(b.board.get(destPos).tile.getPiece() == null  && destPos < 64 && destPos >= ((destPos-off)/8 +1)*8){
						moves.add(new Moves(saveCurr, destPos));
						destPos = destPos+off;
						if(destPos > 63)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color && destPos >= ((destPos-off)/8 +1)*8)
							moves.add(new Moves(saveCurr, destPos));
						
					}
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == 9 && currentPos+off < 64 && currentPos+off < (currentPos/8 +2)*8){
					int destPos = currentPos+off;
					int saveCurr = currentPos;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(saveCurr, destPos));
						
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					while(b.board.get(destPos).tile.getPiece() == null  && destPos < 64 && destPos < ((destPos-off)/8 +2)*8){
						moves.add(new Moves(saveCurr, destPos));
						destPos = destPos+off;
						if(destPos > 63)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color && destPos < ((destPos-off)/8 +2)*8)
							moves.add(new Moves(saveCurr, destPos));
						
					}
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == -7 && currentPos+off >= 0 && currentPos+off < currentPos/8 *8){
					int destPos = currentPos+off;
					int saveCurr = currentPos;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(saveCurr, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					while(b.board.get(destPos).tile.getPiece() == null && destPos >= 0 && destPos < (destPos-off)/8 *8){
						moves.add(new Moves(saveCurr, destPos));
						destPos = destPos+off;
						if(destPos < 0)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color && destPos < (destPos-off)/8 *8)
							moves.add(new Moves(saveCurr, destPos));
						
					}
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == -9 && currentPos+off >= 0 && currentPos+off >= (currentPos/8 -1)*8){
					int destPos = currentPos+off;
					int saveCurr = currentPos;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(saveCurr, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					while(b.board.get(destPos).tile.getPiece() == null && destPos >= 0 && destPos >= ((destPos-off)/8 -1)*8){
						moves.add(new Moves(saveCurr, destPos));
						destPos = destPos+off;
						if(destPos < 0)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color)
							break;
						if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color &&  destPos >= ((destPos-off)/8 -1)*8){
							moves.add(new Moves(saveCurr, destPos));
						}
					}
				}
			}
		
		// In cazul in care nu am gasit nicio mutare pentru piesa curenta, unavailablePiece se aceasta piesa devine 1.
			if(moves.size() == 0)
				unavailablePiece[currentPos] = 1;
	}
	
	public void tryMoveUp( Board b,int unavailablePiece[]) {
		tryMoveDown(b,unavailablePiece);
	}
}
