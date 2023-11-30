//Cristiano Michael 3147571
public class PostalCode implements Comparable<PostalCode> {
    private String code;
    private String area;
    private String province;
    private double latitude;
    private double longitude;

    public PostalCode(String code, String area, String province, double latitude, double longitude) {
        this.code = code;
        this.area = area;
        this.province = province;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters
    public String getCode() { return code; }
    public String getArea() { return area; }
    public String getProvince() { return province; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    // Setters
    public void setCode(String code) { this.code = code; }
    public void setArea(String area) { this.area = area; }
    public void setProvince(String province) { this.province = province; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    @Override
    public int compareTo(PostalCode other) {
        return this.code.compareTo(other.code);
    }

    @Override
    public String toString() {
        return "PostalCode{" +
               "code='" + code + '\'' +
               ", area='" + area + '\'' +
               ", province='" + province + '\'' +
               ", latitude=" + latitude +
               ", longitude=" + longitude +
               '}';
    }
}
