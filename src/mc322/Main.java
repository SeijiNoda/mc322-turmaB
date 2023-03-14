package mc322;

public class Main {

	public static void main(String[] args) {
		Cliente cli1 = new Cliente("Seiji", "059.272.649-56", "14/01/2004", 19, "Campinas - SP");
		Veiculo vei1 = new Veiculo("EVR-8404", "Astra", "Chevrolet");
		Sinistro sin1 = new Sinistro("14/03/2023", "Curitiba - PR");
		Seguradora seg1 = new Seguradora("Seguros do Jose", "46801563", "segjose@gmail.com", "Campinas - SP");
		
		System.out.println("ID do sinsitro: " + sin1.getId());
	}
}
	