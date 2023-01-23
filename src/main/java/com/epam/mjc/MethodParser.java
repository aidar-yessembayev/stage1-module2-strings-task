package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] arrOfStr = signatureString.split("\\(");

        // Divide first part
        String[] fPart = arrOfStr[0].split(" ");

        String accessMod = null;
        String returnType = "";
        String methodName = "";

        if (fPart.length > 2) {
            accessMod = fPart[0];
            returnType = fPart[1];
            methodName = fPart[2];

        } else {
            returnType = fPart[0];
            methodName = fPart[1];
        }

        // Divide second part
        String lastPart = arrOfStr[1].substring(0, arrOfStr[1].length() - 1);

        List<MethodSignature.Argument> arguments = new ArrayList<>();

        if (lastPart.length() > 0) {
            String[] sPart = lastPart.split(", ");


            for (int i = 0; i < sPart.length; i++) {
                String[] args = sPart[i].split(" ");
                MethodSignature.Argument arg = new MethodSignature.Argument(args[0], args[1]);
                arguments.add(arg);
            }
        }

        MethodSignature result = new MethodSignature(methodName, arguments);
        result.setAccessModifier(accessMod);
        result.setReturnType(returnType);

        return result;
    }
}
