package model.report;

public class ReportFactory {

    public static Report createReport(String str) {
        Report report = null;
        switch (str) {
            case "csv":
                report = new CSV_Report();
                break;

            case "json":
                report = new JSON_Report();
                break;
            default:
        }
        return report;
    }
}
