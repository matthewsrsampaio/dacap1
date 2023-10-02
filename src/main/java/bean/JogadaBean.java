package bean;

import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import dao.JogadaDao;
import entity.Jogada;

@ManagedBean
public class JogadaBean {
	
	private Jogada jogada = new Jogada();
	private List<Jogada> listaJogador;
	
	public void onRowEdit(RowEditEvent<Jogada> event) throws Exception {
		Jogada jogada = JogadaDao.buscarPorId(event.getObject().getId());
	
		jogada.setJogador1(String.valueOf(event.getObject().getJogador1()));
		jogada.setJogador2(String.valueOf(event.getObject().getJogador2()));
		JogadaDao.editar(jogada);
		
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Jogadores Editados: ", String.valueOf(event.getObject().getJogador1() + " e " + event.getObject().getJogador2())));
    }
	
    public void onRowCancel(RowEditEvent<Jogada> event) throws Exception {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edição cancelada: ", String.valueOf(event.getObject().getJogador1() + " e " + event.getObject().getJogador2())));
    }	
	
	public void clear() throws Exception{
		jogada.setJogador1(null);
		jogada.setJogador2(null);
    }
    
	public String salvar() {
		//papel = 0   
		//pedra = 1
		//tesoura = 2		
		try {
			Integer pedra=0;
			Integer papel=0;
			Integer tesoura=0;
			
			Random random = new Random();
			int jogadaEspelho1 = random.nextInt(3);
			int jogadaEspelho2 = random.nextInt(3);
			
			if(jogadaEspelho1==0&&jogadaEspelho2==0) {
				jogada.setResultado("Empate");
				jogada.setJogada1("Papel");
				jogada.setJogada2("Papel");
				papel++;
				papel++;
			}else if(jogadaEspelho1==1&&jogadaEspelho2==1) {
				jogada.setResultado("Empate");
				jogada.setJogada1("Pedra");
				jogada.setJogada2("Pedra");
				pedra++;
				pedra++;
			} else if(jogadaEspelho1==2&&jogadaEspelho2==2) {
				jogada.setResultado("Empate");
				jogada.setJogada1("Tesoura");
				jogada.setJogada2("Tesoura");
				tesoura++;
				tesoura++;
			} else if(jogadaEspelho1==0&&jogadaEspelho2==1) {
				jogada.setResultado(jogada.getJogador1());
				jogada.setJogada1("Papel");
				jogada.setJogada2("Pedra");
				papel++;
				pedra++;
			} else if(jogadaEspelho1==0&&jogadaEspelho2==2) {
				jogada.setResultado(jogada.getJogador2());
				jogada.setJogada1("Papel");
				jogada.setJogada2("Tesoura");
				tesoura++;
				pedra++;
			} else if(jogadaEspelho1==1&&jogadaEspelho2==0) {
				jogada.setResultado(jogada.getJogador2());
				jogada.setJogada1("Pedra");
				jogada.setJogada2("Papel");
				papel++;
				pedra++;
			} else if(jogadaEspelho1==1&&jogadaEspelho2==2) {
				jogada.setResultado(jogada.getJogador1());
				jogada.setJogada1("Pedra");
				jogada.setJogada2("Tesoura");
				pedra++;
				tesoura++;
			} else if(jogadaEspelho1==2&&jogadaEspelho2==0) {
				jogada.setResultado(jogada.getJogador1());
				jogada.setJogada1("Tesoura");
				jogada.setJogada2("Papel");
				tesoura++;
				papel++;
			} else if(jogadaEspelho1==2&&jogadaEspelho2==1) {
				jogada.setResultado(jogada.getJogador2());
				jogada.setJogada1("Tesoura");
				jogada.setJogada2("Pedra");
				pedra++;
				tesoura++;
			}
			
			jogada.setPapel(papel);
			jogada.setPedra(pedra);
			jogada.setTesoura(tesoura);
			
			JogadaDao.salvar(jogada);
			
			clear();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Objeto salvo com sucesso!", ""));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Resultado do jogo: "+jogada.getResultado(), null));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String deletar() {
		try {
			JogadaDao.deletar(jogada);
			listaJogador = JogadaDao.buscarTodos();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Objeto excluído com sucesso!"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void editar(Jogada jogada) {
		try {
			JogadaDao.editar(jogada);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Objeto editado com sucesso!"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Long buscarSomaPapel() throws Exception {
		return JogadaDao.buscarSomaPapel();
	}
	
	public Long buscarSomaPedra() throws Exception {
		return JogadaDao.buscarSomaPedra();
	}
	
	public Long buscarSomaTesoura() throws Exception {
		return JogadaDao.buscarSomaTesoura();
	}
	
	public void mostrarSomatorio() throws Exception {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Somatório das saídas -> ", "Papel: "+buscarSomaPapel().toString()+", Pedra: "+buscarSomaPedra().toString()+", Tesoura: "+buscarSomaTesoura().toString()+"."));
	}
	
	//GETTERS AND SETTERS
	public Jogada getJogada() {
		return jogada;
	}
	public void setJogada(Jogada jogada) {
		this.jogada = jogada;
	}
	public List<Jogada> getListaJogador() throws Exception {
		listaJogador = (listaJogador==null) ? listaJogador = JogadaDao.buscarTodos():listaJogador;
		return listaJogador;
	}
	public void setListaJogador(List<Jogada> listaJogador) {
		this.listaJogador = listaJogador;
	}
}