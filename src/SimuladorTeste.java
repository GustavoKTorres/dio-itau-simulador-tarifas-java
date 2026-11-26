import java.math.BigDecimal;

final class SimuladorTeste {
    public static void main(String[] args) {
        var central = new Simulador.CentralDeCotacoes();
        verificar("102.00", central.simular(new BigDecimal("100"), Simulador.Plano.ESSENCIAL).total());
        verificar("100.50", central.simular(new BigDecimal("100"), Simulador.Plano.DIGITAL).total());
        verificar("1.01", central.simular(new BigDecimal("1"), Simulador.Plano.DIGITAL).total());
        verificar("50.00", central.simular(new BigDecimal("50"), valor -> BigDecimal.ZERO).total());

        for (String invalido : new String[]{"0", "-1", "1.001"}) {
            try {
                central.simular(new BigDecimal(invalido), Simulador.Plano.DIGITAL);
                throw new AssertionError("O valor deveria ser rejeitado: " + invalido);
            } catch (IllegalArgumentException esperado) {
                // Valor rejeitado conforme o contrato.
            }
        }
        System.out.println("Sete verificações passaram.");
    }

    private static void verificar(String esperado, BigDecimal atual) {
        if (!atual.equals(new BigDecimal(esperado))) {
            throw new AssertionError("Esperado " + esperado + "; recebido " + atual);
        }
    }
}
