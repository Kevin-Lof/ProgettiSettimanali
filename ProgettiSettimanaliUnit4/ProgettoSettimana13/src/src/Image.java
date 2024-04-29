

public class Image extends Media implements ElementiVisualizzabili {

    private int luminosita =5;

    public Image(String titolo){
        this.setTitolo(titolo);
    }


    public int getLuminosita() {
        return luminosita;
    }


    public void show(){

        System.out.print(this.getTitolo() + " - ");
        for (int i = 0; i<this.getLuminosita(); i++){
            System.out.print("*");
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

