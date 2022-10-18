package model;
public abstract interface AcoesContatos {
	public abstract boolean adicionarContato(Contato c);

	public abstract boolean removerContato(int indicieASerRemovido);
}
