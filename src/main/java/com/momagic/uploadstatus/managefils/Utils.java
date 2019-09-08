package com.momagic.uploadstatus.managefils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.momagic.uploadstatus.model.ModelMoMagic;

public class Utils {

    private static final String CSV_SEPARATOR = ",";

    public static void preZipFile(String folderZip, String fileNameToZip, String zipFileName) throws IOException {

        FileOutputStream fos = new FileOutputStream(folderZip + zipFileName);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(fileNameToZip);
        zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    public static void writeToCSV(String exportCSV, List<ModelMoMagic> moMagicList, String fileName) {
        try {
            try (BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(exportCSV + fileName), "UTF-8"))) {
                for (ModelMoMagic item : moMagicList) {
                    if (item.getAndroidId() != null && item.getIMEI() != null) {
                        if (!item.getAndroidId().isEmpty() && !item.getIMEI().isEmpty()) {
                            StringBuffer oneLine = new StringBuffer();
                            oneLine.append(item.getActivationDate());
                            oneLine.append(CSV_SEPARATOR);
                            oneLine.append(item.getAppName());
                            oneLine.append(CSV_SEPARATOR);
                            oneLine.append(item.getIMEI());
                            oneLine.append(CSV_SEPARATOR);
                            oneLine.append(item.getAndroidId());
                            oneLine.append(CSV_SEPARATOR);
                            oneLine.append(item.getAndroidVersion());
                            oneLine.append(CSV_SEPARATOR);
                            oneLine.append(item.getModel());
                            oneLine.append(CSV_SEPARATOR);
                            oneLine.append(item.getStatus());
                            oneLine.append(CSV_SEPARATOR);
                            if (item.getDidCalls() == null) {
                                item.setDidCalls("no");
                            }
                            oneLine.append(item.getDidCalls());
                            bw.write(oneLine.toString());
                            bw.newLine();
                        }
                    }
                }
                bw.flush();
            }
        } catch (UnsupportedTemporalTypeException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    // public static void writeToCSVScrapingPhoneNames(String exportToPhoneNames,
    // List<Phone_Name_Email> numberEmails, String fileName ){
    // try{
    // try ( BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new
    // FileOutputStream( App.ExportScrapingToPhoneNames + fileName ), "UTF-8" ) ) ){
    // for( Phone_Name_Email numberEmail : numberEmails ){
    // if( numberEmail.getPhone() != null ){
    // if( !numberEmail.getPhone().isEmpty() ){
    // StringBuffer oneLine = new StringBuffer();
    // oneLine.append( numberEmail.getPhone() );
    // oneLine.append( CSV_SEPARATOR );
    // oneLine.append( numberEmail.getName() );
    // bw.write( oneLine.toString() );
    // bw.newLine();
    // }
    // }
    // }
    // bw.flush();
    // }
    // } catch ( UnsupportedEncodingException e ) {
    // } catch ( FileNotFoundException e ) {
    // } catch ( IOException e ) {
    // }
    // }

}