package ejemplo;

public class recursividad {
	
	public static int factoriales(int n) {
		int total = n;
		if(n != 0) {
			total = n * factoriales(n-1);
		
		}else {
			total = 1;
		}
		return total;
	}
	
	public static int MCD(int a , int b) {
		
		if(a == b){
			return a;
		}else if(a > b) {
			MCD(a-b, b);
		}else {
			MCD(a, b-a);
		}
		return b;
		
			
		
	}

	public static void main(String[] args) {
		recursividad r = new recursividad();
		System.out.println(r.MCD(412, 184));
		
		
	}

}
