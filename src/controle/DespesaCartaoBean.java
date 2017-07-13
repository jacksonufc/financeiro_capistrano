package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import dao.DespesaCartaoDAO;
import dao.DespesaCartaoJPADAO;
import modelo.DespesaCartao;
import modelo.Mes;

@ManagedBean
@ViewScoped
public class DespesaCartaoBean extends AbstractBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private DespesaCartao despesaCartao;
	private List<DespesaCartao> despesasCartoes;
	private List<Mes> meses;
	private Mes mes;
	private int ano;
	private float despesaTotal;
	
	public DespesaCartaoBean(){
		this.despesaCartao = new DespesaCartao();
		this.despesasCartoes = new ArrayList<DespesaCartao>();
		this.meses = Arrays.asList(Mes.values());
	}
	
	public void cadastrar(){
		DespesaCartaoDAO dDAO = new DespesaCartaoJPADAO();
		dDAO.save(this.despesaCartao);
		displayInfoMessageToUser("Cadastrado com sucesso!");
		this.despesaCartao = new DespesaCartao();
	}
	
	public void selecionarParaAtualizar(DespesaCartao d){
		this.despesaCartao = d;
		RequestContext.getCurrentInstance().execute("PF('edit').show()");
	}
	
	public void atualizar(){
		DespesaCartaoDAO dDAO = new DespesaCartaoJPADAO();
		dDAO.save(this.despesaCartao);
		displayInfoMessageToUser("Atualizado com sucesso!");
		this.despesaCartao = new DespesaCartao();
	}
	
	public void excluir(DespesaCartao dc){
		DespesaCartaoDAO dDAO = new DespesaCartaoJPADAO();
		dDAO.delete(dc);
		displayInfoMessageToUser("Excluido com sucesso!");
		this.despesasCartoes.remove(dc);
	}
	
	public void buscarPorAno(){
		this.despesasCartoes = new ArrayList<DespesaCartao>();
		DespesaCartaoDAO dDAO = new DespesaCartaoJPADAO();
		this.despesasCartoes = dDAO.buscarPorAno(this.ano);
		this.despesaTotal = somaDespesaTotal(this.despesasCartoes);
	}
	
	public void buscarPorMesAno(){
		this.despesasCartoes = new ArrayList<DespesaCartao>();
		DespesaCartaoDAO dDAO = new DespesaCartaoJPADAO();
		this.despesasCartoes = dDAO.buscarPorMesAno(this.mes, this.ano);
		this.despesaTotal = somaDespesaTotal(this.despesasCartoes);
	}
	
	public float somaDespesaTotal(List<DespesaCartao> d){
		float total = 0;
		for(int i = 0; i < d.size(); i++){
			total += d.get(i).getValor();
		}
		return total;
	}
	
	public DespesaCartao getDespesaCartao() {
		return despesaCartao;
	}
	public void setDespesaCartao(DespesaCartao despesaCartao) {
		this.despesaCartao = despesaCartao;
	}
	public List<DespesaCartao> getDespesasCartoes() {
		return despesasCartoes;
	}
	public void setDespesasCartoes(List<DespesaCartao> despesasCartoes) {
		this.despesasCartoes = despesasCartoes;
	}
	public List<Mes> getMeses() {
		return meses;
	}
	public void setMeses(List<Mes> meses) {
		this.meses = meses;
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
	public float getDespesaTotal() {
		return despesaTotal;
	}
	public void setDespesaTotal(float despesaTotal) {
		this.despesaTotal = despesaTotal;
	}
	
	

}
