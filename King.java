import java.util.List;
import java.util.ArrayList;
/**
 * Clasa care  King care extinde clasa Piece si reprezinta regele.
 *
 */
public class King extends Piece{
	/**	Construieste un rege si ii asociaza o casuta ocupata, valoarea 20000 si 
	 * o matrice de bonusuri pt fiecare pozitie de pe tabla in parte, in functie de culoarea piesei curente.
	 * 
	 */	
	King(int position, Color color) {
		super(position, color);
		this.pieceType = "KING";
		this.tile = new OccupiedTile(position, this);
		this.value = 20000;
		this.moved = false;
		if(color.equals(Color.WHITE))
			this.bonusuri = new int[]{-30,-40,-40,-50,-50,-40,-40,-30,
									-30,-40,-40,-50,-50,-40,-40,-30,
									-30,-40,-40,-50,-50,-40,-40,-30,
									-30,-40,-40,-50,-50,-40,-40,-30,
									-20,-30,-30,-40,-40,-30,-30,-20,
									-10,-20,-20,-20,-20,-20,-20,-10,
 									20, 20,  0,  0,  0,  0, 20, 20,
 									20, 20, 10,  0,  0, 10, 20, 20};
 		else 
 			this.bonusuri = new int[]{20, 20, 10,  0,  0, 10, 20, 20,
									20, 20,  0,  0,  0,  0, 20, 20,
									-10,-20,-20,-20,-20,-20,-20,-10,
									-20,-30,-30,-40,-40,-30,-30,-20,
									-30,-40,-40,-50,-50,-40,-40,-30,
									-30,-40,-40,-50,-50,-40,-40,-30,
									-30,-40,-40,-50,-50,-40,-40,-30,
 									-30,-40,-40,-50,-50,-40,-40,-30};
	}

	public void setEnPassant(int destPosP1){}
	public void setEnPassant2(int destPosP1){}

	public final static int[] offset = {-9, -8, -7, -1, 1, 7, 8, 9};
	/** Verficam intai cazul de rocada, apoi pentru fiecare offset in parte daca destinatia
	 * (reprezentata de pozitia curenta + offset) este una valida.
	 * In cazul in care destinatia e valida, se creaza o noua mutare avand ca parametrii pozitia curenta si 
	 * destinatia, mutare pe care o adaugam in moves; in caz contrar trecem la urmatorul offset.
	 */
	public void tryMoveDown( Board b,int unavailablePiece[]) {
		this.moves=new ArrayList<Moves>();
		int []unavailablePieceAdversary = new int[65];
		int currentPos=this.position;
		System.out.println("MOVE DOWN REGE");

			// Verificam rocada pentru negru
			if(b.board.get(7).tile.getPiece()!=null && b.board.get(7).tile.getPiece().pieceType=="ROOK" && b.board.get(7).tile.getPiece().color.equals(b.board.get(currentPos).tile.getPiece().color) )
				if(this.position == 4 && this.moved == false && b.board.get(7).tile.getPiece().moved == false){
					System.out.println("nu au fost mutate");
					if(b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos, unavailablePieceAdversary) == 0)
					if(b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos+1, unavailablePieceAdversary) == 0 && b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos+2, unavailablePieceAdversary) == 0){
						System.out.println("nu e in sah");
						if(b.board.get(5).tile.getPiece() == null && b.board.get(6).tile.getPiece() == null &&b.board.get(6).tile.getPiece() == null ){
							System.out.println("adauga mutare");
							moves.add(new Moves(currentPos, currentPos+2));
						}
					}
				}
				if(b.board.get(0).tile.getPiece()!=null && b.board.get(0).tile.getPiece().pieceType=="ROOK" && b.board.get(0).tile.getPiece().color.equals(b.board.get(currentPos).tile.getPiece().color))
				if(this.position == 4 && this.moved == false && b.board.get(0).tile.getPiece().moved == false){
					if(b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos, unavailablePieceAdversary) == 0)
					if(b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos-1, unavailablePieceAdversary) == 0 && b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos-2, unavailablePieceAdversary) == 0){
						if(b.board.get(3).tile.getPiece() == null && b.board.get(2).tile.getPiece() == null &&b.board.get(1).tile.getPiece() == null){
							moves.add(new Moves(currentPos, currentPos-2));

						}
					}
				}
				
				// Verificam rocada pentru alb
				if(b.board.get(63).tile.getPiece()!=null && b.board.get(63).tile.getPiece().pieceType=="ROOK" && b.board.get(63).tile.getPiece().color.equals(b.board.get(currentPos).tile.getPiece().color))
				if(this.position == 60 && this.moved == false && b.board.get(63).tile.getPiece().moved == false){
					if(b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos, unavailablePieceAdversary) == 0)
					if(b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos+1, unavailablePieceAdversary) == 0 && b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos+2, unavailablePieceAdversary) == 0){
						if(b.board.get(currentPos+1).tile.getPiece() == null && b.board.get(currentPos+2).tile.getPiece() == null){
							moves.add(new Moves(currentPos, currentPos+2));
						}
					}
				}
				if(b.board.get(56).tile.getPiece()!=null && b.board.get(56).tile.getPiece().pieceType=="ROOK" && b.board.get(56).tile.getPiece().color.equals(b.board.get(currentPos).tile.getPiece().color))
				if(this.position == 60 && this.moved == false && b.board.get(56).tile.getPiece().moved == false){
					if(b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos, unavailablePieceAdversary) == 0)
					if(b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos-1, unavailablePieceAdversary) == 0 && b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos-2, unavailablePieceAdversary) == 0){
						if(b.board.get(currentPos-1).tile.getPiece() == null && b.board.get(currentPos-2).tile.getPiece() == null && b.board.get(currentPos-3).tile.getPiece() == null ){
							moves.add(new Moves(currentPos, currentPos-2));

						}
					}
				}
		for(int i = 0; i < 8; i++){
			int off = offset[i];
			// Verificam intai conditia ca destinatia sa fie una valida.
			if(offset[i] == 1 && currentPos+off < 64 && currentPos+off < (currentPos/8 +1)*8 && b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos+off, unavailablePieceAdversary) == 0){
					int destPos = currentPos+off;
					// Cazul in care la destinatie se afla o piesa a adversarului.
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(currentPos, destPos));
					}
					// Cazul in care la destinatie se afla o piesa a mea.
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					// Cazul in care la destinatie nu se afla nicio piesa.
					if(b.board.get(destPos).tile.getPiece() == null)
						moves.add(new Moves(currentPos, destPos));
				
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == 8 && currentPos+off < 64 && b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos+off, unavailablePieceAdversary) == 0){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(currentPos, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					if(b.board.get(destPos).tile.getPiece() == null)
						moves.add(new Moves(currentPos, destPos));
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == -1 && currentPos+off >= 0 && currentPos+off >= currentPos/8 *8 && b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos+off, unavailablePieceAdversary) == 0){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(currentPos, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					if(b.board.get(destPos).tile.getPiece() == null)
						moves.add(new Moves(currentPos, destPos));
				}
				
				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == -8 && currentPos+off >= 0 && b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos+off, unavailablePieceAdversary) == 0){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(currentPos, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					if(b.board.get(destPos).tile.getPiece() == null)
						moves.add(new Moves(currentPos, destPos));
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == 7 && currentPos+off < 64 && currentPos+off >= (currentPos/8 +1)*8 && b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos+off, unavailablePieceAdversary) == 0){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(currentPos, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					if(b.board.get(destPos).tile.getPiece() == null)
						moves.add(new Moves(currentPos, destPos));
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == 9 && currentPos+off < 64 && currentPos+off < (currentPos/8 +2)*8 && b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos+off, unavailablePieceAdversary) == 0){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(currentPos, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					if(b.board.get(destPos).tile.getPiece() == null)
						moves.add(new Moves(currentPos, destPos));
				}

				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == -7 && currentPos+off >= 0 && currentPos+off < currentPos/8 *8 && b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos+off, unavailablePieceAdversary) == 0){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(currentPos, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					if(b.board.get(destPos).tile.getPiece() == null)
						moves.add(new Moves(currentPos, destPos));
				}
				
				// Verificam intai conditia ca destinatia sa fie una valida, dupa care urmam pasii ca la cazul anterior.
				if(offset[i] == -9 && currentPos+off >= 0 && currentPos+off >= (currentPos/8 -1)*8 && b.KingAttacked( b.board.get(currentPos).tile.getPiece().color, currentPos+off, unavailablePieceAdversary) == 0){
					int destPos = currentPos+off;
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color){
						moves.add(new Moves(currentPos, destPos));
					}
					if(b.board.get(destPos).tile.getPiece() != null && b.board.get(destPos).tile.getPiece().color == b.board.get(currentPos).tile.getPiece().color){
						continue;
					}
					if(b.board.get(destPos).tile.getPiece() == null)
						moves.add(new Moves(currentPos, destPos));
				}

			
			}

		// In cazul in care nu am gasit nicio mutare pentru piesa curenta, unavailablePiece se aceasta piesa devine 1.
			if(moves.size() == 0){
				unavailablePiece[currentPos] = 1;
			}
		}
	public void tryMoveUp(Board b,int unavailablePiece[]) {
		tryMoveDown(b,unavailablePiece);
	}
	
	
}
