/**
 * 
 */
package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author andersonnogueira
 * @since Feb 12, 2021
 */
public class LeilaoTest {
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Lenovo 13");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Zé Neto"), 2000));
		
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Lenovo 13");
		leilao.propoe(new Lance(new Usuario("Zé Neto"), 2000));
		leilao.propoe(new Lance(new Usuario("Zé Neto"), 3000));
		
		assertEquals(1, leilao.getLances().size());
	}
}
