/**
 * 
 */
package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.servico.Avaliador;


/**
 * @author andersonnogueira
 * @since Feb 12, 2021
 */
public class AvaliadorTest {
	
	private Avaliador leiloeiro;
	Usuario joao;
	Usuario jose;
	Usuario maria;
	
	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
	}

	@Test
    public void deveEntenderLancesEmOrdemCrescente() {

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(jose, 400.0));
        leilao.propoe(new Lance(maria, 250.0));

        
        leiloeiro.avalia(leilao);
        
        double maiorEsperado = 400;
        double menorEsperado = 250;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
    }
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		
		Leilao leilao = new Leilao("Playstation 3 Novo");
		
		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(jose, 270.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		
		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, maiores.size());
		assertEquals(400, maiores.get(0).getValor(), 0.00001);
		assertEquals(300, maiores.get(1).getValor(), 0.00001);
		assertEquals(270, maiores.get(2).getValor(), 0.00001);
	}
}