package com.dowjones.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dowjones.domain.DowJonesIndex;

public class FileUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

	public static final MultipartFile readFile(String fileName) throws IOException {
		InputStream is = FileUtils.class.getResourceAsStream(fileName);

		File file = new File("src/main/resources/".concat(fileName).concat(".tmp")); // TODO: should use global temp folder, configured, instead of "src/main/resources/" 
		org.apache.commons.io.FileUtils.copyInputStreamToFile(is, file);
		FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), false, file.getName(),
				(int) file.length(), file.getParentFile());

		try {
			IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
		} catch (IOException ex) {
			LOGGER.error("Could not read data file, error: {}", ex);
		}

		MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
		return multipartFile;
	}
	
	public static List<DowJonesIndex> parseCSVFile(final MultipartFile file) throws Exception {
        final List<DowJonesIndex> dowJonesData=new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line=br.readLine()) != null) {
                    final String[] data=line.split(",");
                    final DowJonesIndex elem=new DowJonesIndex();
                    elem.setQuarter(new Integer(data[0]));
                    elem.setStock(data[1]);
                    elem.setDate(LocalDate.parse(data[2], DowJonesConstants.dateFormatter));
                    elem.setOpen(StringUtils.isEmpty(data[3]) ? BigDecimal.ZERO : new BigDecimal(data[3]));
                    elem.setHigh(StringUtils.isEmpty(data[4]) ? BigDecimal.ZERO : new BigDecimal(data[4]));
                    elem.setLow(StringUtils.isEmpty(data[5]) ? BigDecimal.ZERO : new BigDecimal(data[5]));
                    elem.setClose(StringUtils.isEmpty(data[6]) ? BigDecimal.ZERO : new BigDecimal(data[6]));
                    elem.setVolume(StringUtils.isEmpty(data[7]) ? 0l : new Long(data[7]));
                    elem.setPercentChangePrice(StringUtils.isEmpty(data[8]) ? BigDecimal.ZERO : new BigDecimal(data[8]));
                    elem.setPercentChangeVolumeOverLastWeek(StringUtils.isEmpty(data[9]) ? BigDecimal.ZERO : new BigDecimal(data[9]));
                    elem.setPreviousWeeksVolume(StringUtils.isEmpty(data[10]) ? BigDecimal.ZERO : new BigDecimal(data[10]));
                    elem.setNextWeeksOpen(StringUtils.isEmpty(data[11]) ? BigDecimal.ZERO : new BigDecimal(data[11]));
                    elem.setNextWeeksClose(StringUtils.isEmpty(data[12]) ? BigDecimal.ZERO : new BigDecimal(data[12]));
                    elem.setPercentChangeNextWeeksPrice(StringUtils.isEmpty(data[13]) ? BigDecimal.ZERO : new BigDecimal(data[13]));
                    elem.setDaysToNextDividend(StringUtils.isEmpty(data[14]) ? BigDecimal.ZERO : new BigDecimal(data[14]));
                    elem.setPercentReturnNextDividend(StringUtils.isEmpty(data[15]) ? BigDecimal.ZERO : new BigDecimal(data[15]));
                     
                    LOGGER.debug("elem: {}", elem);
                    dowJonesData.add(elem);
                }
                return dowJonesData;
            }
        } catch(final IOException e) {
            LOGGER.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }
	
}
