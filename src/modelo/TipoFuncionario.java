package modelo;

public enum TipoFuncionario {
	
	PRESIDENTE("Presidente"), VICE_PRESIDENTE("Vice-presidente"), DIRETOR("Diretor"), SECRETARIO("Secretario"), OUTRO("Outro");
	
	private String descricao;
	
	TipoFuncionario(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
