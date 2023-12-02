package edu.project03;

import org.apache.logging.log4j.core.util.ArrayUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.ArrayList;
import java.util.stream.Stream;
import static java.lang.StringTemplate.STR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParsingLogTest {
    @Test
    @DisplayName("Error test")
    void f1(){
        assertThrows(PatternNginx.PatternNotMatch.class,() -> {
            PatternNginx.getMPattern("ip - ip and somethings");
        });
    }

    @ParameterizedTest
    @DisplayName("String variations")
    @ArgumentsSource(RecordNGINX.class)
    void f2(String parsed, String a) {
        assertThat(parsed).isEqualTo(a);
    }

    static class RecordNGINX implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            ArrayList<Arguments> arguments = new ArrayList<>();
            for (String ip1 : ips) {
                for (String ip2 : ips) {
                    for (String time : times) {
                        for (String method : methods) {
                            for (String resource : resources) {
                                for (String protocol : protocols) {
                                    for (String code : codes) {
                                        for (String size : sizes) {
                                            for (String referer : referrers) {
                                                for (String userAgent : userAgents) {
                                                    String record  =
                                                        STR."\{ip1} - \{ip2} [\{time}] \"\{method} \{resource} \{protocol}\" \{code} \{size} \"\{referer}\" \"\{userAgent}\"";
                                                    try {
                                                        PatternNginx nginx = PatternNginx.getMPattern(record);
                                                        arguments.add(Arguments.of(nginx.getFirstIp(), ip1));
                                                        arguments.add(Arguments.of(nginx.getSecondIp(), ip2));
                                                        arguments.add(Arguments.of(nginx.getTimeS(), time));
                                                        arguments.add(Arguments.of(nginx.getRequestMethod(), method));
                                                        arguments.add(Arguments.of(nginx.getRequestResources(), resource));
                                                        arguments.add(Arguments.of(nginx.getRequestProtocol(), protocol));
                                                        arguments.add(Arguments.of(nginx.getResponseCode(), code));
                                                        arguments.add(Arguments.of(nginx.getSize(), size));
                                                        arguments.add(Arguments.of(nginx.getReferer(), referer));
                                                        arguments.add(Arguments.of(nginx.getUserAgent(), userAgent));
                                                    } catch (PatternNginx.PatternNotMatch e) {
                                                        throw new RuntimeException(e + record);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return arguments.stream();
        }

        String[] ips = new String[]{
            "93.180.71.3",
            "::",
            "2a01:7e00:f03c:f03c:91ff:fe70:a4cc:a4cc"
        };
        String[] times = new String[]{
            "17/May/2015:08:05:34 +0000"
        };
        String[] methods = PatternNginx.REQUEST_METHOD_PATTERN.split("\\|");
        String[] resources = new String[]{
            "index.html",
            "product_2"
        };
        String[] protocols = new String[]{
            "HTTP/1.1"
        };
        String[] codes = new String[]{
            "404",
            "200"
        };
        String[] sizes = new String[]{
            "0",
            "1000000"
        };
        String[] referrers = new String[]{
            "-"
        };
        String[] userAgents = new String[]{
            "Windows user"
        };
    }
}

