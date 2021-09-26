/**
 * Reprezentarea celor doua culori (WHITE si BLACK).
 *
 */
public enum Color {

	WHITE(){
		public boolean isWhite() {
			return true;
		}
		
		public boolean isBlack() {
			return false;
		}
		
		public int getDirection() {
			return upDirection;
		}
		
		public int getOppositeDirection() {
			return downDirection;
		}
		
	},
	BLACK(){
		public boolean isWhite() {
			return false;
		}
		
		public boolean isBlack() {
			return true;
		}	
		
		public int getDirection() {
			return downDirection;
		}
		
		public int getOppositeDirection() {
			return upDirection;
		}

	};
	
	private static final int upDirection = -1;
	private static final int downDirection = 1;
}
