import java.util.InputMismatchException;
import java.util.Scanner;

import repository.EquipamentoRepository;
import repository.ManutencaoRepository;
import repository.RelatorioRepository;
import repository.TecnicoRepository;
import services.EquipamentoService;
import services.ManutencaoService;
import services.RelatorioService;
import services.TecnicoService;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        EquipamentoRepository equipamentoRepository = new EquipamentoRepository();
        ManutencaoRepository manutencaoRepository = new ManutencaoRepository();
        TecnicoRepository tecnicoRepository = new TecnicoRepository();
        RelatorioRepository relatorioRepository = new RelatorioRepository();

        EquipamentoService equipamentoService = new EquipamentoService(equipamentoRepository);
        ManutencaoService manutencaoService = new ManutencaoService(manutencaoRepository);
        TecnicoService tecnicoService = new TecnicoService(tecnicoRepository);
        RelatorioService relatorioService = new RelatorioService(relatorioRepository);
        
        boolean sair = false;
        
        while(!sair) {
            System.out.println("\n---=== Controle de Equipamentos ===---");
            System.out.print("1. Gerenciar Equipamentos\n2. Gerenciar Manutenções\n3. Gerenciar Técnicos\n4. Relatórios\n0. Sair\n-> ");

            try {
                int opcao = input.nextInt();

                switch (opcao) {
                    case 1:
                        //Redirecionando código para service
                        //equipamentoService.menu();
                        break;
                    case 2:
                        //Redirecionando código para service
                        manutencaoService.menu();
                        break;
                    case 3:
                        //Redirecionando código para service
                        tecnicoService.menu();
                        break;
                    case 4:
                        //Redirecionando código para service
                        relatorioService.menu();
                        break;
                    case 0:
                        System.out.println("\nSaindo...");
                        sair = true;
                        break;
                    default:
                        System.out.println("\nERRO: Opção inválida!");
                        break;
                }
            } catch(InputMismatchException e) {
                //Tratando erro de input
                System.out.println("\nERRO: O Valor digitado deve ser um Número Inteiro!");
                input.nextLine();
            } catch(Exception e) {
                //Tratando erros gerais
                System.out.println("\nERRO: " + e.getMessage());
            }
        }
        input.close();
    }
}
