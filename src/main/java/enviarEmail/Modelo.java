package enviarEmail;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Modelo {

	private StringProperty valorServidor = new SimpleStringProperty();
	private StringProperty valorPuerto = new SimpleStringProperty();
	private StringProperty valorRemitente = new SimpleStringProperty();
	private StringProperty valorContrasenia = new SimpleStringProperty();
	private StringProperty valorDestinatario = new SimpleStringProperty();
	private StringProperty valorAsunto = new SimpleStringProperty();
	private StringProperty valorMensaje = new SimpleStringProperty();
	
	
	

    public final void setServidor(String value) {
        this.valorServidor.set(value);
    }
    
    public final void setPuerto(String value) {
        this.valorPuerto.set(value);
    }
    
    public final void setRemitente(String value) {
        this.valorRemitente.set(value);
    }
    
    public final void setContrasenia(String value) {
        this.valorContrasenia.set(value);
    }
    
    public final void setDestinatario(String value) {
        this.valorDestinatario.set(value);
    }
    
    public final void setAsunto(String value) {
        this.valorAsunto.set(value);
    }
    
    public final void setMensaje(String value) {
        this.valorMensaje.set(value);
    }
	
	public StringProperty getValorServidor() {
		return valorServidor;
	}
	public void setValorServidor(StringProperty valorSmtp) {
		this.valorServidor = valorSmtp;
	}
	public StringProperty getValorPuerto() {
		return valorPuerto;
	}
	public void setValorPuerto(StringProperty valorPuerto) {
		this.valorPuerto = valorPuerto;
	}
	public StringProperty getValorRemitente() {
		return valorRemitente;
	}
	public void setValorRemitente(StringProperty valorRemitente) {
		this.valorRemitente = valorRemitente;
	}
	public StringProperty getValorContrasenia() {
		return valorContrasenia;
	}
	public void setValorContrasenia(StringProperty valorContrasenia) {
		this.valorContrasenia = valorContrasenia;
	}
	public StringProperty getValorDestinatario() {
		return valorDestinatario;
	}
	public void setValorDestinatario(StringProperty valorDestinatario) {
		this.valorDestinatario = valorDestinatario;
	}
	public StringProperty getValorAsunto() {
		return valorAsunto;
	}
	public void setValorAsunto(StringProperty valorAsunto) {
		this.valorAsunto = valorAsunto;
	}
	public StringProperty getValorMensaje() {
		return valorMensaje;
	}
	public void setValorMensaje(StringProperty valorMensaje) {
		this.valorMensaje = valorMensaje;
	}
	
	
	
	
}
