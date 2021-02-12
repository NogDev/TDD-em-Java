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
		
		leilao.propoe(new Lance(new Usuario("Zé Neto"), 12000));
		
		assertEquals(1, leilao.getLances().size());
	}
	
	@Test
	public void naoDeveAceitarMaisDeCinco5DoMesmoUsuario() {
		Leilao leilao = new Leilao("Lenovo 13");
		leilao.propoe(new Lance(new Usuario("Zé Neto"), 2000));
		leilao.propoe(new Lance(new Usuario("Ricardo"), 3000));
		
		leilao.propoe(new Lance(new Usuario("Zé Neto"), 4000));
		leilao.propoe(new Lance(new Usuario("Ricardo"), 5000));
		
		leilao.propoe(new Lance(new Usuario("Zé Neto"), 6000));
		leilao.propoe(new Lance(new Usuario("Ricardo"), 7000));
		
		leilao.propoe(new Lance(new Usuario("Zé Neto"), 8000));
		leilao.propoe(new Lance(new Usuario("Ricardo"), 9000));
		
		leilao.propoe(new Lance(new Usuario("Zé Neto"), 10000));
		leilao.propoe(new Lance(new Usuario("Ricardo"), 11000));
		
		//Deve ser ignorado
		leilao.propoe(new Lance(new Usuario("Zé Neto"), 12000));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size() -1).getValor(), 0.00001);
	}
}
