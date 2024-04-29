

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Riproducibile[] lista = new Riproducibile[5];
        ElementiVisualizzabili[] lista2 = new ElementiVisualizzabili[5];

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.println((i+1) + "/5 - Scegli il tipo (1.Audio, 2.Video, 3.Immagine): ");
            int sceltaTipo = scanner.nextInt();
            scanner.nextLine();

            switch (sceltaTipo) {
                case 1:

                    System.out.println("Inserisci il titolo");
                    String titoloAudio = scanner.nextLine();
                    System.out.println("Inserisci la durata");
                    int durataAudio = scanner.nextInt();
                    Riproducibile Audio = new Audio(titoloAudio, durataAudio);
                    lista[i] = Audio;
                    break;

                case 2:

                    System.out.println("Inserisci il titolo");
                    String titoloVideo = scanner.nextLine();
                    System.out.println("Inserisci la durata");
                    int durataVideo = scanner.nextInt();
                   Riproducibile Video = new Videos(titoloVideo, durataVideo);
                    lista[i] = Video;
                    break;

                case 3:

                    System.out.println("Inserisci il titolo");
                    String titoloImmagine = scanner.nextLine();
                    ElementiVisualizzabili Immagine = new Image(titoloImmagine);
                    lista2[i] = Immagine;
                    break;
                default:
                    System.out.println("Devi riprovare!");
                    i--;
                    break;

            }
        }

        int riproduzione = 0;

        do {
            System.out.println("Digita un numero compreso tra 1 e 5 per effettuare la tua scelta, se digiti 0 chiudi l'applicazione.");
            riproduzione = scanner.nextInt();
            scanner.nextLine();
            if (riproduzione > 5) {
                System.out.println("Digita un numero tra 1 e 5 oppure digita 0 per chiudere.");
                riproduzione = scanner.nextInt();
                scanner.nextLine();
            }
            if (riproduzione != 0 && riproduzione<6) {
                lista[riproduzione - 1].play();
            }
        }
        while (riproduzione > 0 && riproduzione <= 5);
    }
    }
