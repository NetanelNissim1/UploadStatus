package com.momagic.uploadstatus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.momagic.uploadstatus.managedatabase.*;
import com.momagic.uploadstatus.managefils.ManageCsvFiles;
import com.momagic.uploadstatus.managefils.ManageProperties;
import com.momagic.uploadstatus.managefils.Utils;
import com.momagic.uploadstatus.model.ModelMoMagic;

/**
 * Hello world!
 */
public final class App {

    public static String UploadMoMagic;
    public static String ExportMoMagic;

    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        UploadMoMagic = ManageProperties.getProperties("UploadMoMagic");
        ExportMoMagic = ManageProperties.getProperties("ExportMoMagic");
        DB.getConnection();
        processFiles(UploadMoMagic);
    }

    private static void processFiles(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        List<ModelMoMagic> moMagic = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                moMagic = ManageCsvFiles.processInputFile(file.getAbsolutePath());
                
                String ExportStatusMoMagic = "ExportStatusMoMagic.csv";
                Utils.writeToCSV(ExportMoMagic, DB.selectStatus(moMagic), ExportStatusMoMagic);
            }
        }
    }
}
