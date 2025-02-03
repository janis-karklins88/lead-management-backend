
package Salesforelikeapp.Salesforelikeapp.utils;


public class Validations {
    
    public static boolean validateRequiredField(String field, String fieldName) {
    if (field == null || field.isEmpty()) {
        System.out.println(fieldName + " cannot be empty.");
        return false;
    }
    return true;
}

    public static boolean validateEmail(String email) {
    if (email != null && !email.isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
        System.out.println("Invalid email format.");
        return false;
    }
    return true;
}

    public static boolean validatePhone(String phone) {
    if (phone != null && !phone.isEmpty() && !phone.matches("\\d+")) {
        System.out.println("Phone number must contain only digits if provided.");
        return false;
    }
    return true;
}
    public static boolean validatePasswordStrength(String password) {
    if (password.length() < 8 || 
        !password.matches(".*[A-Za-z].*") || 
        !password.matches(".*\\d.*")) {
        System.out.println("Password must be at least 8 characters long and include both letters and numbers.");
        return false;
    }
    return true;
}
    public static boolean validateFieldLength(String field, String fieldName, int maxLength) {
    if (field != null && field.length() > maxLength) {
        System.out.println(fieldName + " cannot exceed " + maxLength + " characters.");
        return false;
    }
    return true;
}

}
