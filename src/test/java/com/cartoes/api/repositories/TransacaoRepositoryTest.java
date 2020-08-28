package com.cartoes.api.repositories;



import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cartoes.api.entities.Cartao;
import com.cartoes.api.entities.Cliente;
import com.cartoes.api.entities.Transacao;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class TransacaoRepositoryTest {
	
	@Autowired
	private ClienteRepository clienteRepository;  
	
	private Cliente clienteTeste;
	
	private void CriarClienteTestes() throws ParseException {
		
		clienteTeste = new Cliente();
		
		clienteTeste.setNome("Nome Teste");
		clienteTeste.setCpf("05887098082");
		clienteTeste.setUf("CE");
		clienteTeste.setDataAtualizacao(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
		
	}
	
	@Autowired
	private CartaoRepository cartaoRepository;  
	
	private Cartao cartaoTeste;
	
	private void CriarCartaoTestes() throws ParseException {
		
		cartaoTeste = new Cartao();
		
		
		cartaoTeste.setNumero("1230981203");
		cartaoTeste.setDataValidade(new SimpleDateFormat("dd/MM/yyyy").parse("01/12/2020"));
		cartaoTeste.setBloqueado(false);;
		cartaoTeste.setDataAtualizacao(new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2020"));
		cartaoTeste.setCliente(clienteTeste);;

		}

	@Autowired
	private TransacaoRepository transacaoRepository;  
	
	private Transacao transacaoTeste;

	private void CriarTransacaoTestes() throws ParseException {
		
		transacaoTeste = new Transacao();
		
		
		transacaoTeste.setDataTransacao(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
		transacaoTeste.setCnpj("05887098082");
		transacaoTeste.setQtdParcelas(3);
		transacaoTeste.setValor(120.00);
		transacaoTeste.setJuros(10.00);
		transacaoTeste.setCartao(cartaoTeste);
		}
	
	@Before
	public void setUp() throws Exception {
		
		CriarClienteTestes();
		clienteRepository.save(clienteTeste);
		
		CriarCartaoTestes();
		cartaoRepository.save(cartaoTeste);
		
		CriarTransacaoTestes();
		transacaoRepository.save(transacaoTeste);
	}
	
	@After
	public void tearDown() throws Exception {
		
		transacaoRepository.deleteAll();
		
	}
	
	/*@Test
	public void findByCartaoId() {	
		
		
	}*/
	
	/*@Test
	public void testFindByNumero() {
		
	
	}*/
	
}
