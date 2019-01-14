package br.com.alura.leilao.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class FormataMoedaUtil {

    static public String formataParaReais(double valor) {
        NumberFormat formatador = DecimalFormat
                .getCurrencyInstance(new Locale("pt", "br"));

        return formatador.format(valor)
                .replace("R$", "R$ ");
    }
}
