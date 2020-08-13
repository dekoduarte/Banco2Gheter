package br.com.banco2gether.usuarios;


public abstract class Funcionario extends Usuario implements Autenticavel {

	protected Cargos cargo;

	public Cargos getCargo() {
		return cargo;
	}

	public void setCargo(Cargos cargo) {
		this.cargo = cargo;
	}
}
