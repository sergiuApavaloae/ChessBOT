import java.util.Scanner;
import java.io.*;  // Import the File class
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

class Pair<F, S> {
    	public F score;
    	public S move;

   		 public Pair(F score, S move) {
        	this.score = score;
        	this.move = move;
    	}
	}

public class Main {
	public static int Inf = 999999999;
	public Main() {
	}
	
	/** Metoda care calculeaza scorul total al jucatorului curent, adunand valorile tuturor pieselor de culoarea sa.
	 * @return scorul total.
	 */
	public static int computeScore(Color color, Board b){
		int score = 0; 
		for(int i = 0; i < 64; i++){
			if(b.board.get(i).tile.getPiece()!= null && b.board.get(i).tile.getPiece().color == color){
				score+= b.board.get(i).tile.getPiece().value;
			}
		}
		return score;
	}

	/** Verifica daca regele e in sah.
	 * @return
	 */
	public static boolean eInSah(Piece piece, Board b){
		int []unavailablePieceAdversary = new int[65];
		int currentPos = piece.position;
		if(piece.pieceType.equals("KING") && b.KingAttacked( piece.color, currentPos, unavailablePieceAdversary) == 1)
			{	
				return true;
			}
		return false;
	}


	

	public static int evaluate2(Piece piece, Board b, Color color, NumberPieces np1){
		int score = 0;
		int mobilitateaMea = 0;
		int mobilitateAdversar = 0;
		int scoreMobilitate = 0;
		int scoreMaterial = 0;
		//if(eInSah(piece,b) == true){
		//	score = 9000000;
		//}
		NumberPieces np=new NumberPieces();
		
			// Euristica1 - Bonusuri: aduna la scor bonusurile corespondente pozitiilor pieselor.   
			   for(int i = 0; i < 64; i++)
			   	if(b.board.get(i).tile.getPiece()!= null && b.board.get(i).tile.getPiece().color == color)
			   		score += b.board.get(i).tile.getPiece().bonusuri[i];
				 	
			//Calculeaza numarul de piese din fiecare fel. 
			for(int i = 0; i < 64; i++)
				if(b.board.get(i).tile.getPiece()!= null){
					if(b.board.get(i).tile.getPiece().pieceType.equals("KING") && b.board.get(i).tile.getPiece().color.equals(Color.WHITE))
						np.wK ++; 
					if(b.board.get(i).tile.getPiece().pieceType.equals("KING") && b.board.get(i).tile.getPiece().color.equals(Color.BLACK))
						np.bK ++;
					if(b.board.get(i).tile.getPiece().pieceType.equals("QUEEN") && b.board.get(i).tile.getPiece().color.equals(Color.WHITE))
						np.wQ ++; 
					if(b.board.get(i).tile.getPiece().pieceType.equals("QUEEN") && b.board.get(i).tile.getPiece().color.equals(Color.BLACK))
						np.bQ++;
					if(b.board.get(i).tile.getPiece().pieceType.equals("KNIGHT") && b.board.get(i).tile.getPiece().color.equals(Color.WHITE))
						np.wk ++; 
					if(b.board.get(i).tile.getPiece().pieceType.equals("KNIGHT") && b.board.get(i).tile.getPiece().color.equals(Color.BLACK))
						np.bk ++;
					if(b.board.get(i).tile.getPiece().pieceType.equals("ROOK") && b.board.get(i).tile.getPiece().color.equals(Color.WHITE))
						np.wR ++; 
					if(b.board.get(i).tile.getPiece().pieceType.equals("ROOK") && b.board.get(i).tile.getPiece().color.equals(Color.BLACK))
						np.bR ++;
					if(b.board.get(i).tile.getPiece().pieceType.equals("BISHOP") && b.board.get(i).tile.getPiece().color.equals(Color.WHITE))
						np.wB ++; 
					if(b.board.get(i).tile.getPiece().pieceType.equals("BISHOP") && b.board.get(i).tile.getPiece().color.equals(Color.BLACK))
						np.bB ++;
					if(b.board.get(i).tile.getPiece().pieceType.equals("PAWN") && b.board.get(i).tile.getPiece().color.equals(Color.WHITE))
						np.wP ++; 
					if(b.board.get(i).tile.getPiece().pieceType.equals("PAWN") && b.board.get(i).tile.getPiece().color.equals(Color.BLACK))
						np.bP ++;

					// Euristica2 - Mobilitate: Calculeaza cate mutari are fiecare piesa in parte si face totalul acestor mutari.
					if(b.board.get(i).tile.getPiece()!= null && b.board.get(i).tile.getPiece().color == color)
				  	scoreMobilitate += b.board.get(i).tile.getPiece().moves.size();
				}

			//Euristica3 - Material: Calculeaza scorul pt material ca fiind valoarea fiecarei piese inmultita cu avantajul pe care il are fata de piesa de acelasi tip a adversarului.
			if(color.equals(Color.WHITE))
				scoreMaterial+=20000*(np.wK - np.bK)+900*(np.wQ - np.bQ)+320*(np.wk - np.bk)+500*(np.wR - np.bR)+330*(np.wB - np.bB)+100*(np.wP - np.bP);
			else 
				scoreMaterial+=20000*(np.bK - np.wK)+900*(np.bQ - np.wQ)+320*(np.bk - np.wk)+500*(np.bR - np.wR)+330*(np.bB - np.wB)+100*(np.bP - np.wP);
			
			// Adauga la scorul total scorul pentru mobilitate si pe cel pentru material.
			score += scoreMaterial + scoreMobilitate;
		
		return score;
	}


	/** Functie care actualizeaza board-ul in urma aplicarii unei mutari.
	 */
	public static void apply_move(Board b, Moves move){
		
		System.out.println("APPLY"+move.curr+" "+move.dest);
		// In cazul in care trebuie sa mutam pion.
		if(b.board.get(move.curr).tile.getPiece().pieceType.equals("PAWN")){
			// Daca e cazul cu pawn promotion, adaugam regina.
			if((move.curr >= 48 && move.curr < 56 && b.board.get(move.curr).tile.getPiece().color.equals(Color.BLACK)) || (move.curr >= 8 && move.curr < 16 && b.board.get(move.curr).tile.getPiece().color.equals(Color.WHITE))){
				b.board.get(move.curr).tile.getPiece().updatePosition(move.dest);
				b.board.put(move.dest, new Queen(move.dest, b.board.get(move.curr).tile.getPiece().color));
				b.board.get(move.dest).tile.isOccupied = true;
				b.board.put(move.curr,new NoPiece());
			}
			// Altfel actualizam normal.
			else{
				System.out.println("ACTUALIZEAZA BOARD");
				b.board.get(move.curr).tile.getPiece().updatePosition(move.dest);
				b.board.put(move.dest, b.board.get(move.curr).tile.getPiece());
				b.board.get(move.dest).tile.isOccupied = true;
				b.board.put(move.curr,new NoPiece());
			}
		}
		// In caz contrar, actualizeaza normal.
		else {
			// Daca e rege si face rocada, trebuie sa actualizam in plus si pozitiile turelor.
			if(b.board.get(move.curr).tile.getPiece().pieceType.equals("KING")){
				int currPosP1 = move.curr;
				int destPosP1 = move.dest;
				if(currPosP1 == 4 && destPosP1 == 6)
	    	 		b.updateBoard(7, 5);
	     		if(currPosP1 == 4 && destPosP1 == 2)
	     			b.updateBoard(0, 3);
	     		if(currPosP1 == 60 && destPosP1 == 62)
	     			b.updateBoard(63, 61);
	     		if(currPosP1 == 60 && destPosP1 == 58)
	     			b.updateBoard(56, 59);
			}
			System.out.println("ACTUALIZEAZA BOARD");
			b.board.get(move.curr).tile.getPiece().updatePosition(move.dest);
			b.board.put(move.dest, b.board.get(move.curr).tile.getPiece());
			b.board.get(move.dest).tile.isOccupied = true;
			b.board.put(move.curr,new NoPiece());
		}
		b.printBoard();
	}
		

	/** Metoda care returneaza regele de o anumita culoare.
	 */
	public static Piece getKing(Board b, Color color){
		int index = 0;
		for(int i = 0; i < 64; i++){
			if(b.board.get(i).tile.getPiece()!= null && b.board.get(i).tile.getPiece().pieceType.equals("KING") && b.board.get(i).tile.getPiece().color == color){
				index = i;
				break;
			}
		}
		return b.board.get(index);
	}

	static List <List<Moves>> allmoves=new ArrayList();
	
	/** Metoda care realizeaza clonarea board-ului.
	 */
	public static Board clone(Board board)
	{			Board copy=new Board(1);
				System.out.println("CLONARE");
			
				for(int k = 0; k<64; k++)
         			{	board.board.get(k).position=k;
         				copy.board.put(k,board.board.get(k));
         				copy.board.get(k).position=k;
         			}
     

         		return copy;
	}
  public static Pair<Integer, Moves> minimax(Board b, Piece piece, int depth, Color color, NumberPieces np) {	
  		allmoves=new ArrayList();
  		int []unavailablePiece=new int[65];
  		System.out.println("INTRA MINIMAX "+ color);
  		// Daca am ajuns in situatia in care depth-ul este 0.
  		if(depth == 0){
            return new Pair<Integer, Moves>(evaluate2(piece, b, color, np), new Moves());
        }
         
  		// Cautam toate mutarile care pot fi facute de culoarea curenta, daca aceasta este alb.
        	if(color.equals(Color.WHITE)){
        		 for(int i = 0; i < 64; i++){
         			if(b.board.get(i).tile.getPiece()!= null && b.board.get(i).tile.getPiece().color == color){       	
         				Piece currentPiece = b.board.get(i).tile.getPiece();
         				currentPiece.position=i;
         				currentPiece.tryMoveUp(b,unavailablePiece);
         				allmoves.add(currentPiece.moves);
         			}
         		}
         	}
         
        // Cautam toate mutarile care pot fi facute de culoarea curenta, daca aceasta este negru.
         if(color.equals(Color.BLACK)){
         		for(int i = 63; i >= 0; i--){
         			if(b.board.get(i).tile.getPiece()!= null )if(b.board.get(i).tile.getPiece().color == color){       	
         				Piece currentPiece = b.board.get(i).tile.getPiece();         		
         				currentPiece.position=i;       
         				currentPiece.tryMoveDown(b,unavailablePiece);
           				allmoves.add(currentPiece.moves);
         			}
         		}
        	 }
         
         int maxScore = -Inf;
         Moves maxMove =  new Moves();

         //Pentru fiecare mutare in parte:
         for(List<Moves> mutarileuneipiese:allmoves)
         		for(int i=0;i<mutarileuneipiese.size();i++)
         		{         			
         			Board copy=clone(b); // clonam board-ul
         			apply_move(copy,mutarileuneipiese.get(i)); // aplicam mutarea pe clona
         			// Verificam daca ne aflam in sah, si in acest caz trecem la urmatoarea mutare.
         			if(eInSah(piece, copy)){
         				System.out.println("E IN SAH IN MINIMAX");
         				copy.printBoard();
         				continue;
         			}
         			// Determinam culoarea adversarului.
         			Color adversaryColor;
         			if(color.equals(Color.WHITE))
         					adversaryColor = Color.BLACK;
         			else
         				adversaryColor = Color.WHITE;
         			//Determinam regele adversar.	
         			Piece adversaryKing = getKing(b, adversaryColor);
         			Pair<Integer, Moves> eval = minimax(copy, adversaryKing, depth-1, adversaryColor, np); // apelam minimax pe depth-1
         			if(-eval.score > maxScore) { // -scor adversar > max
                   		maxScore = -eval.score; // max = -scor adversar
                   		maxMove = mutarileuneipiese.get(i); // mutare max = mutare aplicata de mine
                	 }
         		}
         		return new Pair<Integer, Moves>(maxScore, maxMove);
         	}
 

	public static void main(String[] args) throws InterruptedException, IOException {
		NumberPieces np = new NumberPieces();
		Scanner myObj = new Scanner(System.in);
		int i=0, colorChanged = -1;
		int nr_mutare = 0;
		String mod;
		String previousCommand = "";
		Board b = new Board();
		int wh=0,bl=0;
		int modforce=0;
		int k = 0;
		while (true)
		{
			b.printBoard();
			i++;
			Writer myWriter = new PrintWriter(System.out);
		
    		String command = myObj.nextLine();  // Read user input
			if(command.contains("protover"))
			 	System.out.println("feature SIGINT=0");
    		if(command.contains("xboard"))
				mod="xboard";
    		// Interpretarea comenzii new a xboard-ului, in care creeaza un nou board si seteaza parametrii bl, wh, colorChanged si modforce la valorile initiale.  
    		if(command.contains("new")) {		
    			b=new Board();
    			colorChanged=-1;
    			bl=0;
    			wh=0;
    			modforce=0;
    			continue;
    		}
    		
    		int [] v1=new int [65];
    		int currPos=0, destPos=0;
    		
    		// Interpretarea comenzii white a xboard-ului, in care cauta prima piesa alba disponibila si incerca sa efectueze mutarea(tryMoveUp).
    		if(command.contains("white") && bl==1) {
    			System.out.println("##INTRA WHITE");
    			
    			Pair<Integer, Moves> p;
						Piece myKing = getKing(b, Color.WHITE);
						System.out.println("POSITION KING " +myKing.position);
						p = minimax(b, myKing, 3, Color.WHITE, np);
						Moves move = p.move;
						b.board.get(move.curr).moved = true;
						apply_move(b,move);
						System.out.println("DUPA APLICARE MAIN");
						b.printBoard();

						System.out.println("move "+(char)(move.curr%8+97)+ ""+(8-move.curr/8)+""+ (char)(move.dest%8+97)+(8-move.dest/8));



						System.out.println("#AFTER WHITE MOVE");
						b.printBoard();

    			colorChanged = 1; 
    			wh=0;
    			bl=0;
    		}
    		
    		// Interpretarea comenzii black a xboard-ului, in care cauta prima piesa neagra disponibila si incerca sa efectueze mutarea(tryMoveDown).
    		if(command.contains("black") && wh==1) {   			
    			colorChanged = -1;
    			bl=0;
    			wh=0;
    		}

    		if(command.contains("black") && wh==0) {
    			bl=1;
    		}

    		if(command.contains("white") && bl==0) {  
    			wh=1;
    		}
    		
    		
    		// Interpretarea comenzii force mode in care seteaza modforce pe 1.
    		if(command.contains("force") ) {
    			modforce=1;
    		}
    		
    		if(command.contains("exit") ) {
    			continue;
    		}
    		
    		// Interpretarea comenzii go, in care seteaza modforce pe 0.
    		if(command.contains("go")) {
    			modforce = 0;
    		}
    		
    		// Interpretarea comenzii quit.
    		if(command.contains("quit")) {
    			System.exit(0);
    		}

    		// In cazul in care suntem in force mode, pentru fiecare comanda de mutare doar actualizam tabla.
			/*if(modforce==1 && command.length()==4) {
				System.out.println("#FORCE INPUT COMMAND:"+command);
				int currPosP1 = (command.charAt(0)-97) + 8*(8-(command.charAt(1)-48));		
				int destPosP1 = (command.charAt(2)-97) + 8*(8-(command.charAt(3)-48));
				b.updateBoard(currPosP1, destPosP1);
			}
			*/	     	
			
			// In cazul in care nu suntem in force mode, interpretarea comenzii move este urmatoarea:
	     	if((previousCommand.contains("otim")) && ((command.length() == 4) || (command.length()==5 && command.charAt(4)=='q'))) 
	     	{
	     		nr_mutare++;
	     		System.out.println("#INPUT COMMAND:"+command);
	     		
	     		// Pentru comanda primita actualizam tabla de joc in functie de pozitia curenta si destinatia mutarii.
	     		int currPosP1 = (command.charAt(0)-97) + 8*(8-(command.charAt(1)-48));		
	     		int destPosP1 = (command.charAt(2)-97) + 8*(8-(command.charAt(3)-48));
	     		if(command.length()==5 && command.charAt(4)=='q')
	     			b.updateBoardPromotion(currPosP1, destPosP1);
	     		else{
	     			
	     			if(b.board.get(currPosP1).tile.getPiece().pieceType.equals("KING") &&  b.board.get(destPosP1).tile.getPiece() == null){
		     			if(currPosP1 == 4 && destPosP1 == 6)
	    	 				b.updateBoard(7, 5);
	     				if(currPosP1 == 4 && destPosP1 == 2)
	     					b.updateBoard(0, 3);
	     				if(currPosP1 == 60 && destPosP1 == 62)
	     					b.updateBoard(63, 61);
	     				if(currPosP1 == 60 && destPosP1 == 58)
	     					b.updateBoard(56, 59);
	     				System.out.println("rocada");
	     				b.printBoard();
	     			}
	     			b.updateBoard(currPosP1, destPosP1);
	     		}

	     		// Daca parametrul colorChanged este nemodificat, am considerat ca ramane culoarea default(adica BLACK).
	     		if(colorChanged == -1){
	     			System.out.println("#COLOR IS BLACK");
	     			b.printBoard();
				 
				 
	     			int []unavailablePiece=new int[65];
				 	
				 		//Verificam enPassant
	     				if(b.board.get(destPosP1).tile.getPiece().pieceType.equals("PAWN") && destPosP1 == currPosP1-16 ){
	     					if(b.board.get(destPosP1-1).tile.getPiece() != null && b.board.get(destPosP1-1).tile.getPiece().pieceType.equals("PAWN"))
	     						b.board.get(destPosP1-1).tile.getPiece().setEnPassant(destPosP1);
	     					if(b.board.get(destPosP1+1).tile.getPiece() != null && b.board.get(destPosP1+1).tile.getPiece().pieceType.equals("PAWN"))
	     						b.board.get(destPosP1+1).tile.getPiece().setEnPassant(destPosP1);
	     				}	
						// Se cauta mutarea valida pentru piesa gasita.
						Pair<Integer, Moves> p;
						Piece myKing = getKing(b, Color.BLACK);
						System.out.println("POSITION KING " +myKing.position);
						p = minimax(b, myKing, 3, Color.BLACK, np);
						Moves move = p.move;
						if(move.curr == 0 && move.dest ==0)
							System.out.println("resign");
						b.board.get(move.curr).moved = true;
						apply_move(b,move);

						System.out.println("DUPA APLICARE MAIN");
						b.printBoard();

						System.out.println("move "+(char)(move.curr%8+97)+ ""+(8-move.curr/8)+""+ (char)(move.dest%8+97)+(8-move.dest/8));
						System.out.println("#AFTER BLACK MOVE");
						
						b.printBoard();
			
	     		}
	     		// Daca parametrul colorChanged s-a modificat, am considerat ca culoarea este WHITE.
	     		else{
	     			System.out.println("#CHANGE COLOR TO WHITE");
	     			b.printBoard();
					int []unavailablePiece=new int[65];
				 	
				 		
				 		//Verificam enPassant
	     				if(b.board.get(destPosP1).tile.getPiece().pieceType.equals("PAWN") && destPosP1 == currPosP1-16 ){
	     					if(b.board.get(destPosP1-1).tile.getPiece() != null && b.board.get(destPosP1-1).tile.getPiece().pieceType.equals("PAWN"))
	     						b.board.get(destPosP1-1).tile.getPiece().setEnPassant(destPosP1);
	     					if(b.board.get(destPosP1+1).tile.getPiece() != null && b.board.get(destPosP1+1).tile.getPiece().pieceType.equals("PAWN"))
	     						b.board.get(destPosP1+1).tile.getPiece().setEnPassant(destPosP1);
	     				}
				 		// Se cauta mutarea valida pentru piesa gasita.
	     				Pair<Integer, Moves> p;
						Piece myKing = getKing(b, Color.WHITE);
						System.out.println("POSITION KING " +myKing.position);
						p = minimax(b, myKing, 3, Color.WHITE, np);
						Moves move = p.move;
						if(move.curr == 0 && move.dest ==0)
							System.out.println("resign");
						b.board.get(move.curr).moved = true;
						apply_move(b,move);
						System.out.println("DUPA APLICARE MAIN");
						b.printBoard();

						System.out.println("move "+(char)(move.curr%8+97)+ ""+(8-move.curr/8)+""+ (char)(move.dest%8+97)+(8-move.dest/8));

						System.out.println("#AFTER WHITE MOVE");
						b.printBoard();
						     		
	     			System.out.println("#AFTER WHITE MOVE");
	     			b.printBoard();
	     		}
	     	}
			previousCommand = command.copyValueOf(command.toCharArray());
		}	
	}

}
