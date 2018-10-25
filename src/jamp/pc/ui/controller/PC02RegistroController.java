/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jamp.pc.ui.controller;

import jamp.pc.logic.ILogic;
import jamp.pc.logic.UserLoginExistException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class for users sign up view. It contains event handlers
 *
 * @author ander
 */
public class PC02RegistroController {

    /**
     * Users Login text field
     */
    @FXML
    private TextField tfLogin;
    /**
     * Users Name and Surname text field
     */
    @FXML
    private TextField tfFullName;
    /**
     * Users email text field
     */
    @FXML
    private TextField tfEmail;
    /**
     * Users Password password field
     */
    @FXML
    private TextField tfPassw;
    /**
     * Repetition of the Password password field
     */
    @FXML
    private TextField tfRpassw;
    /**
     * Show written password button
     */
    
    @FXML
    private PasswordField pfPassw;
    /**
     * Repetition of the Password password field
     */
    @FXML
    private PasswordField pfRpassw;
    /**
     * Show written password button
     */
    
    @FXML
    private Button btnEye;
    /**
     *
     */
    @FXML
    private Label lblLoginW;
    /**
     *
     */
    @FXML
    private Label lblFNameW;
    /**
     *
     */
    @FXML
    private Label lblEmailW;
    /**
     *
     */
    @FXML
    private Label lblPasswW;
    /**
     *
     */
    @FXML
    private Label lblRpasswW;
    /**
     * Go back to Login view button
     */
    @FXML
    private Button btnBack;
    /**
     * User Signup button
     */
    @FXML
    private Button btnSignUp;
    /**
     * Loading image view
     */
    @FXML
    private ImageView imgLoading;
    
    private ILogic iLogic;
    
    public void setILogic(ILogic iLogic) {
        this.iLogic = iLogic;
    }

    /**
     * Stage in which the scene will be loaded
     */
    private Stage stage;

    /**
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    private static final Logger LOGGER
            = Logger.getLogger("jamp.pc.ui.controller");
    /**
     *
     */
    private final int MAX_LENGTH = 255;

    /**
     * Method for initializing PC02Registro Stage.
     *
     * @param root The Parent object
     */
    public void initStage(Parent root) {
        try {
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.setResizable(false);
            //stage.setOnShowing(this::handleWindowShowing);
            LOGGER.info("helloooooooooooooooooo");
            stage.show();
            LOGGER.info("holis");
            
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "{0} No! se ha podido abrir la ventana. \n ",
                    e.getMessage());
        }
    }

    /**
     * Initializes the window when shown.
     *
     * @param event
     */
    private void handleWindowShowing(WindowEvent event) {
        LOGGER.info("Beggining PC02RegistroController::handleWindowShowing");
        LOGGER.info("ventana de registro");
        btnBack.setDisable(false);
        btnEye.setDisable(false);
        btnSignUp.setDisable(false);
        lblLoginW.setVisible(false);
        lblFNameW.setVisible(false);
        lblEmailW.setVisible(false);
        lblPasswW.setVisible(false);
        lblRpasswW.setVisible(false);
        imgLoading.setVisible(false);
        tfPassw.setVisible(false);
        tfRpassw.setVisible(false);
        btnSignUp.requestFocus();
        /*
          btnBack.setOnAction((ActionEvent e) -> {
                back();
            }); 
            btnEye.setOnAction((ActionEvent ev) -> {
                showPassword();
            });
            btnSignUp.setOnAction((ActionEvent eve) -> {
                regis();
            });
        */
    }

    /**
     * Close current view and open Login view method.
     */
    private void back() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/jamp/pc/ui/view/PC01Login.fxml"));
            Parent root = (Parent) loader.load();
            stage = new Stage();
            PC01LoginController loginStageController
                    = ((PC01LoginController) loader.getController());
            loginStageController.setStage(stage);
            loginStageController.initStage(root);
            
            stage.hide();
        } catch (IOException e) {
            LOGGER.log(Level.INFO, "{0} No se ha podido abrir la ventana. \n ",
                    e.getMessage());
        }
        
    }

    /**
     * Method that sets visible or not visible password fields.
     */
    private void showPassword() {
        if (pfPassw.isVisible()) {
            //Change Passw textfield to visible
            tfPassw.setText(pfPassw.getText());
            pfPassw.setVisible(false);
            tfPassw.setVisible(true);
            //Change Rpassw textfield to visible
            tfRpassw.setText(pfRpassw.getText());
            pfRpassw.setVisible(false);
            tfRpassw.setVisible(true);
        } else {
            //Change Passw passwordfield to visible
            pfPassw.setText(tfPassw.getText());
            tfPassw.setVisible(false);
            pfPassw.setVisible(true);
            //Change Rpassw passwordfield to visible
            pfRpassw.setText(tfRpassw.getText());
            tfRpassw.setVisible(false);
            pfRpassw.setVisible(true);
        }
    }

    /**
     *
     */
    private void regis() {
        boolean filled = chkAllFieldsFilled();
        boolean fieldsLength = chkFieldsLength();
        boolean passwMatch = chkPasswMatch();
        boolean passwLen = chkPasswLength();
        if (filled && fieldsLength && passwMatch && passwLen) {
            imgLoading.setVisible(true);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            UserBean user = new UserBean(tfLogin.getText().trim(),
                    tfEmail.getText(), tfFullName.getText(),
                    pfPassw.getText(), now, now);
            //Recibo la Ilogic que me pasa Paula
            //iLogic.userSignUp(user);

        }
        
    }

    /**
     * Method that check if all fields are filled
     *
     * @return filled Boolean
     */
    private boolean chkAllFieldsFilled() {
        boolean filled = true;
        if (pfPassw.getText().trim().isEmpty()
                || tfEmail.getText().trim().isEmpty()
                || tfLogin.getText().trim().isEmpty()
                || tfFullName.getText().trim().isEmpty()) {
            filled = false;

            //Set  textfield and passwordfiels border colors to red
            //and show labels if not filled
            if (tfEmail.getText().trim().isEmpty()) {
                tfEmail.setStyle("-fx-border-color:red;");
                lblEmailW.setText("*Campo obligatorio");
                lblEmailW.setStyle("-fx-text-inner-color: red;");
                lblEmailW.setVisible(true);
            }
            
            if (tfLogin.getText().trim().isEmpty()) {
                tfLogin.setStyle("-fx-border-color: red;");
                lblLoginW.setText("*Campo obligatorio");
                lblLoginW.setStyle("-fx-text-inner-color: red;");
                lblLoginW.setVisible(true);
            }
            
            if (tfFullName.getText().trim().isEmpty()) {
                tfFullName.setStyle("-fx-border-color: red;");
                lblFNameW.setText("*Campo obligatorio");
                lblFNameW.setStyle("-fx-text-inner-color: red;");
                lblFNameW.setVisible(true);
            }
            
            if (pfPassw.getText().trim().isEmpty()) {
                pfPassw.setStyle("-fx-border-color: red;");
                lblPasswW.setText("*Campo obligatorio");
                lblPasswW.setStyle("-fx-text-inner-color: red;");
                lblPasswW.setVisible(true);
            }

            //Set textfields and passwordfield border colors to default and 
            //hide labels if they're filled
            if (!tfEmail.getText().trim().isEmpty()) {
                tfEmail.setStyle("-fx-border-color: default;");
                lblEmailW.setText("");
                lblEmailW.setVisible(false);
            }
            
            if (!tfLogin.getText().trim().isEmpty()) {
                tfLogin.setStyle("-fx-border-color: default;");
                lblLoginW.setText("");
                lblLoginW.setVisible(false);
            }
            
            if (!tfFullName.getText().trim().isEmpty()) {
                tfFullName.setStyle("-fx-border-color: default;");
                lblFNameW.setText("");
                lblFNameW.setVisible(false);
            }
            
            if (pfPassw.getText().trim().isEmpty()) {
                pfPassw.setStyle("-fx-border-color: default;");
                lblPasswW.setText("");
                lblPasswW.setVisible(false);
            }
        }
        
        return filled;
    }

    /**
     * Method that checks fields length
     *
     * @return fieldsLength Boolean
     */
    private boolean chkFieldsLength() {
        boolean fieldsLength = true;
        if (pfPassw.getText().trim().length() > MAX_LENGTH
                || tfEmail.getText().trim().length() > MAX_LENGTH
                || tfLogin.getText().trim().length() > MAX_LENGTH
                || tfFullName.getText().trim().length() > MAX_LENGTH) {
            fieldsLength = false;

            //Set textfields and passwordfiels border colors to red and show 
            // tip labels
            if (pfPassw.getText().trim().length() > MAX_LENGTH) {
                pfPassw.setText("");
                pfPassw.setStyle("-fx-border-color: red;");
                lblPasswW.setText("Longitud máxima de 255 caracteres");
                lblPasswW.setStyle("-fx-text-inner-color: red;");
                lblPasswW.setVisible(true);
            }
            
            if (tfEmail.getText().trim().length() > MAX_LENGTH) {
                tfEmail.setStyle("-fx-border-color: red;");
                lblEmailW.setText("Longitud máxima de 255 caracteres");
                lblEmailW.setStyle("-fx-text-inner-color: red;");
                lblEmailW.setVisible(true);
            }
            
            if (tfLogin.getText().trim().length() > MAX_LENGTH) {
                tfLogin.setStyle("-fx-border-color: red;");
                lblLoginW.setText("Longitud máxima de 255 caracteres");
                lblLoginW.setStyle("-fx-text-inner-color: red;");
                lblLoginW.setVisible(true);
            }
            
            if (tfFullName.getText().trim().length() > MAX_LENGTH) {
                tfFullName.setStyle("-fx-border-color: red;");
                lblFNameW.setText("Longitud máxima de 255 caracteres");
                lblFNameW.setStyle("-fx-text-inner-color: red;");
                lblFNameW.setVisible(true);
            }

            //Set textfields and passwordfiels border colors to default
            //and hides tip label
            if (pfPassw.getText().trim().length() < MAX_LENGTH) {
                pfPassw.setText("");
                pfPassw.setStyle("-fx-border-color: default;");
                lblPasswW.setText("");
                lblEmailW.setVisible(false);
            }
            
            if (tfEmail.getText().trim().length() < MAX_LENGTH) {
                tfEmail.setStyle("-fx-border-color: default;");
                lblEmailW.setText("");
                lblEmailW.setVisible(false);
            }
            
            if (tfLogin.getText().trim().length() < MAX_LENGTH) {
                tfLogin.setStyle("-fx-border-color: default;");
                lblLoginW.setText("");
                lblLoginW.setVisible(false);
            }
            
            if (tfFullName.getText().trim().length() < MAX_LENGTH) {
                tfFullName.setStyle("-fx-border-color: default;");
                lblFNameW.setText("");
                lblFNameW.setVisible(false);
            }
        }
        
        return fieldsLength;
    }

    /**
     * Method that checks passwords length
     *
     * @return
     */
    private boolean chkPasswLength() {
        boolean passwLen = true;
        if (pfPassw.getText().length() < 8) {
            passwLen = false;
            pfPassw.setStyle("-fx-border-color: red;");
            lblPasswW.setText("La contraseña tiene que tener 8 caracteres mínimo");
            lblPasswW.setStyle("-fx-text-inner-color: red;");
            lblPasswW.setVisible(true);
            pfRpassw.setText("");
        } else {
            pfPassw.setStyle("-fx-border-color: default;");
            lblPasswW.setText("");
            lblPasswW.setVisible(false);
        }
        return passwLen;
    }

    /**
     * Method that ckecks if the passwords match
     *
     * @return passwMatch Boolean
     */
    private boolean chkPasswMatch() {
        boolean passwMatch = true;
        if (!pfPassw.getText().equals(pfRpassw.getText())) {
            passwMatch = false;
            pfRpassw.setText("");
            pfRpassw.setStyle("-fx-border-color: red;");
            lblRpasswW.setText("La contraseña no coincide, inténtalo de nuevo.");
            lblRpasswW.setStyle("-fx-text-inner-color: red;");
            lblRpasswW.setVisible(true);
            pfRpassw.setText("");
        } else {
            pfRpassw.setStyle("-fx-border-color: default;");
            lblRpasswW.setText("");
            lblRpasswW.setVisible(false);
        }
        return passwMatch;
    }
    
}
