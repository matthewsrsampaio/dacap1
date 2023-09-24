package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.JogadaDao;
import entity.Jogada;

@SessionScoped
@ManagedBean
public class JogadaBean {
	
	private Jogada jogada = new Jogada();
	private List<Jogada> listaJogador;
	private Boolean outputEdit = true;
	private Boolean inputEdit = false;
	private String jogadorEspelho1;
	private String jogadorEspelho2;
	
	public void clear() throws Exception{
		try {
			jogada.setJogador1(null);
			jogada.setJogador2(null);
		}catch(Exception e) {
			throw e;
		}
    }
	
	public String salvar() throws Exception{
		try {
			JogadaDao.salvar(jogada);
			//String texto = jogada.toString();
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Info =>", texto));
			clear();
			return null;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public String deletar() throws Exception {
		try {
			JogadaDao.deletar(jogada);
			return null;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void ligaCampo() {
		inputEdit = true;
		outputEdit = false;
	}
	
	public void desligaCampo() {
		inputEdit = false;
		outputEdit = true;
	}
	
	public String botaoEditar(Jogada jogada) throws Exception {
		ligaCampo();
		//clear();
		return null;
	}
	
	public String editar(Jogada jogada) throws Exception {
		try {
			jogada.setJogador1(jogadorEspelho1);
			jogada.setJogador2(jogadorEspelho2);
			JogadaDao.editar(jogada);
			desligaCampo();
			return null;
		}catch(Exception e) {
			throw e;
		}
	}
	
	//GETTERS AND SETTERS
	public Jogada getJogada() {
		return jogada;
	}
	public void setJogada(Jogada jogada) {
		this.jogada = jogada;
	}
	public List<Jogada> getListaJogador() throws Exception {
		return JogadaDao.buscarTodos();
	}
	public void setListaJogador(List<Jogada> listaJogador) {
		this.listaJogador = listaJogador;
	}
	public Boolean getOutputEdit() {
		return outputEdit;
	}
	public void setOutputEdit(Boolean outputEdit) {
		this.outputEdit = outputEdit;
	}
	public Boolean getInputEdit() {
		return inputEdit;
	}
	public void setInputEdit(Boolean inputEdit) {
		this.inputEdit = inputEdit;
	}
	public String getJogadorEspelho1() {
		return jogadorEspelho1;
	}
	public void setJogadorEspelho1(String jogadorEspelho1) {
		this.jogadorEspelho1 = jogadorEspelho1;
	}
	public String getJogadorEspelho2() {
		return jogadorEspelho2;
	}
	public void setJogadorEspelho2(String jogadorEspelho2) {
		this.jogadorEspelho2 = jogadorEspelho2;
	}
	
}
