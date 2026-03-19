package com.orchestration.instantcard.utils;

import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.utils.constants.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class InjectionValidator {

    private InjectionValidator() {
        throw new IllegalStateException(CommonConstants.ILLEGAL_STATE_EXCEPTION_MSG);
    }

    private static final Logger logger = LoggerFactory.getLogger(InjectionValidator.class);

    private static final List<Pattern> INJECTION_PATTERNS = Arrays.asList(
            // Comentarios SQL
            Pattern.compile("(?i)--[^\\r\\n]*"),              // Comentario de una línea
            Pattern.compile("(?i);"),                         // Finalización de instrucción
            Pattern.compile("(?i)/\\*"),                      // Inicio de comentario de bloque
            Pattern.compile("(?i)\\*/"),                      // Fin de comentario de bloque

            // Procedimientos y palabras clave peligrosas
            Pattern.compile("(?i)xp_"),                       // Procedimientos extendidos en SQL Server
            Pattern.compile("(?i)sp_"),                       // Procedimientos almacenados (SQL Server)

            // Palabras clave SQL de modificación de datos
            Pattern.compile("(?i)\\b(drop|insert|select|delete|update|exec|union|alter|create|truncate|grant|revoke|declare|show|load_file|outfile|information_schema)\\b"),

            // Expresiones booleanas comunes en inyecciones SQL
            Pattern.compile("(?i)\\b(or|and)\\b\\s*\\d=\\d"),             // Expresiones tipo "OR 1=1" o "AND 1=1"
            Pattern.compile("(?i)\\b(or|and)\\b\\s*'[^']*'='[^']*'"),     // Expresiones tipo "OR 'a'='a'"

            // Encadenación de consultas (típica en ataques de SQL injection)
            Pattern.compile("(?i)';\\s*(exec|select|insert|update|delete|alter|create|drop|truncate)\\s"),
            Pattern.compile("(?i)';\\s*\\b(exec|select|insert|delete|update|drop)\\b"),  // Encadenación con comillas y comandos

            // Expresiones hexadecimales y codificación común
            Pattern.compile("(?i)0x[0-9a-f]+"),                      // Valores hexadecimales
            Pattern.compile("(?i)\\b(select|union)\\b.*?\\b(select|union)\\b"),  // Subconsultas UNION SELECT
            Pattern.compile("(?i)\\b(select|exists)\\b.*?\\b(select|from)\\b"),  // Subconsultas SELECT/EXISTS
            Pattern.compile("(?i)\\bcase\\b.*?\\bwhen\\b"),              // Expresiones CASE WHEN

            // Comillas escapadas o dobles
            Pattern.compile("(?i)(\\\\'|\\\\\")"),                       // Comillas escapadas

            // Etiquetas HTML peligrosas y variantes de <script>
            Pattern.compile("(?i)<\\s*script\\b[^>]*>(.*?)<\\s*/\\s*script\\s*>"),  // Etiquetas <script> completas
            Pattern.compile("(?i)<\\s*script\\b[^>]*>"),                           // Etiquetas <script> abiertas

            // Atributos de eventos HTML comunes en ataques XSS
            Pattern.compile("(?i)onerror\\s*="),                         // Evento onerror
            Pattern.compile("(?i)onclick\\s*="),                         // Evento onclick
            Pattern.compile("(?i)onload\\s*="),                          // Evento onload
            Pattern.compile("(?i)onmouseover\\s*="),                     // Evento onmouseover

            // Funciones JavaScript peligrosas
            Pattern.compile("(?i)alert\\s*\\("),                         // Función alert
            Pattern.compile("(?i)prompt\\s*\\("),                        // Función prompt
            Pattern.compile("(?i)eval\\s*\\("),                          // Función eval
            Pattern.compile("(?i)setTimeout\\s*\\("),                    // Función setTimeout
            Pattern.compile("(?i)Function\\s*\\("),                      // Constructor Function
            Pattern.compile("(?i)window\\."),                            // Objeto window
            Pattern.compile("(?i)document\\."),                          // Objeto document
            Pattern.compile("(?i)location\\."),                          // Objeto location
            Pattern.compile("(?i)cookie\\s*="),                          // Acceso a cookies

            // JavaScript URI en src o href
            Pattern.compile("(?i)(src|href)\\s*=\\s*['\"]?javascript:"), // URIs de JavaScript en href o src

            // Codificación hexadecimal, decimal y unicode en URIs o scripts (evitan detección directa)
            Pattern.compile("(?i)\\\\x[0-9a-f]{2}"),                   // Secuencia de escape hexadecimal en JavaScript

            // Palabras clave y expresiones avanzadas en ataques XSS
            Pattern.compile("(?i)javascript\\s*:"),                       // URIs de JavaScript
            Pattern.compile("(?i)vbscript\\s*:"),                         // URIs de VBScript
            Pattern.compile("(?i)data\\s*:[^\\s]+\\s*;base64"),           // URIs de data base64

            // Caracteres de escapado avanzados que podrían evadir detección
            Pattern.compile("(?i)(\\\\\"|\\\\')")                         // Comillas escapadas
    );

    public static void validateStringsForInjection(Object obj, boolean fieldAccess) {

        ArrayList<ErrorModel> errors = new ArrayList<>();

        if (obj == null)
            return;

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(fieldAccess);
            try {
                Object value = field.get(obj);
                if (value instanceof String stringValue && validateAgainstSqlInjection(stringValue)) {
                    ErrorModel error = new ErrorModel();
                    error.setCode(InstantCardEnumError.HTTP_STATUS_BAD_REQUEST_INYECTION.getCode());
                    error.setTitle(InstantCardEnumError.HTTP_STATUS_BAD_REQUEST_INYECTION.getTitle());
                    error.setDetail(String.format(InstantCardEnumError.HTTP_STATUS_BAD_REQUEST_INYECTION.getMessage(), field.getName()));
                    errors.add(error);
                }
            } catch (IllegalAccessException e) {
                logger.error(String.format(CommonConstants.FIELD_NOT_FOUND, field.getName(), e));
            }
        }

        if(!errors.isEmpty())
            throw new BusinessException(InstantCardEnumError.HTTP_STATUS_BAD_REQUEST_INYECTION.getCode(), errors);
    }

    private static boolean validateAgainstSqlInjection(String input) {
        for (Pattern pattern : INJECTION_PATTERNS) {
            if (pattern.matcher(input).find()) {
                return true;
            }
        }
        return false;
    }

}
