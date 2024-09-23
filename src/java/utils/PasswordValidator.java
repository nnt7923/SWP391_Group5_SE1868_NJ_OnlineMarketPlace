/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordValidator {

    // Định nghĩa regex cho mật khẩu
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    // Phương thức kiểm tra mật khẩu hợp lệ
    public static boolean isValidPassword(String newPassword) {
        if (newPassword == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(newPassword);
        return matcher.matches();
    }
}