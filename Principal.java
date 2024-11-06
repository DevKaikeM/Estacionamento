import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Vacancy> vacancies = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();
        boolean flagLoop = true;

        while (flagLoop) {
            System.out.println("\n[1] Cadastrar vaga\n[2] Entrada de veículo\n[3] Saída de veículo\n[4] Ver relatório de vagas ocupadas\n[5] Histórico de permanência\n[6] Sair");
            int option = scan.nextInt();

            switch (option) {
                case 1: // Cadastrar vaga
                    System.out.println("Digite o número da vaga:");
                    int numberVacancy = scan.nextInt();
                    System.out.println("Digite o tamanho da vaga \n[1] Pequeno\n[2] Médio\n[3] Grande");
                    int sizeOption = scan.nextInt();
                    String size = getSize(sizeOption);
                    vacancies.add(new Vacancy(numberVacancy, size, true));
                    System.out.println("Vaga cadastrada com sucesso!");
                    break;

                case 2: // Entrada de veículo
                    System.out.println("Digite a placa do veículo:");
                    String plate = scan.next();
                    System.out.println("Digite o modelo do veículo:");
                    String model = scan.next();
                    System.out.println("Digite o tamanho do veículo \n[1] Pequeno\n[2] Médio\n[3] Grande");
                    int vehicleSizeOption = scan.nextInt();
                    String vehicleSize = getSize(vehicleSizeOption);
                    System.out.println("Digite a hora de entrada (HH:mm):");
                    String entryTime = scan.next();

                    // Verificar se há vaga disponível para o tamanho do veículo
                    Vacancy availableVacancy = findAvailableVacancy(vacancies, vehicleSize);
                    if (availableVacancy != null) {
                        availableVacancy.setAvailable(false);
                        Vehicle vehicle = new Vehicle(plate, model, vehicleSize, entryTime, availableVacancy);
                        vehicles.add(vehicle);
                        System.out.println("Veículo registrado com sucesso na vaga " + availableVacancy.getNumber());
                    } else {
                        System.out.println("Nenhuma vaga disponível para o tamanho do veículo.");
                    }
                    break;

                case 3: // Saída de veículo
                    System.out.println("Digite a placa do veículo:");
                    String exitPlate = scan.next();
                    Vehicle vehicle = findVehicleByPlate(vehicles, exitPlate);
                    if (vehicle != null) {
                        System.out.println("Digite a hora de saída (HH:mm):");
                        String departureTime = scan.next();
                        vehicle.setDepartureTime(departureTime);

                        long duration = vehicle.calculateDuration();
                        double amount = vehicle.calculatePayment(duration);

                        vehicle.getVacancy().setAvailable(true); // Liberar a vaga
                        System.out.println("Veículo removido. Tempo de permanência: " + duration + " minutos. Valor a pagar: R$ " + amount);
                    } else {
                        System.out.println("Veículo não encontrado.");
                    }
                    break;

                case 4: // Relatório de vagas ocupadas
                    System.out.println("Vagas Ocupadas:");
                    for (Vehicle v : vehicles) {
                        if (v.getDepartureTime() == null) {
                            System.out.println("Vaga " + v.getVacancy().getNumber() + " - Tamanho: " + v.getVacancy().getSize() + " - Placa: " + v.getPlate());
                        }
                    }
                    break;

                case 5: // Histórico de permanência
                    System.out.println("Histórico de Permanência:");
                    for (Vehicle v : vehicles) {
                        if (v.getDepartureTime() != null) {
                            System.out.println("Placa: " + v.getPlate() + " - Entrada: " + v.getEntryTime() + " - Saída: " + v.getDepartureTime());
                        }
                    }
                    break;

                case 6: // Sair
                    System.out.println("Você escolheu sair.");
                    flagLoop = false;
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        scan.close();
    }

    // Métodos auxiliares
    private static String getSize(int sizeOption) {
        switch (sizeOption) {
            case 1: return "Pequeno";
            case 2: return "Médio";
            case 3: return "Grande";
            default: return "Indefinido";
        }
    }

    private static Vacancy findAvailableVacancy(List<Vacancy> vacancies, String size) {
        for (Vacancy v : vacancies) {
            if (v.isAvailable() && v.getSize().equals(size)) {
                return v;
            }
        }
        return null;
    }

    private static Vehicle findVehicleByPlate(List<Vehicle> vehicles, String plate) {
        for (Vehicle v : vehicles) {
            if (v.getPlate().equals(plate) && v.getDepartureTime() == null) {
                return v;
            }
        }
        return null;
    }
}