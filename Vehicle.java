class Vehicle {
    private String plate;
    private String model;
    private String size;
    private String entryTime;
    private String departureTime;
    private Vacancy vacancy;

    public Vehicle(String plate, String model, String size, String entryTime, Vacancy vacancy) {
        this.plate = plate;
        this.model = model;
        this.size = size;
        this.entryTime = entryTime;
        this.vacancy = vacancy;
    }

    public String getPlate() { return plate; }
    public String getEntryTime() { return entryTime; }
    public String getDepartureTime() { return departureTime; }
    public Vacancy getVacancy() { return vacancy; }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public long calculateDuration() {
        // Lógica simples de cálculo de duração (em minutos)
        String[] entryParts = entryTime.split(":");
        String[] departureParts = departureTime.split(":");

        int entryHour = Integer.parseInt(entryParts[0]);
        int entryMinute = Integer.parseInt(entryParts[1]);
        int departureHour = Integer.parseInt(departureParts[0]);
        int departureMinute = Integer.parseInt(departureParts[1]);

        return ((departureHour * 60 + departureMinute) - (entryHour * 60 + entryMinute));
    }

    public double calculatePayment(long duration) {
        if (duration <= 60) return 5.0;
        else if (duration <= 180) return 10.0;
        else return 15.0;
    }
}