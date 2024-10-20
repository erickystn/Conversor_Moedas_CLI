import io.github.cdimascio.dotenv.Dotenv;
import modelo.TipoMoeda;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        int opcaoEscolhida = -1;
        double valorAConverter = 0.0;
        double valorConvertido = 0.0;
        Scanner leitor = new Scanner(System.in);
        ConversorMoeda conversor = null;

        String menu = """
                ***********************************
                Seja bem-vindo/a ao Conversor de Moedas :)
                
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileiro
                4) Real brasileiro =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Sair
                
                Escolha uma opção válida:
                ***********************************
                >>>  """;



        do {
            System.out.print(menu);
            try {
                opcaoEscolhida = Integer.valueOf(leitor.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Erro, por favor digite somente números");
                System.out.println(e.getMessage());
                continue;
            }

            switch (opcaoEscolhida) {
                case 1:
                    conversor = new ConversorMoeda(TipoMoeda.DOLAR, TipoMoeda.PESO_ARGENTINO);
                    break;
                case 2:
                    conversor = new ConversorMoeda(TipoMoeda.PESO_ARGENTINO, TipoMoeda.DOLAR);
                    break;
                case 3:
                    conversor = new ConversorMoeda(TipoMoeda.DOLAR, TipoMoeda.REAL_BRASILEIRO);
                    break;
                case 4:
                    conversor = new ConversorMoeda(TipoMoeda.REAL_BRASILEIRO, TipoMoeda.DOLAR);
                    break;
                case 5:
                    conversor = new ConversorMoeda(TipoMoeda.DOLAR, TipoMoeda.PESO_COLOMBIANO);
                    break;
                case 6:
                    conversor = new ConversorMoeda(TipoMoeda.PESO_COLOMBIANO, TipoMoeda.DOLAR);
                    break;
                case 7:
                    System.out.println("Saindo...");
                    continue;

                default:
                    System.out.println("Opção inválida!");
                    continue;



            }

            System.out.println("Digite o valor que deseja converter: ");
            valorAConverter = leitor.nextDouble();
            leitor.nextLine();

            valorConvertido = conversor.converterMoeda(valorAConverter);

            System.out.println("""
                    Valor %.2f [%s] corresponde ao valor final  de =>>> %.3f [%s]  
                    """.formatted(valorAConverter, conversor.getMoedaBase().getCodigo(), valorConvertido, conversor.getMoedaAlvo().getCodigo()));


        } while (opcaoEscolhida != 7);


    }

}
