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
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDescrescente() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 400.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 300.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(300.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoReceberExatosTresLances() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 400.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 300.0));
        CONSOLE.propoem(new Lance(new Usuario("Ana"), 200.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
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
        CONSOLE.propoem(new Lance(ALESSANDRO, 400.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 300.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }


    @Test
    public void deve_DevolverTresMaioresLances_QuandoMaisDeTresLances() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 400.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 300.0));
        CONSOLE.propoem(new Lance(ALESSANDRO, 100.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 200.0));
        CONSOLE.propoem(new Lance(ALESSANDRO, 500.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(500.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

}