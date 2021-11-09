package papaya.instastory.utils;

import android.os.Environment;

import java.io.File;

public class DirectoryUtils{
    public static String FOLDER = "/Instagram Downloader/";
    public static File DIRECTORY_FOLDER = new File(Environment.getExternalStorageDirectory() + "/Download/Instagram Downloader/");

    public static void createFile() {
        if (!DIRECTORY_FOLDER.exists()) {
            DIRECTORY_FOLDER.mkdirs();
        }
    }
}
