package net.vlands.util;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.Pattern;
import dev.dejvokep.boostedyaml.dvs.segment.Segment;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import dev.dejvokep.boostedyaml.spigot.SpigotSerializer;
import net.vlands.VLandsUtilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class YamlUtils {

    private final VLandsUtilities plugin;

    public YamlUtils(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    /**
     * @param path Uses pluginDataFolder:folder:folder:child syntax.
     * @param fileName The file name (.yml not needed)
     * @param inputStream The inputStream (plugin.getResource())
     * @param update Toggles whether to update the file
     * @param reload Toggles whether to reload the file
     * @return The final document state
     */
    public static YamlDocument createConfig(String path, String fileName, InputStream inputStream, boolean update, boolean reload) {
        path = path.replace(":", File.separator);
        fileName = fileName.endsWith(".yml") ? fileName : fileName + ".yml";

        try {
            YamlDocument document = YamlDocument.create(new File(path, fileName), inputStream, GeneralSettings.builder().setSerializer(SpigotSerializer.getInstance()).build(), LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT, UpdaterSettings.builder().setAutoSave(true).setVersioning(new Pattern(Segment.range(1, Integer.MAX_VALUE), Segment.literal("."), Segment.range(0, 10)), "version").build());
            if (update) document.update();
            if (reload) document.reload();
            document.save();
            return document;
        } catch (IOException e) {
            e.printStackTrace();
            GenericUtils.sendError("Failed to create/update/reload configuration file '" + fileName + "' at location '" + path + "' using input stream '" + inputStream + "'.");
            return null;
        }
    }
}
