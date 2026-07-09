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

        EquipamentoService equipamentoService = new EquipamentoService(equipamentoRepository, manutencaoRepository);
        TecnicoService tecnicoService = new TecnicoService(tecnicoRepository, manutencaoRepository);
        ManutencaoService manutencaoService = new ManutencaoService(tecnicoRepository, equipamentoRepository, manutencaoRepository);
        
        MenuController menu = new MenuController(equipamentoRepository, tecnicoRepository, manutencaoRepository, tecnicoService, manutencaoService, equipamentoService);
        menu.startMenu();
    }
}
