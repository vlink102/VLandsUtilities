package net.vlands.data.manager;

import net.vlands.data.player.PlayerData;

import java.io.File;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class SQLiteDataManager extends DataManager {

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

    public SQLiteDataManager(File dbFile) {
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

            List<String> currentColumnsInTable = new ArrayList<>();
            ResultSet set = statement.executeQuery(getColumns);
            while (set.next()) {
                currentColumnsInTable.add(set.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveNameAndUUID(String name, String uuid) {
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
    public void savePlayerDataMultiple(Set<PlayerData.PlayerDataSnapShot> data) {

    }

    @Override
    public Map<UUID, PlayerData.PlayerDataSnapShot> getDataFromUUIDMultiple(Set<UUID> uuids) {
        return null;
    }

    @Override
    public Map<String, PlayerData.PlayerDataSnapShot> getDataFromNameMultiple(Set<String> names) {
        return null;
    }
}
