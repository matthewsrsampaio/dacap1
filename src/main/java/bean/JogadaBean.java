package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import dao.JogadaDao;
import entity.Jogada;

@ApplicationScoped
//@SessionScoped
//@RequestScoped
//@ViewScoped
@ManagedBean
public class JogadaBean {
	
	private Jogada jogada = new Jogada();
	private List<Jogada> listaJogador = new ArrayList<Jogada>();
	private Boolean inputEdit = false;
	private String jogadorEspelho1;
	private String jogadorEspelho2;
	
	public void onRowEdit(RowEditEvent<Jogada> event) {
		try {
			Jogada jogada = JogadaDao.buscarPorId(event.getObject().getId());
		
			jogada.setJogador1(String.valueOf(event.getObject().getJogador1()));
			jogada.setJogador2(String.valueOf(event.getObject().getJogador2()));
			System.out.println("VALOR     ->     "+String.valueOf(event.getObject().getJogador1()));
			System.out.println("VALOR     ->     "+String.valueOf(event.getObject().getJogador2()));
			JogadaDao.editar(jogada);
			
	        FacesMessage msg = new FacesMessage("Jogadores Editados: ", String.valueOf(event.getObject().getJogador1() + " e " + event.getObject().getJogador2()));
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	
    public void onRowCancel(RowEditEvent<Jogada> event) {
        FacesMessage msg = new FacesMessage("Edição cancelada: ", String.valueOf(event.getObject().getJogador1() + " e " + event.getObject().getJogador2()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
//    public void onCellEdit(CellEditEvent<Jogada> event) {
//        Object oldValue = event.getOldValue();
//        Object newValue = event.getNewValue();
//
//        if (newValue != null && !newValue.equals(oldValue)) {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
//    }
	
	
	public void clear() throws Exception{
		try {
			jogada.setJogador1(null);
			jogada.setJogador2(null);
			jogadorEspelho1 = null;
			jogadorEspelho2 = null;
		}catch(Exception e) {
			throw e;
		}
    }
	
	public String salvar() throws Exception{
		//papel = 0   
		//pedra = 1
		//tesoura = 2
		try {
			JogadaDao.salvar(jogada);
			Random random = new Random();
			int jogadaEspelho1 = random.nextInt(3);
			int jogadaEspelho2 = random.nextInt(3);
			
			if(jogadaEspelho1==0&&jogadaEspelho2==0) {
				jogada.setResultado("Empate");
			}else if(jogadaEspelho1==1&&jogadaEspelho2==1) {
				jogada.setResultado("Empate");
			} else if(jogadaEspelho1==2&&jogadaEspelho2==2) {
				jogada.setResultado("Empate");
			} else if(jogadaEspelho1==0&&jogadaEspelho2==1) {
				jogada.setResultado(jogada.getJogador1());
			} else if(jogadaEspelho1==0&&jogadaEspelho2==2) {
				jogada.setResultado(jogada.getJogador2());
			} else if(jogadaEspelho1==1&&jogadaEspelho2==0) {
				jogada.setResultado(jogada.getJogador2());
			} else if(jogadaEspelho1==1&&jogadaEspelho2==2) {
				jogada.setResultado(jogada.getJogador1());
			} else if(jogadaEspelho1==2&&jogadaEspelho2==0) {
				jogada.setResultado(jogada.getJogador1());
			} else if(jogadaEspelho1==2&&jogadaEspelho2==1) {
				jogada.setResultado(jogada.getJogador2());
			}
			
			if(jogada.getId() != 0 || jogada.getId() != null) {
				Jogada jogo = JogadaDao.buscarPorId(jogada.getId()-1);
						
				Integer pedra=0;
				Integer papel=0;
				Integer tesoura=0;
							
				switch(jogadaEspelho1) {
				 case 0:
					 jogada.setJogada1("Papel");
					 papel++;
					 jogada.setPedra(jogo.getPedra());
					 jogada.setTesoura(jogo.getTesoura());
					 break;
				 case 1:
					 jogada.setJogada1("Pedra");
					 jogada.setPapel(jogo.getPapel());
					 pedra++;
					 jogada.setTesoura(jogo.getTesoura());
					 break;
				 case 2:
					 jogada.setJogada1("Tesoura");
					 jogada.setPapel(jogo.getPapel());
					 jogada.setPedra(jogo.getPedra());
					 tesoura++;
					 break;
				 default:
					 jogada.setJogada1(null);
					 System.out.println("invalid result");
				}
				
				switch(jogadaEspelho2) {
				 case 0:
					 jogada.setJogada2("Papel");
					 papel++;
					 jogada.setPedra(jogo.getPedra());
					 jogada.setTesoura(jogo.getTesoura());
					 break;
				 case 1:
					 jogada.setJogada2("Pedra");
					 jogada.setPapel(jogo.getPapel());
					 pedra++;
					 jogada.setTesoura(jogo.getTesoura());
					 break;
				 case 2:
					 jogada.setJogada2("Tesoura");
					 jogada.setPapel(jogo.getPapel());
					 jogada.setPedra(jogo.getPedra());
					 tesoura++;
					 break;
				 default:
					 jogada.setJogada2(null);
					 System.out.println("invalid result");
				}
				
				jogada.setPedra(jogo.getPedra()+pedra);
				jogada.setPapel(jogo.getPapel()+papel);
				jogada.setTesoura(jogo.getTesoura()+tesoura);
				
				JogadaDao.editar(jogada);
				
				String texto = jogada.getResultado();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Objeto salvo com sucesso!", ""));
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Resultado do jogo: ", texto));
				
			} else {
				System.out.println("ERRO! Id é zero ou nulo.");
			}
			clear();
			return null;
			
		}catch(Exception e) {
			throw e;
		}
	}
	
	public String deletar() throws Exception {
		try {
			JogadaDao.deletar(jogada);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Objeto excluído com sucesso!"));
			return null;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void ligaCampo(Boolean edit) {
		inputEdit = edit;
	}
	
	public String botaoEditar() throws Exception {
		System.out.println("CAMPOS DE INPUT: "+jogadorEspelho1+" "+jogadorEspelho1);
		System.out.println("botão editar: "+jogada.getJogador1()+ " e "+jogada.getJogador2());
		ligaCampo(true);
		return null;
	}
	
	public String editar(Jogada jogada) throws Exception {
		try {
			JogadaDao.editar(jogada);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Objeto editado com sucesso!"));
//			return null;
			return String.valueOf(jogada);
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
		if(listaJogador != null ) {
			listaJogador = JogadaDao.buscarTodos();
		}
		return listaJogador;
	}
	public void setListaJogador(List<Jogada> listaJogador) {
		this.listaJogador = listaJogador;
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
