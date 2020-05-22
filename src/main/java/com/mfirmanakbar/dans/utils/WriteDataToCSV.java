package com.mfirmanakbar.dans.utils;

import java.io.PrintWriter;
import java.util.List;

import com.mfirmanakbar.dans.response.PositionResponse;
import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;

public class WriteDataToCSV {

    public static void positionResponseToCsv(PrintWriter writer, List<PositionResponse> positionResponses) {
        String[] CSV_HEADER = {
                "ID",
                "Type",
                "URL",
                "Created At",
                "Company",
                "Company URL",
                "Location",
                "Title",
                //"Description",
                //"How to Apply",
                "Company Logo"
        };

        try (
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            csvWriter.writeNext(CSV_HEADER);

            for (PositionResponse position : positionResponses) {
                String[] data = {
                        removeComma(position.getId()),
                        removeComma(position.getType()),
                        removeComma(position.getUrl()),
                        removeComma(position.getCreatedAt()),
                        removeComma(position.getCompany()),
                        removeComma(position.getCompanyUrl()),
                        removeComma(position.getLocation()),
                        removeComma(position.getTitle()),
                        //removeComma(htmlToText(position.getDescription())),
                        //removeComma(htmlToText(position.getHowToApply())),
                        removeComma(position.getCompanyLogo())
                };

                csvWriter.writeNext(data);
            }

            System.out.println("Write CSV using CSVWriter successfully!");
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        }
    }

    private static String removeComma(String data) {
        return (data != null) ? data.replace(",", " ") : "";
    }

    // the performance very slow
    public static String htmlToText(String html) {
        return Jsoup.parse(html).text();
    }

}
