/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.entities.vo.siigo;

/**
 * Representa las cuentas bancarias utilizadas en Siigo para identificar
 * diferentes medios de recaudo y pagos.
 * <p>
 * Cada constante almacena el código contable asociado a una cuenta bancaria
 * específica configurada en el sistema Siigo.
 * </p>
 *
 * <h3>Constantes definidas:</h3>
 * <ul>
 *   <li>{@link #BANK_ACCOUNT_BANCO_DE_BOGOTA} → Cuenta Banco de Bogotá</li>
 *   <li>{@link #BANK_ACCOUNT_DAVIVIENDA} → Cuenta Davivienda</li>
 *   <li>{@link #BANK_ACCOUNT_CAJA} → Caja principal</li>
 *   <li>{@link #BANK_ACCOUNT_ADDI} → Cuenta Addi</li>
 *   <li>{@link #BANK_ACCOUNT_PAYU} → Cuenta PayU</li>
 *   <li>{@link #BANK_ACCOUNT_SUFI} → Cuenta Sufi</li>
 *   <li>{@link #BANK_ACCOUNT_BOLD} → Cuenta Bold</li>
 * </ul>
 *
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */
public class Siigo_BankAccountVO {
    
    public static final String BANK_ACCOUNT_BANCO_DE_BOGOTA = "11100501";
    public static final String BANK_ACCOUNT_DAVIVIENDA = "11100502";
    public static final String BANK_ACCOUNT_CAJA = "1";
    public static final String BANK_ACCOUNT_ADDI = "11100507";
    public static final String BANK_ACCOUNT_PAYU = "11100503 ";
    public static final String BANK_ACCOUNT_SUFI = "6";
        public static final String BANK_ACCOUNT_BOLD= "11100506";
    
}
