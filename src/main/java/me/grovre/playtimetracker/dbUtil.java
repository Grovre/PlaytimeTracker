package me.grovre.playtimetracker;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.CheckForNull;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("UnstableApiUsage")
public class dbUtil {

    private static final File f = new File(PlaytimeTracker.getPlugin().getDataFolder() + File.pathSeparator + "TimeData.json");

    private boolean filePrep() {
        if(!f.exists()) {
            try {
                return f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    @CheckForNull
    public HashMap<UUID, Long> loadEntries() {
        if(!filePrep()) return null;
        Gson gson = new Gson();
        FileReader fr = null;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert fr != null;
        HashMap<UUID, Long> loaded = gson.fromJson(fr, new TypeToken<HashMap<UUID, Long>>(){}.getType());
        StringBuilder s = new StringBuilder("Loaded playtimes: ");
        for(Map.Entry<UUID, Long> entry : loaded.entrySet()) {
            s.append("\n");
            s.append(entry.getKey()).append(" : ").append(entry.getValue());
        }
        System.out.println(s);
        return loaded;
    }

    public void saveEntries(@NonNull HashMap<UUID, Long> entries) {
        if(!filePrep()) return;
        Gson gson = new Gson();
        FileWriter fw;
        try {
            fw = new FileWriter(f, false);
            fw.write(gson.toJson(entries));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
