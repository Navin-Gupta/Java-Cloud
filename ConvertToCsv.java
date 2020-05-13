import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Employee{

    private String name;
    private String email;

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Employee(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

public class ConvertToCsv{
    public static void main(String[] args) throws IOException {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("First", "first@mail.com"));
        employees.add(new Employee("Second", "second@mail.com"));
        employees.add(new Employee("Third", "third@mail.com"));

        FileWriter csvWriter = new FileWriter("new.csv");


        for (Employee employee : employees) {
            csvWriter.append(employee.getName() + "," + employee.getEmail());
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }
}