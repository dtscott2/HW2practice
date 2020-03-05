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
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                        System.out.println("TABLE ALREADY EXISTS, NOT CREATED");
                        alertLabel.setText("TABLE ALREADY EXISTS, NOT CREATED");
                    }

                    /*var student1 = new Student();
                    student1.name = "Steve Vai";
                    student1.id = UUID.randomUUID();
                    student1.age = 33;
                    student1.major = "Biology";
                    student1.GPA = 3.4;*/


                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Steve Vai', '"+UUID.randomUUID().toString()+"', 33, 'Biology', 3.4)");

                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Joe', '"+UUID.randomUUID().toString()+"', 33, 'Biology', 3.4)");

                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Angus', '"+UUID.randomUUID().toString()+"', 33, 'Biology', 3.4)");

                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('Buckethead', '"+UUID.randomUUID().toString()+"', 33, 'Biology', 3.4)");

                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('EVH', '"+UUID.randomUUID().toString()+"', 33, 'Biology', 3.4)");


                    /*var student2 = new Student();
                    student2.name = "Stevie Ray Vaughan";
                    student2.id = UUID.randomUUID();
                    student2.age = 23;
                    student2.major = "Business";
                    student2.GPA = 2.9;


                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('" +student2.name + "', '"+student2.id.toString() +"', "+student2.age +", '"+student2.major+"', "+student2.GPA+ ")");
                    System.out.println("b");


                    var student3 = new Student();
                    student2.name = "Paul Gilbert";
                    student2.id = UUID.randomUUID();
                    student2.age = 21;
                    student2.major = "Music";
                    student2.GPA = 2.3;


                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('" +student3.name + "', '"+student3.id.toString() +"', "+student3.age +", '"+student3.major+"', "+student3.GPA+ ")");

                    System.out.println("c");

                    var student4 = new Student();
                    student4.name = "Jimi Hendrix";
                    student4.id = UUID.randomUUID();
                    student4.age = 28;
                    student4.major = "CIS";
                    student4.GPA = 3.7;


                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('" +student4.name + "', '"+student4.id.toString() +"', "+student4.age +", '"+student4.major+"', "+student4.GPA+ ")");

                    System.out.println("d");

                    var student5 = new Student();
                    student5.name = "Eric Clapton";
                    student5.id = UUID.randomUUID();
                    student5.age = 19;
                    student5.major = "Digital Media";
                    student5.GPA = 2.8;

                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('" +student5.name + "', '"+student5.id.toString() +"', "+student5.age +", '"+student5.major+"', "+student5.GPA+ ")");

                    System.out.println("e");
                    var student6 = new Student();
                    student6.name = "B.B. King";
                    student6.id = UUID.randomUUID();
                    student6.age = 31;
                    student6.major = "Communication";
                    student6.GPA = 1.9;


                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('" +student6.name + "', '"+student6.id.toString() +"', "+student6.age +", '"+student6.major+"', "+student6.GPA+ ")");
                    System.out.println("f");

                    var student7 = new Student();
                    student7.name = "joe Satriani";
                    student7.id = UUID.randomUUID();
                    student7.age = 35;
                    student7.major = "Fine Arts";
                    student7.GPA = 2.7;

                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('" +student7.name + "', '"+student7.id.toString() +"', "+student7.age +", '"+student7.major+"', "+student7.GPA+ ")");

                    System.out.println("g");
                    var student8 = new Student();
                    student8.name = "Jimmy Page";
                    student8.id = UUID.randomUUID();
                    student8.age = 25;
                    student8.major = "Computer Science";
                    student8.GPA = 3.1;


                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('" +student8.name + "', '"+student8.id.toString() +"', "+student8.age +", '"+student8.major+"', "+student8.GPA+ ")");
                    System.out.println("h");
                    var student9 = new Student();
                    student9.name = "Randy Rhoads";
                    student9.id = UUID.randomUUID();
                    student9.age = 19;
                    student9.major = "Engineering";
                    student9.GPA = 4.0;




                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('" +student9.name + "', '"+student9.id.toString() +"', "+student9.age +", '"+student9.major+"', "+student9.GPA+ ")");
                    System.out.println("i");

                    var student10 = new Student();
                    student10.name = "Eddie VanHalen";
                    student10.id = UUID.randomUUID();
                    student10.age = 26;
                    student10.major = "Undecided";
                    student10.GPA = 2.3;

                    stmt.executeUpdate("INSERT INTO StudentTable VALUES" + "('" +student10.name + "', '"+student10.id.toString() +"', "+student10.age +", '"+student10.major+"', "+student10.GPA+ ")");

                    System.out.println("j");*/

                    System.out.println("TABLE FILLED");
                    alertLabel.setText("TABLE FILLED");

                    stmt.close();
                    conn.close();
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
                        student.major = result.getString("name");
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
