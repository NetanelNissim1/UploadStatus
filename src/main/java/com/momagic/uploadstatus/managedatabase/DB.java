package com.momagic.uploadstatus.managedatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.momagic.uploadstatus.managefils.ManageProperties;
import com.momagic.uploadstatus.model.ModelMoMagic;

public class DB {

    public static Connection connection;

    public static void getConnection() {
        Properties props = ManageProperties.getJdbcProperties();
        try {
            Class.forName(props.getProperty("db.driver.class"));
            connection = DriverManager.getConnection(props.getProperty("db.conn.url"), props.getProperty("db.username"),
                    props.getProperty("db.password"));
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<ModelMoMagic> selectStatus(List<ModelMoMagic> moMagic) {

        /* preistalled_campaign */

        PreparedStatement statement = null;
        int j = 1;
        int x = 1;

        try {
            if (moMagic.size() > 0) {
                StringBuilder sqlSelect = new StringBuilder();
                sqlSelect.append(
                        "SELECT status, imei, os_id, last_update FROM eyecon.preistalled_campaign order by last_update desc ");
                /*
                 * imei in (") .append(repeat("?,", moMagic.size()));
                 */
                // sqlSelect = sqlSelect.deleteCharAt(sqlSelect.length() - 1);
                /*
                 * sqlSelect.append(" os_id in (").append(repeat("?,", moMagic.size()));
                 * sqlSelect = sqlSelect.deleteCharAt(sqlSelect.length() - 1);
                 * sqlSelect.append(")");
                 */
                statement = connection.prepareStatement(sqlSelect.toString());
                // x = moMagic.size() + 1;
                // for (ModelMoMagic item : moMagic) {
                // // statement.setString(j++, item.getIMEI());
                // statement.setString(j++, item.getAndroidId());
                // }
                ResultSet rs = statement.executeQuery();
                List<ModelMoMagic> newMoMagicList = new ArrayList<ModelMoMagic>();
                while (rs.next()) {
                    ModelMoMagic item = new ModelMoMagic();
                    item.setStatus(rs.getString("status"));
                    item.setIMEI(rs.getString("imei"));
                    item.setAndroidId(rs.getString("os_id"));
                    item.setLastUpdate(rs.getTimestamp("last_update"));
                    newMoMagicList.add(item);
                }

                Collections.sort(newMoMagicList);
                int count = 0;
                for (ModelMoMagic item : newMoMagicList) {
                    for (ModelMoMagic oldItem : moMagic) {
                        if (oldItem.getIMEI() == null || item.getIMEI() == null) {
                            continue;
                        }
                        if (item.getIMEI().split(",")[0].equals(oldItem.getIMEI())) {
                            oldItem.setStatus(item.getStatus());
                            count++;
                        }
                    }
                }
                System.out.println("Count: " + count);
                return moMagic;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }

    public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }
}
