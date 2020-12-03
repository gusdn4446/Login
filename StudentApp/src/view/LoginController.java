package view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DBConnect;

public class LoginController {

    @FXML
    private AnchorPane anPane;

    @FXML
    private JFXTextField tfEmail;

    @FXML
    private JFXPasswordField tfPass;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnLogin;
    
    Message mgs = new Message();
    DBConnect connect = new DBConnect();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    

    @FXML
    void createLogin(ActionEvent event) throws SQLException, IOException {
    	//System.out.println("로그인 체크");
//    	if(tfFname.getText().equals("")) {
//    		mgs.setMessage("이름 미입력!");
//    	}
    	//DB에 이메일과 비밀번호를 확인하여 로그인 가능 또는 불가
    	conn = connect.getConnection();
    	String sql = "SELECT * FROM student WHERE email=? AND password=?";
    	
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, tfEmail.getText());
    	pstmt.setString(2, tfPass.getText());
    	
    	rs = pstmt.executeQuery();  //executeQuery는 결과값이 있고 Update는 결과값이 없다.
    	
    	boolean isValid = false;   //로그인 가능 초기값은 False .
    	
    	if(rs.next()) {
    		//mgs.setMessage("로그인 성공");
    		btnLogin.getScene().getWindow().hide();
    		//새 HomePage 화면을 뛰운다.
    		Stage home = new Stage();
        	Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
    		Scene scene = new Scene(root);
    		home.setScene(scene);
    		home.show();
    		
    	}
    	else {
    		mgs.setMessage("로그인 실패!");
    	}
    }

    @FXML
    void createregister(ActionEvent event) throws IOException {
    	//System.out.println("가입 페이지로");
    	btnRegister.getScene().getWindow().hide(); //현재 페이지를 안보이게
    	
    	Stage signup = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("../view/SignUP.fxml"));
		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
    	
    	
    }

}





