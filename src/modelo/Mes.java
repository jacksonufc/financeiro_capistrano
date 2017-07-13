package modelo;

public enum Mes {
	JANEIRO("Janeiro"), FEVEREIRO("Fevereiro"), MARCO("Março"), ABRIL("Abril"), MAIO("Maio"), JUNHO("Junho"),
	JULHO("Julho"), AGOSTO("Agosto"), SETEMBRO("Setembro"), OUTUBRO("Outubro"), NOVEMBRO("Novembro"), DEZEMBRO("Dezembro");
	
	private String descricao;
	
	Mes(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
