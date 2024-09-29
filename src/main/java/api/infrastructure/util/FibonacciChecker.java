package api.infrastructure.util;

import org.springframework.stereotype.Component;

@Component
public class FibonacciChecker {
    private boolean isPerfectSquare(int n) {
        int root = (int) Math.sqrt(n);
        return (root * root == n);
    }

    public boolean isFibonacci(int n) {
        if (n == 0) {
            return true;
        }
        int a = 0, b = 1, c = 1;
        while (c < n) {
            a = b;
            b = c;
            c = a + b;
        }
        return (c == n || isPerfectSquare(5 * n * n + 4) || isPerfectSquare(5 * n * n - 4));
    }
}
