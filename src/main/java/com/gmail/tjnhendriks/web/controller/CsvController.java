package com.gmail.tjnhendriks.web.controller;

import com.gmail.tjnhendriks.service.CsvReadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CsvController {
    private CsvReadService csvReader;

    public CsvController(CsvReadService csvReader){
        this.csvReader = csvReader;
    }

    @RequestMapping(value="/readcsv",
                    method= RequestMethod.POST)
    public void readCsv(@RequestBody MultipartFile multiPartFile, @RequestParam  String splitBy) throws IOException {
            File file = csvReader.convert(multiPartFile);
            csvReader.readCsv(file.toPath(), splitBy);
    }

}
