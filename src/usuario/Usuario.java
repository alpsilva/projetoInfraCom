
package usuario;

/*
 * usuario controla cliente e servidor
 * 
 */

import comandos.EnviarMsg;
import comandos.ReceberMsg;

public class Usuario {
	private Servidor servidor;
	private Cliente cliente;
	private EnviarMsg enviarC;
	
	
	public Usuario(){
		this.cliente = new Cliente();
	}
	
	
	 // ao se conectar com um servidor, o usu�rio primeiro inicia uma conex�o de comandos com o servidor (envia e recebe mensagens)	
	public void conectar(String ip, int porta, String caminho) {
		this.cliente.conectar(ip, porta);
		enviarC = new EnviarMsg(this.cliente.pegarSoquete());
		ReceberMsg receber = new ReceberMsg(this.cliente.pegarSoquete());
		receber.setCaminho(caminho);
		new Thread(receber).start();
	}
	
	public void iniciarServidor(int porta, String caminho){
		this.servidor = new Servidor(porta, caminho);
		this.servidor.iniciar();
	}
	
	//� com essa fun��o que se d� a troca de comandos entre cliente e servidor (entre usuarios)
	public void enviarMsg(String msg){
		this.enviarC.enviar(msg);
	}
}
