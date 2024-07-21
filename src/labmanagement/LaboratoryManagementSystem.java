package labmanagement;

import java.sql.*;
import java.util.Scanner;

public class LaboratoryManagementSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Manage Experiments");
            System.out.println("2. Manage Samples");
            System.out.println("3. Manage Researchers");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    manageExperiments(sc);
                    break;
                case 2:
                    manageSamples(sc);
                    break;
                case 3:
                    manageResearchers(sc);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void manageExperiments(Scanner sc) {
        System.out.println("1. Add Experiment");
        System.out.println("2. View Experiment");
        System.out.println("3. Update Experiment");
        System.out.println("4. Delete Experiment");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                addExperiment(sc);
                break;
            case 2:
                viewExperiment(sc);
                break;
            case 3:
                updateExperiment(sc);
                break;
            case 4:
                deleteExperiment(sc);
                break;
            default:
                System.out.println("Invalid option. Try again.");
        }
    }

    private static void addExperiment(Scanner sc) {
        try (Connection connection = DbConnection.getConnection()) {
            System.out.print("Enter experiment name: ");
            String experimentName = sc.next();
            System.out.print("Enter researcher ID: ");
            int researcherId = sc.nextInt();
            System.out.print("Enter start date (YYYY-MM-DD): ");
            String startDate = sc.next();
            System.out.print("Enter end date (YYYY-MM-DD): ");
            String endDate = sc.next();
            System.out.print("Enter status (Planned, In Progress, Completed): ");
            String status = sc.next();

            String query = "INSERT INTO Experiment (experiment_name, researcher_id, start_date, end_date, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, experimentName);
            statement.setInt(2, researcherId);
            statement.setDate(3, Date.valueOf(startDate));
            statement.setDate(4, Date.valueOf(endDate));
            statement.setString(5, status);
            statement.executeUpdate();
            System.out.println("Experiment added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewExperiment(Scanner sc) {
        try (Connection connection = DbConnection.getConnection()) {
            System.out.print("Enter experiment ID to view: ");
            int experimentId = sc.nextInt();
            String query = "SELECT * FROM Experiment WHERE experiment_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, experimentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Experiment ID: " + resultSet.getInt("experiment_id"));
                System.out.println("Experiment Name: " + resultSet.getString("experiment_name"));
                System.out.println("Researcher ID: " + resultSet.getInt("researcher_id"));
                System.out.println("Start Date: " + resultSet.getDate("start_date"));
                System.out.println("End Date: " + resultSet.getDate("end_date"));
                System.out.println("Status: " + resultSet.getString("status"));
            } else {
                System.out.println("Experiment not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateExperiment(Scanner sc) {
        try (Connection connection = DbConnection.getConnection()) {
            System.out.print("Enter experiment ID to update: ");
            int experimentId = sc.nextInt();
            System.out.print("Enter new experiment name: ");
            String experimentName = sc.next();
            System.out.print("Enter new researcher ID: ");
            int researcherId = sc.nextInt();
            System.out.print("Enter new start date (YYYY-MM-DD): ");
            String startDate = sc.next();
            System.out.print("Enter new end date (YYYY-MM-DD): ");
            String endDate = sc.next();
            System.out.print("Enter new status (Planned, In Progress, Completed): ");
            String status = sc.next();

            String query = "UPDATE Experiment SET experiment_name = ?, researcher_id = ?, start_date = ?, end_date = ?, status = ? WHERE experiment_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, experimentName);
            statement.setInt(2, researcherId);
            statement.setDate(3, Date.valueOf(startDate));
            statement.setDate(4, Date.valueOf(endDate));
            statement.setString(5, status);
            statement.setInt(6, experimentId);
            statement.executeUpdate();
            System.out.println("Experiment updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteExperiment(Scanner sc) {
        try (Connection connection = DbConnection.getConnection()) {
            System.out.print("Enter experiment ID to delete: ");
            int experimentId = sc.nextInt();
            String query = "DELETE FROM Experiment WHERE experiment_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, experimentId);
            statement.executeUpdate();
            System.out.println("Experiment deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void manageSamples(Scanner sc) {
        System.out.println("1. Add Sample");
        System.out.println("2. View Sample");
        System.out.println("3. Update Sample");
        System.out.println("4. Delete Sample");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                addSample(sc);
                break;
            case 2:
                viewSample(sc);
                break;
            case 3:
                updateSample(sc);
                break;
            case 4:
                deleteSample(sc);
                break;
            default:
                System.out.println("Invalid option. Try again.");
        }
    }

    private static void addSample(Scanner sc) {
        try (Connection connection = DbConnection.getConnection()) {
            System.out.print("Enter sample name: ");
            String sampleName = sc.next();
            System.out.print("Enter experiment ID: ");
            int experimentId = sc.nextInt();
            System.out.print("Enter collection date (YYYY-MM-DD): ");
            String collectionDate = sc.next();
            System.out.print("Enter description: ");
            String description = sc.next();

            String query = "INSERT INTO Sample (sample_name, experiment_id, collection_date, description) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, sampleName);
            statement.setInt(2, experimentId);
            statement.setDate(3, Date.valueOf(collectionDate));
            statement.setString(4, description);
            statement.executeUpdate();
            System.out.println("Sample added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewSample(Scanner sc) {
        try (Connection connection = DbConnection.getConnection()) {
            System.out.print("Enter sample ID to view: ");
            int sampleId = sc.nextInt();
            String query = "SELECT * FROM Sample WHERE sample_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, sampleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Sample ID: " + resultSet.getInt("sample_id"));
                System.out.println("Sample Name: " + resultSet.getString("sample_name"));
                System.out.println("Experiment ID: " + resultSet.getInt("experiment_id"));
                System.out.println("Collection Date: " + resultSet.getDate("collection_date"));
                System.out.println("Description: " + resultSet.getString("description"));
            } else {
                System.out.println("Sample not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateSample(Scanner sc) {
        try (Connection connection = DbConnection.getConnection()) {
            System.out.print("Enter sample ID to update: ");
            int sampleId = sc.nextInt();
            System.out.print("Enter new sample name: ");
            String sampleName = sc.next();
            System.out.print("Enter new experiment ID: ");
            int experimentId = sc.nextInt();
            System.out.print("Enter new collection date (YYYY-MM-DD): ");
            String collectionDate = sc.next();
            System.out.print("Enter new description: ");
            String description = sc.next();

            String query = "UPDATE Sample SET sample_name = ?, experiment_id = ?, collection_date = ?, description = ? WHERE sample_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, sampleName);
            statement.setInt(2, experimentId);
            statement.setDate(3, Date.valueOf(collectionDate));
            statement.setString(4, description);
            statement.setInt(5, sampleId);
            statement.executeUpdate();
            System.out.println("Sample updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteSample(Scanner sc) {
        try (Connection connection = DbConnection.getConnection()) {
            System.out.print("Enter sample ID to delete: ");
            int sampleId = sc.nextInt();
            String query = "DELETE FROM Sample WHERE sample_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, sampleId);
            statement.executeUpdate();
            System.out.println("Sample deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void manageResearchers(Scanner sc) {
        System.out.println("1. Add Researcher");
        System.out.println("2. View Researcher");
        System.out.println("3. Update Researcher");
        System.out.println("4. Delete Researcher");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                addResearcher(sc);
                break;
            case 2:
                viewResearcher(sc);
                break;
            case 3:
                updateResearcher(sc);
                break;
            case 4:
                deleteResearcher(sc);
                break;
            default:
                System.out.println("Invalid option. Try again.");
        }
    }

    private static void addResearcher(Scanner sc) {
        try (Connection connection = DbConnection.getConnection()) {
            System.out.print("Enter researcher name: ");
            String researcherName = sc.next();
            System.out.print("Enter specialization: ");
            String specialization = sc.next();
            System.out.print("Enter email: ");
            String email = sc.next();
            System.out.print("Enter phone number: ");
            String phoneNumber = sc.next();

            String query = "INSERT INTO Researcher (researcher_name, specialization, email, phone_number) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, researcherName);
            statement.setString(2, specialization);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);
            statement.executeUpdate();
            System.out.println("Researcher added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewResearcher(Scanner sc) {
        try (Connection connection = DbConnection.getConnection()) {
            System.out.print("Enter researcher ID to view: ");
            int researcherId = sc.nextInt();
            String query = "SELECT * FROM Researcher WHERE researcher_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, researcherId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Researcher ID: " + resultSet.getInt("researcher_id"));
                System.out.println("Researcher Name: " + resultSet.getString("researcher_name"));
                System.out.println("Specialization: " + resultSet.getString("specialization"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Phone Number: " + resultSet.getString("phone_number"));
            } else {
                System.out.println("Researcher not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateResearcher(Scanner sc) {
        try (Connection connection = DbConnection.getConnection()) {
            System.out.print("Enter researcher ID to update: ");
            int researcherId = sc.nextInt();
            System.out.print("Enter new researcher name: ");
            String researcherName = sc.next();
            System.out.print("Enter new specialization: ");
            String specialization = sc.next();
            System.out.print("Enter new email: ");
            String email = sc.next();
            System.out.print("Enter new phone number: ");
            String phoneNumber = sc.next();

            String query = "UPDATE Researcher SET researcher_name = ?, specialization = ?, email = ?, phone_number = ? WHERE researcher_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, researcherName);
            statement.setString(2, specialization);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);
            statement.setInt(5, researcherId);
            statement.executeUpdate();
            System.out.println("Researcher updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteResearcher(Scanner sc) {
        try (Connection connection = DbConnection.getConnection()) {
            System.out.print("Enter researcher ID to delete: ");
            int researcherId = sc.nextInt();
            String query = "DELETE FROM Researcher WHERE researcher_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, researcherId);
            statement.executeUpdate();
            System.out.println("Researcher deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
