import java.util.*;

public class Board {
	Map<Integer, Piece> board = new HashMap<Integer, Piece>();
	/**	Initializarea tablei de joc pentru pozitia initiala.
	 * 
	 */
	Board (int x)
	{
		
	}
	Board(){

		board.put(0, new Rook(0, Color.BLACK));
		board.put(1, new Knight(1, Color.BLACK));
		board.put(2, new Bishop(2, Color.BLACK));
		board.put(3, new Queen(3, Color.BLACK));
		board.put(4, new King(4, Color.BLACK));
		board.put(5, new Bishop(5, Color.BLACK));
		board.put(6, new Knight(6, Color.BLACK));
		board.put(7, new Rook(7, Color.BLACK));
		for(int i = 8; i < 16; i++)
			board.put(i, new Pawn(i, Color.BLACK));
		
		
		for(int i = 16; i < 48; i++)
			board.put(i, new NoPiece());
		
		for(int i = 48; i < 56; i++)
			board.put(i, new Pawn(i, Color.WHITE));
		board.put(56, new Rook(56, Color.WHITE));
		board.put(57, new Knight(57, Color.WHITE));
		board.put(58, new Bishop(58, Color.WHITE));
		board.put(59, new Queen(59, Color.WHITE));
		board.put(60, new King(60, Color.WHITE));
		board.put(61, new Bishop(61, Color.WHITE));
		board.put(62, new Knight(62, Color.WHITE));
		board.put(63, new Rook(63, Color.WHITE));
		
	}
		
	/**	Metoda care actualizeaza tabla de joc in functie de mutarile care se fac in force mode.
	 * @param currentPos reprezinta pozitia curenta.
	 * @param destPos	reprezinta destinatia.
	 */
	public void updateBoard(int currentPos, int destPos) {
		if(board.get(currentPos).tile.getPiece() != null)
			board.get(currentPos).tile.getPiece().updatePosition(destPos);	// updatam coordonatele piesei care se va muta de pe pozitia currentPos pe destPos
			board.put(destPos, board.get(currentPos).tile.getPiece());	// punem la destinatie piesa de pe pozitia curenta
			board.get(destPos).tile.isOccupied = true;	// marcam casuta de la destinatie ca fiind ocupata
			board.put(currentPos,new NoPiece());	// punem pe pozitia curenta o piesa nula
	}
	

	public void updateBoardPromotion(int currentPos, int destPos) {
		if(board.get(currentPos).tile.getPiece() != null)
			board.get(currentPos).tile.getPiece().updatePosition(destPos);	// updatam coordonatele piesei care se va muta de pe pozitia currentPos pe destPos
			board.put(destPos, new Queen(destPos, this.board.get(currentPos).tile.getPiece().color));	// punem la destinatie piesa de pe pozitia curenta
			board.get(destPos).tile.isOccupied = true;	// marcam casuta de la destinatie ca fiind ocupata
			board.put(currentPos,new NoPiece());	// punem pe pozitia curenta o piesa nula
	}
	/**	Metoda folosita pentru afisarea tablei de joc(utila pentru debug).
	 * 
	 */
	public void printBoard(){
		System.out.println("#PRINT BOARD");
		for(int i=0; i<64; i++) 
			if(board.get(i).tile.getPiece()!= null){
	
				if(board.get(i).tile.getPiece().pieceType == "ROOK")
					System.out.printf("r ");
				if(board.get(i).tile.getPiece().pieceType == "KNIGHT")
					System.out.printf("k ");
				if(board.get(i).tile.getPiece().pieceType == "BISHOP")					
					System.out.printf("b ");
				if(board.get(i).tile.getPiece().pieceType == "QUEEN")
					System.out.printf("q ");
				if(board.get(i).tile.getPiece().pieceType == "KING")
					System.out.printf("K ");
				if(board.get(i).tile.getPiece().pieceType == "PAWN")
					System.out.printf("p ");
				if((i+1)%8 == 0 && i!=0)
					System.out.println();
			}
			else {
					System.out.printf("o ");
					if((i+1)%8 == 0 && i!=0)
						System.out.println();
			}
			System.out.println();
	}
	public void printBoardpos(){
		System.out.println("#PRINT BOARD POZITII");
		for(int i=0; i<64; i++) 
			if(board.get(i).tile.getPiece()!= null){
	
				System.out.print(board.get(i).tile.getPiece().position+" ");
			}
			else {
					System.out.printf("o ");
					if((i+1)%8 == 0 && i!=0)
						System.out.println();
			}
			System.out.println();
	}



	/**	Metoda care cauta prima piesa disponibila care poate efectua o mutare in functie de culoare.
	 * @param currColor culoarea dupa care se cauta piesa.
	 * @param unavailablePiece	vector in care sunt marcate piesele care nu mai pot efectua mutari(unavailablePiece[i] == 1 nu pot efectua).
	 * @return	prima piesa disponibila.
	 */
	public Piece searchForMoves(Color currColor,int unavailablePiece[])
	{
		// Cautam prima piesa neagra(pion) disponibila.
		if (currColor.equals(Color.BLACK))
			for(int j=0; j < 64; j++)
				if(board.get(j).tile.getPiece()!= null){
					if(board.get(j).tile.getPiece().color == currColor){
						if(unavailablePiece[j]==0)
							return board.get(j).tile.getPiece()	;			
					}
				}
		// Cautam prima piesa alba(pion) disponibila.
		if (currColor.equals(Color.WHITE))
			for(int j=55; j>=0; j--)
				if(board.get(j).tile.getPiece()!= null){
					if(board.get(j).tile.getPiece().color == currColor){
						if(unavailablePiece[j]==0)return board.get(j).tile.getPiece();
									
					}
			}
	return null;

	}



	/** Verifica daca regele este in sah.
	 * @param currColor culoarea regelui
	 * @param KingPosition pozitia regelui
	 * @return 1 daca regele este in sah, 0 in caz contrar
	 */
	public int KingAttacked(Color currColor, int KingPosition, int unavailablePiece[])
	{		
		// Pentru culoarea negru
		if (currColor.equals(Color.BLACK))
			for(int j=0; j < 64; j++)
				if(board.get(j).tile.getPiece()!= null ) {
					if(board.get(j).tile.getPiece().color != currColor){
						if(unavailablePiece[j]==0){     /// verificam daca piesa adversara are mutari
							if(board.get(j).tile.getPiece().pieceType.equals("PAWN")){     // pt pion verificam doar daca pionul poate ataca regele
								if(j-7 == KingPosition || j-9 == KingPosition)
									return 1;
							}
							else{
								board.get(j).tile.getPiece().position=j;
								board.get(j).tile.getPiece().tryMoveUp(this, unavailablePiece);																
								for(int k =0 ; k < board.get(j).tile.getPiece().moves.size(); k++){ // pt fiecare mutare in parte
									if(board.get(j).tile.getPiece().moves.get(k).dest == KingPosition) {// verificam daca ataca regele
										System.out.println("ALTA PIESA ATACA" + j + "LA" + KingPosition);
										return 1;
									}
								}
							
							}
							
						}
										
					}
				}

		if (currColor.equals(Color.WHITE))
		for(int j=0; j < 64; j++)
				if(board.get(j).tile.getPiece()!= null){
					if(board.get(j).tile.getPiece().color != currColor){
						if(unavailablePiece[j]==0){ /// verificam daca piesa adversara are mutari
							if(board.get(j).tile.getPiece().pieceType.equals("PAWN")){ // pt pion verificam doar daca pionul poate ataca regele
								if(j + 7 == KingPosition || j + 9 == KingPosition)
									return 1;
							}
							else{
								board.get(j).tile.getPiece().position=j;
								if(board.get(j).tile.getPiece().pieceType.equals("KING"))
									continue;
								board.get(j).tile.getPiece().tryMoveDown(this, unavailablePiece);
								for(int k =0 ; k < board.get(j).tile.getPiece().moves.size(); k++){ // pt fiecare mutare in parte
									if(board.get(j).tile.getPiece().moves.get(k).dest == KingPosition) // verificam daca ataca regele
										return 1;
								}
							}
							
						}
										
					}
				}
	return 0;

	}




}
