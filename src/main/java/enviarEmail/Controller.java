package enviarEmail;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller implements Initializable{
	//DECLARACION DE VARIABLES
	private Alert alerta;
	private Modelo modelo = new Modelo();
	private Task<Void> segundoplano;
	@FXML
	private GridPane view;
	
	@FXML
    private TextArea textMensaje;

    @FXML
    private Button botonVaciar;

    @FXML
    private Button botonCerrar;

    @FXML
    private TextField textPuerto;

    @FXML
    private TextField textServidor;

    @FXML
    private TextField textRemitente;

    @FXML
    private Button botonEnviar;

    @FXML
    private PasswordField textContrasenia;

    @FXML
    private TextField textDestinatario;

    @FXML
    private TextField textAsunto;

    @FXML
    private CheckBox checkSsl;
	
    //CREAMOS EL CONSTRUCTOR DEL CONTROLLER PARA CARGAR EL FXML
    public Controller() throws IOException {//CARGAMOS EL VIEW.FXML DONDE ESTA LA VISTA
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
		loader.setController(this);
		loader.load();
	}
	
    //LE HACEMOS UN GETVIEW PARA LLAMAR A LA VISTA
	public GridPane getView() {
		return view;
	}

	//CREAMOS LA ACCION DEL BOTON DE CERRAR
	@FXML
	public void onCerrarButtonAction(ActionEvent event) {
		Stage stage = (Stage) botonCerrar.getScene().getWindow();//CON ELLO CERRAMOS EL PROGRAMA
	    stage.close();
    }
	
	//CREAMOS EL VACIAR HACIENDO LOS SET CON EL CAMPO VACIO
	@FXML
	public void onVaciarButtonAction(ActionEvent event) {
		modelo.setServidor("");
		modelo.setAsunto("");
		modelo.setContrasenia("");
		modelo.setMensaje("");
		modelo.setPuerto("");
		modelo.setRemitente("");
		modelo.setDestinatario("");
    }
	
	//CREAMOS EL BOTON ENVIAR
	@FXML
    public void onEnviarButtonAction(ActionEvent event) {
		//EN EL HACEMOS EL TRY / SI FUNCIONA
    	
    		int puerto = Integer.parseInt(textPuerto.textProperty().getValue());//COGEMOS EL VALOR DEL PUERTO CON EL TEXT PROPERTY
    		
    		if(checkSsl.selectedProperty().get() == false)//SI EL SSL NO ESTA ACTIVADO MOSTRAMOS UN ALERT
    		{
    			alerta = new Alert(AlertType.WARNING);
        		alerta.setTitle("Información");
        		alerta.setHeaderText("Debe usar conexión SSL.");
        		alerta.showAndWait();
        		return;
    		}
    		else{
    			segundoplano = new Task<Void>() {
    			@Override
    			protected Void call() throws Exception {
	    			Email email = new SimpleEmail();//LLAMAMOS A EMAIL DE APACHE
	    			email.setHostName(textServidor.textProperty().getValue());//LE DAMOS VALOR AL HOST
	    			email.setSslSmtpPort(textPuerto.textProperty().getValue());//PUERTO
	    			email.setAuthenticator(new DefaultAuthenticator(textRemitente.textProperty().getValue(), textContrasenia.textProperty().getValue()));
	    			email.setSSLOnConnect(true);
	    			email.setFrom(textRemitente.textProperty().getValue());
	    			email.setSubject(textAsunto.textProperty().getValue());
	    			email.setMsg(textMensaje.textProperty().getValue());
	    			email.addTo(textDestinatario.textProperty().getValue());
	    			email.send();
	    			return null;//ENVIAMOS EL MENSAJE
    			} 
    			};
    			
    			segundoplano.setOnSucceeded(e -> {
	    			alerta = new Alert(AlertType.INFORMATION);//UNA VEZ ENVIADO MUESTRA ALERT DE SUCCESS
	        		alerta.setTitle("Éxito");
	        		alerta.setHeaderText("Email enviado");
	        		alerta.setContentText("Email enviado con éxito a '" + textDestinatario.textProperty().getValue() + "'");
	        		alerta.showAndWait();
	        		
	        		//BORRAMOS ASUNTO Y MENSAJE POR SI QUIERE MANDAR NUEVO MENSAJE
	        		modelo.setAsunto("");
	        		modelo.setMensaje("");
    			});
    		
	    		//ALERT DE ENVIO ERRONEO
	    		segundoplano.setOnFailed(e -> {
	    		alerta = new Alert(AlertType.ERROR);
	    		alerta.setTitle("Error");
	    		alerta.setHeaderText("No se pudo enviar el email");
	    		alerta.setContentText(e.getSource().getException().getMessage());
	    		alerta.showAndWait();
	    		});
	    		botonEnviar.disableProperty().bind(segundoplano.runningProperty());
	    		new Thread(segundoplano).start();
    }
	}



	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//EN EL INITIALIZABLE HACEMOS UN BINDBIDIRECCIONAL DE CADA UNO DE LOS CAMPO POR SI CAMBIA EL VALOR
		modelo.getValorServidor().bindBidirectional(textServidor.textProperty());
		modelo.getValorPuerto().bindBidirectional(textPuerto.textProperty());
		modelo.getValorRemitente().bindBidirectional(textRemitente.textProperty());
		modelo.getValorContrasenia().bindBidirectional(textContrasenia.textProperty());
		modelo.getValorDestinatario().bindBidirectional(textDestinatario.textProperty());
		modelo.getValorAsunto().bindBidirectional(textAsunto.textProperty());
		modelo.getValorMensaje().bindBidirectional(textMensaje.textProperty());
		
	}
}
