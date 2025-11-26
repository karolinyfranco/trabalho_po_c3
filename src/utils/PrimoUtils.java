package utils;

public class PrimoUtils {
    // Retorna o próximo número primo maior ou igual a uma estimativa baseada em 'n'
    public int proximoPrimo(int n) {
        // Usa um tamanho um pouco maior que o número de elementos (fator de carga ~0.75)
        int num = (int) (n * 1.3);

        while (!ehPrimo(num)) {
            num++;
        }
        return num;
    }

    public boolean ehPrimo(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
