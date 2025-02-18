import java.sql.*;

class DataAccess implements Database {// the database interface has the all the infos of the database
    static Connection connection;

    static {//static block is loaded in the memory automatically when we use any function in the class to make the connection only one time
        try {
            Class.forName(LOAD_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            System.out.println("" + e);
        }
    }

    static synchronized boolean update(String MYSQL) {//to update
        try {
            PreparedStatement statement = connection.prepareStatement(MYSQL);//using prepared statement to avoid the sql injection
            int rowsAffected = statement.executeUpdate();//see if any rows affected which means that the statement executed successfully
            statement.close();//closing the resource
            return rowsAffected > 0;//if the rows affected > 0 will return true
        } catch (SQLException e) {
            System.out.println("" + e);//printing the error at the server console, but it is better to print in logs
            return false;
        }
    }

    static synchronized boolean insert(String MYSQL) {//function for insert
        try {
            PreparedStatement statement = connection.prepareStatement(MYSQL);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("" + e);
            return false;
        }
    }

    static synchronized String select(String MYSQL) {//function for select
        try {
            PreparedStatement statement = connection.prepareStatement(MYSQL);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String result = resultSet.getString(1);
                resultSet.close();
                statement.close();
                return result;
            }
            resultSet.close();
            statement.close();
            return "user data  not found";

        } catch (SQLException e) {
            return "" + e;
        }
    }

    static synchronized boolean delete(String MYSQL) {//function for delete
        try {
            PreparedStatement statement = connection.prepareStatement(MYSQL);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("" + e);
            return false;
        }
    }

    static synchronized String[] viewPersonalStudentInfo(String MYSQL) {// a special select function to let the student view his personal information
        try {
            PreparedStatement statement = connection.prepareStatement(MYSQL);
            ResultSet resultSet = statement.executeQuery();
            int columnCount = resultSet.getMetaData().getColumnCount();//to get the columns count from the metadata of the table
            resultSet.next();
            String[] result = new String[columnCount];//making an array which is a data structure that we use to store the results of the database
            for (int i = 0; i < columnCount; i++) {
                result[i] = resultSet.getString(i + 1);//adding the results in the array
            }
            resultSet.close();
            statement.close();
            return result;

        } catch (SQLException e) {
            return new String[]{"" + e};
        }
    }

    static synchronized String[][] viewStudentGrades(String MYSQL) {//another special select function to get the student grades
        try {
            PreparedStatement statement = connection.prepareStatement(MYSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// to make it cursable result set object
            ResultSet resultSet = statement.executeQuery();
            resultSet.last();
            int rowCount = resultSet.getRow();//moving the result set to the last rows to count the rows number
            resultSet.beforeFirst();//returning the result set to its place that will let it  points on null
            int columnCount = resultSet.getMetaData().getColumnCount();
            String[][] result = new String[rowCount][columnCount];//initializing the array with the rows and columns because its 2-d array
            for (int i = 0; resultSet.next(); i++) {
                for (int j = 0; j < columnCount; j++) {
                    result[i][j] = resultSet.getString(j + 1);//putting the results
                }
            }
            return result;
        } catch (SQLException e) {
            return new String[][]{new String[]{"" + e}};
        }
    }

    static synchronized boolean isObject(String MYSQL) {//to see if the object is in a table according to the query
        try {
            PreparedStatement statement = connection.prepareStatement(MYSQL);
            ResultSet resultSet = statement.executeQuery();
            boolean flag = resultSet.next();
            resultSet.close();
            statement.close();
            return flag;
        } catch (SQLException e) {
            System.out.println("" + e);
            return false;
        }
    }

}
