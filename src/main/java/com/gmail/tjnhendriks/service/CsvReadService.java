package com.gmail.tjnhendriks.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;

@Service
public class CsvReadService {
    Logger log = Logger.getLogger(CsvReadService.class);

    public File convert(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        convertedFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    public void readCsv(Path csvPath, String splitBy){
        BufferedReader bufferedReader = null;
        String line = "";

        try {

            bufferedReader = new BufferedReader(new FileReader(csvPath.toFile()));
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(splitBy);
                for(String value : values){
                    log.info(value);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}