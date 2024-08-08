package com.fiap.hackathon;

import java.util.Random;

public class Utils {

        public static String generateCPF() {
            Random random = new Random();
            int[] cpf = new int[9];

            // Gerando os 9 primeiros dígitos do CPF
            for (int i = 0; i < 9; i++) {
                cpf[i] = random.nextInt(10);
            }

            // Calculando o primeiro dígito verificador
            int firstDigit = calculateDigit(cpf, 10);

            // Calculando o segundo dígito verificador
            int secondDigit = calculateDigit(cpf, 11, firstDigit);

            // Montando o CPF completo
            return String.format("%d%d%d%d%d%d%d%d%d%d%d",
                    cpf[0], cpf[1], cpf[2],
                    cpf[3], cpf[4], cpf[5],
                    cpf[6], cpf[7], cpf[8],
                    firstDigit, secondDigit);
        }

        private static int calculateDigit(int[] cpf, int weight) {
            return calculateDigit(cpf, weight, -1);
        }

        private static int calculateDigit(int[] cpf, int weight, int additionalDigit) {
            int sum = 0;
            int digit;

            for (int i = 0; i < cpf.length; i++) {
                sum += cpf[i] * (weight - i);
            }

            if (additionalDigit >= 0) {
                sum += additionalDigit * 2;
            }

            int result = sum % 11;
            digit = (result < 2) ? 0 : 11 - result;

            return digit;
        }

}
