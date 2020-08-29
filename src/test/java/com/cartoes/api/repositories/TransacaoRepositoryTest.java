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

//Criacao da Transação
	
	@Autowired
	private TransacaoRepository transacaoRepository;  
	
	private Transacao transacaoTeste;	
	
	private void CriarTransacaoTestes() throws ParseException {
		
		transacaoTeste = new Transacao();
		
		transacaoTeste.setDataTransacao(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
		transacaoTeste.setCnpj("18808626000194");
		transacaoTeste.setValor(1500.00);
		transacaoTeste.setQdtParcelas(3);
		transacaoTeste.setJuros(0.05);
		transacaoTeste.setCartao(cartaoTeste);
		
	}
//Finalização da transacao.
	
//Criacao do Cliente
	@Autowired
	private Cliente clienteTeste;
	
	private ClienteRepository clienteRepository;
	
	private void CriarClienteTestes() throws ParseException {
		
		clienteTeste = new Cliente();
		
		clienteTeste.setNome("Nome Teste");
		clienteTeste.setCpf("05887098082");
		clienteTeste.setUf("CE");
		clienteTeste.setDataAtualizacao(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
		
	}
	
//Finalização do Cliente.

//Criacao do Cartão
	@Autowired
	private Cartao cartaoTeste;
	
	private CartaoRepository cartaoRepository;
	
	private void CriarCartaoTestes() throws ParseException {
		
		cartaoTeste = new Cartao();
		
		cartaoTeste.setNumero("1230981203");
        cartaoTeste.setDataValidade(new SimpleDateFormat("dd/MM/yyyy").parse("01/12/2020"));
        cartaoTeste.setBloqueado(false);
        cartaoTeste.setDataAtualizacao(new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2020"));
        cartaoTeste.setCliente(clienteTeste);
		
	}
	
//Finalização do Cartão
	
	@Before
	public void setUp() throws Exception {
		
		CriarTransacaoTestes();
		transacaoRepository.save(transacaoTeste);
		
		CriarClienteTestes();
		clienteRepository.save(clienteTeste);
		
		CriarCartaoTestes();
		cartaoRepository.save(cartaoTeste);
		
	}
	
	@Test
    public void testFindByNumeroCartao() {

		Optional<List<Transacao>> transacoes = transacaoRepository.findByNumeroCartao(transacaoTeste.getCartao().getNumero());
		Transacao transacao = (Transacao) transacoes.get();
		
		assertTrue(transacaoTeste.getCartao().getNumero().equals(transacao.getCartao().getNumero()));

    }

	@After
	public void tearDown() throws Exception {
		
		transacaoRepository.deleteAll();
		clienteRepository.deleteAll();
		cartaoRepository.deleteAll();
		
	}
}