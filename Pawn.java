import java.util.List;
import java.util.ArrayList;
/**
 * Clasa care extinde clasa Piece si reprezinta un pion.
 *
 */
public class Pawn extends Piece{
	Pawn(){}
	/**	Construieste un pion si ii asociaza o casuta ocupata, valoarea 100 si 
	 * o matrice de bonusuri pt fiecare pozitie de pe tabla in parte, in functie de culoarea piesei curente.
	 * 
	 */	
	Pawn(int position, Color color) {
		super(position, color);
		this.pieceType = "PAWN";
		this.tile = new OccupiedTile(position, this);
		this.value = 100;
		this.enPassant = false;
		this.enPassantDest = 100;
		if(color.equals(Color.WHITE))
			this.bonusuri = new int[]{0,  0,  0,  0,  0,  0,  0,  0,
									50, 50, 50, 50, 50, 50, 50, 50,
									10, 10, 20, 30, 30, 20, 10, 10,
									 5,  5, 10, 25, 25, 10,  5,  5,
 									0,  0,  0, 20, 20,  0,  0,  0,
 									5, -5,-10,  0,  0,-10, -5,  5,
 									5, 10, 10,-20,-20, 10, 10,  5,
 									0,  0,  0,  0,  0,  0,  0,  0};
 		else
 			this.bonusuri = new int[]{0,  0,  0,  0,  0,  0,  0,  0,
 									  5, 10, 10,-20,-20, 10, 10,  5,
 									  5, -5,-10,  0,  0,-10, -5,  5,
 									  0,  0,  0, 20, 20,  0,  0,  0,
 									  5,  5, 10, 25, 25, 10,  5,  5,
 									  10, 10, 20, 30, 30, 20, 10, 10,
 									  50, 50, 50, 50, 50, 50, 50, 50,
 									  0,  0,  0,  0,  0,  0,  0,  0};
	}

	public void setEnPassant(int destPosP1){
		if(this.position - 1 == destPosP1){
				this.enPassant = true;
				this.enPassantDest = this.position + 7;
			}
		
		if(this.position + 1 == destPosP1){
			this.enPassant = true;
			this.enPassantDest = this.position + 9;
		}
	}

	public void setEnPassant2(int destPosP1){
		if(this.position - 1 == destPosP1){
				this.enPassant = true;
				this.enPassantDest = this.position - 9;
			}
		
		if(this.position + 1 == destPosP1){
			this.enPassant = true;
			this.enPassantDest = this.position - 7;
		}
	}
	
// List<Moves> moves = new ArrayList<Moves>();
	/**	Metoda efectueaza o mutare(specifica pentru culoarea BLACK).
	 * @param b tabla de sah.
	 * @param unavailablePiece	vector in care sunt marcate piesele care nu mai pot efectua mutari(v[i] == 1 nu pot efectua).
	 */
	public void tryMoveDown( Board b,int unavailablePiece[]) {
				this.moves=new ArrayList<Moves>();
		System.out.println("#CAUT MUTARE PION");
		int currentPos=this.position;
		int destPos=currentPos+8;
		//Verificam enPassant
		if(this.enPassant == true)
			moves.add(new Moves(currentPos, this.enPassantDest));
		//verificam pawn promotion
		if(currentPos >= 48 && currentPos < 56){
			System.out.println("PAWN PROMOTION");
			// daca in dreapta lui(pe diagonala) e ocupat de adversar, piesa curenta nu e pe marginea dreapta(coloana a), atunci capturez adversarul
			if(b.board.get(destPos-1).tile.getPiece() != null && (currentPos%8!=0) && b.board.get(destPos-1).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color) { 
				moves.add(new Moves(currentPos, destPos-1));
			}
			// daca in stanga lui(pe diagonala) e ocupat de adversar, piesa curenta nu e pe marginea stanga(coloana h), atunci capturez adversarul
			else if(b.board.get(destPos+1).tile.getPiece() != null && ((currentPos+1)%8!=0) && b.board.get(destPos+1).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color) { 
				moves.add(new Moves(currentPos, destPos+1));			
			}
			// daca in fata nu are nicio piesa
			else if(b.board.get(destPos).tile.getPiece() == null) {
				moves.add(new Moves(currentPos, destPos));
			}
			else 
				unavailablePiece[currentPos]=1;
		}
		else{
			// daca in dreapta lui(pe diagonala) e ocupat de adversar, piesa curenta nu e pe marginea dreapta(coloana a), atunci capturez adversarul
			if(b.board.get(destPos-1).tile.getPiece() != null && (currentPos%8!=0) && b.board.get(destPos-1).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color) { 
				moves.add(new Moves(currentPos, destPos-1));
			}
			// daca in stanga lui(pe diagonala) e ocupat de adversar, piesa curenta nu e pe marginea stanga(coloana h), atunci capturez adversarul
			else if(b.board.get(destPos+1).tile.getPiece() != null && ((currentPos+1)%8!=0) && b.board.get(destPos+1).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color) { 
				moves.add(new Moves(currentPos, destPos+1));
			}
			// daca in fata nu are nicio piesa
			else if(b.board.get(destPos).tile.getPiece() == null) { 
				// daca e in pozitia initiala si cu daca cu doua randuri in fata nu are nicio piesa, muta 2 casute in fata
				if(b.board.get(destPos+8).tile.getPiece() == null && currentPos >= 8 && currentPos < 16) {
					moves.add(new Moves(currentPos, destPos+8));
				}
				//altfel muta doar o casuta in fata
				else {
					moves.add(new Moves(currentPos, destPos));
				}
			}
			// daca nu se incadreaza in niciun caz de mai sus, trece la urmatorul pion si se marcheaza pozitia curenta ca neputand efectua o mutare valida
			if(moves.size() == 0)
				unavailablePiece[currentPos] = 1;
		}
	}

		/**	Metoda efectueaza o mutare(specifica pentru culoarea WHITE).
	 	* @param b tabla de sah.
	 	* @param unavailablePiece	vector in care sunt marcate piesele care nu mai pot efectua mutari(v[i] == 1 nu pot efectua).
	 	*/
		public void tryMoveUp(Board b, int unavailablePiece[]) {
			this.moves=new ArrayList<Moves>();
		int currentPos= this.position;
		int destPos=this.position-8;
		// verificam enPassant
		if(this.enPassant == true)
			moves.add(new Moves(currentPos, this.enPassantDest));
				//verificam pawn promotion
		if(currentPos >= 8 && currentPos < 16){
			System.out.println("PAWN PROMOTION");
			// daca in stanga lui(pe diagonala) e ocupat de adversar, piesa curenta nu e pe marginea stanga(coloana a), atunci capturez adversarul	
			if(b.board.get(destPos-1).tile.getPiece() != null && (currentPos%8!=0) && b.board.get(destPos-1).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color) {
				moves.add(new Moves(currentPos, destPos-1));	
			}
			// daca in dreapta lui(pe diagonala) e ocupat de adversar, piesa curenta nu e pe marginea dreapta(coloana h), atunci capturez adversarul
			else if(b.board.get(destPos+1).tile.getPiece() != null && ((currentPos+1)%8!=0) && b.board.get(destPos+1).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color) { 
				moves.add(new Moves(currentPos, destPos+1));	
			}
			// daca in fata nu are nicio piesa
			else if(b.board.get(destPos).tile.getPiece() == null) {
				moves.add(new Moves(currentPos, destPos));
			}
			// daca nu se incadreaza in niciun caz de mai sus, trece la urmatorul pion si se marcheaza pozitia curenta ca neputand efectua o mutare valida
			else 
				unavailablePiece[currentPos] = 1;
		}
		else{
			// daca in stanga lui(pe diagonala) e ocupat de adversar, piesa curenta nu e pe marginea stanga(coloana a), atunci capturez adversarul
			if(b.board.get(destPos-1).tile.getPiece() != null && (currentPos%8!=0) && b.board.get(destPos-1).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color) {
			moves.add(new Moves(currentPos, destPos-1));		
			}
			// daca in dreapta lui(pe diagonala) e ocupat de adversar, piesa curenta nu e pe marginea dreapta(coloana h), atunci capturez adversarul
			else if(b.board.get(destPos+1).tile.getPiece() != null && ((currentPos+1)%8!=0) && b.board.get(destPos+1).tile.getPiece().color != b.board.get(currentPos).tile.getPiece().color) { 
				moves.add(new Moves(currentPos, destPos+1));			
			}
			// daca in fata nu are nicio piesa
			else if(b.board.get(destPos).tile.getPiece() == null) {
				// daca e in pozitia initiala si cu daca cu dpua randuri in fata nu are nicio piesa, muta 2 casute in fata
				if(b.board.get(destPos-8).tile.getPiece() == null && currentPos >= 48 && currentPos < 56) {
				moves.add(new Moves(currentPos, destPos-8));			
				}
				// altfel muta doar o casuta in fata
				else {
					moves.add(new Moves(currentPos, destPos));

				}		
			}
			// daca nu se incadreaza in niciun caz de mai sus, trece la urmatorul pion si se marcheaza pozitia curenta ca neputand efectua o mutare valida
			if(moves.size() == 0)
				unavailablePiece[currentPos] = 1;
		}	
	}

}
