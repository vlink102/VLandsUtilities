package net.vlands.data.manager;

import net.vlands.data.player.PlayerData.PlayerDataSnapShot;
import net.vlands.data.serialization.JSONSerializer;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class SQLiteStorageManager extends DataStorageManager {

    private final Map<String, String> COLUMNS = new HashMap<>();

    private Connection connection;
    private final String dataTableName = "vlands_utilities";
    private final String playersTableName = "vlands_players";

    public SQLiteStorageManager(File dbFile) {
        COLUMNS.put("killeffect", "TINYTEXT");
        COLUMNS.put("accuracy", "REAL");
        COLUMNS.put("skillpoints", "INT");
        COLUMNS.put("cooldowns", "LONGTEXT");
        COLUMNS.put("ignorecooldown", "BOOL");
        COLUMNS.put("kit","TINYTEXT");

        if (!dbFile.getParentFile().isDirectory()) {
            if (!dbFile.getParentFile().mkdirs()) {
                throw new UnsupportedOperationException("Could not create the following directory: " + dbFile.getParentFile().getAbsolutePath());
            }
        }
        try {
            if (!dbFile.exists()) dbFile.createNewFile();
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
            Logger.getGlobal().info("Opened database at " + dbFile + " successfully");
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

            Set<String> currentColumnsInTable = new HashSet<>();
            ResultSet set = statement.executeQuery(getColumns);
            while (set.next()) {
                currentColumnsInTable.add(set.getString("NAME"));
            }

            for (String columnName : COLUMNS.keySet()) {
                if (currentColumnsInTable.contains(columnName)) continue;

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

    //FIXME if you need to add new data types
    @Override
    public void savePlayerDataMultiple(Set<PlayerDataSnapShot> data) {
        String sql = "REPLACE INTO " + dataTableName + " (killeffect,accuracy,skillpoints,cooldowns,ignorecooldown,kit,UUID) VALUES (?,?,?,?,?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (PlayerDataSnapShot datum : data) {
                statement.setString(1, datum.getKillEffect());
                statement.setDouble(2, datum.getAccuracy());
                statement.setInt(3, datum.getSkillPoints());
                statement.setString(4, JSONSerializer.serializeCooldownMap(datum.getCooldownsLastUse()));
                statement.setBoolean(5, datum.isIgnoringCooldowns());
                statement.setString(6, datum.getKit());
                statement.setString(7, datum.getUuid().toString());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<UUID, PlayerDataSnapShot> getDataFromUUIDMultiple(List<UUID> uuids) {
        String sql = getFirstPartOfSelectStmt() + getWhereConditionForUUID(uuids.size());
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            Map<UUID, PlayerDataSnapShot> dataMap = new HashMap<>();

            for (int i = 0; i < uuids.size(); i++)
                statement.setString(i + 1, uuids.get(i).toString());

            ResultSet resultSet = statement.executeQuery();

            for (PlayerDataSnapShot snapShot : this.convertResultSetToSnaphots(resultSet)) {
                dataMap.put(snapShot.getUuid(), snapShot);
            }
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
        String sql = getFirstPartOfSelectStmt() + getWhereConditionForNames(names.size());
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            Map<String, PlayerDataSnapShot> dataMap = new HashMap<>();

            for (int i = 0; i < names.size(); i++)
                statement.setString(i + 1, names.get(i));

            ResultSet resultSet = statement.executeQuery();

            for (PlayerDataSnapShot snapShot : this.convertResultSetToSnaphots(resultSet)) {
                dataMap.put(snapShot.getName().toLowerCase(), snapShot);
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

    //FIXME if you need to add new data types
    private String getFirstPartOfSelectStmt() {
        return "SELECT " + dataTableName + ".UUID," + playersTableName + ".NAME,killeffect,accuracy" +
                ",skillpoints,cooldowns,ignorecooldown,kit FROM " + dataTableName + " LEFT JOIN " + playersTableName + " ON " +
                dataTableName + ".UUID=" + playersTableName + ".UUID WHERE ";
    }

    private Set<PlayerDataSnapShot> convertResultSetToSnaphots(ResultSet resultSet) throws SQLException {
        Set<PlayerDataSnapShot> toReturn = new HashSet<>();
        while (resultSet.next()) {
            UUID uuid = UUID.fromString(resultSet.getString("UUID"));
            String name = resultSet.getString("NAME");
            String killeffect = resultSet.getString("killeffect");
            int skillpoints = resultSet.getInt("skillpoints");
            double accuracy = resultSet.getDouble("accuracy");
            boolean ignoreCooldowns = resultSet.getBoolean("ignorecooldown");
            Map<String, Long> cooldownMap = JSONSerializer.deserializeCooldownMap(resultSet.getString("cooldowns"));
            String kit = resultSet.getString("kit");
            toReturn.add(new PlayerDataSnapShot(name, uuid, killeffect, accuracy, skillpoints,ignoreCooldowns, cooldownMap, kit));
        }
        return toReturn;
    }
}