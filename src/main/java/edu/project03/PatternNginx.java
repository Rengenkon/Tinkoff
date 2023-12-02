package edu.project03;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternNginx {
    private static final String IP_PATTERN = "(\\d{1,3}\\.){3}\\d{1,3}|(\\w{0,4}:){2,7}\\w{0,4}";
    private static final int INNER_GROUPS_IP = 2;
    static final String TIME_PATTERN = ".*";
    private static final int INNER_GROUPS_TIME = 0;
    static final String REQUEST_METHOD_PATTERN = "GET|POST|PUT|PATCH|DELETE|HEAD|CONNECT|OPTIONS|TRACE";
    private static final int INNER_GROUPS_REQUEST_METHOD_PATTERN = 0;
    static final String REQUEST_RESOURCES_PATTERN = ".*";
    private static final int INNER_GROUPS_REQUEST_RESOURCES_PATTERN = 0;
    static final String REQUEST_PROTOCOL_PATTERN = "HTTP/1.1";
    private static final int INNER_GROUPS_REQUEST_PROTOCOL_PATTERN = 0;
    static final String REQUEST_PATTERN =
        "(" + REQUEST_METHOD_PATTERN + ") (" + REQUEST_RESOURCES_PATTERN +") (" + REQUEST_PROTOCOL_PATTERN + ")";
    private static final int INNER_GROUPS_REQUEST = 3
        + INNER_GROUPS_REQUEST_METHOD_PATTERN + INNER_GROUPS_REQUEST_RESOURCES_PATTERN
        + INNER_GROUPS_REQUEST_PROTOCOL_PATTERN;
    static final String REFERER_PATTERN = ".*";
    private static final int INNER_GROUPS_REFERER = 0;
    static final String USER_AGENT_PATTERN = ".*";
    private static final int INNER_GROUPS_USER_AGENT = 0;
    public static final Pattern PATTERN = Pattern.compile(
        "(" + IP_PATTERN + ") - ("
            + IP_PATTERN + "|-) "
            + "\\[(" + TIME_PATTERN + ")\\] "
            + "\\\"(" + REQUEST_PATTERN + ")\\\" "
            + "(\\d{1,}) " //response code
            + "(\\d{1,}) " //size
            + "\\\"(" + REFERER_PATTERN + ")\\\" "
            + "\\\"(" + USER_AGENT_PATTERN + ")\\\"");

    private static final int FIRST_IP = 1;
    private static final int SECOND_IP = FIRST_IP + INNER_GROUPS_IP + 1;
    private static final int TIME = SECOND_IP + INNER_GROUPS_IP + 1;
    private static final int REQUEST = TIME + INNER_GROUPS_TIME + 1;
    private static final int REQUEST_METHOD = REQUEST + 1;
    private static final int REQUEST_RESOURCES = REQUEST_METHOD + INNER_GROUPS_REQUEST_METHOD_PATTERN + 1;
    private static final int REQUEST_PROTOCOL = REQUEST_RESOURCES + INNER_GROUPS_REQUEST_METHOD_PATTERN + 1;
    private static final int RESPONSE_CODE = REQUEST + INNER_GROUPS_REQUEST + 1;
    private static final int SIZE = RESPONSE_CODE + 1;
    private static final int REFERER = SIZE + 1;
    private static final int USER_AGENT = REFERER + INNER_GROUPS_REFERER + 1;

    private final Matcher matcher;

    private PatternNginx(Matcher matcher) throws PatternNotMatch {
        this.matcher = matcher;
        if (!this.matcher.find()) {
            throw new PatternNotMatch("Parse error.\n");
        }
    }

    public static PatternNginx getMPattern(String line) throws PatternNotMatch {
        return new PatternNginx(PATTERN.matcher(line));
    }

    public String getFirstIp() {
        return matcher.group(FIRST_IP);
    }

    public String getSecondIp() {
        return matcher.group(SECOND_IP);
    }

    protected String getTimeS() {
        return matcher.group(TIME);
    }

    public OffsetDateTime getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");
        return OffsetDateTime.parse(matcher.group(TIME), formatter);
    }

    public String getRequest() {
        return matcher.group(REQUEST);
    }
    public String getRequestMethod() {
        return matcher.group(REQUEST_METHOD);
    }
    public String getRequestResources() {
        return matcher.group(REQUEST_RESOURCES);
    }
    public String getRequestProtocol() {
        return matcher.group(REQUEST_PROTOCOL);
    }

    public String getResponseCode() {
        return matcher.group(RESPONSE_CODE);
    }

    public String getSize() {
        return matcher.group(SIZE);
    }

    public  String getReferer() {
        return matcher.group(REFERER);
    }

    public String getUserAgent() {
        return matcher.group(USER_AGENT);
    }

    public static class PatternNotMatch extends Exception {
        private PatternNotMatch(String message) {
            super(message);
        }

        public PatternNotMatch add(String s) {
            return new PatternNotMatch(this.getMessage() + s);
        }
    }
}
