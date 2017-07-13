package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.ReceitaDAO;
import dao.ReceitaExtraDAO;
import dao.ReceitaExtraJPADAO;
import dao.ReceitaJPADAO;
import dao.ReceitaPagamentoDAO;
import dao.ReceitaPagamentoJPADAO;
import modelo.Mes;
import modelo.Receita;
import modelo.ReceitaUnica;

@ManagedBean
@ViewScoped
public class ReceitaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Receita> receitas;
	private List<ReceitaUnica> receitaUnica;
	private List<Mes> meses;
	private int ano;
	private Mes mes;
	private double receitaTotal;

	public ReceitaBean() {
		this.receitas = new ArrayList<Receita>();
		this.setReceitaUnica(new ArrayList<ReceitaUnica>());
		this.meses = Arrays.asList(Mes.values());
	}

	public void buscarTodos() {
		ReceitaDAO rDAO = new ReceitaJPADAO();
		this.receitas = rDAO.find();

	}

	public void buscarPorAno() {
		ReceitaDAO rDAO = new ReceitaJPADAO();
		this.receitas = rDAO.buscarPorAno(this.ano);

	}

	public void buscarPorMesAno() {
		ReceitaDAO rDAO = new ReceitaJPADAO();
		this.receitas = rDAO.buscarPorMesAno(this.mes, this.ano);

	}

	public void somaReceita() {
		this.receitaUnica = new ArrayList<ReceitaUnica>();
		ReceitaUnica ru = new ReceitaUnica();
		ReceitaPagamentoDAO rpagDAO = new ReceitaPagamentoJPADAO();
		Double somaPagam = rpagDAO.soma(this.mes, this.ano);
		if (somaPagam == null) {
			somaPagam = new Double(0);
		}
		ru.setTotal(somaPagam);
		ru.setNome("Pagamento");
		this.receitaUnica.add(ru);
		ru = new ReceitaUnica();

		ReceitaExtraDAO reDAO = new ReceitaExtraJPADAO();
		Double somaExtra = reDAO.soma(this.mes, this.ano);
		if (somaExtra == null) {
			somaExtra = new Double(0);
		}
		ru.setTotal(somaExtra);
		ru.setNome("Extra");
		this.receitaUnica.add(ru);
		ru = new ReceitaUnica();

		this.receitaTotal = somaPagam.doubleValue() + somaExtra.doubleValue();
	}

	public double somaReceitaTotal(List<ReceitaUnica> r) {
		double total = 0;
		for (ReceitaUnica re : r) {
			total = total + re.getTotal().doubleValue();
		}
		return total;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	public List<Mes> getMeses() {
		return meses;
	}

	public void setMeses(List<Mes> meses) {
		this.meses = meses;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public double getReceitaTotal() {
		return receitaTotal;
	}

	public void setReceitaTotal(double receitaTotal) {
		this.receitaTotal = receitaTotal;
	}

	public List<ReceitaUnica> getReceitaUnica() {
		return receitaUnica;
	}

	public void setReceitaUnica(List<ReceitaUnica> receitaUnica) {
		this.receitaUnica = receitaUnica;
	}

}
