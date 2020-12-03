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
    	//System.out.println("�α��� üũ");
//    	if(tfFname.getText().equals("")) {
//    		mgs.setMessage("�̸� ���Է�!");
//    	}
    	//DB�� �̸��ϰ� ��й�ȣ�� Ȯ���Ͽ� �α��� ���� �Ǵ� �Ұ�
    	conn = connect.getConnection();
    	String sql = "SELECT * FROM student WHERE email=? AND password=?";
    	
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, tfEmail.getText());
    	pstmt.setString(2, tfPass.getText());
    	
    	rs = pstmt.executeQuery();  //executeQuery�� ������� �ְ� Update�� ������� ����.
    	
    	boolean isValid = false;   //�α��� ���� �ʱⰪ�� False .
    	
    	if(rs.next()) {
    		//mgs.setMessage("�α��� ����");
    		btnLogin.getScene().getWindow().hide();
    		//�� HomePage ȭ���� �ٿ��.
    		Stage home = new Stage();
        	Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
    		Scene scene = new Scene(root);
    		home.setScene(scene);
    		home.show();
    		
    	}
    	else {
    		mgs.setMessage("�α��� ����!");
    	}
    }

    @FXML
    void createregister(ActionEvent event) throws IOException {
    	//System.out.println("���� ��������");
    	btnRegister.getScene().getWindow().hide(); //���� �������� �Ⱥ��̰�
    	
    	Stage signup = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("../view/SignUP.fxml"));
		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
    	
    	
    }

}





