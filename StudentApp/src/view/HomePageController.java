package view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.jfoenix.controls.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DBConnect;
import model.Student;


public class HomePageController {

	@FXML
	private JFXButton btnShow;
	
	@FXML
	private TableView<Student> tvStduent;
	
	@FXML
	private TableColumn<Student, String> colFname;
	
	@FXML
	private TableColumn<Student, String> colPass;
	
	@FXML
	private TableColumn<Student, String> colEmail;
		
	@FXML
	public void handleButton(ActionEvent event) {
		showStudents();
	}
	
    private DBConnect connection= new DBConnect();
    

	// DB�� �����ؼ� ��� �����͸� �����´�. ( 1. DB ���� 2.sql���� �ۼ� 3.sql���� �����ϰ� ����� �޾ƿ´�.)
	public ObservableList<Student> getStudentList(){
		//fx���� ���̺��信 ǥ���ϱ� ���� ����Ʈ�� oblservableList �� ����Ѵ�.(Ư¡ �̺�Ʈ�� ������ ��밡��)
		ObservableList<Student> stList = FXCollections.observableArrayList();
		
		//������ �ۼ�
		String sql = "SELECT * FROM student"; 
		//DB ����
		Connection conn = connection.getConnection();
		Statement stmt;          //DB�� ���� ���� ��ü 
		ResultSet rs;            //DB���� ����� �޾ƿ� ��ü
		
		try {
			stmt = conn.createStatement(); //������ü ����
			rs = stmt.executeQuery(sql);   //stl������ �־ �����ϰ� ����� rs�� ��ƿ�
			Student st;
			while(rs.next()) {
				st = new Student( rs.getString("fullname"), rs.getString("password"), rs.getString("email"));
				stList.add(st);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stList;
	}
	
	public void showStudents() {
		ObservableList<Student> list = getStudentList();
		//���̺��� ����Ʈ�� �Է��Ѵ�.
		tvStduent.setItems(list);
		//������ ���鿡 �´� ���� �����͸� �������� �Ѵ�.
		colFname.setCellValueFactory(new PropertyValueFactory<Student, String>("fullname"));
		colPass.setCellValueFactory(new PropertyValueFactory<Student, String>("password"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		
	}	
}