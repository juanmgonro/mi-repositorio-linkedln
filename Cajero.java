package mx.generation;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Cajero {

	static int opcionNoDisponible(int error) {
		error++;
		if (error == 2)
			System.out.println("Último intento, vuelve a intentarlo");
		if (error < 2)
			System.out.println("Opcion incorrecta, elije una opcion mostrada");
		System.out.println();
		return error;
	}

	public static void main(String[] args) {
		ArrayList<String> movimientos = new ArrayList<String>();
		movimientos.add("No hay movimientos aun");
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		int errorOpcion = 0;
		double dinero = 10000;
		while (opcion != 9 && errorOpcion < 3) {
			System.out.println();
			System.out.println("**************************");
			System.out.println("*** Cajero automatico ***");
			System.out.println("**************************");
			System.out.println("Introduce el numero de la opcion que deseas elegir: ");
			System.out.println("Opción 1: Retirar dinero");
			System.out.println("Opcion 2: Hacer depositos");
			System.out.println("Opción 3: Estado de cuenta");
			System.out.println("Opción 4: Quejas");
			System.out.println("Opción 5: Último movimiento");
			System.out.println("Opción 7: Hablar con un asesor");
			System.out.println("Opción 9: Salir del cajero");
			if (sc.hasNextInt()) {
				opcion = sc.nextInt();
				switch (opcion) {
				case 1:
					System.out.println("Puedes retirar menos de $6000");
					System.out.println("¿Cuanto deseas retirar?");
					double retiroDinero = sc.nextDouble();
					if (retiroDinero >= 6000) {
						System.out.println("No se puede retirar una cantidad igual o mayor a $6,000");
					} else if (retiroDinero >= dinero) {
						System.out.println("Dinero no disponible");
					} else if (retiroDinero % 50 == 0 && retiroDinero > 0) {
						dinero -= retiroDinero;
						movimientos.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())
								+ " - Retiro de $" + retiroDinero);
						System.out.println("Retiro de $" + retiroDinero);
					} else {
						System.out.println("Solo se hacen retiros de multiplos de $50");
					}
					System.out.println("Saliendo al menu principal");
					break;
				case 2:
					System.out.println("Opción 1: Cuenta de cheques ");
					System.out.println("Opcion 2: Tarjeta de crédito");
					System.out.println("Opción 3: Cuenta de terceros");
					int opcionDos = 0;
					
					opcionDos = sc.nextInt();
					switch (opcionDos) {
					case 1:
						System.out.println("¿Cuanto deseas depositar?");
						double deposito = sc.nextDouble();
						if (deposito % 50 == 0) {
							dinero += deposito;
							movimientos.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())
									+ " - Deposito a cuenta: " + deposito);
							System.out.println("Deposito de $" + deposito);
						} else {
							System.out.println("Debes hacen depositos a cuenta en multiplos de $50 ");
						}
						break;
					case 2:
						System.out.println("¿Cuanto deseas depositar a tu tarjeta de credito?");
						double depositoTarjeta = sc.nextDouble();
						if (depositoTarjeta > 0 && depositoTarjeta < dinero) {
							
							dinero -= depositoTarjeta;
							movimientos.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())
									+ " - Deposito a tarjeta de credito: " + depositoTarjeta);
							System.out.printf("Depositaste %.2f a tu tarjeta de credito %n", depositoTarjeta);
						} else {
							System.out.println("No puedes depositar esta cantidad de dinero");
						}
						break;
					case 3:
						System.out.println("¿Cuanto deseas depositar?");
						double depositoTerceros = sc.nextDouble();
						if (depositoTerceros > 0 && depositoTerceros < dinero) {
							
							dinero -= depositoTerceros;
							movimientos.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())
									+ " - Deposito a terceros: " + depositoTerceros);
							System.out.printf("Depositaste %.2f a Fulanito %n", depositoTerceros);
						} else {
							System.out.println("No puedes depositar esta cantidad de dinero");
						}
						break;
					default:
						System.out.println("Esta opcion no esta disponible");
						
						break;
					}
					System.out.println("Saliendo al menu principal");
					break;
				case 3:
					System.out.printf("Tu saldo es: %.2f", dinero);
					System.out.println();
					break;
				case 4:
					System.out.println("Conectando...");
					opcion = 9;
					break;
				case 5:
					System.out.println(movimientos.get(movimientos.size() - 1));
					break;
				case 7:
					System.out.println("Este no es horario de atencion");
					opcion=9;
					break;
				case 9:
					opcion=9;
					break;
				default:
					errorOpcion = opcionNoDisponible(errorOpcion);
				}
			} else {
				sc.nextLine();
				errorOpcion = opcionNoDisponible(errorOpcion);
			}
		}
		System.out.println("**Saliendo del cajero**");
		sc.close();
	}

}
