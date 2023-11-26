package edu.project03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    @DisplayName("1")
    void f(){
        String[] args = new String[]{
            "--path", "raw.githubusercontent.com_elastic_examples_master_Common Data Formats_nginx_logs_nginx_logs.txt",
            "--from", "",
            "--to", "2015-06-01",
            "--format", ""
        };
        Analitik.main(args);
    }
}
