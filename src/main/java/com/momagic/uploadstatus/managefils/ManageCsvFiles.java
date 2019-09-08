package com.momagic.uploadstatus.managefils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.momagic.uploadstatus.model.ModelMoMagic;

public class ManageCsvFiles {

    private final static String COMMA = ",";

    public static List<ModelMoMagic> processInputFile(String inputFilePath) {
        List<ModelMoMagic> inputList = new ArrayList<ModelMoMagic>();
        try {
            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputFS))) {
                inputList = br.lines().map(mapToItemMoMagic).collect(Collectors.toList());
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
            return null;
        }
        return inputList;
    }

    private static Function<String, ModelMoMagic> mapToItemMoMagic = (line) -> {
        String[] p = line.split(COMMA);// a CSV has comma separated lines
        ModelMoMagic item = new ModelMoMagic();

        String header = p[0];
        header = header.replaceAll("\"", "");

        if (!header.equals("ActivationDate")) {
            if (p.length > 0) {
                item.setActivationDate(p[0].replaceAll("\"", ""));
                item.setAppName(p[1].replaceAll("\"", ""));
                item.setIMEI(p[2].replaceAll("\"", ""));
                item.setAndroidId(p[3].replaceAll("\"", ""));
                item.setAndroidVersion(p[4].replaceAll("\"", ""));
                item.setModel(p[5].replaceAll("\"", ""));
            }
        }
        return item;
    };

    public static List<ModelMoMagic> processUploadImei(String absolutePath) {
        List<ModelMoMagic> inputList = new ArrayList<ModelMoMagic>();
        try {
            File inputF = new File(absolutePath);
            InputStream inputFS = new FileInputStream(inputF);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputFS))) {
                inputList = br.lines().map(mapToItemImei).collect(Collectors.toList());
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
            return null;
        }
        return inputList;
    }

    private static Function<String, ModelMoMagic> mapToItemImei = (line) -> {
        String[] p = line.split(COMMA);// a CSV has comma separated lines
        ModelMoMagic item = new ModelMoMagic();
        if (p.length > 0) {
            item.setIMEI(p[0].replaceAll("\"", ""));
        }
        return item;
    };
}
