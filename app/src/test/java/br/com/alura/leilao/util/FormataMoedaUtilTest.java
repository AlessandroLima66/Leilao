package br.com.alura.leilao.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FormataMoedaUtilTest {

    @Test
    public void deve_formatarParaMoeda_QuandoRecebeValorDouble(){
        FormataMoedaUtil formatador = new FormataMoedaUtil();

        String moedaFormatada = formatador.formataParaReais(200.0);

        assertThat(moedaFormatada, is(equalTo("R$ 200,00")));
    }

}