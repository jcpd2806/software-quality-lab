package mx.edu.cetys.software_quality_lab.commons;

// response generic wrapper to include standarized info in all our APIs
public record ApiResponse<T>(String info, T response, String err){}