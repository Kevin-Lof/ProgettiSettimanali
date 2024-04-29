public class Audio extends Riproducibile{


    public Audio(String titolo, int durata){
        this.setTitolo(titolo);
        this.setDurata(durata);

    }

    public void play() {

        for (int i=0; i< this.getDurata(); i++){

            System.out.print(this.getTitolo() + " - ");
            for (int j=0; j<this.getVolume(); j++){
                System.out.print("!");
            }
            System.out.println(".");
        }

    }

}
