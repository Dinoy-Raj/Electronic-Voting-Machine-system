package database;

import java.sql.*;
import java.util.HashMap;

public class MySQL {
    String username = "root";
    String password = "9645682830";
    String dbName = "VotR";
    String url = "jdbc:mysql://localhost:3306/";
    Connection connection;

    public void connectJDBC() {
        try {
            connection = DriverManager.getConnection(url + dbName, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String ID, boolean isAdmin) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO USERS VALUES('" + ID + "', " + isAdmin + ");");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void addElection(String ID, String creatorID, String name, boolean isStarted, boolean isEnded) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO ELECTIONS VALUES('" + ID + "', '" + creatorID + "', '" + name + "', " + isStarted + ", " + isEnded + ");");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void startElection(String ID) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE ELECTIONS SET isStarted = true WHERE ID = '" + ID + "');");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void endElection(String electionID) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE ELECTIONS SET isEnded = true WHERE ID = '" + electionID + "';");

            Statement stmt1 = connection.createStatement();
            ResultSet rs = stmt1.executeQuery("SELECT * FROM CANDIDATES WHERE post_id in (SELECT id from POSTS WHERE election_id = '" + electionID + "') order by post_id, count desc;");

            rs.next();
            String temp = rs.getString("post_id");
            addResult(electionID, rs.getString("id"));

            do {
                if (!temp.equals(rs.getString("post_id"))) {
                    temp = rs.getString("post_id");

                    addResult(electionID, rs.getString("id"));
                }
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void addPost(String ID, String election_id, String name, int count) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO POSTS VALUES('" + ID + "', " + "'" + election_id + "', '" + name + "', " + count + ");");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void deletePost(String ID) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM POSTS WHERE id = '" + ID + "';");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void addCandidate(String ID, String post_id, String voter_id, int count) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO CANDIDATES VALUES('" + ID + "', '" + post_id + "', '" + voter_id + "', " + count + ");");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void deleteCandidate(String ID) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM CANDIDATES WHERE id = '" + ID + "';");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void addVoter(String ID, String name, String gender, String address, String pinCode, String phone) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO VOTERS VALUES('" + ID + "', " + "'" + name + "', '" + gender + "', '" + address + "',  '" + pinCode + "', '" + phone + "');");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void addResult(String electionID, String winnerID) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO RESULTS VALUES('" + electionID + "', '" + winnerID + "');");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void addEligibleVoter(String electionID, String voterID, String password) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO ELIGIBLE_VOTERS VALUES('" + electionID + "', " + "'" + voterID + "', '" + password + "');");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public ResultSet retrieveData(String table) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery("SELECT * FROM " + table + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getOngoingElections(String userID) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery("SELECT * FROM ELECTIONS WHERE creator_id = '" + userID + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEligibleElections(String voterID) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery("SELECT * FROM ELIGIBLE_VOTERS WHERE voter_id = '" + voterID + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getCandidate(String voterID) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery("SELECT * FROM VOTERS WHERE id = '" + voterID + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getPosts(String electionID) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery("SELECT * FROM POSTS WHERE election_id = '" + electionID + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public HashMap<String, Object> startVote(String voterID, String password) {
        try {
            HashMap<String, Object> map = new HashMap<>();

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ELECTIONS JOIN ELIGIBLE_VOTERS ON id = election_id WHERE voter_id = '" + voterID + "' AND password = '" + password + "';");
            rs.next();

            if (!rs.getBoolean("isStarted")) {
                HashMap<String, Object> temp = new HashMap<>();

                temp.put("error", "isNotStarted");

                return temp;
            } else if (!rs.getBoolean("isEnded")) {
                HashMap<String, Object> temp = new HashMap<>();

                temp.put("error", "isEnded");

                return temp;
            }

            String electionName = rs.getString("name");

            Statement stmt1 = connection.createStatement();
            ResultSet rs1 = stmt1.executeQuery("SELECT * FROM ELECTIONS E JOIN POSTS P ON E.id = P.election_id WHERE E.id = '" + rs.getString("id") + "'");

            while (rs1.next()) {
                String postName = rs1.getString("P.name");

                Statement stmt2 = connection.createStatement();
                ResultSet rs2 = stmt2.executeQuery("SELECT * FROM POSTS P JOIN CANDIDATES C ON P.id = C.post_id WHERE P.id = '" + rs1.getString("P.id") + "'");

                HashMap<String, Object> map1 = new HashMap<>();

                for (int i = 0; rs2.next(); i++) {
                    Statement stmt3 = connection.createStatement();
                    ResultSet rs3 = stmt3.executeQuery("SELECT * FROM VOTERS V JOIN CANDIDATES C ON V.id = C.voter_id WHERE V.id = '" + rs2.getString("C.voter_id") + "'");

                    rs3.next();

                    HashMap<String, String> map2 = new HashMap<>();
                    map2.put("name", rs3.getString("V.name"));
                    map2.put("id", rs3.getString("V.id"));

                    map1.put(postName + i, map2);
                }

                map.put(electionName, map1);
            }

            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void castVote(String ID) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE CANDIDATES SET count = count + 1 WHERE id = '" + ID + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResults(String electionID) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery("SELECT * from VOTERS WHERE id in (SELECT voter_id FROM CANDIDATES WHERE id in (SELECT winner_id FROM RESULTS WHERE election_id = '" + electionID + "'));");


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void test() {
//        addEligibleVoter("XN9rg3sRpI", "V10001", "12345");
//        addEligibleVoter("XN9rg3sRpI", "V10002", "12345");
//        addEligibleVoter("XN9rg3sRpI", "V10003", "12345");
//        addEligibleVoter("XN9rg3sRpI", "V10004", "12345");
//        addEligibleVoter("XN9rg3sRpI", "V10005", "12345");
    }
}