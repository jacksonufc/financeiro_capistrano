package modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
	@NamedQuery(name="Despesa.soma", 
			query="SELECT SUM(d.valor) FROM Despesa d WHERE d.mes = :mes AND d.ano = :ano")
})
public class Despesa {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private float valor;
	@Enumerated(EnumType.STRING)
	private Mes mes;
	private int ano;

	public Despesa() {
		this.mes = pegarMesAtual();
		this.ano = pegarAnoAtual();
	}

	public int pegarAnoAtual() {
		Calendar cal = Calendar.getInstance();
		int ano = cal.get(Calendar.YEAR);
		return ano;
	}

	public Mes pegarMesAtual() {
		Calendar cal = Calendar.getInstance();
		int mes = cal.get(Calendar.MONTH);
		switch (mes) {
		case 0:
			return Mes.JANEIRO;
		case 1:
			return Mes.FEVEREIRO;
		case 2:
			return Mes.MARCO;
		case 3:
			return Mes.ABRIL;
		case 4:
			return Mes.MAIO;
		case 5:
			return Mes.JUNHO;
		case 6:
			return Mes.JULHO;
		case 7:
			return Mes.AGOSTO;
		case 8:
			return Mes.SETEMBRO;
		case 9:
			return Mes.OUTUBRO;
		case 10:
			return Mes.NOVEMBRO;
		case 11:
			return Mes.DEZEMBRO;
		default:
			return Mes.JANEIRO;
		}
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

}
