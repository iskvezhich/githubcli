package com.mend.yevhen.githubcli.resultHandlers;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;
import com.mend.yevhen.githubcli.model.Release;
import com.mend.yevhen.githubcli.model.Stat;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class ResultPrinter {
    public int printReleases(List<Release> releases, String output) {
        String table = AsciiTable.getTable(AsciiTable.BASIC_ASCII_NO_DATA_SEPARATORS, releases, Arrays.asList(
                new Column().header("RELEASE NAME").dataAlign(HorizontalAlign.LEFT).with(Release::getName),
                new Column().header("DISTRIBUTION").dataAlign(HorizontalAlign.LEFT).with(Release::getDistribution),
                new Column().header("DOWNLOAD COUNT").with(r -> Integer.toString(r.getDownloadCount()))));
        return printTable(table, output);
    }

    public int printStats(List<Stat> stats, String output) {
        String table = AsciiTable.getTable(AsciiTable.BASIC_ASCII_NO_DATA_SEPARATORS, stats, Arrays.asList(
                new Column().header("STAT").dataAlign(HorizontalAlign.LEFT).with(Stat::getStat),
                new Column().header("VALUE").dataAlign(HorizontalAlign.LEFT).with(Stat::getValue)));
        return printTable(table, output);
    }

    private int printTable(String table, String output) {
        int retVal = 0;
        if (output == null) {
            System.out.println(table);
        } else {
            Path file = Paths.get(output);
            if (!file.isAbsolute()) {
                file = Paths.get(System.getProperty("user.dir"), output);
            }
            if (!Files.exists(file)) {
                try {
                    Files.createDirectories(file.getParent());
                } catch (IOException e) {
                    throw new RuntimeException(
                            String.format("An error occurred during the directory creation: %s", file.getParent()),
                            e);
                }
            }
            try {
                Files.write(file, table.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(
                        String.format("An error occurred during results saving. The file: %s", file),
                        e);
            }
            System.out.printf("Results saved to the file %s%n", file);
        }
        return retVal;
    }
}
