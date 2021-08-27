public class Espia {
	 int espia[] = new int[2];
	
	 
	 
	public void moverEspia() {
			int max = 11;
			int min = 1;

			// espia [0] = x , espia [1] = y
			espia[0] = (int) (Math.floor(Math.random() * (max - min)) + min);
			espia[1] = (int) (Math.floor(Math.random() * (max - min)) + min);
	}
	
	public void iniciar() {		
		moverEspia();
	}
}
