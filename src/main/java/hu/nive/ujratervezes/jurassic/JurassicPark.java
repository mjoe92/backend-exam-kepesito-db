package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "SELECT *" +
                    " FROM dinosaur" +
                    " WHERE actual > expected" +
                    " ORDER BY breed";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<String> breedList = new LinkedList<>();
            while (rs.next()) {
                String breed = rs.getString(1);
                breedList.add(breed);
            }
            return breedList;
        } catch (SQLException ex) {
            throw new RuntimeException("Error while adding new Author.", ex);
        }
    }

}
