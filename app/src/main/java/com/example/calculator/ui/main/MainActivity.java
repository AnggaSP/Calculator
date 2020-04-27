package com.example.calculator.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.calculator.databinding.ActivityMainBinding;
import com.example.calculator.utils.Calculator;
import com.example.calculator.utils.Helper;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Compat for Lollipop status bar
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        /* See https://developer.android.com/topic/libraries/view-binding
         * https://developer.android.com/studio/releases#3-6-0
         */
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        final View view = binding.getRoot();
        setContentView(view);

        // For specifying the maximum dot (lambda var need effectively final declaration)
        final int[] dotInText = {0};

        /*
         * Number button handling
         */
        binding.buttonZero.setOnClickListener(v -> {
            if (binding.textResult.getText().toString().contains("N/A")) {
                binding.textResult.setText("");
                binding.textOperations.setText("");
                binding.textResult.append("0");
                dotInText[0] = 0;
                buttonState(binding, true);
            } else {
                binding.textResult.append("0");
            }
        });

        binding.buttonOne.setOnClickListener(v -> {
            if (binding.textResult.getText().toString().contains("N/A")) {
                binding.textResult.setText("");
                binding.textOperations.setText("");
                binding.textResult.append("1");
                dotInText[0] = 0;
                buttonState(binding, true);
            } else {
                binding.textResult.append("1");
            }
        });

        binding.buttonTwo.setOnClickListener(v -> {
            if (binding.textResult.getText().toString().contains("N/A")) {
                binding.textResult.setText("");
                binding.textOperations.setText("");
                binding.textResult.append("2");
                dotInText[0] = 0;
                buttonState(binding, true);
            } else {
                binding.textResult.append("2");
            }
        });

        binding.buttonThree.setOnClickListener(v -> {
            if (binding.textResult.getText().toString().contains("N/A")) {
                binding.textResult.setText("");
                binding.textOperations.setText("");
                binding.textResult.append("3");
                dotInText[0] = 0;
                buttonState(binding, true);
            } else {
                binding.textResult.append("3");
            }
        });

        binding.buttonFour.setOnClickListener(v -> {
            if (binding.textResult.getText().toString().contains("N/A")) {
                binding.textResult.setText("");
                binding.textOperations.setText("");
                binding.textResult.append("4");
                dotInText[0] = 0;
                buttonState(binding, true);
            } else {
                binding.textResult.append("4");
            }
        });

        binding.buttonFive.setOnClickListener(v -> {
            if (binding.textResult.getText().toString().contains("N/A")) {
                binding.textResult.setText("");
                binding.textOperations.setText("");
                binding.textResult.append("5");
                dotInText[0] = 0;
                buttonState(binding, true);
            } else {
                binding.textResult.append("5");
            }
        });

        binding.buttonSix.setOnClickListener(v -> {
            if (binding.textResult.getText().toString().contains("N/A")) {
                binding.textResult.setText("");
                binding.textOperations.setText("");
                binding.textResult.append("6");
                dotInText[0] = 0;
                buttonState(binding, true);
            } else {
                binding.textResult.append("6");
            }
        });

        binding.buttonSeven.setOnClickListener(v -> {
            if (binding.textResult.getText().toString().contains("N/A")) {
                binding.textResult.setText("");
                binding.textOperations.setText("");
                binding.textResult.append("7");
                dotInText[0] = 0;
                buttonState(binding, true);
            } else {
                binding.textResult.append("7");
            }
        });

        binding.buttonEight.setOnClickListener(v -> {
            if (binding.textResult.getText().toString().contains("N/A")) {
                binding.textResult.setText("");
                binding.textOperations.setText("");
                binding.textResult.append("8");
                dotInText[0] = 0;
                buttonState(binding, true);
            } else {
                binding.textResult.append("8");
            }
        });

        binding.buttonNine.setOnClickListener(v -> {
            if (binding.textResult.getText().toString().contains("N/A")) {
                binding.textResult.setText("");
                binding.textOperations.setText("");
                binding.textResult.append("9");
                dotInText[0] = 0;
                buttonState(binding, true);
            } else {
                binding.textResult.append("9");
            }
        });

        /*
         * Logic handling
         */
        binding.buttonPlusMinus.setOnClickListener(v -> {
            if (containsNegativeInOperator(binding)) {
                int length = binding.textResult.getText().length();
                binding.textResult.getText().delete(length - countTillOperator(binding),
                        length - countTillOperator(binding) + 1);
                binding.textResult.setSelection(binding.textResult.getText().length());
            } else if (binding.textResult.getText().length() != 0 && !endWithOperator(binding)) {
                int length = binding.textResult.getText().length();
                String textResult = binding.textResult.getText().toString();
                textResult = (textResult.substring(0, length - countTillOperator(binding))
                        + "-"
                        + textResult.substring(length - countTillOperator(binding)));
                binding.textResult.setText(textResult);
                binding.textResult.setSelection(binding.textResult.getText().length());
            }
        });

        binding.buttonDot.setOnClickListener(v -> {
            if (binding.textResult.getText().length() != 0 && dotInText[0] < countMaxDot(binding)) {
                if (!endWithOperator(binding)) {
                    binding.textResult.append(".");
                    dotInText[0]++;
                } else {
                    binding.textResult.append("0.");
                    dotInText[0]++;
                }
            }
        });

        binding.buttonBackspace.setOnClickListener(v -> {
            if (binding.textResult.getText().toString().contains("N/A")) {
                binding.textResult.setText("");
                binding.textOperations.setText("");
                dotInText[0] = 0;
                buttonState(binding, true);
            } else if (binding.textResult.getText().length() != 0) {
                int length = binding.textResult.getText().length();
                String currentlyDeleting = binding.textResult.getText()
                        .toString().substring(length - 1, length);
                binding.textResult.getText().delete(length - 1, length);
                if (currentlyDeleting.equals(".")) {
                    dotInText[0]--;
                }
            }
        });

        binding.buttonClear.setOnClickListener(v -> {
            binding.textResult.setText("");
            binding.textOperations.setText("");
            dotInText[0] = 0;
            buttonState(binding, true);
        });

        binding.buttonClearEntry.setOnClickListener(v -> {
            if (isOperable(binding) && !endWithOperator(binding)) {
                int length = binding.textResult.getText().length();
                binding.textResult.getText()
                        .delete(length - countTillOperator(binding), length);
                dotInText[0]--;
            } else if (!endWithOperator(binding) && binding.textResult.getText().length() != 0) {
                binding.textResult.setText("");
                binding.textOperations.setText("");
                dotInText[0] = 0;
                if (binding.textResult.getText().toString().contains("N/A")) {
                    buttonState(binding, true);
                }
            }
        });

        binding.buttonDivide.setOnClickListener(v -> {
            if (!endWithOperator(binding)
                    && !endWithDot(binding)
                    && binding.textResult.getText().length() != 0) {
                binding.textResult.append("÷");
            } else if (endWithOperator(binding) || endWithDot(binding)) {
                int length = binding.textResult.getText().length();
                String currentlyDeleting = binding.textResult.getText()
                        .toString().substring(length - 1, length);
                binding.textResult.getText().delete(length - 1, length);
                binding.textResult.append("÷");
                if (currentlyDeleting.equals(".")) {
                    dotInText[0]--;
                }
            }
        });

        binding.buttonMultiply.setOnClickListener(v -> {
            if (!endWithOperator(binding)
                    && !endWithDot(binding)
                    && binding.textResult.getText().length() != 0) {
                binding.textResult.append("x");
            } else if (endWithOperator(binding) || endWithDot(binding)) {
                int length = binding.textResult.getText().length();
                String currentlyDeleting = binding.textResult.getText()
                        .toString().substring(length - 1, length);
                binding.textResult.getText().delete(length - 1, length);
                binding.textResult.append("x");
                if (currentlyDeleting.equals(".")) {
                    dotInText[0]--;
                }
            }
        });

        binding.buttonSubtract.setOnClickListener(v -> {
            if (!endWithOperator(binding)
                    && !endWithDot(binding)
                    && binding.textResult.getText().length() != 0) {
                binding.textResult.append("–");
            } else if (endWithOperator(binding) || endWithDot(binding)) {
                int length = binding.textResult.getText().length();
                String currentlyDeleting = binding.textResult.getText()
                        .toString().substring(length - 1, length);
                binding.textResult.getText().delete(length - 1, length);
                binding.textResult.append("–");
                if (currentlyDeleting.equals(".")) {
                    dotInText[0]--;
                }
            }
        });

        binding.buttonAdd.setOnClickListener(v -> {
            if (!endWithOperator(binding)
                    && !endWithDot(binding)
                    && binding.textResult.getText().length() != 0) {
                binding.textResult.append("+");
            } else if (endWithOperator(binding) || endWithDot(binding)) {
                int length = binding.textResult.getText().length();
                String currentlyDeleting = binding.textResult.getText()
                        .toString().substring(length - 1, length);
                binding.textResult.getText().delete(length - 1, length);
                binding.textResult.append("+");
                if (currentlyDeleting.equals(".")) {
                    dotInText[0]--;
                }
            }
        });

        binding.buttonEqual.setOnClickListener(v -> {
            if (endWithDot(binding)) {
                int length = binding.textResult.getText().length();
                binding.textResult.getText().delete(length - 1, length);
                if (binding.textResult.getText().toString().contains(".")) {
                    dotInText[0] = 1;
                } else {
                    dotInText[0] = 0;
                }
            }

            if (isOperable(binding) && !endWithOperator(binding)) {
                binding.textOperations.setText(binding.textResult.getText().toString());
                try {
                    String result = Calculator.calculate(binding.textResult.getText().toString());
                    binding.textResult.setText(result);
                    binding.textResult.setSelection(binding.textResult.getText().length());
                    if (binding.textResult.getText().toString().contains(".")) {
                        dotInText[0] = 1;
                    } else {
                        dotInText[0] = 0;
                    }
                } catch (ArithmeticException e) {
                    binding.textResult.setText("N/A");
                    binding.textResult.setSelection(binding.textResult.getText().length());
                    if (binding.textResult.getText().toString().contains(".")) {
                        dotInText[0] = 1;
                    } else {
                        dotInText[0] = 0;
                    }
                }
            }
        });

        // Disable keyboard invoke as we use button for input
        binding.textResult.requestFocus();
        binding.textResult.setOnClickListener(v -> {});
        binding.textResult.setOnFocusChangeListener((v, hasFocus) -> {});
        binding.textResult.setOnTouchListener((v, event) -> true);

        // N/A Handling
        binding.textResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().contains("N/A")) {
                    buttonState(binding, false);
                }
            }
        });
    }

    /*
     * Class specific helper method
     * As far as I know, there's no way to make view binding generic
     * If this got extended, we may need to use data binding?
     * Do tell me if there's a way to make this generic with view binding
     * Also we can make it much more slimmer by delegating common operation
     * to helper but I decided to only delegate the long operation for now
     */
    private void buttonState(ActivityMainBinding binding, boolean enable) {
        binding.buttonPlusMinus.setEnabled(enable);
        binding.buttonDot.setEnabled(enable);
        binding.buttonEqual.setEnabled(enable);
        binding.buttonDivide.setEnabled(enable);
        binding.buttonMultiply.setEnabled(enable);
        binding.buttonSubtract.setEnabled(enable);
        binding.buttonAdd.setEnabled(enable);
    }

    private int countMaxDot(ActivityMainBinding binding) {
        boolean hasDot = false;
        int max = 1;
        for (int i = 0; i < binding.textResult.getText().length(); i++) {
            char resultSubString = binding.textResult.getText().toString().charAt(i);
            if (resultSubString == '.') {
                hasDot = true;
            }

            if (Helper.isOperator(resultSubString) && hasDot) {
                hasDot = false;
                max++;
            }
        }
        return max;
    }

    private boolean containsNegativeInOperator(ActivityMainBinding binding) {
        for (int i = binding.textResult.getText().length() - 1; i >= 0; i--) {
            char resultSubString = binding.textResult.getText().toString().charAt(i);
            if (resultSubString == '-') {
                return true;
            } else if (Helper.isOperator(resultSubString)) {
                return false;
            }
        }
        return false;
    }

    private int countTillOperator(ActivityMainBinding binding) {
        int count = 0;
        for (int i = binding.textResult.getText().length() - 1; i >= 0; i--) {
            char resultSubString = binding.textResult.getText().toString().charAt(i);
            count++;
            if (Helper.isOperator(resultSubString)) {
                return count - 1;
            }
        }
        return count;
    }

    private boolean endWithDot(ActivityMainBinding binding) {
        return binding.textResult.getText().toString().endsWith(".");
    }

    private boolean endWithOperator(ActivityMainBinding binding) {
        return binding.textResult.getText().toString().endsWith("÷")
                || binding.textResult.getText().toString().endsWith("x")
                || binding.textResult.getText().toString().endsWith("–")
                || binding.textResult.getText().toString().endsWith("+");
    }

    private boolean isOperable(ActivityMainBinding binding) {
        for (int i = 0; i < binding.textResult.getText().length(); i++) {
            char resultSubString = binding.textResult.getText().toString().charAt(i);
            if (Helper.isOperator(resultSubString)) {
                return true;
            }
        }
        return false;
    }
}
