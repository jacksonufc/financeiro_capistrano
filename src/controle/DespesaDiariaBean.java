package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import dao.DespesaDiariaDAO;
import dao.DespesaDiariaJPADAO;
import modelo.DespesaDiaria;
import modelo.Mes;

@ManagedBean
@ViewScoped
public class DespesaDiariaBean extends AbstractBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private DespesaDiaria despesaDiaria;
	private List<DespesaDiaria> despesasDiarias;
	private List<Mes> meses;
	private Mes mes;
	private int ano;
	private float despesaTotal;
	
	public DespesaDiariaBean(){
		this.despesaDiaria = new DespesaDiaria();
		this.despesasDiarias = new ArrayList<DespesaDiaria>();
		this.meses = Arrays.asList(Mes.values());
	}
	
	public void cadastrar(){
		DespesaDiariaDAO dDAO = new DespesaDiariaJPADAO();
		dDAO.save(this.despesaDiaria);
		displayInfoMessageToUser("Cadastrado com sucesso!");
		this.despesaDiaria = new DespesaDiaria();
	}
	
	public void selecionarParaAtualizar(DespesaDiaria d){
		this.despesaDiaria = d;
		RequestContext.getCurrentInstance().execute("PF('edit').show()");
	}
	
	public void atualizar(){
		DespesaDiariaDAO dDAO = new DespesaDiariaJPADAO();
		dDAO.save(this.despesaDiaria);
		displayInfoMessageToUser("Atualizado com sucesso!");
		this.despesaDiaria = new DespesaDiaria();
	}
	
	public void excluir(DespesaDiaria dd){
		DespesaDiariaDAO dDAO = new DespesaDiariaJPADAO();
		dDAO.delete(dd);
		displayInfoMessageToUser("Excluido com sucesso!");
		this.despesasDiarias.remove(dd);
	}
	
	public void buscarPorAno(){
		this.despesasDiarias = new ArrayList<DespesaDiaria>();
		DespesaDiariaDAO dDAO = new DespesaDiariaJPADAO();
		this.despesasDiarias = dDAO.buscarPorAno(this.ano);
		this.despesaTotal = somaDespesaTotal(this.despesasDiarias);
	}
	
	public void buscarPorMesAno(){
		this.despesasDiarias = new ArrayList<DespesaDiaria>();
		DespesaDiariaDAO dDAO = new DespesaDiariaJPADAO();
		this.despesasDiarias = dDAO.buscarPorMesAno(this.mes, this.ano);
		this.despesaTotal = somaDespesaTotal(this.despesasDiarias);
	}
	
	public float somaDespesaTotal(List<DespesaDiaria> d){
		float total = 0;
		for(int i = 0; i < d.size(); i++){
			total += d.get(i).getValor();
		}
		return total;
	}
	
	public DespesaDiaria getDespesaDiaria() {
		return despesaDiaria;
	}
	public void setDespesaDiaria(DespesaDiaria despesaDiaria) {
		this.despesaDiaria = despesaDiaria;
	}
	public List<DespesaDiaria> getDespesasDiarias() {
		return despesasDiarias;
	}
	public void setDespesasDiarias(List<DespesaDiaria> despesasDiarias) {
		this.despesasDiarias = despesasDiarias;
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
