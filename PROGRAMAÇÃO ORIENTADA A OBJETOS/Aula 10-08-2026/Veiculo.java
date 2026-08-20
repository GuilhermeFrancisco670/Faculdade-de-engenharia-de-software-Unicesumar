
public class Veiculo {
    public String marca;
    public String modelo;
    public int anoFabricacao;
    public int quantPortas;

    public Veiculo(String marca, String modelo, int anoFabricacao){
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.quantPortas = 2;
    }

    public void exibirDados(){
        System.out.printf("Veículo\n");
        System.out.printf("Marca: %s - Modelo: %s - Ano: %d\n", marca, modelo, anoFabricacao);
    }

}
