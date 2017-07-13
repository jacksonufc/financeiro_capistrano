package controle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import dao.DespesaDAO;
import dao.DespesaJPADAO;
import dao.ReceitaDAO;
import dao.ReceitaJPADAO;
import modelo.Mes;

@ManagedBean
@ViewScoped
public class HomeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nomeUsuario;
	private Double despesaTotal;
	private Double receitaTotal;
	private Double saldo;
	private List<Mes> meses;
	private Mes mes;
	private int ano;
	private String cor;
	
	public HomeBean(){
		this.despesaTotal = new Double(0);
		this.receitaTotal = new Double(0);
		this.saldo = new Double(0);
		buscarNomeUsuario();
		this.meses = Arrays.asList(Mes.values());
		this.mes = pegarMesAtual();
		this.ano = pegarAnoAtual();
		buscarDespesaReceitaTotal();
	}
	
	public void buscarNomeUsuario(){
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.nomeUsuario = (String) sessao.getAttribute("nome");
	}
	
	public void buscarDespesaReceitaTotal(){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(false);
		DespesaDAO dDAO = new DespesaJPADAO();
		Double d = dDAO.soma(this.mes, this.ano);
		if(d != null){ 
			this.despesaTotal = d;
		}else{
			this.despesaTotal = new Double(0);
		}
		ReceitaDAO rDAO = new ReceitaJPADAO();
		Double r = rDAO.soma(this.mes, this.ano);
		if(r != null){
			this.receitaTotal = r;
		}else{
			this.receitaTotal = new Double(0);
		}
		this.saldo = receitaTotal - despesaTotal;
		if(this.saldo == 0){
			this.cor = "black";
		}else if(this.saldo > 0){
			this.cor = "green";
		}else{
			this.cor = "red";
		}
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

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Double getDespesaTotal() {
		return despesaTotal;
	}

	public void setDespesaTotal(Double despesaTotal) {
		this.despesaTotal = despesaTotal;
	}

	public Double getReceitaTotal() {
		return receitaTotal;
	}

	public void setReceitaTotal(Double receitaTotal) {
		this.receitaTotal = receitaTotal;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
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

	public List<Mes> getMeses() {
		return meses;
	}

	public void setMeses(List<Mes> meses) {
		this.meses = meses;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
}
