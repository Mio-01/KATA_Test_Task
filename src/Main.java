import java.util.Scanner;

class Calc {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение (арабские или римские числа): ");
        String expression = scanner.nextLine();
        try {
            String result = parse(expression);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Выполняет анализ и расчет выражения.
     *
     * @param expression Входное выражение для анализа и вычисления.
     * @return Результат выражения.
     * @throws Exception Если выражение содержит ошибку или неподдерживаемую операцию.
     */
    public static String parse(String expression) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть два операнда");
        oper = detectOperation(expression);
        if (oper == null) throw new Exception("Неподдерживаемая математическая операция");

        // Если оба числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            // Конвертируем оба числа в арабские для вычисления
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        // Если оба числа арабские
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        // Если одно число римское, а другое - арабское
        else {
            throw new Exception("Числа должны быть в одном формате");
        }

        // Проверка диапазона чисел
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        // Выполнение математической операции
        int arabian = calc(num1, num2, oper);

        // Если числа римские, конвертируем результат в римскую систему
        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    /**
     * Определяет операцию в выражении.
     *
     * @param expression Выражение для анализа операции.
     * @return Строковое представление операции (+, -, *, /).
     */
    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    /**
     * Выполняет математическую операцию между двумя числами.
     *
     * @param a    Первое число.
     * @param b    Второе число.
     * @param oper Операция (+, -, *, /).
     * @return Результат операции.
     */
    static int calc(int a, int b, String oper) {
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }
}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};

    /**
     * Проверяет, является ли переданное значение римским числом.
     *
     * @param val Значение для проверки.
     * @return true, если значение является римским числом, иначе false.
     */
    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Конвертирует римское число в арабское.
     *
     * @param roman Римское число.
     * @return Арабское представление числа.
     */
    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Конвертирует арабское число в римское.
     *
     * @param arabian Арабское число.
     * @return Римское представление числа.
     */
    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }
}
