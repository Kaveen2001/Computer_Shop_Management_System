/*
package Model;

import Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static ArrayList<String> loadCustomerIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT id from customer");
        ArrayList<String>data=new ArrayList<>();
        while(rst.next()){
            data.add(rst.getString(1));
        }
        return data;
    }


    public static String getCustomerName(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from customer where id=?", id);
        if(rst.next()){
            return rst.getString("name");
        }
        return "erwerer";
    }
}

 */

package Model;

import Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
/*
    public static boolean AddCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        *//*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*//*
        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary());
    }*/

    public static ArrayList<String> loadCustomerIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT Cus_Id from customer");
        ArrayList<String>data=new ArrayList<>();
        while(rst.next()){
            data.add(rst.getString(1));
        }
        return data;
    }

    public static String getCustomerName(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from customer where Cus_Id=?", id);
        if(rst.next()){
            return rst.getString("name");
        }
        return "erwerer";
    }
}
