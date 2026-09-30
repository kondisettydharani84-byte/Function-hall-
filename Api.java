package com.hallbooking.util;

import com.google.gson.Gson;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Map;

/** Small helpers shared by all servlets. */
public class Api {
    private static final Gson GSON = new Gson();

    public static void json(HttpServletResponse r, int code, Object o) throws IOException {
        r.setStatus(code);
        r.setContentType("application/json");
        r.setCharacterEncoding("UTF-8");
        r.getWriter().write(GSON.toJson(o));
    }

    public static void err(HttpServletResponse r, int code, String msg) throws IOException {
        json(r, code, Map.of("error", msg));
    }

    public static Integer uid(HttpServletRequest q) {
        HttpSession s = q.getSession(false);
        return s == null ? null : (Integer) s.getAttribute("uid");
    }

    public static String hash(String salt, String pw) {
        try {
            byte[] b = MessageDigest.getInstance("SHA-256").digest((salt + pw).getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte x : b) sb.append(String.format("%02x", x));
            return sb.toString();
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public static boolean blank(String s) { return s == null || s.isBlank(); }
}
