package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import br.com.alura.leilao.R;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.util.FormataMoedaUtil;

public class LancesLeilaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lances_leilao);
        Intent dadosRecebidos = getIntent();
        if(dadosRecebidos.hasExtra("leilao")){

            Leilao leilao = (Leilao) dadosRecebidos.getSerializableExtra("leilao");

            TextView descricao = findViewById(R.id.lances_leilao_descricao);
            descricao.setText(leilao.getDescricao());

            TextView maiorLance = findViewById(R.id.lances_leilao_maior_lance);
            maiorLance.setText(FormataMoedaUtil.formataParaReais(leilao.getMaiorLance()));

            TextView menorLance = findViewById(R.id.lances_leilao_menor_lance);
            menorLance.setText((FormataMoedaUtil.formataParaReais(leilao.getMenorLance())));

            TextView maioresLances = findViewById(R.id.lances_leilao_maiores_lances);
            List<Lance> lances = leilao.tresMaioresLances();
            StringBuilder sb = new StringBuilder();
            for (Lance l :
                    lances) {
                sb.append(FormataMoedaUtil.formataParaReais(l.getValor()) + "\n");
            }
            maioresLances.setText(sb.toString());
        }
    }
}
