public class Adicao1 {
    
        public Adicao1(){

        }
        public int add(int a, int b){
            int soma = a + b;
            return soma;
        }

        public float add(float c, float d){
            float soma = c + d;
            return soma;
        }

        public String exibe(int a, int b, int soma){
            return "A soma de " + a + "+" + b + "=" + soma;
           
        }

        public String exibe(float a, float b, float soma){
            return "A soma de " + a + "+" + b + "=" + soma;
        }
    }
