class Vacancy {
    private int number;
    private String size;
    private boolean available;

    public Vacancy(int number, String size, boolean available) {
        this.number = number;
        this.size = size;
        this.available = available;
    }

    public int getNumber() { return number; }
    public String getSize() { return size; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
