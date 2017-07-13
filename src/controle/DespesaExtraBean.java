package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import dao.DespesaExtraDAO;
import dao.DespesaExtraJPADAO;
import modelo.DespesaExtra;
import modelo.Mes;

@ManagedBean
@ViewScoped
public class DespesaExtraBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private DespesaExtra despesaExtra;
	private List<DespesaExtra> despesasExtras;
	private List<Mes> meses;
	private Mes mes;
	private int ano;
	private float despesaTotal;
	
	
	public DespesaExtraBean(){
		this.despesaExtra = new DespesaExtra();
		this.despesasExtras = new ArrayList<DespesaExtra>();
		this.meses = Arrays.asList(Mes.values());
	}
	
	public void cadastrar(){
		DespesaExtraDAO dDAO = new DespesaExtraJPADAO();
		dDAO.save(this.despesaExtra);
		displayInfoMessageToUser("Cadastrado com sucesso!");
		this.despesaExtra = new DespesaExtra();
	}
	
	public void selecionarParaAtualizar(DespesaExtra de){
		this.despesaExtra = de;
		RequestContext.getCurrentInstance().execute("PF('edit').show()");
	}
	
	public void atualizar(){
		DespesaExtraDAO dDAO = new DespesaExtraJPADAO();
		dDAO.save(this.despesaExtra);
		displayInfoMessageToUser("Atualizado com sucesso!");
		this.despesaExtra = new DespesaExtra();
	}
	
	public void excluir(DespesaExtra de){
		DespesaExtraDAO dDAO = new DespesaExtraJPADAO();
		dDAO.delete(de);
		displayInfoMessageToUser("Excluido com sucesso!");
		this.despesasExtras.remove(de);
	}
	
	public void buscarPorAno(){
		this.despesasExtras = new ArrayList<DespesaExtra>();
		DespesaExtraDAO dDAO = new DespesaExtraJPADAO();
		this.despesasExtras = dDAO.buscarPorAno(this.ano);
		this.despesaTotal = somaDespesaTotal(this.despesasExtras);
	}
	
	public void buscarPorMesAno(){
		this.despesasExtras = new ArrayList<DespesaExtra>();
		DespesaExtraDAO dDAO = new DespesaExtraJPADAO();
		this.despesasExtras = dDAO.buscarPorMesAno(this.mes, this.ano);
		this.despesaTotal = somaDespesaTotal(this.despesasExtras);
	}
	
	public float somaDespesaTotal(List<DespesaExtra> d){
		float total = 0;
		for(int i = 0; i < d.size(); i++){
			total += d.get(i).getValor();
		}
		return total;
	}
	
	public DespesaExtra getDespesaExtra() {
		return despesaExtra;
	}
	public void setDespesaExtra(DespesaExtra despesaExtra) {
		this.despesaExtra = despesaExtra;
	}
	public List<DespesaExtra> getDespesasExtras() {
		return despesasExtras;
	}
	public void setDespesasExtras(List<DespesaExtra> despesasExtras) {
		this.despesasExtras = despesasExtras;
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
