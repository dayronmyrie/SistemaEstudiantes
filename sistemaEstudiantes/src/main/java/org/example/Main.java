package org.example;

import org.example.DAO.EstudianteDAO;
import org.example.Entity.Estudiante;

import java.util.List;
import java.util.Scanner;
public class Main {

    void main(){
        var salir = false;
        Scanner scanner = new Scanner(System.in);

        EstudianteDAO estudianteDAO = new EstudianteDAO();

        while (!salir){
            try {
                Menu();
                salir = EjecutarOpciones(scanner, estudianteDAO);
            } catch (Exception e){
                System.out.println("Error en " + e.getMessage());
            }
            System.out.println();
        }

    }

    private static void Menu(){
        System.out.print("""
                Sistema de Estudiantes
                1- Lista de Estudiantes
                2- Agregar Estudiante
                3- Buscar Estudiante por ID
                4- Eliminar Estudiante
                5- Modificar Estudiante
                6- Salir
           
                Digita una opcion:
                """);
    }
    private static void subMenuUpdate(){
        System.out.print("""
                1- Modificar Nombre
                2- Modificar apellido
                3- Modificar telefono
                4- Modificar email
                5- Modificar estudiante completo
                6- Volver a Menu Principal
                
                Digita una opcion:
                """);
    }

    private static boolean EjecutarOpciones(Scanner scanner, EstudianteDAO estudianteDAO){
        var salir = false;
        var opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion){
            case 1:
                System.out.println("Listado de Estudiantes");
                List<Estudiante> estudiantes = estudianteDAO.listar();

                estudiantes.forEach(System.out::println);
                break;
            case 2:
                System.out.println("Agregar Estudiante....");
                System.out.println("Ingrese nombre de estudiante: ");
                var nombre = scanner.nextLine();
                System.out.println("Ingrese el apellido de estudiante: ");
                var apellido = scanner.nextLine();
                System.out.println("Ingrese el telefono de estudiante: ");
                var telefono = scanner.nextLine();
                System.out.println("Ingrese el email del estudiante: ");
                var email = scanner.nextLine();
                Estudiante estudiante = new Estudiante(nombre, apellido, telefono, email);

                estudianteDAO.AgregarEstudiante(estudiante);
                System.out.println("Estudiante agregado correctamente");
                break;

            case 3:
                System.out.println("Digite ID: ");
                var id = Integer.parseInt(scanner.nextLine());  // problema en combinacion de nextInt
                Estudiante estudiante1 = new Estudiante(id);
                var encontado = estudianteDAO.findById(estudiante1);
                if (encontado)
                    System.out.println(estudiante1);
                else
                    System.out.println("No se encontro estudiante");
                break;

            case 4:
                System.out.println("ID de estudiante a eliminar: ");
                var id1 = Integer.parseInt(scanner.nextLine());
                var estudiante3 = new Estudiante(id1);
                var encontrado5 = estudianteDAO.findById(estudiante3);
                if (encontrado5) {
                    estudianteDAO.eliminarEstudiante(estudiante3);
                    System.out.println("Estudiante con ID " + id1 + " se ha eliminado");
                } else {
                    System.out.println("No se encontro estudiante con ID "+ id1);
                }

                break;

            case 5:

                System.out.print("ID de estudiante a modificar: ");
                var id2 = Integer.parseInt(scanner.nextLine());
                Estudiante estudiante2 = new Estudiante(id2);
                var encontrado = estudianteDAO.findById(estudiante2);
                if (encontrado) {
                    System.out.println("Estudiante que se desea modificar " + estudiante2);
                    subMenuUpdate();
                    var opcion1 = Integer.parseInt(scanner.nextLine());
                    switch (opcion1 ) {
                        case 1 :
                            try {
                                System.out.println("Nombre existente " + estudiante2.getNombre());
                                System.out.println("Nombre nuevo: ");
                                var newName = scanner.next();
                                estudiante2.setNombre(newName);
                                estudianteDAO.updateName(estudiante2);

                                System.out.println("Nombre actualizado");
                            } catch (Exception e){
                                System.out.println("No se pudo realizar la operacion " + e.getMessage());
                            }
                            break;


                        case 2:
                            try {
                                System.out.println("Apellido existente: " + estudiante2.getApellido());
                                System.out.println("Ingrese el nuevo Apellido: ");
                                var newApellido = scanner.nextLine();
                                estudiante2.setApellido(newApellido);
                                estudianteDAO.updateApellido(estudiante2);
                                System.out.println("Apellido actualizado");
                            } catch (Exception e){
                                System.out.println("No se pudo actualizar el apellido " + e.getMessage());
                            }

                            break;

                        case 3:
                            try {
                                System.out.println("Numero de telefono existente: " + estudiante2.getTelefono());
                                System.out.println("Ingrese el nuevo telefono: ");
                                var newTelefono = scanner.nextLine();
                                estudiante2.setTelefono(newTelefono);
                                estudianteDAO.updateTelefono(estudiante2);
                                System.out.println("Numero de telefono actualizado");
                            } catch (Exception e){
                                System.out.println("No se pudo actualizar el numero de telefono " + e.getMessage());
                            }



                            break;

                        case 4: try {

                            System.out.println("Correo existente : " + estudiante2.getEmail());
                            System.out.println("Digite el nuevo correo: ");
                            var correo = scanner.nextLine();
                            estudiante2.setEmail(correo);
                            estudianteDAO.updateEmail(estudiante2);
                            System.out.println("Correo actualizado");


                        } catch (Exception e){
                            System.out.println("No se pudo actualizar el email " + e.getMessage());
                        }
                        break;

                        case 5: try{
                            System.out.println("Estudiante existente: " + estudiante2);
                            System.out.println("Ingrese un nuevo nombre: ");
                            var nameCom = scanner.nextLine();
                            System.out.println("Ingrese el nuevo apellido: ");
                            var apellidoCom = scanner.nextLine();
                            System.out.println("Ingrese el nuevo telefono: ");
                            var telefonoCom = scanner.nextLine();
                            System.out.println("Ingrese el nuevo email: ");
                            var emailCom = scanner.nextLine();

                            estudiante2.setNombre(nameCom);
                            estudiante2.setApellido(apellidoCom);
                            estudiante2.setTelefono(telefonoCom);
                            estudiante2.setEmail(emailCom);

                            estudianteDAO.updateEstudiante(estudiante2);

                            System.out.println("Estudiante actualizado");
                        } catch (Exception e){
                            System.out.println("No se pudo actualizar el estudiante " + e.getMessage());
                        }
                        break;


                        case 6:

                            break;

                        default:
                            System.out.println("Opcion no valida");
                            break;

                    }
                } else {
                        System.out.println("No se encontro estudiante con ID " + id2);
                    }





                break;


            case 6:
                System.out.println("Hasta Pronto...");
                salir = true;
                break;
            default:
                System.out.println("Opcion no valida");
                break;

        }
        return salir;
    }


}