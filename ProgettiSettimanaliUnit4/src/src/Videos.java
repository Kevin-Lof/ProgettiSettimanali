
public class Videos extends Riproducibile implements ElementiVisualizzabili {

    private int luminosita = 5;


    public Videos(String titolo, int durata){

        this.setTitolo(titolo);
        this.setDurata(durata);

    }


    public int getLuminosita() {
        return luminosita;
    }


    @Override
    public void play() {

        for (int i=0; i< this.getDurata(); i++){

            System.out.print(this.getTitolo() + " - ");
            for (int j=0; j<this.getVolume(); j++){
                System.out.print("!");
            }
            System.out.print(" - ");
            for (int k=0; k<this.getLuminosita(); k++){
                System.out.print("*");
            }
            System.out.println(".");
        }

    }

    @Override
    public void alzaLuminosita() {
        if(luminosita<10){
            luminosita++;
        } else{
            System.out.println("Luminosità già al massimo!");
        }
    }

    @Override
    public void abbassaLuminosita() {
        if(luminosita>0){
            luminosita--;
        } else{
            System.out.println("Luminosità già al minimo!");
        }
    }


}
