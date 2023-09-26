package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "jogada")
public class Jogada {

	@Id
	@GeneratedValue
	private Integer id;
	private String jogador1;
	private String jogador2;
	private String jogada1;
	private String jogada2;
	private String resultado;
	private Integer papel = 0;
	private Integer pedra = 0;
	private Integer tesoura = 0;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "DATE")
	private Date dataJogo = new Date();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJogador1() {
		return jogador1;
	}
	public void setJogador1(String jogador1) {
		this.jogador1 = jogador1;
	}
	public String getJogador2() {
		return jogador2;
	}
	public void setJogador2(String jogador2) {
		this.jogador2 = jogador2;
	}
	public String getJogada1() {
		return jogada1;
	}
	public void setJogada1(String jogada1) {
		this.jogada1 = jogada1;
	}
	public String getJogada2() {
		return jogada2;
	}
	public void setJogada2(String jogada2) {
		this.jogada2 = jogada2;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public Date getDataJogo() {
		return dataJogo;
	}
	public void setDataJogo(Date dataJogo) {
		this.dataJogo = dataJogo;
	}
	public Integer getPapel() {
		return papel;
	}
	public void setPapel(Integer papel) {
		this.papel = papel;
	}
	public Integer getPedra() {
		return pedra;
	}
	public void setPedra(Integer pedra) {
		this.pedra = pedra;
	}
	public Integer getTesoura() {
		return tesoura;
	}
	public void setTesoura(Integer tesoura) {
		this.tesoura = tesoura;
	}	
	
}
