package br.com.trier.ProjetoJovemDev.services.exceptions;

public class IntegrityViolation extends RuntimeException{
	
	public IntegrityViolation (String mensagem) {
		super(mensagem);
	}

}
