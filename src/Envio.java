public class Envio {

     String id;
     String origen;
     String destino;
     double peso;

        public Envio(String id, String origen, String destino, double peso) {
            this.id = id;
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrigen() {
            return origen;
        }

        public void setOrigen(String origen) {
            this.origen = origen;
        }

        public String getDestino() {
            return destino;
        }

        public void setDestino(String destino) {
            this.destino = destino;
        }

        public double getPeso() {
            return peso;
        }

        @Override
        public String toString() {
            return "Envio [id=" + id + ", origen=" + origen + ", destino=" + destino + ", peso=" + peso + "]";
        }

        public void setPeso(double peso) {
            this.peso = peso;
        }

}
