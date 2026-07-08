import controllers.MenuController;
import repository.EquipamentoRepository;
import repository.ManutencaoRepository;
import repository.TecnicoRepository;
import services.EquipamentoService;
import services.ManutencaoService;
import services.TecnicoService;

public class Main {
    public static void main(String[] args) throws Exception {
        EquipamentoRepository equipamentoRepository = new EquipamentoRepository();
        ManutencaoRepository manutencaoRepository = new ManutencaoRepository();
        TecnicoRepository tecnicoRepository = new TecnicoRepository();

        EquipamentoService equipamentoService = new EquipamentoService(equipamentoRepository);
        ManutencaoService manutencaoService = new ManutencaoService(manutencaoRepository);
        TecnicoService tecnicoService = new TecnicoService(tecnicoRepository);
        
        MenuController menu = new MenuController(tecnicoService, manutencaoService, equipamentoService);
        menu.startMenu();
    }
}
