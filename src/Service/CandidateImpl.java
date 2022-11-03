package Service;

import DAO.DBConnect;
import Model.Skill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CandidateImpl implements CandidateService{
    private DBConnect dbConnect;
    private Statement statement;
    @Override
    public boolean insertData(List<String> arr, String id) {
        try {
            dbConnect = new DBConnect();
            if(arr.get(0).equals("1")) {
                String sql1 = "INSERT INTO `employee`(`employeeID`, `type`, `fullName`, `birthDay`, `address`, `homeTown`, `phone`, `email`, `numberOfExp`, `oldWorkPlace`) VALUES (?,?,?,?,?,?,?,?,?,?)";
                statement = dbConnect.getConnect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                PreparedStatement ps = dbConnect.getConnect().prepareStatement(sql1);
                ps.setString(1, id);
                ps.setInt(2, Integer.parseInt(arr.get(0)));
                ps.setString(3, arr.get(1));
                ps.setString(4, arr.get(2));
                ps.setString(5, arr.get(3));
                ps.setString(6, arr.get(4));
                ps.setString(7, arr.get(5));
                ps.setString(8, arr.get(6));
                ps.setDouble(9, Double.parseDouble(arr.get(7)));
                ps.setString(10, arr.get(8));
                ps.executeUpdate();


                // insert skill

            }
            if(arr.get(0).equals("2")) {
                String sql2 = "INSERT INTO `employee`(`employeeID`, `type`, `fullName`, `birthDay`, `address`, `homeTown`, `phone`, `email`,`graduatonTime`, `classification`, `graduationSchool`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = dbConnect.getConnect().prepareStatement(sql2);
                ps.setString(1, id);
                ps.setInt(2, Integer.parseInt(arr.get(0)));
                ps.setString(3, arr.get(1));
                ps.setString(4, arr.get(2));
                ps.setString(5, arr.get(3));
                ps.setString(6, arr.get(4));
                ps.setString(7, arr.get(5));
                ps.setString(8, arr.get(6));
                ps.setInt(9, Integer.parseInt(arr.get(7)));
                ps.setString(10, arr.get(8));
                ps.setString(11, arr.get(9));
                ps.executeUpdate();
            }
            if(arr.get(0).equals("3")) {
                String sql3 = "INSERT INTO `employee`(`employeeID`, `type`, `fullName`, `birthDay`, `address`, `homeTown`, `phone`, `email`, `majorStudy`, `semesterStudying`, `school`, `expectedDateGraduation`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = dbConnect.getConnect().prepareStatement(sql3);
                ps.setString(1, id);
                ps.setInt(2, Integer.parseInt(arr.get(0)));
                ps.setString(3, arr.get(1));
                ps.setString(4, arr.get(2));
                ps.setString(5, arr.get(3));
                ps.setString(6, arr.get(4));
                ps.setString(7, arr.get(5));
                ps.setString(8, arr.get(6));
                ps.setString(9, arr.get(7));
                ps.setString(10, arr.get(8));
                ps.setString(11, arr.get(9));
                ps.setString(12, arr.get(10));
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int countEmployee() throws SQLException {
        try {
            dbConnect = new DBConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM `employee` WHERE 1";
        Statement statement = dbConnect.getConnect().createStatement();
        ResultSet result = statement.executeQuery(sql);
        int count = 0;
        while (result.next()) {
            count++;
        }
        return count;
    }
    @Override
    public boolean insertDataSkill(List<Skill> skills, String id) {
        try {
            dbConnect = new DBConnect();
            for (int i = 0; i < skills.size(); i++) {
                String sql = "INSERT INTO `skill`(`skill_ID`, `employeeID`) VALUES (?,?)";
                statement = dbConnect.getConnect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                PreparedStatement ps = dbConnect.getConnect().prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, skills.get(i).getSkill());
                ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public String checkValidate(List<String> arr) {
        // Kiểm tra ngày sinh
        String[] date = arr.get(2).split("/");
        String newDate = "";
        for (int i = 0; i < date.length; i++) {
            newDate += date[i];
        }
        if(newDate.length() < 8 || date[0].length() != 4 || date[1].length() != 2 || date[2].length() != 2 ) {
            return "Sai dinh dang ngay thang nam sinh";
        }
        // kiểm tra email
        char[] charEmail = arr.get(6).toCharArray();
        int check1 = 0;
        int check2 = 0;
        for (int i = 0; i < charEmail.length; i++) {
            String value = String.valueOf(charEmail[i]);
            if(value.equals("@")) {
                check1++;
            }

            if(value.equals(".")) {
                check2++;
            }
        }
        if(check1 < 0 || check1 > 1 || check2 < 1) {
            return "Sai dinh dang dia chi email";
        }
        // kiểm tra sdt
        if(arr.get(5).length() < 7) {
            return "Sai dinh dang so dien thoai";
        }


        return "Du lieu D";
    }
}
