package StudMan;

import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddRecord extends JFrame implements ActionListener{
    String sname,smobile,sgender,semail,sdob,sgrade,sdiv;
    int sid;
    String pname,pmobile,pemail,pjob;



    private  ButtonGroup buttonGroup;
    private JPanel panel1;
    private JLabel AddRec;
    private JTextField StudNametextField1;
    private JTextField StudMobotextField2;
    private JTextField EmailStudtextField3;
    private JTextField StudDobtextField4;
    private JTextField StudGradetextField5;
    private JTextField StudDivtextField6;
    private JLabel studentName;
    private JLabel admissionDate;
    private JLabel admittedClass;
    private JLabel gradeTill;
    private JLabel currDiv;
    private JLabel parentdetailLabel;
    private JTextField ParNametextField7;
    private JTextField ParEmailtextField8;
    private JTextField ParMobotextField9;
    private JTextField ParJobtextField10;
    private JButton addRecButton;
    private JLabel genderLabel;
    private JRadioButton maleRadio;
    private JRadioButton Female;
    private JRadioButton Other;
    private JLabel membernLabel;
    private JLabel parentEmailLabel;
    private JLabel parentmoboLabel;
    private JButton updateRecButton;
    private JButton deleteRecButton;
    private JButton clearButton;
    private JTextField searchTextField;
    private JButton searchButton;
    private JLabel welAdminLabel;

    public  AddRecord(){
        System.out.println("it is working");
        setResizable(false);
        setContentPane(panel1);
        pack();
        setSize(700,700);
        setVisible(true);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(maleRadio);
        buttonGroup.add(Female);
        buttonGroup.add(Other);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        deleteRecButton.addActionListener(this);
        deleteRecButton.setActionCommand("deleteRecord");

        clearButton.addActionListener(this);
        clearButton.setActionCommand("clearContent");

        addRecButton.addActionListener(this);
        addRecButton.setActionCommand("addRecord");

        searchButton.addActionListener(this);
        searchButton.setActionCommand("displayRecord");

        maleRadio.addActionListener(this);
        maleRadio.setActionCommand("Male");

        Female.addActionListener(this);
        Female.setActionCommand("Female");

        Other.addActionListener(this);
        Other.setActionCommand("Other");

        updateRecButton.addActionListener(this);
        updateRecButton.setActionCommand("updateRecord");

    }

    public void createTables(Connection con) throws SQLException {
        PreparedStatement create = con.prepareStatement(
            "CREATE TABLE IF NOT EXISTS students(id int NOT NULL AUTO_INCREMENT, name varchar(255), mobile varchar(255), gender varchar(255), email varchar(255), date_of_birth date, grade varchar(255), division varchar(255), PRIMARY KEY (id))"
        );
        create.executeUpdate();

        PreparedStatement createp = con.prepareStatement(
                "CREATE TABLE IF NOT EXISTS parent(name varchar(255), mobile varchar(255), email varchar(255), job varchar(255), s_id int, FOREIGN KEY (s_id) REFERENCES students(id))"
        );

        createp.executeUpdate();
    }

    public void setData(){
        sname = StudNametextField1.getText();
        smobile = StudMobotextField2.getText();
        semail = EmailStudtextField3.getText();
        sdob = StudDobtextField4.getText();
        sgrade = StudGradetextField5.getText();
        sdiv = StudDivtextField6.getText();

        pname = ParNametextField7.getText();
        pjob = ParJobtextField10.getText();
        pemail = ParEmailtextField8.getText();
        pmobile = ParMobotextField9.getText();
    }

    public void updateRecord() throws SQLException {

        if(JOptionPane.showConfirmDialog(this,"Are you sure to update record?")==0) {
            DbConClass dbConClass = new DbConClass();
            Connection con = dbConClass.getConnect();
            String id_student = getId();
            setData();
            PreparedStatement stmt = con.prepareStatement("UPDATE students SET name='" + sname + "', mobile='" + smobile + "', gender='" + sgender + "', email='" + semail + "', date_of_birth='" + sdob + "', grade='" + sgrade + "', division='" + sdiv + "' WHERE id='" + id_student + "'");
            stmt.executeUpdate();
            PreparedStatement statement = con.prepareStatement("UPDATE parent SET name='" + pname + "', mobile='" + pmobile + "', email='" + pemail + "', job='" + pjob + "' WHERE s_id='" + id_student + "'");
            statement.executeUpdate();
            dbConClass.closeConnect();
            JOptionPane.showMessageDialog(this,"Information updated successfully");
        }
    }

    public void deleteRecord() throws SQLException {
        if(JOptionPane.showConfirmDialog(this,"Are you sure to delete the record?")==0){
            DbConClass dbConClass = new DbConClass();
            Connection con = dbConClass.getConnect();
            String id_student = getId();
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM parent WHERE s_id='"+id_student+"'");
            preparedStatement.executeUpdate();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM students WHERE id='"+id_student+"'");
            stmt.executeUpdate();
            dbConClass.closeConnect();
            JOptionPane.showMessageDialog(this,"Record deleted successfully");
        }
    }

    public void postData() throws SQLException {
        DbConClass dbConClass = new DbConClass();
        Connection con = dbConClass.getConnect();
        createTables(con);
        setData();

        PreparedStatement stud_post = con.prepareStatement("INSERT INTO students(name, mobile, gender, email, date_of_birth, grade, division) VALUES('"+sname+"','"+smobile+"','"+sgender+"','"+semail+"','"+sdob+"','"+sgrade+"','"+sdiv+"')");
        stud_post.executeUpdate();

        PreparedStatement stmt = con.prepareStatement("SELECT MAX(id) FROM students");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            sid = rs.getInt(1);
        }

        PreparedStatement par_post = con.prepareStatement("INSERT INTO parent(name, mobile, email, job, s_id) VALUES('"+pname+"','"+pmobile+"','"+pemail+"','"+pjob+"','"+sid+"')");
        par_post.executeUpdate();
        System.out.println("Tables Sucessfully Created");
        dbConClass.closeConnect();
    }

    public String getId(){
        return searchTextField.getText();
    }

    public void getDataDisp(String id_student) throws SQLException {
        DbConClass dbConClass = new DbConClass();
        Connection con = dbConClass.getConnect();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM students WHERE id = '"+id_student+"'");
        ResultSet rs = stmt.executeQuery();
        boolean fl=false;
        while(rs.next()){
            fl=true;
            StudDivtextField6.setText(rs.getString(8));
            StudDobtextField4.setText(rs.getString(6));
            StudGradetextField5.setText(rs.getString(7));
            EmailStudtextField3.setText(rs.getString(5));
            StudNametextField1.setText(rs.getString(2));
            StudMobotextField2.setText(rs.getString(3));
            if(rs.getString(4).equals("Male")){
                maleRadio.setSelected(true);
            }
            else if (rs.getString(4).equals("Female")){
                Female.setSelected(true);
            }
            else{
                Other.setSelected(true);
            }
        }
        PreparedStatement statement = con.prepareStatement("SELECT * FROM parent WHERE s_id='"+id_student+"'");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            ParEmailtextField8.setText(resultSet.getString(3));
            ParJobtextField10.setText(resultSet.getString(4));
            ParMobotextField9.setText(resultSet.getString(2));
            ParNametextField7.setText(resultSet.getString(1));
        }

        if(!fl){
            clearContentFun();searchTextField.setText(id_student);
            if(Integer.parseInt(id_student)>=0)
            {
                JOptionPane.showMessageDialog(this,"Record Doesn't Exists");
            }
            else {
                JOptionPane.showMessageDialog(this,"Please Enter Valid Student Id");
            }
        }

        dbConClass.closeConnect();
    }

    public void displayFun() {

        try{
            System.out.println("Helo");
            String id=getId();
            System.out.println(id);
            getDataDisp(id);
        } catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(this,"Please Enter Valid Student Id");
        } finally {
            System.out.println("filna");
        }
    }

    public void clearContentFun(){
            StudDivtextField6.setText("");
            StudDobtextField4.setText("");
            StudGradetextField5.setText("");
            EmailStudtextField3.setText("");
            StudNametextField1.setText("");
            StudMobotextField2.setText("");

            ParEmailtextField8.setText("");
            ParJobtextField10.setText("");
            ParMobotextField9.setText("");
            ParNametextField7.setText("");

            searchTextField.setText("");

            maleRadio.setSelected(true);
            Female.setSelected(false);
            Other.setSelected( false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String res = (String) e.getActionCommand();
        switch (res){
            case "addRecord": try {
                    this.postData();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                    JOptionPane.showMessageDialog(this,"Record is successfully added");
                    break;

            case "Male": sgender="Male";break;
            case "Female": sgender="Female";break;
            case "Other": sgender="Other";break;

            case "clearContent" : this.clearContentFun();break;

            case "deleteRecord":
                try {
                    this.deleteRecord();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            case "updateRecord" :
                try {
                    this.updateRecord();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            case "displayRecord" :
                this.displayFun();
//                try {
//                } catch (SQLException throwables) {
//                    System.out.println(throwables);
//                }
                break;
        }

    }
}
