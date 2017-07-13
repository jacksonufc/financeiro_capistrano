package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import dao.DespesaLuzDAO;
import dao.DespesaLuzJPADAO;
import modelo.DespesaLuz;
import modelo.Mes;

@ManagedBean
@ViewScoped
public class DespesaLuzBean extends AbstractBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private DespesaLuz despesaLuz;
	private List<DespesaLuz> despesasLuzes;
	private float despesaTotal;
	private Mes mes;
	private int ano;
	private List<Mes> meses;
	
	public DespesaLuzBean(){
		this.despesaLuz = new DespesaLuz();
		this.despesasLuzes = new ArrayList<DespesaLuz>();
		this.meses = Arrays.asList(Mes.values());
	}
	
	public void cadastrar(){
		DespesaLuzDAO dDAO = new DespesaLuzJPADAO();
		dDAO.save(this.despesaLuz);
		displayInfoMessageToUser("Cadastrado com sucesso!");
		this.despesaLuz = new DespesaLuz();
	}
	
	public void selecionarParaAtualizar(DespesaLuz dl){
		this.despesaLuz = dl;
		RequestContext.getCurrentInstance().execute("PF('edit').show()");
	}
	
	public void atualizar(){
		DespesaLuzDAO dDAO = new DespesaLuzJPADAO();
		dDAO.save(this.despesaLuz);
		displayInfoMessageToUser("Atualizado com sucesso!");
		this.despesaLuz = new DespesaLuz();
	}
	
	public void excluir(DespesaLuz dl){
		DespesaLuzDAO dDAO = new DespesaLuzJPADAO();
		dDAO.delete(dl);
		displayInfoMessageToUser("Deletado com sucesso!");
		this.despesasLuzes.remove(dl);
	}
	
	
	public void buscarPorAno(){
		this.despesasLuzes = new ArrayList<DespesaLuz>();
		DespesaLuzDAO dDAO = new DespesaLuzJPADAO();
		this.despesasLuzes = dDAO.buscarPorAno(this.ano);
		this.despesaTotal = somaDespesaTotal(this.despesasLuzes);
	}
	
	public void buscarPorMesAno(){
		this.despesasLuzes = new ArrayList<DespesaLuz>();
		DespesaLuzDAO dDAO = new DespesaLuzJPADAO();
		this.despesasLuzes = dDAO.buscarPorMesAno(this.mes, this.ano);
		this.despesaTotal = somaDespesaTotal(this.despesasLuzes);
	}
	
	public float somaDespesaTotal(List<DespesaLuz> d){
		float total = 0;
		for(int i = 0; i < d.size(); i++){
			total += d.get(i).getValor();
		}
		return total;
	}
	
	
	public DespesaLuz getDespesaLuz() {
		return despesaLuz;
	}
	public void setDespesaLuz(DespesaLuz despesaLuz) {
		this.despesaLuz = despesaLuz;
	}
	public List<DespesaLuz> getDespesasLuzes() {
		return despesasLuzes;
	}
	public void setDespesasLuzes(List<DespesaLuz> despesasLuzes) {
		this.despesasLuzes = despesasLuzes;
	}
	public float getDespesaTotal() {
		return despesaTotal;
	}
	public void setDespesaTotal(float despesaTotal) {
		this.despesaTotal = despesaTotal;
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
	
	

}
