package com.epam.lab.xml.parsers;

import com.epam.lab.xml.entity.Medicine;

import java.io.File;
import java.util.List;

public interface MedParser {

    List<Medicine> parse(File xml);

}
