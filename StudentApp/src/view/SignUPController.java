package view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DBConnect;

public class SignUPController implements Initializable {

    @FXML
    private JFXTextField fullname;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXCheckBox check;

    @FXML
    private JFXButton signup;

    @FXML
    private JFXButton login;
    
    Message msg = new Message();
    DBConnect connect = new DBConnect();
    Connection conn;

    @FXML
    void goLogin(ActionEvent event) throws IOException {
    	login.getScene().getWindow().hide(); //현재 페이지를 닫는다.
    	
    	Stage login = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		login.setScene(scene);
		login.setTitle("StudentApp ver 1.0");
		login.show();
    }

    @FXML
    void signUp(ActionEvent event) throws SQLException {
    	//DB로 입력 내용을 저장한다.=> Insert into
    	if(check.isSelected()) {
    		//동의 했을 경우 => DB저장
    		conn = connect.getConnection();
    		String sql= "INSERT INTO student Values(?,?,?)";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, fullname.getText());
    		pstmt.setString(2, email.getText());
    		pstmt.setString(3, password.getText());
    		
    		pstmt.executeUpdate(); //준비된 쿼리문을 DB에 접속해서 실행
    		
    		msg.setMessage("입력 성공!");
    		
    	}
    	else {
    		msg.setMessage("동의하세요");
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 초기 상태값을 설정
		fullname.setStyle("-fx-text-inner-color:#afbccd;");
		email.setStyle("-fx-text-inner-color:#afbccd;");
		password.setStyle("-fx-text-inner-color:#afbccd;");
		
	}

}







