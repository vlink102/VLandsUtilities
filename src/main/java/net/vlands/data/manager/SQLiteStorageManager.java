package net.vlands.data.manager;

import net.vlands.data.player.PlayerData.PlayerDataSnapShot;
import net.vlands.data.serialization.JSONSerializer;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class SQLiteStorageManager extends DataStorageManager {

    private static final Map<String, String> COLUMNS = new HashMap<>();

    static {
        COLUMNS.put("killeffect", "TINYTEXT");
        COLUMNS.put("accuracy", "REAL");
        COLUMNS.put("skillpoints", "INT");
        COLUMNS.put("cooldowns", "LONGTEXT");
    }

    private Connection connection;
    private File file;
    private String dataTableName = "vlands_utilities";
    private String playersTableName = "vlands_players";

    public SQLiteStorageManager(File dbFile) {
        this.file = dbFile;
        if (!this.file.getParentFile().isDirectory()) {
            if (!this.file.getParentFile().mkdirs()) {
                throw new UnsupportedOperationException("Could not create the following directory: " + this.file.getParentFile().getAbsolutePath());
            }
        }
        try {
            if (!this.file.exists())
                this.file.createNewFile();
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + this.file);
            Logger.getGlobal().info("Opened database at " + this.file + " successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        String createDataTable = "CREATE TABLE IF NOT EXISTS " + dataTableName + " (UUID VARCHAR(36) NOT NULL PRIMARY KEY);";
        String createPlayersTable = "CREATE TABLE IF NOT EXISTS " + playersTableName + " (UUID VARCHAR(36) NOT NULL UNIQUE, NAME VARCHAR(16) NOT NULL UNIQUE);";
        String getColumns = "PRAGMA table_info(" + dataTableName + ")";
        String createColumns = "ALTER TABLE " + dataTableName + " ADD %column% %type%;";
        try (Statement statement = this.connection.createStatement()) {

            statement.executeUpdate(createDataTable);
            statement.executeUpdate(createPlayersTable);

            //getting columns in table
            Set<String> currentColumnsInTable = new HashSet<>();
            ResultSet set = statement.executeQuery(getColumns);
            while (set.next()) {
                currentColumnsInTable.add(set.getString("NAME"));
            }

            //adding any columns we need
            for (String columnName : COLUMNS.keySet()) {
                if (currentColumnsInTable.contains(columnName))
                    continue;

                //creating the statment
                String stmt = StringUtils.replace(StringUtils.replace(createColumns, "%column%", columnName), "%type%", COLUMNS.get(columnName));
                statement.executeUpdate(stmt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveNameAndUUID(String name, UUID uuid) {
        String sql = "REPLACE INTO " + playersTableName + " (UUID,NAME) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uuid.toString());
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void savePlayerDataMultiple(Set<PlayerDataSnapShot> data) {
        String sql = "REPLACE INTO " + dataTableName + " (killeffect,accuracy,skillpoints,cooldowns,UUID) VALUES (?,?,?,?,?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (PlayerDataSnapShot datum : data) {
                statement.setString(1, datum.getKillEffect());
                statement.setDouble(2, datum.getAccuracy());
                statement.setInt(3, datum.getSkillPoints());
                statement.setString(4, JSONSerializer.serializeCooldownMap(datum.getCooldownsLastUse()));
                statement.setString(5, datum.getUuid().toString());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<UUID, PlayerDataSnapShot> getDataFromUUIDMultiple(List<UUID> uuids) {
        String sql = "SELECT " + dataTableName + ".UUID," + playersTableName + ".NAME,killeffect,accuracy" +
                ",skillpoints,cooldowns FROM " + dataTableName + " LEFT JOIN " + playersTableName + " ON " +
                dataTableName + ".UUID=" + playersTableName + ".UUID WHERE " + getWhereConditionForUUID(uuids.size());
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            Map<UUID, PlayerDataSnapShot> dataMap = new HashMap<>();

            for (int i = 0; i < uuids.size(); i++)
                statement.setString(i + 1, uuids.get(i).toString());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UUID uuid = UUID.fromString(resultSet.getString("UUID"));
                String name = resultSet.getString("NAME");
                String killeffect = resultSet.getString("killeffect");
                int skillpoints = resultSet.getInt("skillpoints");
                double accuracy = resultSet.getDouble("accuracy");
                Map<String, Long> cooldownMap = JSONSerializer.deserializeCooldownMap(resultSet.getString("cooldowns"));
                dataMap.put(uuid, new PlayerDataSnapShot(name, uuid, killeffect, accuracy, skillpoints, cooldownMap));
            }

            System.out.println(dataMap);
            return dataMap;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getWhereConditionForUUID(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(dataTableName).append(".UUID=?");
            if (i == num - 1)
                stringBuilder.append(";");
            else
                stringBuilder.append("OR ");
        }
        return stringBuilder.toString();
    }

    @Override
    public Map<String, PlayerDataSnapShot> getDataFromNameMultiple(List<String> names) {
        String sql = "SELECT " + dataTableName + ".UUID," + playersTableName + ".NAME,killeffect,accuracy" +
                ",skillponts,cooldowns FROM " + dataTableName + " LEFT JOIN " + playersTableName + " ON " +
                dataTableName + ".UUID=" + playersTableName + ".UUID WHERE " + getWhereConditionForNames(names.size());
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            Map<String, PlayerDataSnapShot> dataMap = new HashMap<>();

            for (int i = 0; i < names.size(); i++)
                statement.setString(i + 1, names.get(i));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UUID uuid = UUID.fromString(resultSet.getString("UUID"));
                String name = resultSet.getString("NAME");
                String killeffect = resultSet.getString("killeffect");
                int skillpoints = resultSet.getInt("skillpoints");
                double accuracy = resultSet.getDouble("accuracy");
                Map<String, Long> cooldownMap = JSONSerializer.deserializeCooldownMap(resultSet.getString("cooldowns"));
                dataMap.put(name.toLowerCase(), new PlayerDataSnapShot(name, uuid, killeffect, accuracy, skillpoints, cooldownMap));
            }

            return dataMap;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getWhereConditionForNames(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(dataTableName)
                    .append(".UUID=SELECT UUID FROM ")
                    .append(playersTableName)
                    .append(" WHERE NAME=?");
            if (i == num - 1)
                stringBuilder.append(";");
            else
                stringBuilder.append("OR ");
        }
        return stringBuilder.toString();
    }
}
