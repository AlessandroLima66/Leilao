package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {
    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario ALESSANDRO = new Usuario("Alessandro");

    // [nome do método] [estadode teste] [resultado esperado]
    // [deve] [resultado esperado] [estado de teste]

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        String descricaoDevolvida = CONSOLE.getDescricao();

        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 300.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(300.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 300.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 400.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(400.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDescrescente() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 400.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 300.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(400.0, maiorLanceDevolvido, DELTA);
    }


    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 300.0));

        double menorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(300.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 300.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 400.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(300.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoReceberExatosTresLances() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 400.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 500.0));
        CONSOLE.propoem(new Lance(new Usuario("Ana"), 600.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(600.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeNenhumLances() {
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoReceberUmLances() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoReceberDoisLances() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 300.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }


    @Test
    public void deve_DevolverTresMaioresLances_QuandoMaisDeTresLances() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 400.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 500.0));
        CONSOLE.propoem(new Lance(ALESSANDRO, 600.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 700.0));
        CONSOLE.propoem(new Lance(ALESSANDRO, 800.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(800.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(700.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(600.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaoTiverLance() {
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLance() {
        double maenorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(0.0, maenorLanceDevolvido, DELTA);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForMenorQueOMaiorLance() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 400.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 300.0));

        int quantidadeLancesDevolvida = CONSOLE.quatidadeLances();

        assertEquals(1, quantidadeLancesDevolvida);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForOMesmoUsuarioDoUltimoLance() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 400.0));
        CONSOLE.propoem(new Lance(new Usuario("Alessandro"), 500.0));


        int quantidadeLancesDevolvida = CONSOLE.quatidadeLances();

        assertEquals(1, quantidadeLancesDevolvida);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoUsuarioDerCincoLances() {
        Usuario carol = new Usuario("Carolina");

        CONSOLE.propoem(new Lance(ALESSANDRO, 100.0));
        CONSOLE.propoem(new Lance(carol, 200.0));
        CONSOLE.propoem(new Lance(ALESSANDRO, 300.0));
        CONSOLE.propoem(new Lance(carol, 400.0));
        CONSOLE.propoem(new Lance(ALESSANDRO, 500.0));
        CONSOLE.propoem(new Lance(carol, 600.0));
        CONSOLE.propoem(new Lance(ALESSANDRO, 700.0));
        CONSOLE.propoem(new Lance(carol, 800.0));
        CONSOLE.propoem(new Lance(ALESSANDRO, 900.0));
        CONSOLE.propoem(new Lance(carol, 1000.0));
        CONSOLE.propoem(new Lance(ALESSANDRO, 1100.0));
        CONSOLE.propoem(new Lance(carol, 1200.0));


        int quantidadeLancesDevolvida = CONSOLE.quatidadeLances();

        assertEquals(10, quantidadeLancesDevolvida);
    }
}