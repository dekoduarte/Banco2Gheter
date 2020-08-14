package br.com.banco2gether.usuarios;

import java.io.IOException;
import java.util.List;

import br.com.banco2gether.relatorios.IRelatorioDiretoria;
import br.com.banco2gether.relatorios.IRelatorioPresidencia;
import br.com.banco2gether.usuarios.exception.ErrosLoginException;
import br.com.banco2gether.util.IOFiles;
import br.com.banco2gether.util.Sistema;

public class Presidente extends Funcionario implements IRelatorioDiretoria, IRelatorioPresidencia {

	public Presidente(String nome, String cpf, String senha) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSenha(senha);
		this.setCargo(Cargos.Presidente);
	}

	@Override
	public void autenticar(String cpf, String senha) {
		if (!this.getCpf().equals(cpf) || !this.getSenha().equals(senha)) {
			throw new ErrosLoginException("Login ou senha inválidos");
		}
	}

	@Override
	public void relatorioClientesDoBanco() throws IOException {

		List<Usuario> lista = Sistema.tabelaUsuario;

		lista.stream().map(c -> "Nome: " + c.getNome() + ", CPF: " + c.getCpf() + ", Agencia: " + c.getConta().getAgencia())
				.forEach(System.out::println);
		
		IOFiles.escreveRelatorioClientesDoBanco(Cargos.Diretor);
	}

	@Override
	public double relatorioTotalCapitalDoBanco() {
		try {
			return IOFiles.leituraDeCapitalDoBanco();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
