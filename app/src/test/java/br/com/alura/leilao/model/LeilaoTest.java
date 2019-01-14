package br.com.alura.leilao.model;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LeilaoTest {
    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario ALESSANDRO = new Usuario("Alessandro");

    @Rule
    public ExpectedException exception = ExpectedException.none();

    // [nome do método] [estadode teste] [resultado esperado]
    // [deve]_[resultado esperado]_[estado de teste]

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        String descricaoDevolvida = CONSOLE.getDescricao();

//        assertEquals("Console", descricaoDevolvida);
        assertThat(descricaoDevolvida, is(equalTo("Console")));
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 300.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

//        assertEquals(300.0, maiorLanceDevolvido, DELTA);
        assertThat(maiorLanceDevolvido, closeTo(300, DELTA));
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 300.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 400.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(400.0, maiorLanceDevolvido, DELTA);
    }

//    @Test
//    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDescrescente() {
//        CONSOLE.propoem(new Lance(ALESSANDRO, 400.0));
//        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 300.0));
//
//        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
//
//        assertEquals(400.0, maiorLanceDevolvido, DELTA);
//    }


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

//        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertThat(tresMaioresLancesDevolvidos, hasSize((equalTo(3))));

//        assertEquals(800.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
//        assertEquals(700.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
//        assertEquals(600.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
        assertThat(tresMaioresLancesDevolvidos, contains(
                new Lance(ALESSANDRO, 800.0),
                new Lance(new Usuario("Carolina"), 700.0),
                new Lance(ALESSANDRO, 600.0)
        ));

//        Outra forma de Fazer os testes, mas agora tentando o tamanho da lista e o itens,
//        em um mesmo assertThat.
//        assertThat(tresMaioresLancesDevolvidos,
//                both(Matchers.<Lance>hasSize(3))
//                        .and(contains(
//                                new Lance(ALESSANDRO, 800.0),
//                                new Lance(new Usuario("Carolina"), 700.0),
//                                new Lance(ALESSANDRO, 600.0)
//                        )));
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

    @Test(expected = LanceMenorQueUltimoLanceException.class)
    public void naoDeve_AdicionarLance_QuandoForMenorQueOMaiorLance() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 400.0));
        CONSOLE.propoem(new Lance(new Usuario("Carolina"), 300.0));
    }

    @Test(expected = LanceSeguidoDoMesmoUsuarioException.class)
    public void naoDeve_AdicionarLance_QuandoForOMesmoUsuarioDoUltimoLance() {
        CONSOLE.propoem(new Lance(ALESSANDRO, 400.0));
        CONSOLE.propoem(new Lance(new Usuario("Alessandro"), 500.0));
    }

    @Test(expected = UsuarioJaDeuCincoLancesException.class)
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
    }
}