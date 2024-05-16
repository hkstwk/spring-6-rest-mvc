package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.model.BeerCsvRecord;
import com.mysql.cj.util.DnsSrv;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BeerCsvServiceImplTest {
    BeerCsvService beerCsvService = new BeerCsvServiceImpl();

    @Test
    void testLoadCsv() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csv/beers.csv");
        List<BeerCsvRecord> beerCsvRecords = beerCsvService.loadCsv(file);
        System.out.println(beerCsvRecords.size());
        assertThat(beerCsvRecords).isNotEmpty();
    }
}
