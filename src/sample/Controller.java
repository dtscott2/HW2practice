package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.UUID;

public class Controller implements Initializable {



    ObservableList<Student> studentObList = FXCollections.observableArrayList();

    public JFXButton ageFilter;
    public JFXButton gpaFilter;
    public JFXButton majorFilter;
    public JFXButton deleteTable;
    public JFXButton createDatabase;
    public JFXButton loadData;
    public Label alertLabel;
    public JFXListView<Student> studentListView;

    final String hostname = "hw2-db.cgvtjh1lky56.us-east-1.rds.amazonaws.com";
    final String dbName = "hw2_db";
    final String port = "3306";
    final String userName = "dtscott2";
    final String password = "Emily.123";
    final String AWS_URL = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        studentListView.setItems(studentObList);

        ageFilter.setOnAction(actionEvent -> {

            if (!studentObList.isEmpty()) {
                int size = studentObList.size();
                for (int i = 0; i < size; i++) {
                    studentObList.remove(0);
                }

            }
            try {
                Connection conn = DriverManager.getConnection(AWS_URL);
                Statement stmt = conn.createStatement();

                ResultSet result = stmt.executeQuery("SELECT * FROM StudentTable WHERE age < 30");

                while (result.next()) {
                    Student student = new Student();
                    student.name = result.getString("name");
                    student.id = UUID.fromString(result.getString("Id"));
                    student.age = result.getInt("age");
                    student.major = result.getString("major");
                    student.GPA = result.getDouble("GPA");

                    studentObList.add(student);
                }
                stmt.close();
                conn.close();

            }catch (Exception ex){

                System.out.println("Fail");
                alertLabel.setText("Fail");
            }
        });

        gpaFilter.setOnAction(actionEvent -> {

            if (!studentObList.isEmpty()) {
                int size = studentObList.size();
                for (int i = 0; i < size; i++) {
                    studentObList.remove(0);
                }

            }
            try {
                Connection conn = DriverManager.getConnection(AWS_URL);
                Statement stmt = conn.createStatement();

                ResultSet result = stmt.executeQuery("SELECT * FROM StudentTable WHERE GPA < 3.00");

                while (result.next()) {
                    Student student = new Student();
                    student.name = result.getString("name");
                    student.id = UUID.fromString(result.getString("Id"));
                    student.age = result.getInt("age");
                    student.major = result.getString("major");
                    student.GPA = result.getDouble("GPA");

                    studentObList.add(student);
                }
                stmt.close();
                conn.close();

            }catch (Exception ex){

                System.out.println("Fail");
                alertLabel.setText("Fail");
            }

        });

        majorFilter.setOnAction(actionEvent -> {

            if (!studentObList.isEmpty()) {
                int size = studentObList.size();
                for (int i = 0; i < size; i++) {
                    studentObList.remove(0);
                }

            }
            try {
                Connection conn = DriverManager.getConnection(AWS_URL);
                Statement stmt = conn.createStatement();

                ResultSet result = stmt.executeQuery("SELECT * FROM StudentTable WHERE major LIKE '%Computer%'");

                while (result.next()) {
                    Student student = new Student();
                    student.name = result.getString("name");
                    student.id = UUID.fromString(result.getString("Id"));
                    student.age = result.getInt("age");
                    student.major = result.getString("major");
                    student.GPA = result.getDouble("GPA");

                    studentObList.add(student);
                }
                stmt.close();
                conn.close();

            }catch (Exception ex){

                System.out.println("Fail");
                alertLabel.setText("Fail");
            }

        });

        createDatabase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Connection conn = DriverManager.getConnection(AWS_URL);
                    Statement stmt = conn.createStatement();
                    try
                    {
                        stmt.execute("CREATE TABLE StudentTable (" +
                                "name CHAR(25), " +
                                "Id VARCHAR(36), " +
                                "age INT(36), " +
                                "major CHAR(36), " +
                                "GPA DOUBLE(4,2) )");
                        System.out.println("TABLE CREATED");
                        alertLabel.setText("TABLE CREATED");


                        stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Steve Vai', '"+UUID.randomUUID().toString()+"', 33, 'Communications', 3.1)");

                        stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Joe Satriani', '"+UUID.randomUUID().toString()+"', 34, 'Marketing', 3.7)");

                        stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Angus Young', '"+UUID.randomUUID().toString()+"', 31, 'Accounting', 2.7)");

                        stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Buckethead', '"+UUID.randomUUID().toString()+"', 26, 'Supply Chain', 3.1)");

                        stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Eddie VanHalen', '"+UUID.randomUUID().toString()+"', 22, 'Digital Media', 3.5)");

                        stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Stevie Ray Vaughn', '"+UUID.randomUUID().toString()+"', 19, 'Computer Science', 3.4)");

                        stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Pete Townshend', '"+UUID.randomUUID().toString()+"', 29, 'Computer Information Systems', 3.8)");

                        stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Paul Gilbert', '"+UUID.randomUUID().toString()+"', 41, 'Engineering', 2.1)");

                        stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Alexi Laiho', '"+UUID.randomUUID().toString()+"', 35, 'Biology', 2.2)");

                        stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Sinister Gates', '"+UUID.randomUUID().toString()+"', 32, 'Geology', 2.7)");


                        System.out.println("TABLE FILLED");
                        alertLabel.setText("TABLE FILLED");

                        stmt.close();
                        conn.close();
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                        System.out.println("TABLE ALREADY EXISTS, NOT CREATED");
                        alertLabel.setText("TABLE ALREADY EXISTS, NOT CREATED");

                    }


                }
                catch (Exception ex)
                {
                    var msg = ex.getMessage();
                    System.out.println(msg);
                }
            }
        });

        loadData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                /*if (!studentObList.isEmpty()) {*/
                    int size = studentObList.size();
                    for (int i = 0; i < size; i++) {
                        studentObList.remove(0);
                    }

                /*}*/

                try{
                    Connection conn = DriverManager.getConnection(AWS_URL);

                    Statement stmt = conn.createStatement();

                    String sqlStatement = "SELECT * FROM StudentTable";
                    ResultSet result = stmt.executeQuery(sqlStatement);

                    while (result.next())
                    {
                        Student student = new Student();
                        student.name = result.getString("name");
                        student.id = UUID.fromString(result.getString("Id"));
                        student.age = result.getInt("age");
                        student.major = result.getString("major");
                        student.GPA = result.getDouble("GPA");

                        studentObList.add(student);
                    }


                    System.out.println("DATA LOADED");
                    alertLabel.setText("DATA LOADED");

                    stmt.close();
                    conn.close();
                }
                catch (Exception ex)
                {
                    var msg = ex.getMessage();
                    System.out.println("DATA NOT LOADED");
                    System.out.println(msg);
                    alertLabel.setText("DATA NOT LOADED");
                    alertLabel.setText(msg);
                }
            }
        });

        deleteTable.setOnAction((actionEvent -> {
            try {
                Connection conn = DriverManager.getConnection(AWS_URL);

                Statement stmt = conn.createStatement();

                String sql = "DROP TABLE StudentTable";
                stmt.execute(sql);

                if (!studentObList.isEmpty()) {
                    int size = studentObList.size();
                    for (int i = 0; i < size; i++) {
                        studentObList.remove(0);
                    }

                }


            }catch (Exception ex){

                System.out.println("Fail");
                alertLabel.setText("Fail");



            }


                }));


    }
}
