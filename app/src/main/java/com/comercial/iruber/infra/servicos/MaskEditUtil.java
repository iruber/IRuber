package com.comercial.iruber.infra.servicos;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class MaskEditUtil {
    public static final String FORMAT_CPF = "###.###.###-##";
    public static final String FORMAT_CNPJ = "##.###.###.####-##";
    public static final String FORMAT_NASCIMENTO = "##-##-####";
    public static final String FORMAT_FONE = "(##)####-#####";
    public static final String FORMAT_CEP = "#####-###";

    private MaskEditUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static TextWatcher mask(final EditText ediTxt, final String mask) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void afterTextChanged(final Editable s) {
                //nada há implementar aqui.
            }

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
                //nada há implementar aqui.
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                final String str = MaskEditUtil.unmask(s.toString());
                StringBuilder mascara = new StringBuilder();
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (final char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara.append(m);
                        continue;
                    }
                    try {
                        mascara.append(str.charAt(i));
                    } catch (final Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
        };
    }

    public static String unmask(final String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]", "").replaceAll("[:]", "").replaceAll("[)]", "");
    }
}
