public class Chave {
    private String rfid;
    private Condutor condutor;
    
    public Chave(String rfid) {
        this.rfid = rfid;
    }
    
    public String getRfid() {
        return rfid;
    }
    
    public Condutor getCondutor() {
        return condutor;
    }
    
    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }
}
