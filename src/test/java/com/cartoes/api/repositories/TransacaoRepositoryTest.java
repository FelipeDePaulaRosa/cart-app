package com.cartoes.api.repositories;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
public class TransacaoRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private TransacaoRepository transacaoRepository;

	private Cliente clienteTeste;
	private Cartao cartaoTeste;
	private Transacao transacaoTeste;

	
	private void CriarTransacaoTestes() throws ParseException {	
		
		Cliente clienteTeste = new Cliente();

		clienteTeste.setId(1);
		clienteTeste.setNome("Nome Teste");
		clienteTeste.setCpf("05887098082");
		clienteTeste.setUf("CE");
		
		Cartao cartaoTeste = new Cartao();
		
		cartaoTeste.setId(1);
		cartaoTeste.setNumero("1230981203");
		cartaoTeste.setBloqueado(false);
		cartaoTeste.setCliente(clienteTeste);
		
		
		Transacao transacaoTeste = new Transacao();
		
		transacaoTeste.setId(1);
		transacaoTeste.setCnpj("18808626000194");
		transacaoTeste.setValor(1500.00);
		transacaoTeste.setQdtParcelas(3);
		transacaoTeste.setJuros(0.05);
		transacaoTeste.setCartao(cartaoTeste);
		transacaoTeste.setDataTransacao(new SimpleDateFormat("dd/MM/yyyy").parse("15/09/2020"));
		
		
	}

	@Before
	public void setUp() throws Exception {

		CriarTransacaoTestes();
		clienteRepository.save(clienteTeste);
		cartaoRepository.save(cartaoTeste);
		transacaoRepository.save(transacaoTeste);
		
	}

	@Test
	public void testFindByNumeroCartao() {

		Optional<List<Transacao>> transacoes = transacaoRepository
				.findByNumeroCartao(transacaoTeste.getCartao().getNumero());
		
		List<Transacao> transacao = transacoes.get();
		
		assertTrue(transacaoTeste.getCartao().getNumero().equals(transacao.get(0).getCartao().getNumero()));

	}

	@After
	public void tearDown() throws Exception {

		transacaoRepository.deleteAll();
		cartaoRepository.deleteAll();
		clienteRepository.deleteAll();
		
	}
}