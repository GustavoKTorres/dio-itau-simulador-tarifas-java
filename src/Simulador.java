import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Simulador {
    enum Plano implements Tarifa {
        ESSENCIAL {
            public BigDecimal calcular(BigDecimal valor) {
                return new BigDecimal("2.00");
            }
        },
        DIGITAL {
            public BigDecimal calcular(BigDecimal valor) {
                return valor.multiply(new BigDecimal("0.005"));
            }
        }
    }

    interface Tarifa {
        BigDecimal calcular(BigDecimal valor);
    }

    record Cotacao(BigDecimal valor, BigDecimal tarifa, BigDecimal total) {}

    static final class CentralDeCotacoes {
        Cotacao simular(BigDecimal valor, Tarifa regra) {
            if (valor.signum() <= 0 || valor.stripTrailingZeros().scale() > 2) {
                throw new IllegalArgumentException("Informe um valor positivo com até duas casas decimais.");
            }
            BigDecimal principal = valor.setScale(2);
            BigDecimal tarifa = regra.calcular(principal).setScale(2, RoundingMode.HALF_UP);
            return new Cotacao(principal, tarifa, principal.add(tarifa));
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: java -cp out Simulador <ESSENCIAL|DIGITAL> <valor>");
            System.exit(2);
        }

        try {
            Cotacao cotacao = new CentralDeCotacoes()
                    .simular(new BigDecimal(args[1]), Plano.valueOf(args[0]));
            System.out.printf("Valor: R$ %s%nTarifa: R$ %s%nTotal: R$ %s%n",
                    cotacao.valor(), cotacao.tarifa(), cotacao.total());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(2);
        }
    }
}
