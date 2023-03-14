package mc322;

public class Main {

	public static void main(String[] args) {
		Cliente c1 = new Cliente("Seiji", "059.272.649-56", "14/01/2004", 19, "Campinas - SP");
		System.out.println(c1.toString());
		System.out.println(c1.validarCPF(c1.getCpf()) + "");
	}
}
