package me.grovre.playtimetracker;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("UnstableApiUsage")
public class dbUtil {

    private final File f;

    public dbUtil() {
        f = new File(PlaytimeTracker.getPlugin().getDataFolder() + File.separator + "TimeData.json");
        System.out.println(f.getAbsolutePath());
        try {
            f.getParentFile().mkdir();
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<UUID, Long> loadEntries() {
        Gson gson = new Gson();
        FileReader fr = null;

        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert fr != null;
        HashMap<UUID, Long> loaded = gson.fromJson(fr, new TypeToken<HashMap<UUID, Long>>(){}.getType());
        if(loaded == null) return new HashMap<>();

        StringBuilder s = new StringBuilder("Loaded playtimes: ");
        for(Map.Entry<UUID, Long> entry : loaded.entrySet()) {
            s.append("\n");
            s.append(entry.getKey()).append(" : ").append(entry.getValue());
        }
        System.out.println(s);

        return loaded;
    }

    public void saveEntries(@NonNull HashMap<UUID, Long> entries) {
        Gson gson = new Gson();
        FileWriter fw;
        try {
            fw = new FileWriter(f, false);
            fw.write(gson.toJson(entries));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Saved " + entries.size() + " players' times");
    }
}
