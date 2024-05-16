package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.model.BeerCsvRecord;

import java.io.File;
import java.util.List;

public interface BeerCsvService {
    List<BeerCsvRecord> loadCsv(File csvFile);
}
