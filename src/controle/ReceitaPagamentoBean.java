package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.NoResultException;

import org.primefaces.context.RequestContext;

import dao.SecretariaDAO;
import dao.SecretariaJPADAO;
import dao.ReceitaPagamentoDAO;
import dao.ReceitaPagamentoJPADAO;
import modelo.Secretaria;
import modelo.Mes;
import modelo.ReceitaPagamento;


@ManagedBean
@ViewScoped
public class ReceitaPagamentoBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ReceitaPagamento pagamento;
	private List<ReceitaPagamento> pagamentos;
	private float receitaTotal;
	private List<Mes> meses;
	private int ano;
	private Secretaria secretaria;
	private Mes mes;

	public ReceitaPagamentoBean() {
		this.pagamento = new ReceitaPagamento();
		this.secretaria = new Secretaria();
		this.pagamentos = new ArrayList<ReceitaPagamento>();
		this.setMeses(Arrays.asList(Mes.values()));
	}

	public void cadastrar() {
		SecretariaDAO secretariaDAO = new SecretariaJPADAO();
		Secretaria s = secretariaDAO.buscarPorNome(this.pagamento.getSecretaria().getNome());
		if (s != null) {
			ReceitaPagamentoDAO rpDAO = new ReceitaPagamentoJPADAO();
			ReceitaPagamento r;
			try {
				r =  rpDAO.buscarPorSecretaria(s.getId(), this.pagamento.getAno());
			} catch (NoResultException e) {
				r = null;
			}
			if(r == null){
				this.pagamento.setSecretaria(s);
				rpDAO.save(this.pagamento);
				displayInfoMessageToUser("Cadastrado com sucesso");
				this.pagamento = new ReceitaPagamento();
			}else{
				displayErrorMessageToUser("Secretária já Cadastrada nesse ano!");
				this.pagamento = new ReceitaPagamento();
			}
		} else {
			displayErrorMessageToUser("Secretária não cadastrada! Cadastre a secretária e tente novamente.");
			this.pagamento = new ReceitaPagamento();
		}
	}

	public void selecionarParaAtualizar(ReceitaPagamento rp) {
		this.pagamento = rp;
		RequestContext.getCurrentInstance().execute("PF('edit').show()");
	}

	public void atualizar() {
		ReceitaPagamentoDAO rpDAO = new ReceitaPagamentoJPADAO();
		rpDAO.save(this.pagamento);
		displayInfoMessageToUser("Atualizado com sucesso");
		this.pagamento = new ReceitaPagamento();
	}

	public void excluir(ReceitaPagamento rp) {
		ReceitaPagamentoDAO rpDAO = new ReceitaPagamentoJPADAO();
		rpDAO.delete(rp);
		displayInfoMessageToUser("Excluido com sucesso!");
		this.pagamentos.remove(rp);
	}

	public void buscarTodos() {
		this.pagamentos = new ArrayList<ReceitaPagamento>();
		ReceitaPagamentoDAO rpDAO = new ReceitaPagamentoJPADAO();
		this.pagamentos = rpDAO.find();
		this.receitaTotal = somaReceitaTotal(this.pagamentos);
	}

	public void buscarPorAno() {
		this.pagamentos = new ArrayList<ReceitaPagamento>();
		ReceitaPagamentoDAO rpDAO = new ReceitaPagamentoJPADAO();
		this.pagamentos = rpDAO.buscarPorAno(this.ano);
		this.receitaTotal = somaReceitaTotal(this.pagamentos);
	}

	public ReceitaPagamento buscarPorSecretaria() {
		ReceitaPagamentoDAO rpDAO = new ReceitaPagamentoJPADAO();
		return rpDAO.buscarPorSecretaria(this.secretaria.getId(), this.ano);
	}

	public int sortByNome(String n1, String n2) {
		return n1.compareTo(n2);
	}

	public List<String> buscarTodosSecretarias(String query) {
		SecretariaDAO secretariaDAO = new SecretariaJPADAO();
		List<String> nomes = secretariaDAO.buscarPorNomeInicial(query.toUpperCase());
		return nomes;
	}

	public float somaReceitaTotal(List<ReceitaPagamento> pagamentos) {
		float total = 0;
		for (ReceitaPagamento pagamento : pagamentos) {
			total += pagamento.getValor();
		}
		return total;
	}

	public ReceitaPagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(ReceitaPagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<ReceitaPagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<ReceitaPagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public float getReceitaTotal() {
		return receitaTotal;
	}

	public void setReceitaTotal(float receitaTotal) {
		this.receitaTotal = receitaTotal;
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

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

}
