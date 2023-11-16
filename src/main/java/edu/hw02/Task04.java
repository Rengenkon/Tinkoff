package edu.hw02;

public class Task04 {
    private Task04() {}

    public static CallingInfo getCalling() {
        var s = new Throwable().getStackTrace();
        String className = s[1].getClassName();
        String methodName = s[1].getMethodName();
        return new CallingInfo(className, methodName);
    }

    public record CallingInfo(String className, String methodName) {}
}
