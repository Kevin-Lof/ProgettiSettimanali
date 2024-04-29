public abstract class Riproducibile extends Media implements ElementiRiproducibili {

    private int durata;
    private int volume = 5;

    public int getVolume() {
        return volume;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }



    @Override
    public void play() {

    }

    @Override
    public void alzaVolume() {
        if(volume<10){
            volume++;
        } else{
            System.out.println("Volume già al massimo!");
        }
    }

    @Override
    public void abbassaVolume() {
        if(volume>0){
            volume--;
        } else{
            System.out.println("Volume già al minimo!");
        }
    }

}
