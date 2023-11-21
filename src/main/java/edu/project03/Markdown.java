package edu.project03;

public class Markdown extends Format{
    @Override
    public String extension() {
        return ".md";
    }

    @Override
    protected String handler(Table table) {
        return "### " + table.getHandler() + "\n";
    }
}
