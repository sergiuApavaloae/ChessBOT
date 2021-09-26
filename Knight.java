import java.util.List;
import java.util.ArrayList;
/**
 * Clasa care  Knight care extinde clasa Piece si reprezinta calul.
 *
 */
public class Knight extends Piece {
	Knight(){}
	/**	Construieste un cal si ii asociaza o casuta ocupata, valoarea 320 si 
	 * o matrice de bonusuri pt fiecare pozitie de pe tabla in parte, in functie de culoarea piesei curente.
	 * 
	 */	
	Knight(int position, Color color) {
		super(position, color);
		this.pieceType = "KNIGHT";
		this.tile = new OccupiedTile(position, this);
		this.value = 320;
		if(color.equals(Color.WHITE))
			this.bonusuri = new int[]{-50,-40,-30,-30,-30,-30,-40,-50,
									-40,-20,  0,  0,  0,  0,-20,-40,
									-30,  0, 10, 15, 15, 10,  0,-30,
									-30,  5, 15, 20, 20, 15,  5,-30,
									-30,  0, 15, 20, 20, 15,  0,-30,
									-30,  5, 10, 15, 15, 10,  5,-30,
									-40,-20,  0,  5,  5,  0,-20,-40,
									-50,-40,-30,-30,-30,-30,-40,-50};
		else
			this.bonusuri = new int[]{-50,-40,-30,-30,-30,-30,-40,-50,
									  -40,-20,  0,  5,  5,  0,-20,-40,
									  -30,  5, 10, 15, 15, 10,  5,-30,
									  -30,  0, 15, 20, 20, 15,  0,-30,
									  -30,  5, 15, 20, 20, 15,  5,-30,
									  -30,  0, 10, 15, 15, 10,  0,-30,
									  -40,-20,  0,  0,  0,  0,-20,-40,
									  -50,-40,-30,-30,-30,-30,-40,-50};

	}

	public void setEnPassant(int destPosP1){}
	public void setEnPassant2(int destPosP1){}
	public final static int[] offset = { -17, -15, -10, -6, 6, 10, 15, 17 };
	/** Verficam intai cazul de rocada, apoi pentru fiecare offset in parte daca destinatia
	 * (reprezentata de pozitia curenta + offset) este una valida.
	 * In cazul in care destinatia e valida, se creaza o noua mutare avand ca parametrii pozitia curenta si 
	 * destinatia, mutare pe care o adaugam in moves; in caz contrar trecem la urmatorul offset.
	 */
	public void tryMoveDown( Board b,int unavailablePiece[]) {
		int currentPos=this.position;
		this.moves=new ArrayList<Moves>();
		System.out.println("MOVE DOWN CAL");
		for(int i = 0; i < 8; i++){
				int off = offset[i];
				// Verificam intai conditia ca destinatia sa fie una valida.
				if(offset[i] == 6 && currentPos+off < 64 && currentPos+offset[i] >= (currentPos/8 +1)*8){
					int destPos = currentPos+offset[i];
					// Cazul in care la destinatie se afla o piesa a adversarului.
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){						
						moves.add(new Moves(currentPos, destPos));					
					}
					// Cazul in care la destinatie nu se afla nicio piesa.
					if(b.board.get(destPos).tile.getPiece() == null){
						moves.add(new Moves(currentPos, destPos));
					}
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == 10  && currentPos+off < 64 && currentPos+off < (currentPos/8 +2)*8){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(currentPos, destPos));		
					}
					if(b.board.get(destPos).tile.getPiece() == null){		
						moves.add(new Moves(currentPos, destPos));
					}
				}
				
				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == 15 && currentPos+off < 64 && currentPos+off >= (currentPos/8 +2)*8){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null )
						if( b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){					
							moves.add(new Moves(currentPos, destPos));				
					}
					if(b.board.get(destPos).tile.getPiece() == null){			
						moves.add(new Moves(currentPos, destPos));			
					}
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == 17 && currentPos+off < 64 && currentPos+off < (currentPos/8 +3)*8){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(currentPos, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() == null){
						moves.add(new Moves(currentPos, destPos));
					}
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == -10  && currentPos+off >= 0 && currentPos+off >= (currentPos/8 -1)*8){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(currentPos, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() == null){
						moves.add(new Moves(currentPos, destPos));
					}
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == -6 && currentPos+off >= 0 && currentPos+off < currentPos/8 *8){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null) 
						if(b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(currentPos, destPos));	
					}
					if(b.board.get(destPos).tile.getPiece() == null){
						moves.add(new Moves(currentPos, destPos));
					}
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == -17 && currentPos+off >= 0 && currentPos+off >= (currentPos/8 - 2)*8){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null){
						if(b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
							moves.add(new Moves(currentPos, destPos));	
						}
					}
					if(b.board.get(destPos).tile.getPiece() == null){						
						moves.add(new Moves(currentPos, destPos));						
					}
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == -15 && currentPos+off >= 0 && currentPos+off < (currentPos/8 -1)*8){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){						
						moves.add(new Moves(currentPos, destPos));												
					}
					if(b.board.get(destPos).tile.getPiece() == null){						
						moves.add(new Moves(currentPos, destPos));						
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
